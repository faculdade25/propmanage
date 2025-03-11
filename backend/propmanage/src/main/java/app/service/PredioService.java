package app.service;

import app.Repository.ApartamentoRepository;
import app.Repository.PredioRepository;
import app.Repository.UserRepository;
import app.entity.Apartamento;
import app.entity.Predio;
import app.entity.Role;
import app.entity.User;
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
    private final UserRepository userRepository;

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

    public ApartamentoDTO newApartamento(Apartamento apartamento, String email) {
        Optional<Predio> predioOptional = predioRepository.findByEmail(email);

        if (predioOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prédio não encontrado para este administrador.");
        }
        Predio predio = predioOptional.get();
        apartamento.setPredio(predio);
        predio.getApartamentos().add(apartamento);
        apartamento = apartamentoRepository.save(apartamento);
        predioRepository.save(predio);
        return new ApartamentoDTO(apartamento.getId(), apartamento.getApnum(), apartamento.getStatus());
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

    public ApartamentoDTO fasteditApartamento(Long id, int apnum) {
        Optional<Apartamento> predioOptional = apartamentoRepository.findById(id);
        if (predioOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prédio não encontrado para este administrador.");
        }
        Apartamento apartamento = predioOptional.get();
        apartamento.setApnum(apnum);
        apartamentoRepository.save(apartamento);
        return new ApartamentoDTO(apartamento.getId(), apartamento.getApnum(), apartamento.getStatus());
    }

    public Predio findByEmail(String email) {
        Optional<Predio> predioOptional = predioRepository.findByEmail(email);
        if (predioOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prédio não encontrado para este administrador.");
        }
        Predio predio = predioOptional.get();
        return predio;
    }

    public Long getPredioIdByInquilino(Long userId) {
        if (isAdmin(userId)) {
            String adminEmail = userRepository.findById(userId)
                    .map(User::getEmail)
                    .orElse(null);

            if (adminEmail != null) {
                return predioRepository.findPredioByEmail(adminEmail).getId();
            }
        }

        return predioRepository.findPredioByInquilinoId(userId)
                .map(Predio::getId)
                .orElse(null);
    }

    public boolean isAdmin(Long userId) {
        if(this.userRepository.findById(userId).get().getRole() == Role.ADMIN) {
            return true;
        } else {
            return false;
        }
    }
}

