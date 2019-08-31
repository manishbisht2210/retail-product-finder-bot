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
import org.outliers.retailproductfinderservice.objects.model.Bay;
import org.outliers.retailproductfinderservice.repository.BayRepository;
import org.outliers.retailproductfinderservice.service.BayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BayServiceImpl implements BayService {

  private BayRepository bayRepository;

  @Autowired
  public BayServiceImpl(BayRepository bayRepository) {
    this.bayRepository = bayRepository;
  }

  @Override
  public Bay save(Bay bay) {
    return bayRepository.saveAndFlush(bay);
  }

  @Override
  public List<Bay> findAll() {
    return bayRepository.findAll();
  }

  @Override
  public Optional<Bay> findById(String bayId) {
    return bayRepository.findById(bayId);
  }

  @Override
  public void deleteById(String bayId) {
    bayRepository.deleteById(bayId);
  }
}
