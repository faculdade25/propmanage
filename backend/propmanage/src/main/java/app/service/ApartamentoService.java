package app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import app.Repository.PredioRepository;
import app.entity.*;
import app.entity.dto.ApartamentoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import app.Repository.ApartamentoRepository;
import app.Repository.LogRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ApartamentoService {

	private final ApartamentoRepository apartamentoRepository;
	private final LogRepository logRepository;
	private final PredioRepository predioRepository;

	public void salvarLog(String action, String tabela, long entityId) {
		Log log = new Log();
		log.setAction(action);
		log.setEntityId(entityId);
		log.setTabela(tabela);
		log.setTimestamp(LocalDateTime.now());
		logRepository.save(log);
	}

	public boolean isOcupado(Apartamento ap) {
		List<Contrato> contratos = ap.getContratos();
		return contratos != null && contratos.stream().anyMatch(Contrato::isAtivo);
	}

	public Apartamento save(Apartamento ap) {
		ap.setStatus(StatusApartamento.OCUPADO);
		Apartamento savedAp = apartamentoRepository.save(ap);
		salvarLog("save", "apartamento", savedAp.getId());
		return savedAp;
	}

	public Apartamento update(Long id, Apartamento ap) {
		if (!apartamentoRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Apartamento não encontrado");
		}
		ap.setId(id);
		Apartamento updatedAp = apartamentoRepository.save(ap);
		salvarLog("update", "apartamento", updatedAp.getId());
		return updatedAp;
	}

	public void delete(Long id) {
		if (!apartamentoRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Apartamento não encontrado");
		}
		apartamentoRepository.deleteById(id);
		salvarLog("delete", "apartamento", id);
	}

	public List<Apartamento> listAll() {
		return apartamentoRepository.findAll();
	}

	public List<ApartamentoDTO> listOccupied(String email) {
		// Buscar o prédio pelo email do administrador
		Predio predio = predioRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Prédio não encontrado"));

		// Buscar apartamentos que não estão vagos
		List<Apartamento> apartamentos = apartamentoRepository.findByPredioAndStatusNot(predio, StatusApartamento.VAGO);

		// Converter para DTO
		return apartamentos.stream()
				.map(ap -> new ApartamentoDTO(ap.getId(), ap.getApnum(), ap.getStatus()))
				.collect(Collectors.toList());
	}
}