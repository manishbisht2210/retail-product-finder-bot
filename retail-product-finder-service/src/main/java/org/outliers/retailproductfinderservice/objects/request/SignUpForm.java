/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.objects.request;

import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpForm {

  @NotBlank
  @Size(min = 3, max = 50)
  private String name;

  @NotBlank
  @Size(min = 3, max = 50)
  private String userName;

  @NotBlank
  @Size(max = 60)
  @Email
  private String email;

  @NotBlank
  @Size(max = 60)
  private String tenantName;

  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

}