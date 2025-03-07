package app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Anuncio;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnuncioRepository extends JpaRepository <Anuncio, Long> {
    List<Anuncio> findByPredioId(Long predioId);
}
