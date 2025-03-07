package app.controller;

import app.entity.User;
import app.entity.dto.InquilinoDTO;
import app.entity.dto.InquilinoRequestDTO;
import app.security.Login;
import app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public String getUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return "User email: " + authentication.getName(); // Normalmente, o email está aqui
        }
        return "No authenticated user";
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) {
        return ResponseEntity.ok(userService.logar(login));
    }

    @PostMapping("/register")
    public ResponseEntity<String> cadastro(@RequestBody User usuario) {
        try {
            String token = userService.cadastro(usuario);
            return ResponseEntity.ok(token);
        } catch (AuthenticationException ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getUser() {
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/inquilinos")
    public ResponseEntity<List<InquilinoDTO>> listarInquilinos(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        List<InquilinoDTO> inquilinos = userService.listarInquilinos(email);
        return ResponseEntity.ok(inquilinos);
    }

    @PostMapping("/new/inquilinos")
    public ResponseEntity<User> criarInquilino(
            @AuthenticationPrincipal User admin,
            @RequestBody @Valid InquilinoRequestDTO dto) {
        User novoInquilino = userService.criarInquilino(admin.getId(), dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoInquilino);
    }

    // Atualizar um inquilino existente
    @PutMapping("/inquilinos/{inquilinoId}")
    public ResponseEntity<User> atualizarInquilino(
            @AuthenticationPrincipal User admin,
            @PathVariable Long inquilinoId,
            @RequestBody @Valid InquilinoRequestDTO dto) {
        User inquilinoAtualizado = userService.atualizarInquilino(admin.getId(), inquilinoId, dto);
        return ResponseEntity.ok(inquilinoAtualizado);
    }


}
