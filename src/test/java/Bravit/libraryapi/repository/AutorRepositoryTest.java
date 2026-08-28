package Bravit.libraryapi.repository;

import Bravit.libraryapi.model.Autor;
import Bravit.libraryapi.model.GeneroLivro;
import Bravit.libraryapi.model.Livro;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvaTest() {
        Autor autor = new Autor();
        autor.setNome("Dayara");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1999, 4, 29));

        var autorSalve = repository.save(autor);
        System.out.println("Autor Salvo: " + autorSalve);

    }

    //@Test
    public void atualizarTest() {
        var id = UUID.fromString("aa17121c-2997-466b-bef7-d450062d801f");

        Optional<Autor> possivelAutor = repository.findById(id);
        if (possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor: ");
            System.out.println(autorEncontrado);

            autorEncontrado.setNome("Andrew");

            repository.save(autorEncontrado);
            System.out.println("Dados do Autor Atualizado: ");
            System.out.println(autorEncontrado);
        } else {
            System.out.println("Autor nao encontrado");
        }
    }

    @Test
    public void listaTest() {
        List<Autor> list = repository.findAll();
        list.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        System.out.println("Contagem de Autores: " + repository.count());

    }

    @Test
    public void deletePorIdTest() {
        var id = UUID.fromString("aa17121c-2997-466b-bef7-d450062d801f");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("31ee642a-73b2-4e79-bf6b-eb825b2c4f92");
        var Maria = repository.findById(id).get();
        repository.delete(Maria);
    }

    @Test
    public void salvarAutorComLivro() {
        Autor autor = new Autor();
        autor.setNome("Marcos");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1970, 8, 5));

        Livro livro = new Livro();
        livro.setIsbn("2289-3880");
        livro.setPreco(BigDecimal.valueOf(204));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setTitulo("A vida dos outros");
        livro.setDataPublicacao(LocalDate.of(1999, 1, 1));

        Livro livro2 = new Livro();
        livro2.setIsbn("6546-7652");
        livro2.setPreco(BigDecimal.valueOf(640));
        livro2.setGenero(GeneroLivro.ROMANCE);
        livro2.setTitulo("Quantos vale o amor");
        livro2.setDataPublicacao(LocalDate.of(2000, 5, 6));

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        livro.setAutor(autor);
        livro2.setAutor(autor);

        repository.save(autor);

        //livroRepository.saveAll(autor.getLivros());
    }
    @Test
    @Transactional
    public void mostrarLivrosDoAutor() {
        var id = UUID.fromString("6d2ad1e6-2b75-4a18-b8a7-9def341f2d4a");
        var autor = repository.findById(id).get();

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);

    }
    @Test
    public void pesquisarPorNomeTest(){
        List<Autor> lista = repository.findByNome("Dayara");
        lista.forEach(System.out::println);
    }

}
