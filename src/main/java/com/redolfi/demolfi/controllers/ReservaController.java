package com.redolfi.demolfi.controllers;

import com.redolfi.demolfi.entities.Reserva;
import com.redolfi.demolfi.entities.Usuario;
import com.redolfi.demolfi.entities.Vuelo;
import com.redolfi.demolfi.services.ReservaService;
import com.redolfi.demolfi.services.ReservaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/reservas")
public class ReservaController extends BaseControllerImpl<Reserva, ReservaServiceImpl> {

}
