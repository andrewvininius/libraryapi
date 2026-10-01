package Bravit.libraryapi.validator;

import Bravit.libraryapi.exceptions.RegistroDuplicadoException;
import Bravit.libraryapi.model.Autor;
import Bravit.libraryapi.repository.AutorRepository;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class AutorValidator {

    private AutorRepository repository;

    public AutorValidator(AutorRepository repository) {
        this.repository = repository;
    }
    public void validar(Autor autor) {
        if (existeAutorCadastrado(autor)) {
            throw new RegistroDuplicadoException("Autor já cadastrado");
        }
    }
    private boolean existeAutorCadastrado(Autor autor){

        Optional<Autor> autorEncontrado =
                repository.findByNomeAndDataNascimentoAndNacionalidade(
                        autor.getNome(),
                        autor.getDataNascimento(),
                        autor.getNacionalidade());

        System.out.println("ID do autor: " + autor.getId());
        System.out.println("Autor encontrado: " + autorEncontrado);

        if (autor.getId() == null) {
            return autorEncontrado.isPresent();
        }

        return autorEncontrado.isPresent()
                && !autor.getId().equals(autorEncontrado.get().getId());
    }
}
