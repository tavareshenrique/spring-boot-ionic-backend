package com.henriquetavares.cursomc.resources;

import com.henriquetavares.cursomc.domain.Categoria;
import com.henriquetavares.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResources {

    @Autowired
    private CategoriaService service;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {

        Categoria obj = service.buscar(id);
        return ResponseEntity.ok().body(obj);
    }

}

//        Categoria cat1 = new Categoria(1, "Informática");
//        Categoria cat2 = new Categoria(2, "Escritório");
//
//        List<Categoria> lista = new ArrayList<>();
//        lista.add(cat1);
//        lista.add(cat2);
//        return lista;