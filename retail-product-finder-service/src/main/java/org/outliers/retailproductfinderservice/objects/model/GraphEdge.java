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
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "graph_edges")
@Data
//@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class GraphEdge extends AuditModel {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "graph_edge_id")
	private String graphEdgeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "graph_id")
	@NotNull
	private Graph graph;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "start_graph_node_id")
	@NotNull
	private GraphNode startGraphNode;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "end_graph_node_id")
	@NotNull
	private GraphNode endGraphNode;

	@Column(name = "distance")
	private String distance;

	@Enumerated(EnumType.STRING)
	@Column(name = "orientation")
	private Orientation orientation;

	public String getGraphEdgeId() {
		return graphEdgeId;
	}

	public void setGraphEdgeId(String graphEdgeId) {
		this.graphEdgeId = graphEdgeId;
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public GraphNode getStartGraphNode() {
		return startGraphNode;
	}

	public void setStartGraphNode(GraphNode startGraphNode) {
		this.startGraphNode = startGraphNode;
	}

	public GraphNode getEndGraphNode() {
		return endGraphNode;
	}

	public void setEndGraphNode(GraphNode endGraphNode) {
		this.endGraphNode = endGraphNode;
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

}
