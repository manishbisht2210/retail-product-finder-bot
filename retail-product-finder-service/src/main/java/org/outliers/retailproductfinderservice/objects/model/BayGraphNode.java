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
@Table(name = "bay_graph_nodes")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BayGraphNode extends AuditModel {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "bay__graph_node_id")
  private String bayGraphNodeId;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tenant_id")
  @NotNull
  private Tenant tenant;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bay_id")
  @NotNull
  private Bay bay;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "graph_node_id")
  @NotNull
  private GraphNode graphNode;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bay_node_id")
  @NotNull
  private BayNode bayNode;

  @Column(name = "end_locator")
  private int endLocator;

}
