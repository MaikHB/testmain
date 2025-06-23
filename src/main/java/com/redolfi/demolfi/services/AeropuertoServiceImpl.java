package com.redolfi.demolfi.services;

import com.redolfi.demolfi.entities.Aeropuerto;
import com.redolfi.demolfi.repositories.AeropuertoRepository;
import org.springframework.stereotype.Service;

@Service
public class AeropuertoServiceImpl extends BaseServiceImpl<Aeropuerto, Long> implements AeropuertoService {
    public AeropuertoServiceImpl(AeropuertoRepository aeropuertoRepository) {
        super(aeropuertoRepository);
    }
}
