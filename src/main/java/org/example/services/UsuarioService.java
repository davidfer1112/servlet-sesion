package org.example.services;

import org.example.models.Usuario;

import java.util.Optional;


public interface UsuarioService {

    Optional<Usuario> login(String username, String password);
}
