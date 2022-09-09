package com.example.ProjetowebAutenticacao.repository;

import com.example.ProjetowebAutenticacao.model.Sistema;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SistemaReposistory extends CrudRepository<Sistema, Integer> {
}
