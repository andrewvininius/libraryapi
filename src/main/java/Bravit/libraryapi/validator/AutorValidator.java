package Bravit.libraryapi.validator;

import Bravit.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Component;

@Component
public class AutorValidator {
    private AutorRepository repository;

    public AutorValidator(AutorRepository repository) {
        this.repository = repository;
    }
}
