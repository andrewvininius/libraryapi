package Bravit.libraryapi.controller.dto;

import Bravit.libraryapi.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
        UUID id,
        @NotBlank(message = "Nome do autor não pode ser vazio")
        String nome,
        @NotNull(message = "Data de nascimento do autor não pode ser nula")
        LocalDate dataNascimento,
        @NotBlank(message = "Nacionalidade do autor não pode ser vazia")
        String nacionalidade) {

    public Autor mapearParaAutor() {
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}
