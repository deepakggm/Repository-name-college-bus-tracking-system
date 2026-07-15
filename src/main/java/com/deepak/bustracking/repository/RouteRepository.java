package com.deepak.bustracking.repository;

import com.deepak.bustracking.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository
        extends JpaRepository<Route, Long> {

}