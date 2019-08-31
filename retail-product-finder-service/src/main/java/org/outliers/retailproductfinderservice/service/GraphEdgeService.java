/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.service;

import java.util.List;
import java.util.Optional;
import org.outliers.retailproductfinderservice.objects.model.GraphEdge;

public interface GraphEdgeService {

  GraphEdge save(GraphEdge graphEdge);

  List<GraphEdge> findAll();

  Optional<GraphEdge> findById(String graphEdgeId);

  void deleteById(String graphEdgeId);
}

