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
import org.outliers.retailproductfinderservice.objects.model.Device;

public interface DeviceService {

  Device save(Device device);

  List<Device> findAll();

  Optional<Device> findById(String deviceId);

  List<Device> findFreeDevicesByTenantId(String tenantId);

  void deleteById(String deviceId);
}
