package app.Repository;

import app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Cliente;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long> {

    Optional<Cliente> findByEmail(String email);

}
