/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.objects.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginForm {

  @NotBlank
  @Size(min = 3, max = 60)
  private String userName;

  @NotBlank
  @Size(min = 3, max = 256)
  private String tenantName;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

}