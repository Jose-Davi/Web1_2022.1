package com.example.ProjetowebAutenticacao.model;

import com.example.ProjetowebAutenticacao.dto.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer usu_nr_id;
    @Column(nullable = false,length = 100)
    private String usu_tx_nome;
    @Column(name="usu_tx_login",nullable = false,length = 50,unique = true)
    private String usuTxLogin;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name="usu_tx_senha",nullable = false,length = 500)
    private String usuTxSenha;
    @Column(nullable = false,length = 1)
    private char usu_tx_status;
    @Column(nullable = false)
    private Timestamp usu_dt_cadastro;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @OneToMany(cascade = CascadeType.ALL,mappedBy = "usu_nr_id",fetch = FetchType.LAZY)
    private List<Perfil> perfil= new ArrayList<>();



    public UsuarioDTO toUsuario(){
        return UsuarioDTO.builder()
                .usu_nr_id(usu_nr_id)
                .usu_tx_nome(usu_tx_nome)
                .usuTxLogin(usuTxLogin)
                .usu_tx_status(usu_tx_status)
                .usu_dt_cadastro(usu_dt_cadastro)
                .build();
    }

}
