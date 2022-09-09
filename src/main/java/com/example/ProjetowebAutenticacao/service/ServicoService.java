package com.example.ProjetowebAutenticacao.service;

import com.example.ProjetowebAutenticacao.model.Servico;
import com.example.ProjetowebAutenticacao.model.Transacao;
import com.example.ProjetowebAutenticacao.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {
    @Autowired
    private ServicoRepository servicoRepository;

    //Metodo listar
    public List<Servico> listarTodos(){
        return (List<Servico>) servicoRepository.findAll();
    }

    //filtrar pelo id
    public Servico findById(Integer ser_nr_id){
        Optional<Servico> obj= servicoRepository.findById(ser_nr_id);
        return obj.get();
    }

    //Metodo criar
    public void criarServico(Servico servico)throws Exception{
        if(servico.getSis_nr_id()==null){
            throw new Exception("Id de sistema é obrigatorio para realizar um servico!");
        }
        servicoRepository.save(servico);
    }

    //metodo alterar
    public ResponseEntity<Servico> alterarservico(@PathVariable Integer ser_nr_id, @RequestBody Servico servico)throws Exception{
        if(!servicoRepository.existsById(ser_nr_id)){
            throw new Exception("Este Servico não existe!");
        }
        servico.setSer_nr_id(ser_nr_id);
        servico = servicoRepository.save(servico);
        return ResponseEntity.ok(servico);
    }

    //Metodo deletar
    public ResponseEntity<Servico> deletarServico(@PathVariable Integer ser_nr_id)throws Exception{
        if(!servicoRepository.existsById(ser_nr_id)){
            throw new Exception("Este Servico não existe!");
        }
        servicoRepository.deleteById(ser_nr_id);
        return ResponseEntity.noContent().build();
    }
}
