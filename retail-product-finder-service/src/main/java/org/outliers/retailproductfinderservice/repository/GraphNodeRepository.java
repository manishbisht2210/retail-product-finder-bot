
/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.repository;

import org.outliers.retailproductfinderservice.objects.model.GraphNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphNodeRepository extends JpaRepository<GraphNode, String> {

	GraphNode findByGraphNodeProducts_ProductId(String productId);
}