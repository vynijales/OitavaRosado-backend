package com.oitavarosado.medico;

public record MedicoRequestDTO(String nome, String conselho_medico, String uf_conselho, String num_conselho, String cbo, String cpf, String lagradouro, String bairro, String cidade, String uf, String cep, String telefone, String email, String clinica) {
}
