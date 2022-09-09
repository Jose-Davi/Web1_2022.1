package com.example.ProjetowebAutenticacao.controller;

import com.example.ProjetowebAutenticacao.model.Sistema;
import com.example.ProjetowebAutenticacao.model.Transacao;
import com.example.ProjetowebAutenticacao.service.SistemaService;
import com.example.ProjetowebAutenticacao.util.ResponseDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("sistema")
public class SistemaController {

    @Autowired
    private SistemaService sistemaService;

    @GetMapping(value="/listar")
    public Object listarSistemas(){

        return sistemaService.listarSistemas();
    }
    //Metodo para filtrarsistemas pelo id
    @GetMapping(value = "/{sis_nr_id}")
    public ResponseEntity<?> findById(@PathVariable Integer sis_nr_id) {
        Sistema list = sistemaService.findById(sis_nr_id);
        ;
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value = "/criarSistema")
    public Object criarSistema(@RequestBody Sistema sistema) {
        ResponseDefault response = new ResponseDefault();
        try {
          sistemaService.criarSistema(sistema);
            response.setValue(true);
            response.setCodigo(200);
            response.setMensagem("Sistema criado com sucesso!");
        } catch (Exception e) {
            response.setCodigo(400);
            System.out.println(e.getMessage());
            response.setMensagem("Erro ao tentar criar sistema");
            response.setValue(false);
        }
        return response;
    }

    @PutMapping(value = "/{sis_nr_id}")
    public Object alterarSistema(@PathVariable Integer sis_nr_id, @RequestBody Sistema sistema) {
        ResponseDefault response = new ResponseDefault();
        try {
           sistemaService.alterarSistema(sis_nr_id,sistema);
            response.setValue(true);
            response.setCodigo(200);
            response.setMensagem("Sistema Alterado com sucesso!");
        } catch (Exception e) {
            response.setCodigo(400);
            e.printStackTrace();
            System.out.println(e.getMessage());
            response.setMensagem("Erro ao fazer alteração no sistema " + sistema.getSis_tx_nome());
            response.setValue(false);
        }
        return response;
    }


    @DeleteMapping(value = "/{sis_nr_id}")
    public Object deletarSistema(@PathVariable Integer sis_nr_id) {
        ResponseDefault response = new ResponseDefault();
        try {
           sistemaService.deletarSistema(sis_nr_id);
            response.setValue(true);
            response.setCodigo(200);
            response.setMensagem("Sistema deletado com sucesso!");
        } catch (Exception e) {
            response.setCodigo(400);
            e.printStackTrace();
            System.out.println(e.getMessage());
            response.setMensagem("Erro, não foi possivel deletar o sistema");
            response.setValue(false);
        }
        return response;
    }
}
