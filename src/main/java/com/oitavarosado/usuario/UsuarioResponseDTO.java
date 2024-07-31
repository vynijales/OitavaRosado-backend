package com.oitavarosado.usuario;

public record UsuarioResponseDTO(Long id, String login, String password) {
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin(), usuario.getPassword());
    }
}
