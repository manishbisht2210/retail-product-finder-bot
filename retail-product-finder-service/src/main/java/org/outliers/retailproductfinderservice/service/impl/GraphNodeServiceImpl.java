/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.service.impl;

import java.util.List;
import java.util.Optional;
import org.outliers.retailproductfinderservice.objects.model.GraphNode;
import org.outliers.retailproductfinderservice.repository.GraphNodeRepository;
import org.outliers.retailproductfinderservice.service.GraphNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraphNodeServiceImpl implements GraphNodeService {

	private GraphNodeRepository graphNodeRepository;

	@Autowired
	public GraphNodeServiceImpl(GraphNodeRepository graphNodeRepository) {
		this.graphNodeRepository = graphNodeRepository;
	}

	@Override
	public GraphNode save(GraphNode graphNode) {
		return graphNodeRepository.saveAndFlush(graphNode);
	}

	@Override
	public List<GraphNode> findAll() {
		return graphNodeRepository.findAll();
	}

	@Override
	public Optional<GraphNode> findById(String graphNodeId) {
		return graphNodeRepository.findById(graphNodeId);
	}

	@Override
	public void deleteById(String graphNodeId) {
		graphNodeRepository.deleteById(graphNodeId);
	}

	@Override
	public GraphNode findNodeByProductId(String productId) {
		return graphNodeRepository.findByGraphNodeProducts_ProductId(productId);
	}
}
