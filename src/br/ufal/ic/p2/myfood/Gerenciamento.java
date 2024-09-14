package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.modelo.*;
import java.util.*;

public class Gerenciamento {

    private Map<Integer, Usuario> usuarios;
    private Map<Integer, Empresa> empresas;
    private Map<Integer, Pedido> pedidos;
    private Map<Integer, Produto> produtos;
    private int proximoIdUsuario = 1;
    private int proximoIdEmpresa = 1;
    private int proximoIdPedido = 1;
    private int proximoIdProduto = 1;

    public Gerenciamento() {
        this.usuarios = new HashMap<>();
        this.empresas = new HashMap<>();
        this.pedidos = new HashMap<>();
        this.produtos = new HashMap<>();
    }

    // Método para zerar o sistema
    public void zerarSistema() {
        usuarios.clear();
        empresas.clear();
        pedidos.clear();
        produtos.clear();
        proximoIdUsuario = 1;
        proximoIdEmpresa = 1;
        proximoIdPedido = 1;
        proximoIdProduto = 1;
    }

    // Método para encerrar o sistema
    public void encerrarSistema() {
        // Implementação para encerrar o sistema, como salvar dados em arquivo
    }

    // Criar usuário
    public void criarUsuario(String nome, String email, String senha, String endereco) throws Exception {
        if (emailExistente(email)) throw new Exception("Email já cadastrado.");
        Usuario usuario = new DonoRestaurante(proximoIdUsuario++, nome, email, senha, endereco) {
            @Override
            public String getTipoUsuario() {
                return "";
            }
        };
        usuarios.put(usuario.getId(), usuario);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws Exception {
        if (emailExistente(email)) throw new Exception("Email já cadastrado.");
        Usuario usuario = new DonoRestaurante(proximoIdUsuario++, nome, email, senha, endereco) {
            @Override
            public String getTipoUsuario() {
                return "";
            }
        };
        usuarios.put(usuario.getId(), usuario);
    }

    private boolean emailExistente(String email) {
        return usuarios.values().stream().anyMatch(u -> u.getEmail().equals(email));
    }

    // Login de usuário
    public int login(String email, String senha) throws Exception {
        return usuarios.values().stream()
                .filter(u -> u.getEmail().equals(email) && u.getSenha().equals(senha))
                .findFirst()
                .orElseThrow(() -> new Exception("Usuário ou senha inválidos."))
                .getId();
    }

    // Obter atributo de usuário
    public String getAtributoUsuario(int id, String nome) throws Exception {
        Usuario usuario = usuarios.get(id);
        if (usuario == null) throw new Exception("Usuário não encontrado.");
        return usuario.getNome();
    }

    // Criar empresa
    public void criarEmpresa(String nome, String endereco) throws Exception {
        Usuario dono = usuarios.getId();
        if (dono == null) throw new Exception("Dono não encontrado.");
        Empresa empresa = new Empresa(proximoIdEmpresa++, nome, endereco, donoId);
        empresas.put(empresa.getId(), empresa);
    }

    // Obter empresas do usuário
    public List<Empresa> getEmpresasDoUsuario(int donoId) {
        List<Empresa> empresasDoUsuario = new ArrayList<>();
        for (Empresa empresa : empresas.values()) {
            if (empresa.getDono() == donoId) {
                empresasDoUsuario.add(empresa);
            }
        }
        return empresasDoUsuario;
    }

    // Criar produto
    public int criarProduto(int empresaId, String nome, double preco, String categoria) throws Exception {
        Empresa empresa = empresas.get(empresaId);
        if (empresa == null) throw new Exception("Empresa não encontrada.");
        Produto produto = new Produto(proximoIdProduto++, nome, preco, empresa);
        produtos.put(produto.getId(), produto);
        return empresaId;
    }

    // Editar produto
    public void editarProduto(int produtoId, String nome, String preco, String categoria) throws Exception {
        Produto produto = produtos.get(produtoId);
        if (produto == null) throw new Exception("Produto nao cadastrado");
        if (nome == null || nome.isEmpty()) { throw new Exception("Nome invalido"); }
        if (preco == null || preco.isEmpty()) { throw new Exception("Valor invalido"); }
        if (categoria == null || categoria.isEmpty()) { throw new Exception("Categoria invalido"); } //invalidA
        produto.setNome(nome);
        produto.setValor(produto.getValor());
        produto.setValor(Float.parseFloat(categoria));
    }

    // Listar produtos de uma empresa
    public List<String> listarProdutos(int empresaId) throws Exception {
        Empresa empresa = empresas.get(empresaId);
        if (empresa == null) throw new Exception("Empresa nao encontrada");

        List<String> nomesProduto = new ArrayList<>();
        for (Produto produto : empresa.getProdutos()) {
            nomesProduto.add(produto.getNome());
        }

        return nomesProduto; // TODO: conferir output
    }

    // Criar pedido
    public int criarPedido(int clienteId, int empresaId) throws Exception {
        Empresa empresa = empresas.get(empresaId);
        Cliente cliente = (Cliente) usuarios.get(clienteId); //TODO: atenção pra ver se vai dar certo

        if (cliente == null || empresa == null) throw new Exception("Cliente ou empresa nao encontrados");

        String nomeCliente = cliente.getNome();
        String nomeEmpresa = empresa.getNome();

        Pedido pedido = new Pedido(nomeCliente, nomeEmpresa);
        pedidos.put(pedido.getNumero(), pedido);

        empresa.getClientes().add(cliente);
        // TODO: empresa deveria ter pedidos???

        return pedido.getNumero();
    }

    // Adicionar produto ao pedido
    public void adicionarProduto(int pedidoNumero, int produtoId) throws Exception {
        Pedido pedido = pedidos.get(pedidoNumero);
        Produto produto = produtos.get(produtoId);
        if (pedido == null || produto == null) throw new Exception("Pedido ou produto não encontrado.");
        pedido.adicionarProduto(produto);
    }

    // Fechar pedido
    public void fecharPedido(int pedidoNumero) throws Exception {
        Pedido pedido = pedidos.get(pedidoNumero);
        if (pedido == null) throw new Exception("Pedido não encontrado.");
        pedido.fechar();
    }

    // Remover produto do pedido
    public void removerProduto(int pedidoNumero, String nomeProduto) throws Exception {
        Pedido pedido = pedidos.get(pedidoNumero);
        if (pedido == null) throw new Exception("Pedido não encontrado.");
        pedido.removerProduto(nomeProduto);
    }

    // Obter número de pedido do cliente na empresa
    public int getNumeroPedido(int idCliente, Empresa empresa, int indice) throws Exception {
        List<Pedido> pedidosCliente = new ArrayList<>();
        for (Pedido pedido : pedidos.values()) {
            if ((pedido.getCliente().equals("cliente")) && (pedido.getEmpresa().getChars() == empresa)) {
                pedidosCliente.add(pedido);
            }
        }
        if (indice < 0 || indice >= pedidosCliente.size()) {
            throw new Exception("Índice fora de alcance.");
        }
        return pedidosCliente.get(indice).getNumero();
    }

    public String getAtributoEmpresa(int id, String atributo) {
        return atributo;
    }

    public String getPedidos(int numero, String atributo) {
        return atributo;
    }

    public String getProduto(String nome, int empresaId, String atributo) throws Exception {
        Empresa empresa = empresas.get(empresaId);

        if (empresa == null) {
            throw new Exception("Empresa nao existe");
        }

        if (atributo.equals("empresa")) { // Terceiro caso de teste
            return empresa.getNome();
        }

        Produto produto = empresa.getProdutoEspecifico(nome);

        if (produto == null) {
            return "Produto nao encontrado";
        }

        if (atributo.equals("categoria")) {
            return produto.getCategoria();
        }

        if (atributo.equals("valor")) {
            return produto.getValor();
        }

        return "Atributo nao existe";
    }

}
