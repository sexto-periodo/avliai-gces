package com.ti.avaliai.academicmail;


import com.ti.avaliai.global.domain.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AcademicEmailService {

    @Autowired
    private AcademicMailRepository academicMailRepository;

    public void create(AcademicEmail academicEMail){
        academicMailRepository.save(academicEMail);
    }

    public AcademicEmail findByDomain(String domain){
        return academicMailRepository.findByDomain(domain)
                .orElseThrow(() -> new EntityNotFoundException("Dominio de E-mail acadêmico não encontrado", HttpStatus.NOT_FOUND));
    }

    public AcademicEmail findByHashId(String hashId){
        return academicMailRepository.findByHashId(hashId)
                .orElseThrow(() -> new EntityNotFoundException("Dominio de E-mail acadêmico não encontrado", HttpStatus.NOT_FOUND));
    }

    public boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        int atIndex = email.indexOf('@');
        if (atIndex <= 0 || atIndex == email.length() - 1) {
            return false;
        }

        String domain = email.substring(atIndex + 1);
        List<String> acceptedDomains = getValidAcceptedDomains();
        return acceptedDomains.contains(domain);
    }

    private List<String> getValidAcceptedDomains(){
        List<AcademicEmail> domains = academicMailRepository.getAllByValidTrue();
        List<String> validDomains = domains.stream().map(AcademicEmail::getDomain).collect(Collectors.toList());
        return validDomains;
    }

    public AcademicEmail save(AcademicEmail academicEmail) {
        return academicMailRepository.save(academicEmail);
    }
}
