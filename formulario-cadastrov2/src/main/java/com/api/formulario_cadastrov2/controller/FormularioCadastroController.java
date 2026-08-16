package com.api.formulario_cadastrov2.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.formulario_cadastrov2.dto.FormularioCadastroDto;
import com.api.formulario_cadastrov2.dto.LoginDto;
import com.api.formulario_cadastrov2.models.FormularioCadastroModels;
import com.api.formulario_cadastrov2.services.FormularioCadastroService;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/formulario-cadastro")
public class FormularioCadastroController {

    final FormularioCadastroService formularioCadastroService;

    public FormularioCadastroController(FormularioCadastroService formularioCadastroService) {
        this.formularioCadastroService = formularioCadastroService;
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarFormulario(@RequestBody @Valid FormularioCadastroDto formularioCadastroDto) {

        if (formularioCadastroService.existsByEmail(formularioCadastroDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Email já cadastrado!");
        }
        var formularioCadastroModel = new FormularioCadastroModels();
        BeanUtils.copyProperties(formularioCadastroDto, formularioCadastroModel);
        formularioCadastroModel.setDataCadastro(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(formularioCadastroService.save(formularioCadastroModel));
    }

    @GetMapping
    public ResponseEntity<Object> getAllFormulariosCadastro() {
        return ResponseEntity.status(HttpStatus.OK).body(formularioCadastroService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneFormularioCadastro(@PathVariable(value = "id") UUID id) {
        Optional<FormularioCadastroModels> formularioCadastroModelsOptional = formularioCadastroService.findById(id);
        if (!formularioCadastroModelsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Formulário de cadastro não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(formularioCadastroModelsOptional.get());
    }


    @DeleteMapping ("/{id}")
    public ResponseEntity<Object> deleteFormularioCadastro(@PathVariable(value = "id") UUID id) {
        Optional<FormularioCadastroModels> formularioCadastroModelsOptional = formularioCadastroService.findById(id);
        if (!formularioCadastroModelsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Formulário de cadastro não encontrado.");
        }
        formularioCadastroService.delete(formularioCadastroModelsOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Formulário de cadastro deletado com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateFormularioCadastro(@PathVariable(value = "id") UUID id, @RequestBody @Valid FormularioCadastroDto formularioCadastroDto) {
        Optional<FormularioCadastroModels> formularioCadastroModelsOptional = formularioCadastroService.findById(id);
        if (!formularioCadastroModelsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Formulário de cadastro não encontrado.");
        }
        var formularioCadastroModel = formularioCadastroModelsOptional.get();
        BeanUtils.copyProperties(formularioCadastroDto, formularioCadastroModel);
        return ResponseEntity.status(HttpStatus.OK).body(formularioCadastroService.save(formularioCadastroModel));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginDto loginDto) {
        Optional<FormularioCadastroModels> usuario = formularioCadastroService.findByEmail(loginDto.email());

        if (usuario.isEmpty() || !usuario.get().getSenha().equals(loginDto.senha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha incorretos.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Login realizado com sucesso!");
    }

}