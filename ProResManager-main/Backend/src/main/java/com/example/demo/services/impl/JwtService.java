package com.example.demo.services.impl;

import com.example.demo.entities.JwtRequest;
import com.example.demo.entities.JwtResponse;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class JwtService implements UserDetailsService {

    @Autowired
    private UserRepo userdDao;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest)throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName ,userPassword);
        final UserDetails userDetails = loadUserByUsername(userName);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);
        Long userid = userdDao.findByUserName(userName).getUserId();
        User user = userdDao.findById(userid).get();
        return  new JwtResponse(user , newGeneratedToken);
    }
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Long userid = userdDao.findByUserName(userName).getUserId();
        User user = userdDao.findById(userid).get();
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getUserPassword(),
                    getAuthority(user)
            );
        } else {
            throw new UsernameNotFoundException(" user not found with username :" + userName);
        }
    }

    private Set getAuthority(User user){
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        String roleName = "ROLE_" + user.getRole().getRoleName();
        authorities.add(new SimpleGrantedAuthority(roleName));
        return authorities;
    }


    private void authenticate(String userName , String userPassword)throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e){
            throw new Exception("User is desabled ");
        } catch (BadCredentialsException e){
            throw new Exception("Bad credentials from user");
        }


    }}
