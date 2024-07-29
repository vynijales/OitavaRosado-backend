package com.oitava_rosado.oitava_rosado.backend.paciente;

import java.util.Date;

public record PacienteRequestDTO(String nome, String sexo, Date data_nascimento, String cpf, String rg, String orgao_emissor, String logradouro, String bairro, String cidade, String uf, String cep, String telefone, String email, String observacoes) {
}