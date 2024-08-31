package main.facade;

public class MyFoodFacade {
    private UsuarioManager usuarioManager;

    public MyFoodFacade() {
        usuarioManager = new UsuarioManager();
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
