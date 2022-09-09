package com.example.ProjetowebAutenticacao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity

public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer tra_nr_id;//PK

    @ManyToOne
    @JoinColumn(name="per_nr_id")
    private Perfil per_nr_id;//FK

    @Column(nullable = false,length = 50)
    private String tra_tx_nome;
    @Column(nullable = false,length = 1)
    private char tra_tx_status;

    @ManyToOne
    @JoinColumn(name="ser_nr_id")
    private Servico ser_nr_id;

    @Column(nullable = false,length = 1000)
    private String tra_tx_url;


}
