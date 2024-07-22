package com.generation.projetofarmacia.controller;

import com.generation.projetofarmacia.model.Categoria;
import com.generation.projetofarmacia.model.Produto;
import com.generation.projetofarmacia.repository.ProdutoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProdutoControllerTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("✔ Cadastrar uma nova Produto")
    public void deveCadastrarUmaNovoProduto() {
        HttpEntity<Produto> corpoRequisicao = new HttpEntity<Produto>(new Produto(0L,
                "Novo Produto", "Descrição da novo produto", 29.99, 10));

        ResponseEntity<Categoria> corpoResposta = testRestTemplate
                .exchange("/produtos", HttpMethod.POST, corpoRequisicao, Categoria.class);

        assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
    }

    @Test
    @DisplayName("Atualizar um Produto")
    public void deveAtualizarUmProduto(){

        Produto produtoCadastrado = produtoRepository.save(new Produto(0L,
                "Novo Produto", "Descrição da novo produto", 29.99, 10));

        Produto produtoUpdate = new Produto(produtoCadastrado.getId(),
                "Novo Produto", "Descrição da novo produto", 29.99, 10);

        HttpEntity<Produto> corpoRequisicao = new HttpEntity<Produto>(produtoUpdate);

        ResponseEntity<Produto> corpoResposta = testRestTemplate
                .exchange("/produtos", HttpMethod.PUT, corpoRequisicao, Produto.class);

        assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
    }

    @Test
    @DisplayName("Listar todos os Produtos")
    public void deveMostrarTodosProdutos() {

        produtoRepository.save(new Produto(0L,
                "Novo Produto", "Descrição da novo produto", 29.99, 10));

        produtoRepository.save(new Produto(0L,
                "Novo Produto", "Descrição da novo produto", 29.99, 15));

        ResponseEntity<String> resposta = testRestTemplate
                .exchange("/produtos", HttpMethod.GET, null, String.class);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }
}
