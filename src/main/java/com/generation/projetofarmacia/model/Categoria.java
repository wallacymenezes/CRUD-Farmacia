package com.generation.projetofarmacia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O atributo nome não pode ser nulo e nem vazio.")
    private String nome;

    @NotBlank(message = "O atributo descricao não pode ser nulo e nem vazio.")
    @Size(min = 5, max = 255, message = "O atributo deve ter no mínimo 5 e no máximo 255 caracteres")
    private String descricao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
