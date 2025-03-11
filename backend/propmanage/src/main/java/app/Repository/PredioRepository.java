package app.Repository;

import app.entity.Predio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface PredioRepository extends JpaRepository<Predio, Long> {
    Optional<Predio> findByEmail(String email);

    @Query("SELECT c.apartamento.predio FROM Contrato c WHERE c.inquilino.id = :userId")
    Optional<Predio> findPredioByInquilinoId(@Param("userId") Long userId);
    
    Predio findPredioByEmail(String email);

}