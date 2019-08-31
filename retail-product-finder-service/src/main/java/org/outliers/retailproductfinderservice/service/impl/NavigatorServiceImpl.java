/*
 *  * ********************************************************************************
 *  * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *  *
 *  * This file is part of Team Outliers.
 *  *
 *  * Team Outliers can not be copied and/or distributed without the express
 *  * permission of Team Outliers
 * *********************************************************************************
 *
 */

package org.outliers.retailproductfinderservice.service.impl;

import org.infosys.makeathon.outliers.retailproductfinderservice.objects.model.*;
import org.infosys.makeathon.outliers.retailproductfinderservice.service.*;
import org.infosys.makeathon.outliers.retailproductfinderservice.util.pathfinder.*;
import org.outliers.retailproductfinderservice.objects.model.Bay;
import org.outliers.retailproductfinderservice.objects.model.BayGraphNode;
import org.outliers.retailproductfinderservice.objects.model.BayNode;
import org.outliers.retailproductfinderservice.objects.model.Device;
import org.outliers.retailproductfinderservice.objects.model.Graph;
import org.outliers.retailproductfinderservice.objects.model.GraphNode;
import org.outliers.retailproductfinderservice.objects.model.Orientation;
import org.outliers.retailproductfinderservice.objects.model.Product;
import org.outliers.retailproductfinderservice.service.BayGraphNodeService;
import org.outliers.retailproductfinderservice.service.BayNodeService;
import org.outliers.retailproductfinderservice.service.BayService;
import org.outliers.retailproductfinderservice.service.DeviceService;
import org.outliers.retailproductfinderservice.service.GraphNodeService;
import org.outliers.retailproductfinderservice.service.GraphService;
import org.outliers.retailproductfinderservice.service.NavigatorService;
import org.outliers.retailproductfinderservice.service.ProductService;
import org.outliers.retailproductfinderservice.util.pathfinder.BotCommandHandler;
import org.outliers.retailproductfinderservice.util.pathfinder.DiGraph;
import org.outliers.retailproductfinderservice.util.pathfinder.GraphToDiGraphConverter;
import org.outliers.retailproductfinderservice.util.pathfinder.Instruction;
import org.outliers.retailproductfinderservice.util.pathfinder.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NavigatorServiceImpl implements NavigatorService {

	//private static final Logger logger = Logger.getLogger(NavigatorServiceImpl.class);

	private GraphNodeService graphNodeService;
	private BayNodeService bayNodeService;
	private BayService bayService;
	private GraphService graphService;
	private DeviceService deviceService;
	private BayGraphNodeService bayGraphNodeService;

	private BotCommandHandler handler;

	private ProductService productService;

	@Autowired
	public NavigatorServiceImpl(GraphNodeService graphNodeService, GraphService graphService,
			DeviceService deviceService, BotCommandHandler handler, ProductService productService,
			BayNodeService bayNodeService, BayService bayService, BayGraphNodeService bayGraphNodeService) {
		this.graphNodeService = graphNodeService;
		this.graphService = graphService;
		this.deviceService = deviceService;
		this.handler = handler;
		this.productService = productService;
		this.bayNodeService = bayNodeService;
		this.bayService = bayService;
		this.bayGraphNodeService = bayGraphNodeService;
	}

	@Override
	public Result handleSearch(String productName, String deviceId) {

		// Finding Product in the database
		Product product = productService.findByProductName(productName);
		if (product == null) {
			return null;
		}

		// Finding Node mapped to the Product
		GraphNode destination = graphNodeService.findNodeByProductId(product.getProductId());

		if (destination == null) {
			return null;
		}

		// Finding the current node and orientation of the bot
		String currentNode = "";
		Orientation currentOrientation = Orientation.N; // Note this is the default orientation
		Optional<Device> device = deviceService.findById(deviceId);
		if (device.isPresent()) {
			currentNode = graphNodeService.findById(device.get().getCurrentNodeId()).get().getGraphNodeId();
			currentOrientation = device.get().getCurrentOrientation();
		}
		Graph graph=destination.getGraph();
		// Apply the algorithm
		DiGraph digraph = GraphToDiGraphConverter.convertGraph(graph);
		Result pathResult = digraph.getPath(currentNode, destination.getGraphNodeId(), currentOrientation);

		// Send the instructions recieved from the algorithm to the bot
		commandBot(pathResult);

		// After execution of all commands, update the final position and orientation of
		// the bot
		if (device.isPresent() && !pathResult.getInstructions().isEmpty()) {
			device.get().setCurrentOrientation(getOrientation(pathResult.getFinalDirection()));
			device.get().setCurrentNodeId(pathResult.getFinalNode());
			deviceService.save(device.get());
		}

		return pathResult;

	}

	@Override
	public Result handleSearchWithBay(String productName, String deviceId) {

		// Finding Product in the database
		Product product = productService.findByProductName(productName);
		if (product == null) {
			return null;
		}

		// Finding BayNode mapped to the Product
		BayNode bayNodeDestination = bayNodeService.findNodeByProductId(product.getProductId());

		if (bayNodeDestination == null) {
			return null;
		}

		// Finding Bay
		Optional<Bay> bayOptional = bayService.findById(bayNodeDestination.getBay().getBayId());
		if (bayOptional.isPresent()) {

			Bay bay = bayOptional.get();

			GraphNode destination = null;
			GraphNode left = null;
			GraphNode right = null;
			
			Optional<BayGraphNode> destinationB = bayGraphNodeService.findByBay_BayIdAndEndLocator(bay.getBayId(), 0); // GraphNode Center of the bay
			Optional<BayGraphNode> leftB = bayGraphNodeService.findByBay_BayIdAndEndLocator(bay.getBayId(), -1); // GraphNode Left of the bay
			Optional<BayGraphNode> rightB = bayGraphNodeService.findByBay_BayIdAndEndLocator(bay.getBayId(), 1); // GraphNode Right of the bay
			if(destinationB.isPresent())
			{
				destination = destinationB.get().getGraphNode();
			}
			if(leftB.isPresent())
			{
				left = leftB.get().getGraphNode();
			}
			if(rightB.isPresent())
			{
				right = rightB.get().getGraphNode();
			}
			if(left==null || right == null || destination == null)
			{
				return null;
			}

			// Finding the current node and orientation of the bot
			String currentNode = "";
			Orientation currentOrientation = Orientation.N; // Note this is the default orientation
			Optional<Device> device = deviceService.findById(deviceId);
			if (device.isPresent()) {
				currentNode = device.get().getCurrentNodeId();
				currentOrientation = device.get().getCurrentOrientation();
			}

			// Finding Path till the Bay
			DiGraph digraph = GraphToDiGraphConverter.convertGraph(destination.getGraph());
			Result pathResult = digraph.getPath(currentNode, destination.getGraphNodeName(), currentOrientation);

			//Finding the end of the Bay
			GraphNode endGraphNode = null;
			List<Instruction> subset = new ArrayList<>();
			for (Instruction instruction : pathResult.getInstructions()) {
				subset.add(instruction);
				if (instruction.getCurrentNode().equalsIgnoreCase(left.getGraphNodeName())) {
					endGraphNode = left;
					break;
				}
				if (instruction.getCurrentNode().equalsIgnoreCase(right.getGraphNodeName())) {
					endGraphNode = right;
					break;
				}
			}

			BayGraphNode bayNodeSourceB = bayGraphNodeService.findByGraphNode_GraphNode(endGraphNode);

			if(bayNodeSourceB!=null)
			{
				System.out.println("Its here..");
				BayNode bayNodeSource = bayNodeSourceB.getBayNode();
				// Finding Path inside the Bay
				DiGraph bayDigraph = GraphToDiGraphConverter.convertBayGraph(bayNodeDestination.getBay());
				Result bayNodePathResult = digraph.getPath(bayNodeSource.getBayNodeName(),
						bayNodeDestination.getBayNodeName(), currentOrientation);

				//Merging the instructions
				pathResult.setInstructions(subset);
				pathResult.getInstructions().addAll(bayNodePathResult.getInstructions());
			}
			
			// Send the instructions received from the algorithm to the bot
			commandBot(pathResult);

			// After execution of all commands, update the final position and orientation of the bot
			if (device.isPresent()) {
				device.get().setCurrentOrientation(getOrientation(pathResult.getFinalDirection()));
				device.get().setCurrentNodeId(pathResult.getFinalNode());
				deviceService.save(device.get());
			}
			return pathResult;
		}
		return null;

	}

	private Orientation getOrientation(String direction) {
		if (direction.equalsIgnoreCase("N")) {
			return Orientation.N;
		} else if (direction.equalsIgnoreCase("E")) {
			return Orientation.E;
		} else if (direction.equalsIgnoreCase("W")) {
			return Orientation.W;
		} else if (direction.equalsIgnoreCase("S")) {
			return Orientation.S;
		}
		return Orientation.N;
	}

	private void commandBot(Result pathResult) {

		for (Instruction instruction : pathResult.getInstructions()) {
			try {
				handler.callBotInstruction(instruction.getAction(), instruction.getMoveMetric());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Reached to item");
	}
}
