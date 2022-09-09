package com.example.ProjetowebAutenticacao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
@Getter
@Setter
@Entity

public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer log_nr_id;
    @Column(nullable = false)
    private Integer usu_nr_id;
    @Column(length = 500, nullable = false)
    private String log_tx_execucao;
    @Column(nullable = false)
    private Timestamp log_dt_execucao;
}
