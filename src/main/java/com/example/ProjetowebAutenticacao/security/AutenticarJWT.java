package com.example.ProjetowebAutenticacao.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.ProjetowebAutenticacao.data.DetalhesUserData;
import com.example.ProjetowebAutenticacao.model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AutenticarJWT extends UsernamePasswordAuthenticationFilter{

    public static final int TOKEN_EXPIRACAO = 1800000;
    public static final String TOKEN_SENHA="e5c69a8b-868c-4ec9-bdbb-cb14c0070e33";
    private final AuthenticationManager authenticationManager;

    public AutenticarJWT(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Usuario usuario =new  ObjectMapper().readValue(request.getInputStream(), Usuario.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    usuario.getUsuTxLogin(),
                    usuario.getUsuTxSenha(),
                    new ArrayList<>()
                    //mexi

            ));

        } catch (IOException e) {

            throw new RuntimeException("Falha ao se autenticar",e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        DetalhesUserData detalhesUserData =(DetalhesUserData) authResult.getPrincipal();

        //Criando token
        String token= JWT.create().withSubject(detalhesUserData.getUsername()).
                withExpiresAt(new Date(System.currentTimeMillis()+TOKEN_EXPIRACAO)).
                sign(Algorithm.HMAC512(TOKEN_SENHA));
        response.getWriter().write(token);
        response.getWriter().flush();
        System.out.println("sucesso ao autenticar");



    }
}
