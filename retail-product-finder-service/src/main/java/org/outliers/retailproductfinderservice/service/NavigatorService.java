/*
 *  * ********************************************************************************
 *  * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *  *
 *  * This file is part of Team Outliers.
 *  *
 *  * Team Outliers can not be copied and/or distributed without the express
 *  * permission of Team Outliers
 * *********************************************************************************
 *
 */

package org.outliers.retailproductfinderservice.service;

import org.outliers.retailproductfinderservice.util.pathfinder.Result;

public interface NavigatorService {

Result handleSearch(String search, String deviceId);

Result handleSearchWithBay(String productName, String deviceId);
  
}
