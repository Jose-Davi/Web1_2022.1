package com.example.ProjetowebAutenticacao.controller;

import com.example.ProjetowebAutenticacao.dto.UsuarioDTO;
import com.example.ProjetowebAutenticacao.model.Log;
import com.example.ProjetowebAutenticacao.model.Usuario;
import com.example.ProjetowebAutenticacao.service.LogService;
import com.example.ProjetowebAutenticacao.service.UsuarioService;
import com.example.ProjetowebAutenticacao.util.ResponseDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UsuarioController{

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LogService logService;



    @GetMapping(value="/listar")
    public Object getTeste(){

        return usuarioService.getTodos();

    }

    @PostMapping(value="/criarUsuario")
    public Object criarUsuario(@RequestBody UsuarioDTO usuario){

        ResponseDefault response = new ResponseDefault();

        try{
            usuarioService.criar(usuario);
            response.setValue(true);
            response.setCodigo(200);
            response.setMensagem("Usuario Criado com sucesso!");

        }catch (Exception e){
            response.setCodigo(400);
            System.out.println(e.getMessage());
            response.setMensagem(e.getMessage());
            response.setValue(false);


        }
        return response;

    }

    @PutMapping(value = "/{usu_nr_id}")
    public Object alterarUsuario(@PathVariable Integer usu_nr_id,@RequestBody Usuario usuario){
        ResponseDefault response = new ResponseDefault();
        try{
            usuarioService.alterar(usu_nr_id,usuario);
            response.setValue(true);
            response.setCodigo(200);
            response.setMensagem("Alterações realizadas com sucesso!");

        }catch (Exception e){
            response.setCodigo(400);
            e.printStackTrace();
            System.out.println(e.getMessage());
            response.setMensagem(e.getMessage());
            response.setValue(false);
        }
        return response;
    }
    /*@GetMapping(value="/validar")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String usuTxLogin,@RequestParam String usuTxSenha){
       return usuarioService.validarSenha(usuTxLogin,usuTxSenha);
    }*/
    @GetMapping(value = "/{usu_nr_id}")
    public ResponseEntity<?> findById(@PathVariable Integer usu_nr_id){
        UsuarioDTO list= usuarioService.findById(usu_nr_id);
        return ResponseEntity.ok().body(list);
    }





    @DeleteMapping(value = "/{usu_nr_id}")
    public Object deletarUsuario(@PathVariable Integer usu_nr_id){
        ResponseDefault response = new ResponseDefault();
        try{
            usuarioService.delete(usu_nr_id);
            response.setValue(true);
            response.setCodigo(200);
            response.setMensagem("Usuario foi deletado com sucesso!");
        }catch (Exception e){
            response.setCodigo(400);
            e.printStackTrace();
            System.out.println(e.getMessage());
            response.setMensagem(e.getMessage());
            response.setValue(false);
        }
        return response;
    }

    //Mertodo listar log so sistema
    @GetMapping(value = "/logs")
    public List<Log> geTodos(){
        return logService.getTodos();
    }




}
