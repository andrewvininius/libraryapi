package Bravit.libraryapi.service;

import Bravit.libraryapi.model.Autor;
import Bravit.libraryapi.model.GeneroLivro;
import Bravit.libraryapi.model.Livro;
import Bravit.libraryapi.repository.AutorRepository;
import Bravit.libraryapi.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroRepository livroRepository;

    public void salvarLivroComFoto(){

    }

    @Transactional
    public void atualizacaoSemAtualizar(){
        var livro = livroRepository
                .findById(UUID.fromString("ad74bbc6-992d-4e01-ad99-f1c3d591706c"))
                .orElse(null);
        livro.setDataPublicacao(LocalDate.of(2024, 6, 1));


    }

    @Transactional
    public void executar(){

        Autor autor = new Autor();
        autor.setNome("testFrancisca");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1999, 4, 29));

        autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setIsbn("12345-67890");
        livro.setTitulo("testLivro de Francisca");
        livro.setDataPublicacao(LocalDate.of(2020, 1, 1));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setPreco(new BigDecimal("10.00"));


        livro.setAutor(autor);

        livroRepository.save(livro);

        if(autor.getNome().equals("testfrancisca")){
            throw new RuntimeException("Rollback");
        }
    }
}
