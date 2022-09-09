package com.example.ProjetowebAutenticacao.repository;

import com.example.ProjetowebAutenticacao.model.Transacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends CrudRepository<Transacao, Integer> {
}
