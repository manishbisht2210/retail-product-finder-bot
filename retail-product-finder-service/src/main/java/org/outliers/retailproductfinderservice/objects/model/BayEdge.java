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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "bay_edges")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BayEdge extends AuditModel {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "bay_edge_id")
  private String bayEdgeId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bay_id")
  @NotNull
  private Bay bay;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "start_bay_node_id")
  @NotNull
  private BayNode startBayNode;

  public String getBayEdgeId() {
	return bayEdgeId;
}

public void setBayEdgeId(String bayEdgeId) {
	this.bayEdgeId = bayEdgeId;
}

public Bay getBay() {
	return bay;
}

public void setBay(Bay bay) {
	this.bay = bay;
}

public BayNode getStartBayNode() {
	return startBayNode;
}

public void setStartBayNode(BayNode startBayNode) {
	this.startBayNode = startBayNode;
}

public BayNode getEndBayNode() {
	return endBayNode;
}

public void setEndBayNode(BayNode endBayNode) {
	this.endBayNode = endBayNode;
}

public String getDistance() {
	return distance;
}

public void setDistance(String distance) {
	this.distance = distance;
}

public Orientation getOrientation() {
	return orientation;
}

public void setOrientation(Orientation orientation) {
	this.orientation = orientation;
}

@OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "end_bay_node_id")
  @NotNull
  private BayNode endBayNode;

  @Column(name = "distance")
  private String distance;

  @Enumerated(EnumType.STRING)
  @Column(name = "orientation")
  private Orientation orientation;

}
