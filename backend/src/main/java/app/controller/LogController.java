package app.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.Repository.LogRepository;
import app.entity.Log;
import app.service.LogService;

@RestController
@RequestMapping("/api/logs")
@CrossOrigin("*")
public class LogController {

	@Autowired
	private LogRepository repo;
	
	@Autowired
	private LogService service;
	
	@GetMapping
	public ResponseEntity<List<Log>> getAllLogs(){
		List<Log> logs = repo.findAll();
		return new ResponseEntity<>(logs, HttpStatus.OK);
	}
	
	@GetMapping("/findbytabela/{tabela}")	
	public ResponseEntity<List<Log>> getLogsByTabela(@PathVariable String tabela){
		List<Log> logs = repo.findByTabela(tabela);
		return new ResponseEntity<>(logs, HttpStatus.OK);
	}
	
	@GetMapping("/findbydate")
    public ResponseEntity<List<Log>> getLogsByDate(
            @RequestParam("start") LocalDateTime startDate,
            @RequestParam("end") LocalDateTime endDate) {
        List<Log> logs = repo.findByTimestampBetween(startDate, endDate);
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }
	
    @GetMapping("/findbyAction/{action}")
	public ResponseEntity<List<Log>> getLogsByAction(@PathVariable String action){
		List<Log> logs = repo.findByTabela(action);
		return new ResponseEntity<>(logs, HttpStatus.OK);
	}
	
	@GetMapping("/listAll")
	public ResponseEntity<List<Log>> listAll(){
		try {
			List<Log> lista = this.service.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
