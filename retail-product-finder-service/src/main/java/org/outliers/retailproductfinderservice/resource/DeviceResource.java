/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.outliers.retailproductfinderservice.exception.ResourceNotFoundException;
import org.outliers.retailproductfinderservice.objects.model.Device;
import org.outliers.retailproductfinderservice.objects.model.Orientation;
import org.outliers.retailproductfinderservice.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/devices")
public class DeviceResource {

  private DeviceService deviceService;

  @Autowired
  public DeviceResource(DeviceService deviceService) {
    this.deviceService = deviceService;
  }

  @GetMapping("/{deviceId}")
  public Device findDeviceById(@PathVariable String deviceId) throws ResourceNotFoundException {
    Optional<Device> deviceOptional = deviceService.findById(deviceId);
    if (!deviceOptional.isPresent()) {
      throw new ResourceNotFoundException("deviceId-" + deviceId);
    }
    return deviceOptional.get();
  }

  @GetMapping("/free/{tenantId}")
  public Device findFreeDeviceByTenantId(@PathVariable String tenantId)
      throws ResourceNotFoundException {
    List<Device> devices = deviceService.findFreeDevicesByTenantId(tenantId);
    if (devices.isEmpty()) {
      throw new ResourceNotFoundException("No free device for tenantId-" + tenantId);
    }
    return devices.get(0);
  }

  @GetMapping("/isfree/{tenantId}")
  public Device findIsFreeDeviceByTenantId(@PathVariable("tenantId") String tenantId) {
    Device device = new Device();
    device.setDeviceId("DeviceId");
    device.setIsFree(true);
    device.setCurrentNodeId("CurrentNodeId");
    device.setCurrentOrientation(Orientation.N);
    return device;
  }

  @GetMapping
  public List<Device> findDevices() {
    return deviceService.findAll();
  }

  @DeleteMapping("/{deviceId}")
  public void deleteDevice(@PathVariable String deviceId) {
    deviceService.deleteById(deviceId);
  }

  @PutMapping("/{deviceId}")
  public ResponseEntity<Object> updateDevice(@RequestBody Device device,
      @PathVariable String deviceId) {
    Optional<Device> deviceOptional = deviceService.findById(deviceId);
    if (!deviceOptional.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    device.setDeviceId(deviceId);
    deviceService.save(device);
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<Object> createDevice(@RequestBody Device device) {
    Device savedDevice = deviceService.save(device);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{deviceId}")
        .buildAndExpand(savedDevice.getDeviceId()).toUri();
    return ResponseEntity.created(location).build();
  }
}
