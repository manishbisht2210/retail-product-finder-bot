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
import org.outliers.retailproductfinderservice.objects.model.BayNode;
import org.outliers.retailproductfinderservice.repository.BayNodeRepository;
import org.outliers.retailproductfinderservice.service.BayNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BayNodeServiceImpl implements BayNodeService {

	private BayNodeRepository bayNodeRepository;

	@Autowired
	public BayNodeServiceImpl(BayNodeRepository bayNodeRepository) {
		this.bayNodeRepository = bayNodeRepository;
	}

	@Override
	public BayNode save(BayNode bayNodeEdge) {
		return bayNodeRepository.saveAndFlush(bayNodeEdge);
	}

	@Override
	public List<BayNode> findAll() {
		return bayNodeRepository.findAll();
	}

	@Override
	public Optional<BayNode> findById(String bayNodeId) {
		return bayNodeRepository.findById(bayNodeId);
	}

	@Override
	public void deleteById(String bayNodeId) {
		bayNodeRepository.deleteById(bayNodeId);
	}

	@Override
	public BayNode findNodeByProductId(String productId) {
		return bayNodeRepository.findByBayNodeProducts_ProductId(productId);
	}
}
