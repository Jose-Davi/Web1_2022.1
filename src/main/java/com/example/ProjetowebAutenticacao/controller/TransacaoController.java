package com.example.ProjetowebAutenticacao.controller;

import com.example.ProjetowebAutenticacao.model.Perfil;
import com.example.ProjetowebAutenticacao.model.Transacao;
import com.example.ProjetowebAutenticacao.service.TransacaoService;
import com.example.ProjetowebAutenticacao.util.ResponseDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("transacao")
public class TransacaoController {
    @Autowired
    private TransacaoService transacaoService;


    @GetMapping(value="/listar")
    public Object listarTransacoes(){

        return transacaoService.listTransacoes();
    }
    //Metodo para filtrar transacao pelo id
    @GetMapping(value = "/{tra_nr_id}")
    public ResponseEntity<?> findById(@PathVariable Integer tra_nr_id) {
        Transacao list = transacaoService.findById(tra_nr_id);
        ;
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value = "/criarTransacao")
    public Object criarTransacao(@RequestBody Transacao transacao) {
        ResponseDefault response = new ResponseDefault();
        try {
            transacaoService.fazerTransacao(transacao);
            response.setValue(true);
            response.setCodigo(200);
            response.setMensagem("Transacao realizada com sucesso!");
        } catch (Exception e) {
            response.setCodigo(400);
            System.out.println(e.getMessage());
            response.setMensagem("Erro ao fazer transacao");
            response.setValue(false);
        }
        return response;
    }

    @PutMapping(value = "/{tra_nr_id}")
    public Object alterarTransacao(@PathVariable Integer tra_nr_id, @RequestBody Transacao transacao) {
        ResponseDefault response = new ResponseDefault();
        try {
            transacaoService.alterarTransacao(tra_nr_id, transacao);
            response.setValue(true);
            response.setCodigo(200);
            response.setMensagem("Transação Alterado com sucesso!");
        } catch (Exception e) {
            response.setCodigo(400);
            e.printStackTrace();
            System.out.println(e.getMessage());
            response.setMensagem("Erro ao fazer alteração na Transação " + transacao.getTra_tx_nome());
            response.setValue(false);
        }
        return response;
    }


    @DeleteMapping(value = "/{tra_nr_id}")
    public Object deletarTransacao(@PathVariable Integer tra_nr_id) {
        ResponseDefault response = new ResponseDefault();
        try {
            transacaoService.deletetransacao(tra_nr_id);
            response.setValue(true);
            response.setCodigo(200);
            response.setMensagem("Transacao deletada com sucesso!");
        } catch (Exception e) {
            response.setCodigo(400);
            e.printStackTrace();
            System.out.println(e.getMessage());
            response.setMensagem("Erro não foi possivel deletar a transacao");
            response.setValue(false);
        }
        return response;
    }
}
