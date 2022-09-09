package com.example.ProjetowebAutenticacao.service;

import com.example.ProjetowebAutenticacao.data.DetalhesUserData;
import com.example.ProjetowebAutenticacao.model.Usuario;
import com.example.ProjetowebAutenticacao.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalheUserService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public DetalheUserService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByUsuTxLogin(username);
        if(usuario.isEmpty()){//se usuario for vazio lancara exceção
           throw new UsernameNotFoundException("Usuario "+username+ "não encontrado!");
        }
        //se usuario encontrado retorno a classe detahesuserdata com um optional usuario como parametro
        return new DetalhesUserData(usuario);

    }
}
