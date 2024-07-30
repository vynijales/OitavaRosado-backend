package com.oitavarosado.paciente;

import java.util.Date;

public record PacienteResponseDTO(Long id, String nome, String sexo, Date data_nascimento, String cpf, String rg, String orgao_emissor, String logradouro, String bairro, String cidade, String uf, String cep, String telefone, String email, String observacoes) {
    public PacienteResponseDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getSexo(), paciente.getData_nascimento(), paciente.getCpf(), paciente.getRg(), paciente.getOrgao_emissor(), paciente.getLogradouro(), paciente.getBairro(), paciente.getCidade(), paciente.getUf(), paciente.getCep(), paciente.getTelefone(), paciente.getEmail(), paciente.getObservacoes());
    }
}