package com.deepak.bustracking.service;

import com.deepak.bustracking.entity.Bus;
import com.deepak.bustracking.repository.BusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {

    private final BusRepository busRepository;

    // Constructor injection
    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    // Add a new bus
    public Bus addBus(Bus bus) {
        return busRepository.save(bus);
    }

    // Get all buses
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    // Get one bus by ID
    public Bus getBusById(Long id) {

        return busRepository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Bus not found with ID: " + id
                        )
                );
    }

    // Update a bus
    public Bus updateBus(
            Long id,
            Bus updatedBus
    ) {

        Bus existingBus = getBusById(id);

        existingBus.setBusNumber(
                updatedBus.getBusNumber()
        );

        existingBus.setBusName(
                updatedBus.getBusName()
        );

        existingBus.setCapacity(
                updatedBus.getCapacity()
        );

        existingBus.setStatus(
                updatedBus.getStatus()
        );

        return busRepository.save(existingBus);
    }

    // Delete a bus
    public void deleteBus(Long id) {

        Bus bus = getBusById(id);

        busRepository.delete(bus);
    }
}