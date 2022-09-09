package com.example.ProjetowebAutenticacao.dto;


import com.example.ProjetowebAutenticacao.model.Usuario;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Integer usu_nr_id;
    private String usu_tx_nome;
    private String usuTxLogin;
    private String usuTxSenha;
    private char usu_tx_status;
    private Timestamp usu_dt_cadastro;


   /* public static List<UsuarioDTO> convert(List<Usuario> usuario){
        return usuario.stream().map(UsuarioDTO::new).collect(Collectors.toList());
    }*/


     public Usuario toUsuario(){
        return Usuario.builder()
                .usu_nr_id(usu_nr_id)
                .usu_tx_nome(usu_tx_nome)
                .usuTxLogin(usuTxLogin)
                .usuTxSenha(usuTxSenha)
                .usu_tx_status(usu_tx_status)
                .usu_dt_cadastro(usu_dt_cadastro)
                .build();
    }


}
