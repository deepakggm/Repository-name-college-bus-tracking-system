package com.deepak.bustracking.controller;

import com.deepak.bustracking.entity.Route;
import com.deepak.bustracking.service.RouteService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(
            RouteService routeService
    ) {
        this.routeService = routeService;
    }

    // Add route
    @PostMapping
    public ResponseEntity<Route> addRoute(
            @RequestBody Route route
    ) {

        return ResponseEntity.ok(
                routeService.addRoute(route)
        );
    }

    // Get all routes
    @GetMapping
    public ResponseEntity<List<Route>>
    getAllRoutes() {

        return ResponseEntity.ok(
                routeService.getAllRoutes()
        );
    }

    // Get route using ID
    @GetMapping("/{id}")
    public ResponseEntity<Route> getRouteById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                routeService.getRouteById(id)
        );
    }

    // Update route
    @PutMapping("/{id}")
    public ResponseEntity<Route> updateRoute(
            @PathVariable Long id,
            @RequestBody Route route
    ) {

        return ResponseEntity.ok(
                routeService.updateRoute(
                        id,
                        route
                )
        );
    }

    // Delete route
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoute(
            @PathVariable Long id
    ) {

        routeService.deleteRoute(id);

        return ResponseEntity.ok(
                "Route deleted successfully"
        );
    }
}