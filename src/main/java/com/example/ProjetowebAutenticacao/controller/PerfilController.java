package com.example.ProjetowebAutenticacao.controller;

import com.example.ProjetowebAutenticacao.dto.UsuarioDTO;
import com.example.ProjetowebAutenticacao.model.Perfil;
import com.example.ProjetowebAutenticacao.model.Usuario;
import com.example.ProjetowebAutenticacao.service.PerfilService;
import com.example.ProjetowebAutenticacao.util.ResponseDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("perfil")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;
    @GetMapping(value="/listar")
    public Object listarPerf(){
        return perfilService.listPerfis();
    }

    //Metodo para retornar perfil pelo id
    @GetMapping(value = "/{per_nr_id}")
    public ResponseEntity<?> findById(@PathVariable Integer per_nr_id){
        Perfil list= null;
        try {
            list = perfilService.findById(per_nr_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value="/criarPerfil")
    public Object criarPerfil(@RequestBody Perfil perfil){
        ResponseDefault response = new ResponseDefault();
        try{
            perfilService.criarPerfil(perfil);
            response.setValue(true);
            response.setCodigo(200);
            response.setMensagem("Perfil Criado com sucesso!");
        }catch (Exception e){
            response.setCodigo(400);
            System.out.println(e.getMessage());
            response.setMensagem(e.getMessage());
            response.setValue(false);
        }
        return response;
    }

    @PutMapping(value = "/{per_nr_id}")
    public Object alterarPerfil(@PathVariable Integer per_nr_id,@RequestBody Perfil perfil){
        ResponseDefault response = new ResponseDefault();
        try{
            perfilService.alterarPerfil(per_nr_id,perfil);
            response.setValue(true);
            response.setCodigo(200);
            response.setMensagem("Perfil Alterado com sucesso!");
        }catch (Exception e){
            response.setCodigo(400);
            e.printStackTrace();
            System.out.println(e.getMessage());
            response.setMensagem(e.getMessage());
            response.setValue(false);
        }
        return response;
    }


    @DeleteMapping(value = "/{per_nr_id}")
    public Object deletarPerfil(@PathVariable Integer per_nr_id){
        ResponseDefault response = new ResponseDefault();
        try{
            perfilService.delete(per_nr_id);
            response.setValue(true);
            response.setCodigo(200);
            response.setMensagem("Perfil deletado com sucesso!");
        }catch (Exception e){
            response.setCodigo(400);
            e.printStackTrace();
            System.out.println(e.getMessage());
            response.setMensagem(e.getMessage());
            response.setValue(false);
        }
        return response;
    }
}
