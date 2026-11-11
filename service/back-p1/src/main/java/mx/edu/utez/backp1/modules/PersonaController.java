package mx.edu.utez.backp1.modules;

import mx.edu.utez.backp1.models.Persona;
import mx.edu.utez.backp1.repositories.PersonaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/people")
@CrossOrigin(origins = "*")
public class PersonaController {

    private final PersonaRepository personaRepository;

    public PersonaController(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Persona>> getPeople() {
        return ResponseEntity.ok(personaRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Persona> createPersona(@RequestBody Persona persona) {
        if (persona.getName() == null || persona.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Persona saved = personaRepository.save(new Persona(persona.getName().trim()));
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
