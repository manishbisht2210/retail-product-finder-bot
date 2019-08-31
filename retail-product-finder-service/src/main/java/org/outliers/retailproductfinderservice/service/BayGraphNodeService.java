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
import org.outliers.retailproductfinderservice.objects.model.BayGraphNode;
import org.outliers.retailproductfinderservice.objects.model.GraphNode;

public interface BayGraphNodeService {

  BayGraphNode save(BayGraphNode bayGraphNode);

  List<BayGraphNode> findAll();

  Optional<BayGraphNode> findById(String bayGraphNodeId);

  void deleteById(String bayGraphNodeId);

	Optional<BayGraphNode> findByBay_BayIdAndEndLocator(String bayId, int i);

	BayGraphNode findByGraphNode_GraphNode(GraphNode endGraphNode);
}

