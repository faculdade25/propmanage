package app.controller;

import java.util.List;

import app.entity.dto.ApartamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Anuncio;
import app.entity.Apartamento;
import app.service.ApartamentoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/apartamento")
public class ApartamentoController {

	private final ApartamentoService service;

	public ApartamentoController(ApartamentoService service) {
		this.service = service;
	}

	@PostMapping("/save")
	public ResponseEntity<Apartamento> save(@RequestBody Apartamento ap) {
		try {
			Apartamento savedAp = this.service.save(ap);
			return new ResponseEntity<>(savedAp, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Apartamento> update(@PathVariable Long id, @RequestBody Apartamento ap) {
		try {
			Apartamento updatedAp = this.service.update(id, ap);
			return new ResponseEntity<>(updatedAp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		try {
			this.service.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/listAll")
	public ResponseEntity<List<Apartamento>> listAll() {
		try {
			return ResponseEntity.ok(this.service.listAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/get")
	public ResponseEntity<List<ApartamentoDTO>> getAllRooms() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication == null || !authentication.isAuthenticated()) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
			List<ApartamentoDTO> lista = this.service.listOccupied(authentication.getName());
			return ResponseEntity.ok(lista);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}