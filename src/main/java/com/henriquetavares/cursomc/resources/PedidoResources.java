package com.henriquetavares.cursomc.resources;

import com.henriquetavares.cursomc.domain.Pedido;
import com.henriquetavares.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResources {

    @Autowired
    private PedidoService service;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Pedido> find(@PathVariable Integer id) {

        Pedido obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
        service.insert(obj); //obj = service.insert(obj); //Pega a URI do novo Recurso que foi inserido

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

}
