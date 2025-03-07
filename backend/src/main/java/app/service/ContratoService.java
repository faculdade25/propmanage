package app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import app.Repository.*;
import app.entity.*;
import app.entity.dto.ContratoDTO;
import app.entity.dto.ContratoDTOuser;
import app.entity.dto.NovoContratoDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ContratoService {

	@Autowired
	private ContratoRepository crepo;
	@Autowired
	private LogRepository lrepo;

	private final ApartamentoRepository apartamentoRepository;
	private final PredioRepository predioRepository;
	private final UserRepository userRepository;
	private final UserService userService;
	public ContratoService(ApartamentoRepository apartamentoRepository, PredioRepository predioRepository, UserRepository userRepository, UserService userService) {
		this.apartamentoRepository = apartamentoRepository;
		this.predioRepository = predioRepository;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public void salvarLog(String action, String tabela, long entityid) {
		Log log = new Log();
		log.setAction(action);
		log.setEntityId(entityid);
		//log.setNome(nome);
		log.setTabela(tabela);
		log.setTimestamp(LocalDateTime.now());
		lrepo.save(log);
	}
	public String save (Contrato con) {
		this.crepo.save(con);
		salvarLog ("save", "contrato", con.getId());
		return con.getCodigoContrato()+ "contrato criado";
	}
	
	public String update (Long id, Contrato con) {
		con.setId(id);
		this.crepo.save(con);
		salvarLog ("update", "contrato", con.getId());
		return "editado com sucesso";
	}
	
	public String delete (Long id) {
		this.crepo.deleteById(id);
		salvarLog ("delete","contrato", id);
		return "contrato deletado";
	}

	public List<ContratoDTO> listAll() {
		return crepo.findAll()
				.stream()
				.map(this::toDTO2)
				.collect(Collectors.toList());
	}

	public List<ContratoDTO> getContratosByEmailAdmin(String emailAdmin) {
		Optional<Predio> predioOptional = predioRepository.findByEmail(emailAdmin);

		if (predioOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prédio não encontrado para este administrador.");
		}
		Predio predio = predioOptional.get();
		List<Apartamento> apartamentos = apartamentoRepository.findByPredio(predio);

		if (apartamentos.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum apartamento encontrado para este prédio.");
		}
		List<Contrato> contratos = crepo.findByApartamentoIn(apartamentos);
		return contratos.stream().map(ContratoDTO::new).collect(Collectors.toList());
	}

	public ContratoDTO newContratoAdmin(String emailAdmin, NovoContratoDTO novoContratoDTO) {
		Predio predio = predioRepository.findByEmail(emailAdmin)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prédio não encontrado para este administrador."));

		System.out.println(novoContratoDTO.getApnum());
		Apartamento apartamento = apartamentoRepository.findById(novoContratoDTO.getApnum())
				.filter(predio.getApartamentos()::contains)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Apartamento não encontrado ou não pertence ao prédio."));

		System.out.println(novoContratoDTO.getTitularId());
		User inquilino = userRepository.findById(novoContratoDTO.getTitularId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Titular (inquilino) não encontrado."));

		Contrato contrato = new Contrato();
		contrato.setCodigoContrato(UUID.randomUUID().toString());
		contrato.setValorCondominio(novoContratoDTO.getValorCondominio());
		contrato.setValorIptu(novoContratoDTO.getValorIptu());
		contrato.setValorInternet(novoContratoDTO.getValorInternet());
		contrato.setValorAluguel(novoContratoDTO.getValorAluguel());
		contrato.setEntrada(novoContratoDTO.getEntrada());
		contrato.setAtivo(true);
		contrato.setStatusPagamento(StatusPagamento.PENDENTE);
		contrato.setApartamento(apartamento);
		contrato.setTitular(inquilino.getNome() + " " + inquilino.getSobrenome());
		contrato.setInquilino(inquilino);
		contrato.setDataCriacao(LocalDate.now());
		contrato.setProcesso(novoContratoDTO.getProcesso());
		contrato.setReferenteAno(novoContratoDTO.getReferente());
		contrato.setDataAceite(novoContratoDTO.getDataAceite());
		contrato.setStatus(novoContratoDTO.getStatus());

		crepo.save(contrato);
		apartamento.setStatus(StatusApartamento.OCUPADO);
		this.apartamentoRepository.save(apartamento);
		return new ContratoDTO(contrato);
	}

	public List<ContratoDTOuser> listarContratosUsuario(String email) {
		User usuario = userService.getUserByEmail(email)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		return crepo.findByInquilino(usuario)
				.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}

	public ContratoDTOuser obterContratoAtual(String email) {
		User usuario = userService.getUserByEmail(email)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		Contrato contrato = crepo.findTopByInquilinoOrderByDataAceiteDesc(usuario)
				.orElseThrow(() -> new RuntimeException("Nenhum contrato encontrado"));

		return toDTO(contrato);
	}

	private ContratoDTOuser toDTO(Contrato contrato) {
		return new ContratoDTOuser(
				contrato.getDataCriacao(),
				contrato.getProcesso(),
				contrato.getReferenteAno(),
				contrato.getDataAceite(),
				contrato.getStatus()
		);
	}

	private ContratoDTO toDTO2(Contrato contrato) {
		return new ContratoDTO(
				contrato.getId(),
				contrato.getCodigoContrato(),
				contrato.getDataCriacao(),
				contrato.getTitular(),
				contrato.getApartamento().getApnum(),
				contrato.getProcesso(),
				contrato.getReferenteAno(),
				contrato.getDataAceite(),
				contrato.getStatus(),
				contrato.getValorAluguel(),
				contrato.getValorCondominio(),
				contrato.getValorIptu(),
				contrato.getValorInternet(),
				contrato.isAtivo()
		);
	}
}
