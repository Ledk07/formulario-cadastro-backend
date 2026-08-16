package com.api.formulario_cadastrov2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.formulario_cadastrov2.models.FormularioCadastroModels;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FormularioCadastroRepository extends JpaRepository<FormularioCadastroModels, UUID> {

    boolean existsByEmail(String email);

    Optional<FormularioCadastroModels> findByEmail(String email);


}




