package Bravit.libraryapi.controller.dto;

import Bravit.libraryapi.model.Autor;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
        UUID id,
        @NotBlank(message = "Nome do autor não pode ser vazio")
        @Size(min = 2, max = 100, message = "campo fora do tamanho")
        String nome,
        @NotNull(message = "Data de nascimento do autor não pode ser nula")
        @Past(message = "nao pode ser data futura ")
        LocalDate dataNascimento,
        @NotBlank(message = "Nacionalidade do autor não pode ser vazia")
        @Size(min = 2, max = 50, message = "campo obrigatorio")
        String nacionalidade) {

    public Autor mapearParaAutor() {
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}
