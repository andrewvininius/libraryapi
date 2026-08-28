package Bravit.libraryapi.repository;

import Bravit.libraryapi.model.Autor;
import Bravit.libraryapi.model.GeneroLivro;
import Bravit.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
/**
* @see LivroRepositoryTest
*/
public interface LivroRepository extends JpaRepository<Livro, UUID> {
    List<Livro> findByAutor(Autor autor);

    List<Livro> findByTitulo(String titulo);
    List<Livro>findByIsbn(String isbn);
    List<Livro>findByTituloAndPreco(String titulo, BigDecimal preco);
    List<Livro>findByTituloOrIsbnOrderByTitulo(String titulo, String isbn);

    List<Livro>findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);

    //List<Livro>findByGenero(GeneroLivro genero);

    List<Livro>findByPreco( BigDecimal preco);

    // JPQL -> referncia as entidades e as propriedades
    @Query(" select l from Livro as l order by l.titulo, l.preco")
    List<Livro>listaTodosOrdenadosPorTituloAndPreco();

    @Query(" select a from Livro l join l.autor a")
    List<Autor> listarAutoresDosLivros();

    @Query(" select distinct l.titulo from Livro l ")
    List<String> listarNomesDiferentesLivros();

    @Query("""
        SELECT l.genero
        FROM Livro l
        JOIN l.autor a
        WHERE a.nacionalidade = 'Brasileiro'
        ORDER BY l.genero
""")
    List<String>listarGenerosAutoresBrasileiros();

    @Query("select l from Livro l where l.genero = :nomeDoParametro")
    List<Livro> findByGenero(
            @Param("nomeDoParametro") GeneroLivro generoLivro,
            @Param("paramOrdenacao") String nomeDoParametro
    );
    @Query("select l from Livro l where l.genero = ?2 order by ?1 ")
    List<Livro> findByGeneroPositionalParamete(
            String nomeDoParametro,
            GeneroLivro generoLivro
    );
    @Modifying
    @Transactional
    @Query(" delete from Livro where genero = ?1")
    void deleteByGenero(GeneroLivro genero);

}
