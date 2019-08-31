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
import org.outliers.retailproductfinderservice.objects.model.GraphNode;

public interface GraphNodeService {

	GraphNode save(GraphNode graphNode);

	List<GraphNode> findAll();

	Optional<GraphNode> findById(String graphNodeId);

	void deleteById(String graphNodeId);

	GraphNode findNodeByProductId(String productId);
}
