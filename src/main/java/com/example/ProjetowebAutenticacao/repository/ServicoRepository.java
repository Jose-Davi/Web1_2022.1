package com.example.ProjetowebAutenticacao.repository;

import com.example.ProjetowebAutenticacao.model.Servico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends CrudRepository<Servico,Integer> {
}
