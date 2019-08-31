/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.objects.model;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "graph_nodes")
@Getter
@Setter
//@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class GraphNode extends AuditModel {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "graph_node_id")
    private String graphNodeId;

    @Column(name = "graph_node_name")
    private String graphNodeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "graph_id")
    private Graph graph;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "graph_node_products",
            joinColumns = @JoinColumn(name = "graph_node_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> graphNodeProducts = new HashSet<>();

    public String getGraphNodeId() {
        return graphNodeId;
    }

    public void setGraphNodeId(String graphNodeId) {
        this.graphNodeId = graphNodeId;
    }

    public String getGraphNodeName() {
        return graphNodeName;
    }

    public void setGraphNodeName(String graphNodeName) {
        this.graphNodeName = graphNodeName;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }
}
