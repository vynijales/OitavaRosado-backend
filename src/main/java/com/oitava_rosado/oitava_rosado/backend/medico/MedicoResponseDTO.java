package com.oitava_rosado.oitava_rosado.backend.medico;

public record MedicoResponseDTO(Long id, String nome, String conselho_medico, String uf_conselho, String num_conselho, String cbo, String cpf, String lagradouro, String bairro, String cidade, String uf, String cep, String telefone, String email) {
    public MedicoResponseDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getConselho_medico(), medico.getUf_conselho(), medico.getNum_conselho(), medico.getCbo(), medico.getCpf(), medico.getLagradouro(), medico.getBairro(), medico.getCidade(), medico.getUf(), medico.getCep(), medico.getTelefone(), medico.getEmail());

    }
}