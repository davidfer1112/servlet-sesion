package org.example.services;

import org.example.models.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoService{
    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L, "notebook", "computacion", 175000),
                new Producto(2L, "mesa escritorio", "oficina", 100000),
                new Producto(3L, "teclado mecanico", "computacion", 40000),
                new  Producto(4L, "silla", "oficina", 50000),
                new Producto(5L, "monitor", "computacion", 80000),
                new Producto(6L, "impresora", "computacion", 120000),
                new Producto(7L, "escritorio", "oficina", 150000));
    }

    @Override
    public Optional<Producto> porId(Long id) {
        return listar().stream().filter(p -> p.getId().equals(id)).findAny();
    }
}
