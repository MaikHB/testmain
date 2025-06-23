package com.redolfi.demolfi.repositories;

import com.redolfi.demolfi.entities.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {
}
