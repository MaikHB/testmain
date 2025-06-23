package com.redolfi.demolfi.services;

import com.redolfi.demolfi.entities.Avion;
import com.redolfi.demolfi.repositories.AvionRepository;
import org.springframework.stereotype.Service;

@Service
public class AvionServiceImpl extends BaseServiceImpl<Avion, Long> implements AvionService {
    public AvionServiceImpl(AvionRepository avionRepository) {
        super(avionRepository);
    }
}
