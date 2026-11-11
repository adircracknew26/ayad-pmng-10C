package mx.edu.utez.backp1.repositories;

import mx.edu.utez.backp1.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
