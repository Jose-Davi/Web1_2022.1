package com.example.ProjetowebAutenticacao.controller;

import com.example.ProjetowebAutenticacao.model.Servico;
import com.example.ProjetowebAutenticacao.service.ServicoService;
import com.example.ProjetowebAutenticacao.util.ResponseDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("servico")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping(value="/listar")
    public Object listarServicos(){

        return servicoService.listarTodos();
    }

    //Metodo para filtrar servicos pelo id
    @GetMapping(value = "/{ser_nr_id}")
    public ResponseEntity<?> findById(@PathVariable Integer ser_nr_id) {
       Servico list = servicoService.findById(ser_nr_id);
        ;
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value = "/criarServico")
    public Object criarServico(@RequestBody Servico servico) {
        ResponseDefault response = new ResponseDefault();
        try {
            servicoService.criarServico(servico);
            response.setValue(true);
            response.setCodigo(200);
            response.setMensagem("Servico realizado com sucesso!");
        } catch (Exception e) {
            response.setCodigo(400);
            System.out.println(e.getMessage());
            response.setMensagem("Erro ao fazer servico");
            response.setValue(false);
        }
        return response;
    }

    @PutMapping(value = "/{ser_nr_id}")
    public Object alterarServico(@PathVariable Integer ser_nr_id, @RequestBody Servico servico) {
        ResponseDefault response = new ResponseDefault();
        try {
            servicoService.alterarservico(ser_nr_id,servico);
            response.setValue(true);
            response.setCodigo(200);
            response.setMensagem("Serviço Alterado com sucesso!");
        } catch (Exception e) {
            response.setCodigo(400);
            e.printStackTrace();
            System.out.println(e.getMessage());
            response.setMensagem("Erro ao fazer alteração no serviço " +servico.getSer_tx_nome());
            response.setValue(false);
        }
        return response;
    }


    @DeleteMapping(value = "/{ser_nr_id}")
    public Object deletarServico(@PathVariable Integer ser_nr_id) {
        ResponseDefault response = new ResponseDefault();
        try {
            servicoService.deletarServico(ser_nr_id);
            response.setValue(true);
            response.setCodigo(200);
            response.setMensagem("Serviço deletada com sucesso!");
        } catch (Exception e) {
            response.setCodigo(400);
            e.printStackTrace();
            System.out.println(e.getMessage());
            response.setMensagem("Erro, não foi possivel deletar servico");
            response.setValue(false);
        }
        return response;
    }
}
