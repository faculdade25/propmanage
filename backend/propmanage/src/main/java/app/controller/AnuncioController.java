package app.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.Repository.AnuncioRepository;
import app.Repository.LogRepository;
import app.entity.Anuncio;
import app.entity.Log;
import app.service.AnuncioService;

@RestController
@RequestMapping("/api/anuncios")
@CrossOrigin("*")
public class AnuncioController {

	private final AnuncioService service;

	public AnuncioController(AnuncioService service) {
		this.service = service;
	}

	@PostMapping("/{predioId}/save")
	public ResponseEntity<String> save(@PathVariable Long predioId, @RequestBody Anuncio anuncio) {
		try {
			String message = service.save(predioId, anuncio);
			return ResponseEntity.status(HttpStatus.CREATED).body(message);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
		}
	}

	@PutMapping("/{predioId}/update/{id}")
	public ResponseEntity<String> update(@PathVariable Long predioId, @PathVariable Long id, @RequestBody Anuncio anuncio) {
		try {
			String message = service.update(predioId, id, anuncio);
			return ResponseEntity.ok(message);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
		}
	}

	@DeleteMapping("/{predioId}/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long predioId, @PathVariable Long id) {
		try {
			String message = service.delete(predioId, id);
			return ResponseEntity.ok(message);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
		}
	}

	@GetMapping("/{predioId}/list")
	public ResponseEntity<List<Anuncio>> listByPredio(@PathVariable Long predioId) {
		try {
			List<Anuncio> lista = service.listByPredio(predioId);
			return ResponseEntity.ok(lista);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
}
