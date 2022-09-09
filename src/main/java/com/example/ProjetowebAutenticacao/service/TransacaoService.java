package com.example.ProjetowebAutenticacao.service;

import com.example.ProjetowebAutenticacao.dto.UsuarioDTO;
import com.example.ProjetowebAutenticacao.model.Perfil;
import com.example.ProjetowebAutenticacao.model.Transacao;
import com.example.ProjetowebAutenticacao.model.Usuario;
import com.example.ProjetowebAutenticacao.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {
    @Autowired
    private TransacaoRepository transacaoRepository;
    //Metodo listar
    public List<Transacao> listTransacoes(){
        return(List<Transacao>)transacaoRepository.findAll();
    }

    //Metodo para retonar dados pelo id
    public Transacao findById(Integer tra_nr_id){
        Optional<Transacao> obj= transacaoRepository.findById(tra_nr_id);
        return obj.get();
    }

    //Metodo criar
    public void fazerTransacao(Transacao transacao)throws Exception{
        if(transacao.getSer_nr_id()==null){
            throw new Exception("Id de serviço é Obrigatorio");
        }else if(transacao.getPer_nr_id()==null){
            throw new Exception("Id de Perfil é Obrigatorio");
        }
        transacaoRepository.save(transacao);
    }

//Metodo alterar
    public ResponseEntity<Transacao> alterarTransacao(@PathVariable Integer tra_nr_id, @RequestBody Transacao transacao)throws Exception{
        if(!transacaoRepository.existsById(tra_nr_id)){
            throw new Exception("Esta transação não existe");
        }
        transacao.setTra_nr_id(tra_nr_id);
        transacao = transacaoRepository.save(transacao);
        return ResponseEntity.ok(transacao);

    }
//Metodo deletar
    public ResponseEntity<Void> deletetransacao(@PathVariable Integer tra_nr_id)throws Exception{
        if(!transacaoRepository.existsById(tra_nr_id)){
            throw new Exception("Esta transação não existe");
        }
        transacaoRepository.deleteById(tra_nr_id);;
        return ResponseEntity.noContent().build();


    }
}
