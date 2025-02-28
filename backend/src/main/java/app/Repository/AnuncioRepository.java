package app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Anuncio;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioRepository extends JpaRepository <Anuncio, Long> {

}
