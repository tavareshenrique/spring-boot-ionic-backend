package com.henriquetavares.cursomc.services;

import com.henriquetavares.cursomc.domain.Cliente;
import com.henriquetavares.cursomc.repositories.ClienteRepository;
import com.henriquetavares.cursomc.security.UserSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cli = repo.findByEmail(email);
        if (cli == null) {
            throw new UsernameNotFoundException(email);
        }
        return new UserSpringSecurity(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
    }
}