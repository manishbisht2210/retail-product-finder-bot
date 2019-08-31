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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "graphs")
//@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@NoArgsConstructor
public class Graph extends AuditModel {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "graph_id")
	private String graphId;

	@Column(name = "graph_name")
	private String graphName;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tenant_id")
	@NotNull
	private Tenant tenant;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "graph", cascade = CascadeType.ALL)
	private Set<GraphNode> graphNodes = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "graph", cascade = CascadeType.ALL)
	private Set<GraphEdge> graphEdges = new HashSet<>();

	public String getGraphId() {
		return graphId;
	}

	public void setGraphId(String graphId) {
		this.graphId = graphId;
	}

	public String getGraphName() {
		return graphName;
	}

	public void setGraphName(String graphName) {
		this.graphName = graphName;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public Set<GraphNode> getGraphNodes() {
		return graphNodes;
	}

	public void setGraphNodes(Set<GraphNode> graphNodes) {
		this.graphNodes = graphNodes;
	}

	public Set<GraphEdge> getGraphEdges() {
		return graphEdges;
	}

	public void setGraphEdges(Set<GraphEdge> graphEdges) {
		this.graphEdges = graphEdges;
	}

}
