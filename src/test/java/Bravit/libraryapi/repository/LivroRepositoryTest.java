package Bravit.libraryapi.repository;

import Bravit.libraryapi.model.Autor;
import Bravit.libraryapi.model.GeneroLivro;
import Bravit.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("1234567890");
        livro.setTitulo("Assim falou 1001");
        livro.setDataPublicacao(LocalDate.of(2020, 1, 1));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setPreco(new BigDecimal("10.00"));

        Autor autor = autorRepository.findById(UUID.fromString("69fe528a-4e18-4879-967d-ef4b1b4d7810")).orElse(null);
        livro.setAutor(new Autor());

        repository.save(livro);

    }

    @Test
    void salvarCascadeTest(){
        Livro livro = new Livro();
        livro.setIsbn("1234567890");
        livro.setTitulo("DoeJohn");
        livro.setDataPublicacao(LocalDate.of(2020, 1, 1));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setPreco(new BigDecimal("10.00"));

        Autor autor = new Autor();
        autor.setNome("John Doe");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1999, 4, 29));

        livro.setAutor(autor);

        repository.save(livro);

    }
    @Test
    void salvarAutorELivroTest(){
        Livro livro = new Livro();
        livro.setIsbn("12345-67890");
        livro.setTitulo("O Alquimista2");
        livro.setDataPublicacao(LocalDate.of(2020, 1, 1));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setPreco(new BigDecimal("10.00"));

        Autor autor = new Autor();
        autor.setNome("jorge");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1999, 4, 29));

        autorRepository.save(autor);
        livro.setAutor(autor);

        repository.save(livro);

    }
    @Test
    @Transactional
    void atualizarAutorDoLivroTest(){

        UUID id = UUID.fromString("ad74bbc6-992d-4e01-ad99-f1c3d591706c");

        var livroParaAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("cbb916f1-5b5c-4e0d-af79-dad6e27c4ece");

        Autor Jose = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(Jose);

        repository.save(livroParaAtualizar);
    }

    @Test
    void deleteTest(){
        UUID id = UUID.fromString("ad74bbc6-992d-4e01-ad99-f1c3d591706c");
        repository.deleteById(id);
    }

    @Test
    void deletarCascade(){
        UUID id = UUID.fromString("ad74bbc6-992d-4e01-ad99-f1c3d591706c");
        repository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivrosTest(){
        UUID id = UUID.fromString("ad74bbc6-992d-4e01-ad99-f1c3d591706c");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Livro encontrado: ");
        System.out.println(livro.getTitulo());
//        System.out.println("Autor: ");
//        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void pesquisarPorTituloTest(){
        List<Livro> lista = repository.findByTitulo("Codigo Limpo");
        lista.forEach(System.out::println);

    }
    @Test
    void pesquisarPorIsbnTest(){
        List<Livro> lista = repository.findByIsbn("2289-3880");
        lista.forEach(System.out::println);

    }

    @Test
    void pesquisarPorGeneroTest(){
        List<Livro> lista = repository.findByGenero(GeneroLivro.ROMANCE, "dataPublicacao");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorTituloEPrecoTest(){
        List<Livro> lista = repository.findByTituloAndPreco("Codigo Limpo", new BigDecimal("10.00"));
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorPrecoTest(){
        List<Livro> lista = repository.findByPreco(new BigDecimal("10.00"));
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivrosComQueryJPQL(){
        var resultado = repository.listaTodosOrdenadosPorTituloAndPreco();
        resultado.forEach(System.out::println);
    }
    @Test
    void listarAutoresDosLivros(){
        var resultado = repository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarNomesDiferentesLivros(){
        var resultado = repository.listarNomesDiferentesLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosAutoresBrasileiros(){
        var resultado = repository.listarGenerosAutoresBrasileiros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroQueryParamTest(){
        var resultado = repository.findByGenero(GeneroLivro.MISTERIO,"preco");
        resultado.forEach(System.out::println);

    }

    @Test
    void findByGeneroPositionalParameteTest(){
        var resultado = repository.findByGeneroPositionalParamete("preco", GeneroLivro.MISTERIO);
        resultado.forEach(System.out::println);

    }
    @Test
    void deleteByGeneroTest(){
        repository.deleteByGenero(GeneroLivro.CIENCIA);
    }


}