package com.example.ProjetowebAutenticacao.repository;

import com.example.ProjetowebAutenticacao.model.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface LogRepository extends CrudRepository<Log,BigInteger> {

}
