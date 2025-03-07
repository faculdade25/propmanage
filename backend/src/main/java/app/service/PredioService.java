package app.service;

import app.Repository.ApartamentoRepository;
import app.Repository.PredioRepository;
import app.entity.Apartamento;
import app.entity.Predio;
import app.entity.dto.ApartamentoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PredioService {

    private final PredioRepository predioRepository;
    private final ApartamentoRepository apartamentoRepository;

    public List<Predio> listarTodos() {
        return predioRepository.findAll();
    }

    public Predio buscarPorId(Long id) {
        return predioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prédio não encontrado!"));
    }

    public Predio salvar(Predio predio) {
        return predioRepository.save(predio);
    }

    public void deletar(Long id) {
        predioRepository.deleteById(id);
    }

    public List<ApartamentoDTO> getApartamentosByEmailAdmin(String emailAdmin) {
        Optional<Predio> predioOptional = predioRepository.findByEmail(emailAdmin);

        if (predioOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prédio não encontrado para este administrador.");
        }

        Predio predio = predioOptional.get();
        List<Apartamento> apartamentos = apartamentoRepository.findByPredio(predio);

        // Converte para DTO
        return apartamentos.stream()
                .map(ap -> new ApartamentoDTO(ap.getId(), ap.getApnum(), ap.getStatus()))
                .collect(Collectors.toList());
    }

    public Apartamento newApartamento(Apartamento apartamento, long id) {
        Optional<Predio> predioOptional = predioRepository.findById(id);
        if (predioOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prédio não encontrado para este administrador.");
        }
        Predio predio = predioOptional.get();
        apartamento.setPredio(predio);
        predio.getApartamentos().add(apartamento);
        apartamentoRepository.save(apartamento);
        predioRepository.save(predio);
        return apartamento;
    }

    public Apartamento editApartamento(Long id, Apartamento apartamento, Long apartamentoId) {
        Optional<Predio> predioOptional = predioRepository.findById(id);
        if (predioOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prédio não encontrado para este administrador.");
        }
        Predio predio = predioOptional.get();
        apartamento.setPredio(predio);
        predio.getApartamentos().add(apartamento);
        apartamento.setId(apartamentoId);
        apartamentoRepository.save(apartamento);
        predioRepository.save(predio);
        return apartamento;
    }

    public Predio findByEmail(String email) {
        Optional<Predio> predioOptional = predioRepository.findByEmail(email);
        if (predioOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prédio não encontrado para este administrador.");
        }
        Predio predio = predioOptional.get();
        return predio;
    }
}

