/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.outliers.retailproductfinderservice.exception.ResourceNotFoundException;
import org.outliers.retailproductfinderservice.objects.model.Graph;
import org.outliers.retailproductfinderservice.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/graphs")
public class GraphResource {

  private GraphService graphService;

  @Autowired
  public GraphResource(GraphService graphService) {
    this.graphService = graphService;
  }

  @GetMapping("/{graphId}")
  public Graph findGraphById(@PathVariable String graphId) throws ResourceNotFoundException {
    Optional<Graph> graphOptional = graphService.findById(graphId);
    if (!graphOptional.isPresent()) {
      throw new ResourceNotFoundException("graphId-" + graphId);
    }
    return graphOptional.get();
  }

  @GetMapping
  public List<Graph> findGraphs() {
    return graphService.findAll();
  }

  @DeleteMapping("/{graphId}")
  public void deleteGraph(@PathVariable String graphId) {
    graphService.deleteById(graphId);
  }

  @PutMapping("/{graphId}")
  public ResponseEntity<Object> updateGraph(@RequestBody Graph graph,
      @PathVariable String graphId) {
    Optional<Graph> graphOptional = graphService.findById(graphId);
    if (!graphOptional.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    graph.setGraphId(graphId);
    graphService.save(graph);
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<Object> createGraph(@RequestBody Graph graph) {
    Graph savedGraph = graphService.save(graph);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{graphId}")
        .buildAndExpand(savedGraph.getGraphId()).toUri();
    return ResponseEntity.created(location).build();
  }
}
