package com.example.ProjetowebAutenticacao.service;

import com.example.ProjetowebAutenticacao.model.Log;
import com.example.ProjetowebAutenticacao.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;
    //Metodo para listar todos os logs registrados
    Log log = new Log();
    public List<Log> getTodos(){
        return (List<Log>) logRepository.findAll();
    }

    //Metodo de registrar log
    public void registrarLog(String log_tx_execucao,Integer usuid){

        log.setLog_dt_execucao(new Timestamp(System.currentTimeMillis()));
        log.setLog_tx_execucao(log_tx_execucao);
        log.setUsu_nr_id(usuid);
        logRepository.save(log);
    }


}
