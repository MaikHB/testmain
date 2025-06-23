package com.redolfi.demolfi.services;

import com.redolfi.demolfi.entities.Vuelo;
import com.redolfi.demolfi.repositories.VueloRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VueloServiceImpl extends BaseServiceImpl<Vuelo, Long> implements VueloService {
    public VueloServiceImpl(VueloRepository vueloRepository) {
        super(vueloRepository);
    }
}
