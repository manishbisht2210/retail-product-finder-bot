/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.objects.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "bay_nodes")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BayNode extends AuditModel {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "bay_node_id")
  private String bayNodeId;

  public String getBayNodeId() {
	return bayNodeId;
}

public void setBayNodeId(String bayNodeId) {
	this.bayNodeId = bayNodeId;
}

public Bay getBay() {
	return bay;
}

public void setBay(Bay bay) {
	this.bay = bay;
}

@Column(name = "bay_node_name")
  private String bayNodeName;

  public String getBayNodeName() {
	return bayNodeName;
}

public void setBayNodeName(String bayNodeName) {
	this.bayNodeName = bayNodeName;
}

@ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bay_id")
  @NotNull
  private Bay bay;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "bay_node_products",
      joinColumns = @JoinColumn(name = "bay_node_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id"))
  private Set<Product> bayNodeProducts = new HashSet<>();

}