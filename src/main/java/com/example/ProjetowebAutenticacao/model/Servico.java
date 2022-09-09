package com.example.ProjetowebAutenticacao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity

public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer ser_nr_id;//PK
    @Column(nullable = false,length = 50)
    private String ser_tx_nome;
    @Column(nullable = false,length = 100)
    private String ser_tx_url;
    @Column(nullable = false,length = 1)
    private char ser_tx_status;

    @ManyToOne
    @JoinColumn(name="sis_nr_id")
    private Sistema sis_nr_id;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "ser_nr_id",fetch = FetchType.LAZY)
    private List<Transacao> transacao= new ArrayList<>();

}
