package com.generation.projetofarmacia.controller;

import com.generation.projetofarmacia.model.Categoria;
import com.generation.projetofarmacia.repository.CategoriaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoriaControllerTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CategoriaController categoriaController;

    @Test
    @DisplayName("✔ Cadastrar uma nova categoria")
    public void deveCadastrarUmaNovaCategoria() {
        HttpEntity<Categoria> corpoRequisicao = new HttpEntity<Categoria>(new Categoria(0L,
                "Nova Categoria", "Descrição da nova categoria"));

        ResponseEntity<Categoria> corpoResposta = testRestTemplate
                .exchange("/categorias", HttpMethod.POST, corpoRequisicao, Categoria.class);

        assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
    }

    @Test
    @DisplayName("Atualizar um Usuario")
    public void deveAtualizarUmUsuario(){

        Categoria categoriaCadastrada = categoriaRepository.save(new Categoria(0L, "Nova Categoria", "Descrição da nova categoria"));

        Categoria categoriaUpdate = new Categoria(categoriaCadastrada.getId(), "Categoria Atualizada","A nova descrição da categoria");

        HttpEntity<Categoria> corpoRequisicao = new HttpEntity<Categoria>(categoriaUpdate);

        ResponseEntity<Categoria> corpoResposta = testRestTemplate
                .exchange("/categorias", HttpMethod.PUT, corpoRequisicao, Categoria.class);
    }

    @Test
    @DisplayName("Listar todos os Usuarios")
    public void deveMostrarTodosUsuarios() {

        categoriaRepository.save(new Categoria(0L, "Nova Categoria", "Descrição da nova categoria"));

        categoriaRepository.save(new Categoria(0L, "Nova Categoria", "Descrição da nova categoria"));

        ResponseEntity<String> resposta = testRestTemplate
                .exchange("/categorias", HttpMethod.GET, null, String.class);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }
}
