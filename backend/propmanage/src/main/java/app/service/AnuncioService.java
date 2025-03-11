package app.service;

import java.time.LocalDateTime;
import java.util.List;

import app.Repository.PredioRepository;
import app.entity.Predio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import app.Repository.AnuncioRepository;
import app.Repository.LogRepository;
import app.entity.Anuncio;
import app.entity.Log;

@Service
public class AnuncioService {

	@Autowired
	private AnuncioRepository arepo;

	@Autowired
	private PredioRepository predioRepository;

	@Autowired
	private LogRepository lrepo;

	public void salvarLog(String action, String tabela, long entityid) {
		Log log = new Log();
		log.setAction(action);
		log.setEntityId(entityid);
		//log.setNome(nome);
		log.setTabela(tabela);
		log.setTimestamp(LocalDateTime.now());
		lrepo.save(log);
	}

	public String save(Long predioId, Anuncio anuncio) {
		Predio predio = predioRepository.findById(predioId)
				.orElseThrow(() -> new RuntimeException("Prédio não encontrado"));

		anuncio.setPredioId(predio.getId());
		arepo.save(anuncio);
		salvarLog("save", "anuncio", anuncio.getId());

		return anuncio.getTitulo() + " anúncio enviado";
	}

	public String update(Long predioId, Long id, Anuncio anuncio) {
		Anuncio existingAnuncio = arepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Anúncio não encontrado"));

		if (!existingAnuncio.getPredioId().equals(predioId)) {
			throw new RuntimeException("Este anúncio não pertence a este prédio.");
		}

		anuncio.setId(id);
		anuncio.setPredioId(existingAnuncio.getPredioId()); // Mantendo o prédio correto
		arepo.save(anuncio);
		salvarLog("update", "anuncio", anuncio.getId());

		return "Editado com sucesso";
	}

	public String delete(Long predioId, Long id) {
		Anuncio anuncio = arepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Anúncio não encontrado"));

		if (!anuncio.getPredioId().equals(predioId)) {
			throw new RuntimeException("Este anúncio não pertence a este prédio.");
		}

		arepo.delete(anuncio);
		salvarLog("delete", "anuncio", id);

		return "Anúncio deletado";
	}

	public List<Anuncio> listByPredio(Long predioId) {
		return arepo.findByPredioId(predioId);
	}
	
}
