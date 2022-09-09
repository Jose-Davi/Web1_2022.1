package com.example.ProjetowebAutenticacao.service;

import com.example.ProjetowebAutenticacao.model.Sistema;
import com.example.ProjetowebAutenticacao.model.Transacao;
import com.example.ProjetowebAutenticacao.repository.SistemaReposistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class SistemaService {
    @Autowired
    private SistemaReposistory sistemaReposistory;
    //Metodo listar
    public List<Sistema> listarSistemas(){
        return (List<Sistema>) sistemaReposistory.findAll();
    }
    //listar pelo id

    public Sistema findById(Integer sis_nr_id){
        Optional<Sistema> obj= sistemaReposistory.findById(sis_nr_id);
        return obj.get();
    }
    //Metodo Criar
    public void criarSistema(Sistema sistema)throws Exception{
        if(sistema.getSis_tx_nome()==null){
            throw new Exception("É Obrigatorio o campo nome do sistema ser preenchido!");
        }
        sistemaReposistory.save(sistema);

    }
    //Metodo alterar
    public ResponseEntity<Sistema> alterarSistema(@PathVariable Integer sis_nr_id, @RequestBody Sistema sitema)throws Exception{
     if(!sistemaReposistory.existsById(sis_nr_id)){
     throw new Exception("Este sistema não existe!");}
     sitema.setSis_nr_id(sis_nr_id);
     sitema=sistemaReposistory.save(sitema);
     return ResponseEntity.ok(sitema) ;
    }

    //Metodo delete
    public ResponseEntity<Sistema> deletarSistema(@PathVariable Integer sis_nr_id)throws Exception{
        if(!sistemaReposistory.existsById(sis_nr_id)){
            throw new Exception("Este sistema não existe!");
        }
        sistemaReposistory.deleteById(sis_nr_id);
        return ResponseEntity.noContent().build();
    }
}
