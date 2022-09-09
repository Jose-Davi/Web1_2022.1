package com.example.ProjetowebAutenticacao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity

public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer per_nr_id;//PK
    @Column(nullable = false, length = 50)
    private String per_tx_nome;
    @Column(nullable = false, length = 1)
    private char per_tx_status;
//Testando mapeamento
    @ManyToOne
    @JoinColumn(name="usu_nr_id")
    private Usuario usu_nr_id;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "per_nr_id",fetch = FetchType.LAZY)
    private List<Transacao> transacao= new ArrayList<>();
}


