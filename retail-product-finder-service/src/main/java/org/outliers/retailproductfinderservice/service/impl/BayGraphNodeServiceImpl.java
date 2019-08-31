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
import org.outliers.retailproductfinderservice.objects.model.BayGraphNode;
import org.outliers.retailproductfinderservice.objects.model.GraphNode;
import org.outliers.retailproductfinderservice.repository.BayGraphNodeRepository;
import org.outliers.retailproductfinderservice.service.BayGraphNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BayGraphNodeServiceImpl implements BayGraphNodeService {

  private BayGraphNodeRepository bayGraphNodeRepository;

  @Autowired
  public BayGraphNodeServiceImpl(BayGraphNodeRepository bayGraphNodeRepository) {
    this.bayGraphNodeRepository = bayGraphNodeRepository;
  }

  @Override
  public BayGraphNode save(BayGraphNode bayGraphNodeEdge) {
    return bayGraphNodeRepository.saveAndFlush(bayGraphNodeEdge);
  }

  @Override
  public List<BayGraphNode> findAll() {
    return bayGraphNodeRepository.findAll();
  }

  @Override
  public Optional<BayGraphNode> findById(String bayGraphNodeId) {
    return bayGraphNodeRepository.findById(bayGraphNodeId);
  }

  @Override
  public void deleteById(String bayGraphNodeId) {
    bayGraphNodeRepository.deleteById(bayGraphNodeId);
  }

@Override
public Optional<BayGraphNode> findByBay_BayIdAndEndLocator(String bayId, int end) {
	
	return bayGraphNodeRepository.findByBay_BayIdAndEndLocator(bayId, end);
}

@Override
public BayGraphNode findByGraphNode_GraphNode(GraphNode endGraphNode) {
	return bayGraphNodeRepository.findByGraphNode_GraphNodeId(endGraphNode.getGraphNodeId());
}
}
