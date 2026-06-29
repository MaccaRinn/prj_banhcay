package org.example.prj_banhcay.service;

import org.example.prj_banhcay.model.Account;
import org.example.prj_banhcay.model.UserPrincipal;
import org.example.prj_banhcay.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailService implements UserDetailsService {


    @Autowired
    private AccountRepo accountRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findByUsernameAndIsActiveTrue(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserPrincipal(account);
    }



}