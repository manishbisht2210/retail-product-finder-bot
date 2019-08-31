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
import org.outliers.retailproductfinderservice.objects.model.Device;
import org.outliers.retailproductfinderservice.repository.DeviceRepository;
import org.outliers.retailproductfinderservice.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceService {

  private DeviceRepository deviceRepository;

  @Autowired
  public DeviceServiceImpl(DeviceRepository deviceRepository) {
    this.deviceRepository = deviceRepository;
  }

  @Override
  public Device save(Device device) {
    return deviceRepository.saveAndFlush(device);
  }

  @Override
  public List<Device> findAll() {
    return deviceRepository.findAll();
  }

  @Override
  public Optional<Device> findById(String deviceId) {
    return deviceRepository.findById(deviceId);
  }

  @Override
  public List<Device> findFreeDevicesByTenantId(String tenantId) {
    return deviceRepository.findByTenant_TenantIdAndAndIsFree(tenantId, true);
  }

  @Override
  public void deleteById(String deviceId) {
    deviceRepository.deleteById(deviceId);
  }
}
