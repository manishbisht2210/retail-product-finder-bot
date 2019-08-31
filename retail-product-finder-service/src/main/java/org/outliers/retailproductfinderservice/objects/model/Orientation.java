/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.objects.model;

public enum Orientation {

  N("N"), E("E"), S("S"), W("W");
  private String direction;


  Orientation(String direction) {
    this.direction = direction;
  }

  public String getDirection() {
    return direction;
  }
}
