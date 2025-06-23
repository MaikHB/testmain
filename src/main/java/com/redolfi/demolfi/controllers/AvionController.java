package com.redolfi.demolfi.controllers;

import com.redolfi.demolfi.entities.Avion;
import com.redolfi.demolfi.services.AvionServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/aviones")
public class AvionController extends BaseControllerImpl<Avion, AvionServiceImpl> {
}
