package com.redolfi.demolfi.controllers;

import com.redolfi.demolfi.entities.Vuelo;
import com.redolfi.demolfi.services.VueloServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/vuelos")
public class VueloController extends BaseControllerImpl<Vuelo, VueloServiceImpl> {
}
