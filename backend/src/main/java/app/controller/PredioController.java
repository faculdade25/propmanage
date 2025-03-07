package app.controller;

import app.entity.Apartamento;
import app.entity.Predio;
import app.entity.dto.ApartamentoDTO;
import app.service.PredioService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/predios")
@RequiredArgsConstructor
public class PredioController {

    private final PredioService predioService;

    @GetMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<List<Predio>> listarTodos() {
        return ResponseEntity.ok(predioService.listarTodos());
    }

    @GetMapping("/{id}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Predio> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(predioService.buscarPorId(id));
    }

    @PostMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<Predio> salvar(@RequestBody Predio predio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(predioService.salvar(predio));
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        predioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/apartamentos")
    @RolesAllowed("ADMIN")
    public ResponseEntity<List<ApartamentoDTO>> getApartamentosByAdminEmail(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        List<ApartamentoDTO> apartamentos = predioService.getApartamentosByEmailAdmin(email);
        return ResponseEntity.ok(apartamentos);
    }


    @PostMapping("/new/apartamento/{buldingId}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Apartamento> newApartamento(@PathVariable Long buldingId, @RequestBody Apartamento apartamento) {
        Apartamento newApartamento = predioService.newApartamento(apartamento, buldingId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newApartamento);
    }
}