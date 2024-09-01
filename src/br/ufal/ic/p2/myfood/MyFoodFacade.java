package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.models.UsuarioManager;

public class Facade {
    private Usuario.UsuarioManager usuarioManager;

    public Facade() {
        usuarioManager = new Usuario.UsuarioManager(); // Correção da criação da instância
    }

    public void zerarSistema() {
        usuarioManager.zerarSistema();
    }

    public void encerrarSistema() {
        usuarioManager.encerrarSistema();
    }

    public void criarUsuario(String nome, String email, String senha, String endereco) throws Exception {
        usuarioManager.criarUsuario(nome, email, senha, endereco);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws Exception {
        usuarioManager.criarUsuario(nome, email, senha, endereco, cpf);
    }

    public int login(String email, String senha) throws Exception {
        return usuarioManager.login(email, senha);
    }

    public String getAtributoUsuario(int id, String atributo) throws Exception {
        return usuarioManager.getAtributoUsuario(id, atributo);
    }
}
