package com.henriquetavares.cursomc.resources;

import com.henriquetavares.cursomc.domain.Produto;
import com.henriquetavares.cursomc.dto.ProdutoDTO;
import com.henriquetavares.cursomc.resources.utils.URL;
import com.henriquetavares.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResources {

    @Autowired
    private ProdutoService service;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Produto> find(@PathVariable Integer id) {

        Produto obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/page", method=RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "nome", defaultValue = "") String nome,
                                                     @RequestParam(value = "categorias", defaultValue = "") String categorias,
                                                     @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                     @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                     @RequestParam(value = "direction", defaultValue = "ASC") String direction ) throws UnsupportedEncodingException {
        String nomeDecoded = URL.decodeParam(nome);
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Produto> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok().body(listDTO);
    }

}
