package app.service;

import app.Repository.ClienteRepository;
import app.Repository.UserRepository;
import app.entity.Cliente;
import app.entity.User;
import app.security.JwtServiceGenerator;
import app.security.Login;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtServiceGenerator jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ClienteRepository clienteRepository;

    public UserService(UserRepository userRepository,
                       JwtServiceGenerator jwtService,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       ClienteRepository clienteRepository) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.clienteRepository = clienteRepository;
    }

    public String logar(Login login) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword()
                )
        );
        User user = userRepository.findByEmail(login.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email not found"));
        return jwtService.generateToken(user);
    }

    public String logarUser(Login login) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword()
                )
        );
        Cliente user = clienteRepository.findByEmail(login.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email not found"));
        return jwtService.generateTokenUser(user);
    }

    public String cadastro(User usuario) throws Exception {
        Optional<User> existingUser = userRepository.findByEmail(usuario.getEmail());
        if (existingUser.isPresent()) {
            throw new Exception("Usuário já cadastrado com este email");
        }

        User user = new User();
        user.setEmail(usuario.getEmail());
        user.setPassword(passwordEncoder.encode(usuario.getPassword()));
        user.setFirstName(usuario.getFirstName());
        user.setLastName(usuario.getLastName()); // Fixed: was using firstName instead of lastName

        userRepository.save(user);

        Login login = new Login();
        login.setEmail(usuario.getEmail());
        login.setPassword(usuario.getPassword());

        return logar(login);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado com o ID: " + id);
        }
        userRepository.deleteById(id);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}