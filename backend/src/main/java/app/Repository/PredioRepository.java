package app.Repository;

import app.entity.Predio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface PredioRepository extends JpaRepository<Predio, Long> {
    Optional<Predio> findByEmail(String email);
}