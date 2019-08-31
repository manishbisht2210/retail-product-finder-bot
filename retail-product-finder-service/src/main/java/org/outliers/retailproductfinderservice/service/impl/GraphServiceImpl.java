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
import org.outliers.retailproductfinderservice.objects.model.Graph;
import org.outliers.retailproductfinderservice.repository.GraphRepository;
import org.outliers.retailproductfinderservice.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraphServiceImpl implements GraphService {

  private GraphRepository graphRepository;

  @Autowired
  public GraphServiceImpl(GraphRepository graphRepository) {
    this.graphRepository = graphRepository;
  }

  @Override
  public Graph save(Graph graphEdge) {
    return graphRepository.saveAndFlush(graphEdge);
  }

  @Override
  public List<Graph> findAll() {
    return graphRepository.findAll();
  }

  @Override
  public Optional<Graph> findById(String graphId) {
    return graphRepository.findById(graphId);
  }

  @Override
  public void deleteById(String graphId) {
    graphRepository.deleteById(graphId);
  }
}
