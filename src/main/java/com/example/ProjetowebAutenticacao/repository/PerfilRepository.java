package com.example.ProjetowebAutenticacao.repository;

import com.example.ProjetowebAutenticacao.model.Perfil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends CrudRepository<Perfil,Integer> {
}
