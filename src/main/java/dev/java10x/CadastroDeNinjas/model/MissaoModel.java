package dev.java10x.CadastroDeNinjas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_missao")
public class MissaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeMissao;
    private String dificuldade;

    //Uma missao pode ter varios ninjas
    @OneToMany(mappedBy = "missoes")
    private NinjaModel ninja;

    private MissaoModel(){}

    public MissaoModel(String nomeMissao, String dificuldade) {
        this.nomeMissao = nomeMissao;
        this.dificuldade = dificuldade;
    }

    public String getNomeMissao() {
        return nomeMissao;
    }

    public void setNomeMissao(String nomeMissao) {
        this.nomeMissao = nomeMissao;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }
}
