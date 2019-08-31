/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.resource;

import java.util.List;
import java.util.Optional;
import org.outliers.retailproductfinderservice.exception.ResourceNotFoundException;
import org.outliers.retailproductfinderservice.objects.model.User;
import org.outliers.retailproductfinderservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UserResource {

  private UserService userService;

  @Autowired
  public UserResource(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{userId}")
  public User findUserById(@PathVariable String userId) throws ResourceNotFoundException {
    Optional<User> userOptional = userService.findById(userId);
    if (!userOptional.isPresent()) {
      throw new ResourceNotFoundException("userId-" + userId);
    }
    return userOptional.get();
  }

  @GetMapping
  public List<User> findUsers() {
    return userService.findAll();
  }

}
