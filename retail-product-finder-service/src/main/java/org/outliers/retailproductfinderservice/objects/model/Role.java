/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.objects.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "roles")
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends AuditModel {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "role_id")
  private String roleId;

  @Enumerated(EnumType.STRING)
  @Column(length = 60)
  private RoleName name;
}