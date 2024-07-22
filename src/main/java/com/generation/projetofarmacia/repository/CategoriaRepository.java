package com.generation.projetofarmacia.repository;

import com.generation.projetofarmacia.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    public List<Categoria> findAllByNomeContainingIgnoreCase(String nome);

    public List<Categoria> findAllByDescricaoContainingIgnoreCase(String nome);
}
