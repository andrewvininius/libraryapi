package Bravit.libraryapi;

import Bravit.libraryapi.model.Autor;
import Bravit.libraryapi.repository.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;

@SpringBootApplication
@EnableJpaAuditing
public class LibraryapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryapiApplication.class, args);

//		var context = SpringApplication.run(LibraryapiApplication.class, args);
//		AutorRepository repository = context.getBean(AutorRepository.class);
//		exemploSalvarRegistro(repository);
	}

//	public static void exemploSalvarRegistro(AutorRepository autorRepository){
//		Autor autor = new Autor();
//		autor.setNome("Jose");
//		autor.setNacionalidade("Brasileiro");
//		autor.setDataNascimento(LocalDate.of(1950, 1, 31));
//
//		/*
//		Autor autorSalve = autorRepository.save(autor);
//		var autorSalve = autorRepository.save(autor);
//		é a mesma coisa. A diferença é só na forma de declarar a variável.
//		 */
//		var autorSalve = autorRepository.save(autor);
//		//Autor autorSalve = autorRepository.save(autor);
//		System.out.println("Autor Salvo: " + autorSalve);
//	}

}
