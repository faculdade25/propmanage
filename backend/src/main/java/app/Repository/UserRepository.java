package app.Repository;
import app.entity.User;
import app.entity.dto.InquilinoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT DISTINCT new app.entity.dto.InquilinoDTO(u.id, CONCAT(u.nome, ' ', u.sobrenome), u.email, u.telefone) " +
            "FROM User u " +
            "JOIN u.contratos c " +
            "JOIN c.apartamento a " +
            "WHERE a.predio.id = :predioId AND u.role = 'USER'")
    List<InquilinoDTO> findInquilinosByPredio(@Param("predioId") Long predioId);
}
