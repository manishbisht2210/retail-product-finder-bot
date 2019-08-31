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
import org.outliers.retailproductfinderservice.objects.model.GraphEdge;
import org.outliers.retailproductfinderservice.repository.GraphEdgeRepository;
import org.outliers.retailproductfinderservice.service.GraphEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraphEdgeServiceImpl implements GraphEdgeService {

  private GraphEdgeRepository graphEdgeRepository;

  @Autowired
  public GraphEdgeServiceImpl(GraphEdgeRepository graphEdgeRepository) {
    this.graphEdgeRepository = graphEdgeRepository;
  }

  @Override
  public GraphEdge save(GraphEdge graphEdgeEdge) {
    return graphEdgeRepository.saveAndFlush(graphEdgeEdge);
  }

  @Override
  public List<GraphEdge> findAll() {
    return graphEdgeRepository.findAll();
  }

  @Override
  public Optional<GraphEdge> findById(String graphEdgeId) {
    return graphEdgeRepository.findById(graphEdgeId);
  }

  @Override
  public void deleteById(String graphEdgeId) {
    graphEdgeRepository.deleteById(graphEdgeId);
  }
}
