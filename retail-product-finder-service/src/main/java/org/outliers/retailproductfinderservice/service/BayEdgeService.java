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
import org.outliers.retailproductfinderservice.objects.model.BayEdge;

public interface BayEdgeService {

  BayEdge save(BayEdge bayEdge);

  List<BayEdge> findAll();

  Optional<BayEdge> findById(String bayEdgeId);

  void deleteById(String bayEdgeId);
}

