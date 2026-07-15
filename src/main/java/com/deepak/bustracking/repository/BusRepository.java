package com.deepak.bustracking.repository;

import com.deepak.bustracking.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository
        extends JpaRepository<Bus, Long> {

}