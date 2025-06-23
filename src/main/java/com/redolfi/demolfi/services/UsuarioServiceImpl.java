package com.redolfi.demolfi.services;

import com.redolfi.demolfi.entities.Usuario;
import com.redolfi.demolfi.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UsuarioService {
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        super(usuarioRepository);
    }
}
