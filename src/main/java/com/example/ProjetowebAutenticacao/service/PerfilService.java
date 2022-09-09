package com.example.ProjetowebAutenticacao.service;

import com.example.ProjetowebAutenticacao.dto.UsuarioDTO;
import com.example.ProjetowebAutenticacao.model.Perfil;
import com.example.ProjetowebAutenticacao.model.Usuario;
import com.example.ProjetowebAutenticacao.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {
    @Autowired
    private PerfilRepository perfilRepository;

    public List<Perfil> listPerfis(){
        return(List<Perfil>)perfilRepository.findAll();
    }

    public Perfil criarPerfil(Perfil perfil)throws Exception{

       return perfilRepository.save(perfil);
    }


    public ResponseEntity<Perfil> alterarPerfil(@PathVariable Integer per_nr_id,@RequestBody Perfil perfil)throws Exception{
        if(!perfilRepository.existsById(per_nr_id)){
            throw new Exception("Este Perfil não existe");
        }
        perfil.setPer_nr_id(per_nr_id);
        perfil = perfilRepository.save(perfil);
        return ResponseEntity.ok(perfil);

    }

    public ResponseEntity<Void> delete(@PathVariable Integer per_nr_id){
        if(!perfilRepository.existsById(per_nr_id)){
            return ResponseEntity.notFound().build();
        }
        perfilRepository.deleteById(per_nr_id);;
        return ResponseEntity.noContent().build();
}

    public Perfil findById(Integer per_nr_id)throws Exception{
        if(!perfilRepository.existsById(per_nr_id)){
            throw new Exception("Id de Perfil não existe!");
        }
        Optional<Perfil> obj= perfilRepository.findById(per_nr_id);
        return obj.get();
    }
}
