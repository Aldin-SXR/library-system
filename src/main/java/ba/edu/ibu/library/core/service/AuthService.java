package ba.edu.ibu.library.core.service;

import ba.edu.ibu.library.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.library.core.model.User;
import ba.edu.ibu.library.core.repository.UserRepository;
import ba.edu.ibu.library.rest.dto.LoginDTO;
import ba.edu.ibu.library.rest.dto.LoginRequestDTO;
import ba.edu.ibu.library.rest.dto.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginDTO signUp(UserRequestDTO userRequestDTO) {
        userRequestDTO.setPassword(
                passwordEncoder.encode(userRequestDTO.getPassword())
        );
        userRepository.save(userRequestDTO.toEntity());
        String jwt = jwtService.generateToken(userRequestDTO.toEntity());

        return new LoginDTO(jwt);
    }

    public LoginDTO signIn(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword())
        );
        User user = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("This user does not exist."));
        String jwt = jwtService.generateToken(user);

        return new LoginDTO(jwt);
    }
}
