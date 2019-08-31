/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.resource;

import lombok.extern.slf4j.Slf4j;
import org.outliers.retailproductfinderservice.service.NavigatorService;
import org.outliers.retailproductfinderservice.util.pathfinder.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/api/v1/navigation")
public class CommonResource {

  private NavigatorService navigatorService;

  public CommonResource(NavigatorService navigatorService) {
    this.navigatorService = navigatorService;
  }

  @GetMapping("/searchAndControlBot")
  public ResponseEntity<Result> searchAndControlBot(@RequestParam String productName, @RequestParam String deviceId) {
	  return new ResponseEntity<>(navigatorService.handleSearch(productName, deviceId), HttpStatus.OK);
  }

}
