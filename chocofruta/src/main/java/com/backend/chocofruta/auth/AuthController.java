package com.backend.chocofruta.auth;

import com.backend.chocofruta.security.JwtUtils;
import com.backend.chocofruta.repositories.UsuarioRepositories;
import com.backend.chocofruta.entities.Usuario;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserDetailsService uds;
    private final JwtUtils jwtUtils;
    private final UsuarioRepositories usuarioRepo;

    public AuthController(AuthenticationManager am,
                          UserDetailsService uds,
                          JwtUtils jwtUtils,
                          UsuarioRepositories usuarioRepo) {
        this.authManager = am;
        this.uds = uds;
        this.jwtUtils = jwtUtils;
        this.usuarioRepo = usuarioRepo;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(req.username(), req.password()));
            UserDetails user = uds.loadUserByUsername(req.username());
            Usuario u = usuarioRepo.findByUsernameIgnoreCase(user.getUsername()).orElseThrow();
            String token = jwtUtils.generateToken(user.getUsername(), u.getId(), u.getRol().name());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build();
        }
        String token = authHeader.substring(7);
        Long uid = null;
        try {
            uid = jwtUtils.extractClaim(token, claims -> claims.get("uid", Long.class));
        } catch (Exception ignored) { }
        Usuario u;
        if (uid != null) {
            u = usuarioRepo.findById(uid).orElseThrow();
        } else {
            String username;
            try {
                username = jwtUtils.getUsernameFromToken(token);
            } catch (ExpiredJwtException e) {
                username = e.getClaims().getSubject();
            }
            u = usuarioRepo.findByUsernameIgnoreCase(username).orElseThrow();
        }
        String nuevo = jwtUtils.generateToken(u.getUsername(), u.getId(), u.getRol().name());
        return ResponseEntity.ok(new AuthResponse(nuevo));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.ok().build();
    }
}
