package org.example.services;

import org.example.models.Producto;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();
    Optional<Producto> porId(Long id);
}

