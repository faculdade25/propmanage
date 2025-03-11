package app.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import app.Repository.ApartamentoRepository;
import app.Repository.ContratoRepository;
import app.Repository.PredioRepository;
import app.Repository.UserRepository;
import app.entity.*;
import app.entity.dto.ApartamentoDTO;
import app.entity.dto.ContratoDTO;
import app.entity.dto.ContratoDTOuser;
import app.entity.dto.NovoContratoDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.service.ContratoService;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/contratos")
@CrossOrigin("*")
public class ContratoController {


	@Autowired
	private ContratoService service;

	@Autowired
	private PredioRepository predioRepository;

	@Autowired
	private ApartamentoRepository apartamentoRepository;

	@Autowired
	private ContratoRepository contratoRepository;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/save")
	public ResponseEntity<String> save (@RequestBody Contrato con){
		try {
			String message = this.service.save(con);
			return new ResponseEntity<String> (message, HttpStatus.CREATED);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("erro" + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update (@RequestParam Long id, @RequestBody Contrato con){
		try {String message = this.service.update(id, con);
		return new ResponseEntity<String> (message, HttpStatus.OK);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return new ResponseEntity<String>( "erro"+ e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete (@RequestParam Long id){
		try {String message = this.service.delete(id);
		return new ResponseEntity<String> (message, HttpStatus.OK);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return new ResponseEntity<String>( "erro"+ e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/listAll")
	public ResponseEntity<List<ContratoDTO>> listAll (){
		try {List<ContratoDTO> lista = this.service.listAll();
		return new ResponseEntity<> (lista, HttpStatus.OK);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/adm/contratos")
	public ResponseEntity<List<ContratoDTO>> listAllContratos(@AuthenticationPrincipal UserDetails userDetails) {
		if (userDetails == null) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autenticado.");
		}

		String emailAdmin = userDetails.getUsername();
		List<ContratoDTO> contratos = service.getContratosByEmailAdmin(emailAdmin);
		return ResponseEntity.ok(contratos);
	}

	@PostMapping("/create")
	public ResponseEntity<ContratoDTO> criarContrato(
			@AuthenticationPrincipal UserDetails userDetails,
			@RequestBody @Valid NovoContratoDTO novoContratoDTO) {

		String emailAdmin = userDetails.getUsername();
		return ResponseEntity.status(HttpStatus.CREATED).body(service.newContratoAdmin(emailAdmin, novoContratoDTO));
	}

	@GetMapping("/user/contratos")
	public ResponseEntity<List<ContratoDTOuser>> listarContratosUsuario(@AuthenticationPrincipal UserDetails userDetails) {
		String email = userDetails.getUsername();
		List<ContratoDTOuser> contratos = service.listarContratosUsuario(email);
		return ResponseEntity.ok(contratos);
	}

	@GetMapping("/user/contrato/atual")
	public ResponseEntity<ContratoDTOuser> obterContratoAtual(@AuthenticationPrincipal UserDetails userDetails) {
		String email = userDetails.getUsername();
		ContratoDTOuser contratoAtual = service.obterContratoAtual(email);
		return ResponseEntity.ok(contratoAtual);
	}

}
