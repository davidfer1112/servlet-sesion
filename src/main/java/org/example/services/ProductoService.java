package org.example.services;

import org.example.models.Categoria;
import org.example.models.Producto;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();
    Optional<Producto> porId(Long id);

    void guardar(Producto producto);
    void eliminar(Long id);

    List<Categoria> listarCategoria();
    Optional<Categoria> porIdCategoria(Long id);
}

