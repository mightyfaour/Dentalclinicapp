package com.philipneo.dentalclinicapp.service;

import com.philipneo.dentalclinicapp.data.model.Patient;
import com.philipneo.dentalclinicapp.data.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientDetailsServiceImpl implements PatientDetailsService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public PatientDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var patient = patientRepository.findByEmail(email);
        if (patient == null) {
            throw new UsernameNotFoundException("Invalid email or password");
        }
        return new org.springframework.security.core.userdetails.User(patient.getEmail(), patient.getPassword(),
                getAuthority(patient));
    }

    private List<GrantedAuthority> getAuthority(Patient patient) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + patient.getRole()));
        return authorities;
    }

}
