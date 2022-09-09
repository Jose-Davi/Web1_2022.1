package com.example.ProjetowebAutenticacao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity

public class Sistema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer sis_nr_id;//PK
    @Column(nullable = false,length = 50)
    private String sis_tx_nome;
    @Column(nullable = false,length = 1)
    private char sis_tx_status;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "sis_nr_id",fetch = FetchType.LAZY)
    private List<Servico> servico= new ArrayList<>();
}
