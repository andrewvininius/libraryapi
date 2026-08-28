package Bravit.libraryapi.repository;

import Bravit.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacoesTest {
    @Autowired
    AutorRepository autorRepository;

    @Autowired
    TransacaoService transacaoService;

    /**
     * Commit -> confirmar as alterações
     * Rollback -> desfazer as alterações
     */
    @Test
    @Transactional
    void transsacaoSimples(){
        // salvar um livro
        // salvar o autor
        // alugar o livro
        // enviar email pro locatário
        // notificar que o livro saiu da livraria

    }
    @Test
    void transacaoSimplesTest(){
        transacaoService.executar();
    }
    @Test
    void transacaoEstadoManaged(){
        transacaoService.atualizacaoSemAtualizar();
    }

}
