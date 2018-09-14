package com.henriquetavares.cursomc.resources;

import com.henriquetavares.cursomc.domain.Cidade;
import com.henriquetavares.cursomc.domain.Estado;
import com.henriquetavares.cursomc.dto.CidadeDTO;
import com.henriquetavares.cursomc.dto.EstadoDTO;
import com.henriquetavares.cursomc.services.CidadeService;
import com.henriquetavares.cursomc.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

    @Autowired
    EstadoService estadoService;

    @Autowired
    CidadeService cidadeService;

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<EstadoDTO>> findAll() {
        List<Estado> list = estadoService.findAll();
        List<EstadoDTO> listDTO = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }

    @RequestMapping(value="/{estadoId}/cidades", method= RequestMethod.GET)
    public  ResponseEntity<List<CidadeDTO>> find(@PathVariable Integer estadoId) {

        List<Cidade> list = cidadeService.findAll(estadoId);
        List<CidadeDTO> listDTO = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }



}
