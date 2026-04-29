package com.bortolanza.agendadortarefas.infrastructure.security;



import com.bortolanza.agendadortarefas.business.dto.UserDTO;
import com.bortolanza.agendadortarefas.infrastructure.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UserClient userClient;

    public UserDetails loadUserByUserName(String email,String token){
        UserDTO userDTO = userClient.searchUserByEmail(email,token);
        return User
                .withUsername(userDTO.getEmail()) // Define o nome de usuário como o e-mail
                .password(userDTO.getPassword()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails
    }
}
