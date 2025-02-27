package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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



@RestController
@RequestMapping("/api/apartamentos")
@CrossOrigin("*")
public class ApartamentoController {
	
	@Autowired
	private ApartamentoService service;

	@PostMapping("/save")
	public ResponseEntity<String> save (@RequestBody Apartamento ap){
		try {
			String message = this.service.save(ap);
			return new ResponseEntity<String> (message, HttpStatus.CREATED);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("erro" + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update (@RequestParam Long apnum, @RequestBody Apartamento ap){
		try {String message = this.service.update(apnum, ap);
		return new ResponseEntity<String> (message, HttpStatus.OK);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return new ResponseEntity<String>( "erro"+ e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete, {apnum}")
	public ResponseEntity<String> delete (@RequestParam Long apnum){
		try {String message = this.service.delete(apnum);
		return new ResponseEntity<String> (message, HttpStatus.OK);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return new ResponseEntity<String>( "erro"+ e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/listAll")
	public ResponseEntity<List<Apartamento>> listAll (){
		try {List<Apartamento> lista = this.service.listAll();
		return new ResponseEntity<> (lista, HttpStatus.OK);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST);
		}
	}
}
