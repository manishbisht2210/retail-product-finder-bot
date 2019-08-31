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
import org.outliers.retailproductfinderservice.objects.model.BayNode;

public interface BayNodeService {

  BayNode save(BayNode bayNode);

  List<BayNode> findAll();

  Optional<BayNode> findById(String bayNodeId);

  void deleteById(String bayNodeId);

	BayNode findNodeByProductId(String productId);
}

