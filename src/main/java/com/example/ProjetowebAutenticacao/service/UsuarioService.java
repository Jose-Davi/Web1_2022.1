package com.example.ProjetowebAutenticacao.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.ProjetowebAutenticacao.dto.UsuarioDTO;
import com.example.ProjetowebAutenticacao.model.Usuario;
import com.example.ProjetowebAutenticacao.repository.UsuarioRepository;
import com.example.ProjetowebAutenticacao.security.AutenticarJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;


    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> getTodos(){
        return (List<Usuario>) usuarioRepository.findAll();
    }

   /* public List<UsuarioDTO > getTodos(){
       List<Usuario> usuario = (List<Usuario>) usuarioRepository.findAll();
       return UsuarioDTO.convert(usuario);

    }*/

    public void criar(UsuarioDTO usuario)throws Exception{

        usuario.setUsu_dt_cadastro(new Timestamp(System.currentTimeMillis()));
        usuario.setUsuTxSenha(passwordEncoder.encode(usuario.getUsuTxSenha()));
        usuarioRepository.save(usuario.toUsuario());
    }


    public ResponseEntity<Usuario> alterar(@PathVariable Integer usu_nr_id,@RequestBody Usuario usuario)throws Exception{
        if(!usuarioRepository.existsById(usu_nr_id)){
            throw new Exception("Usuario não encontrado");
        }
        usuario.setUsu_dt_cadastro(new Timestamp(System.currentTimeMillis()));
        usuario.setUsu_nr_id(usu_nr_id);
        usuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    public ResponseEntity<Void> delete(@PathVariable Integer usu_nr_id){
        if(!usuarioRepository.existsById(usu_nr_id)){
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(usu_nr_id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Boolean> validarSenha(@RequestParam String usuTxLogin,@RequestParam String usuTxSenha){
       //verificacao de usuario, se nao for encontardo retorna um unauthorized com boolean false
        Optional<Usuario> optionalUsuario = usuarioRepository.findByUsuTxLogin(usuTxLogin);
        if(optionalUsuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
        //verificacao de senha valida, e retorno o httpstatus ok caso ela seja valida e um unauthorized se nao for valida
        //usei metodo matches para comparar as senhas
        Usuario usuario = optionalUsuario.get();
       boolean valid = passwordEncoder.matches(usuTxSenha,usuario.getUsuTxSenha());
        HttpStatus status=(valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);
    }
    //metodo para retornar usuario especifico pelo id

    public UsuarioDTO findById(Integer usu_nr_id){
        Optional<Usuario> obj= usuarioRepository.findById(usu_nr_id);
        return obj.get().toUsuario();
    }

   /* //verificar este metodo-->

    public List<UsuarioDTO> getByPerfil(Integer per_nr_id) {
        List<UsuarioDTO> l = new ArrayList<>();
        usuarioRepository.getByPerfil(per_nr_id).forEach(item -> {
            l.add(item.toUsuario());
        });
        return l;
    }*/


}
