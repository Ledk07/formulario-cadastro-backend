package com.api.formulario_cadastrov2.services;

import java.util.Optional;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.formulario_cadastrov2.models.FormularioCadastroModels;
import com.api.formulario_cadastrov2.repositories.FormularioCadastroRepository;

@Service
public class FormularioCadastroService {

    private final FormularioCadastroRepository formularioCadastroRepository;

    public FormularioCadastroService(FormularioCadastroRepository formularioCadastroRepository) {
        this.formularioCadastroRepository = formularioCadastroRepository;
    }

    @Transactional
    public FormularioCadastroModels save(FormularioCadastroModels formularioCadastroModel) {
        return formularioCadastroRepository.save(formularioCadastroModel);
    }

    public boolean existsByEmail(String email) {
    
        return formularioCadastroRepository.existsByEmail(email);
    }

    public @Nullable Object findAll() {
    
        return formularioCadastroRepository.findAll();
    }

    public Optional<FormularioCadastroModels> findById(UUID id) {

        return formularioCadastroRepository.findById(id);
    }

    @Transactional
    public void delete(FormularioCadastroModels formularioCadastroModels) {
        formularioCadastroRepository.delete(formularioCadastroModels);
    }

    @Transactional
    public Optional<FormularioCadastroModels> findByEmail(String email) {
    return formularioCadastroRepository.findByEmail(email);
}



}