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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "bays")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Bay extends AuditModel {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "bay_id")
    private String bayId;

    @Column(name = "bay_name")
    private String bayName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bay", cascade = CascadeType.ALL)
    private Set<BayNode> bayNodes = new HashSet<>();

    public String getBayId() {
        return bayId;
    }

    public void setBayId(String bayId) {
        this.bayId = bayId;
    }

    public String getBayName() {
        return bayName;
    }

    public void setBayName(String bayName) {
        this.bayName = bayName;
    }

    public Set<BayNode> getBayNodes() {
        return bayNodes;
    }

    public void setBayNodes(Set<BayNode> bayNodes) {
        this.bayNodes = bayNodes;
    }

    public Set<BayEdge> getBayEdges() {
        return bayEdges;
    }

    public void setBayEdges(Set<BayEdge> bayEdges) {
        this.bayEdges = bayEdges;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bay", cascade = CascadeType.ALL)
    private Set<BayEdge> bayEdges = new HashSet<>();


    public static void main(String[] args) {
        String url = "aws://tag:mis.instance.role=management:8080/ace/rest";
        String arr[]=url.substring(6).split("(?<!tag):");
        for(String a: arr){
            System.out.println(a);
        }
    }
}