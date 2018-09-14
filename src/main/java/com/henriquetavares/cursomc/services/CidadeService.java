package com.henriquetavares.cursomc.services;

import com.henriquetavares.cursomc.domain.Cidade;
import com.henriquetavares.cursomc.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repo;

    public List<Cidade> findAll(Integer estado_id) {
        return repo.findCidades(estado_id);
    }

}
