package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.excessoes.*;
import br.ufal.ic.p2.myfood.persistencia.*;
import br.ufal.ic.p2.myfood.modelo.*;
import br.ufal.ic.p2.myfood.utils.*;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Gerenciamento {

    public static AuxPersistencia<Usuario> persistenciaUsuario = new PersistenciaUsuario();
    public static AuxPersistencia<Empresa> persistenciaEmpresa = new PersistenciaEmpresa();
    public static AuxPersistencia<Produto> persistenciaProduto = new PersistenciaProduto();
    public static AuxPersistencia<Pedido> persistenciaPedido = new PersistenciaPedido();

   public Gerenciamento() {
        persistenciaUsuario.iniciar();
        persistenciaEmpresa.iniciar();
        persistenciaProduto.iniciar();
        persistenciaPedido.iniciar();

        // Carregar o ArrayList comp_list dos donos
        for (Usuario usuario : persistenciaUsuario.listar()) {
            if (usuario instanceof Dono dono) {
                List<Empresa> companiesOfUser = persistenciaEmpresa.listar()
                        .stream()
                        .filter(company -> company.getDono().getId() == dono.getId())
                        .toList();

                dono.setListaEmpresas(companiesOfUser);
            }
        }

        for (Empresa empresa : persistenciaEmpresa.listar()) {
            List<Produto> produtosDaEmpresa = persistenciaProduto.listar()
                    .stream()
                    .filter(produto -> produto.getIDono() == empresa.getId())
                    .toList();

            empresa.setListaProdutos(produtosDaEmpresa);
        }
   }

   public void zerarSistema() {
        persistenciaUsuario.limpar();
        persistenciaEmpresa.limpar();
        persistenciaProduto.limpar();
        persistenciaPedido.limpar();
   }

   public void encerrarSistema() {
   }

   public List<Pedido> pedidosClienteEmpresa(int idCliente, int idEmpresa) {
        String nomeCliente = persistenciaUsuario.buscar(idCliente).getNome();
        String nomeEmpresa = persistenciaEmpresa.buscar(idEmpresa).getNome();

        return persistenciaPedido.listar()
                .stream()
                .filter(pedido -> pedido.getCliente().getNome().equals(nomeCliente) && pedido.getEmpresa().getNome().equals(nomeEmpresa) && pedido.getEstado().equals("aberto"))
                .toList();
   }

   private void testUserInvalid(String nome, String email, String senha, String endereco) throws EnderecoInvalidoException, SenhaInvalidaException, EmailInvalidoException, NomeInvalidoException {
        if (nome == null || nome.isEmpty()) {
            throw new NomeInvalidoException("Nome invalido");
        }

        if (email == null || !(email.contains("@"))) {
            throw new EmailInvalidoException("Email invalido");
        }

        if (senha == null || senha.isEmpty()) {
            throw new SenhaInvalidaException("Senha invalido");
        }

        if (endereco == null || endereco.isEmpty()) {
            throw new EnderecoInvalidoException("Endereco invalido");
        }

   }

   public void criarUsuario(String nome, String email, String senha, String endereco) throws EmailJaCadastradoException, EmailInvalidoException, NomeInvalidoException, EnderecoInvalidoException, SenhaInvalidaException {

        testUserInvalid(nome, email, senha, endereco);

        for (Usuario user : persistenciaUsuario.listar()) {
            if (user.getEmail().equals(email)) {
                throw new EmailJaCadastradoException("Conta com esse email ja existe");
            }
        }

        Usuario cliente = new Usuario(nome, email, senha, endereco);
        persistenciaUsuario.salvar(cliente);
   }

   public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws CPFInvalidoException, EmailJaCadastradoException, EmailInvalidoException, NomeInvalidoException, EnderecoInvalidoException, SenhaInvalidaException {

        testUserInvalid(nome, email, senha, endereco);

        if (cpf == null || cpf.length() != 14) {
            throw new CPFInvalidoException("CPF invalido");
        }

        for (Usuario user : persistenciaUsuario.listar()) {
            if (user.getEmail().equals(email)) {
                throw new EmailJaCadastradoException("Conta com esse email ja existe");
            }
        }

        Dono dono = new Dono(nome, email, senha, endereco, cpf);
        persistenciaUsuario.salvar(dono);
   }

   public String getAtributoUsuario(int id, String atributo) throws UsuarioNaoCadastradoException {
        Usuario usuario = persistenciaUsuario.buscar(id);

        if (usuario == null)
            throw new UsuarioNaoCadastradoException("Usuario nao cadastrado.");

        return usuario.getAtributo(atributo);
   }

   public int login(String email, String senha) throws UsuarioNaoEncontradoException {
        for (Usuario user : persistenciaUsuario.listar()) {
            if (user.getEmail().equals(email) && user.getSenha().equals(senha)) {
                return user.getId();
            }
        }
        throw new UsuarioNaoEncontradoException();
   }

   public int criarEmpresa(String tipoEmpresa, int dono, String nome, String endereco, String tipoCozinha) throws EmpresaJaExisteException, UsuarioNaoPodeCriarEmpresaException, EnderecoInvalidoException, NomeInvalidoException {

        if (nome == null || nome.isEmpty()) {
            throw new NomeInvalidoException("Nome invalido");
        }
        if (endereco == null || endereco.isEmpty()) {
            throw new EnderecoInvalidoException("Endereco invalido");
        }

        for (Empresa empresa : persistenciaEmpresa.listar()) {
            if (empresa.getNome().equals(nome) && empresa.getDono().getId() != dono) {
                throw new EmpresaJaExisteException("Empresa com esse nome ja existe");
            }
        }

        if (!(persistenciaUsuario.buscar(dono).getClass().getSimpleName().equals("Dono"))) {
            throw new UsuarioNaoPodeCriarEmpresaException("Dono de empresa nao pode fazer um pedido");
        }

        if (tipoEmpresa.equals("restaurante")) {
            Dono tempDono = (Dono) persistenciaUsuario.buscar(dono);
            Restaurante restaurante = new Restaurante(nome, endereco, tempDono, tipoCozinha);
            persistenciaEmpresa.salvar(restaurante);
            tempDono.addListaEmpresa(restaurante);
            return restaurante.getId();
        }

        return -1;
   }

   public String getEmpresasDoUsuario(int idDono) throws UsuarioNaoPodeCriarEmpresaException {
        if (!(persistenciaUsuario.buscar(idDono).getClass().getSimpleName().equals("Dono"))) {
            throw new UsuarioNaoPodeCriarEmpresaException("Dono de empresa nao pode fazer um pedido");
        }

        Dono tempDono = (Dono) persistenciaUsuario.buscar(idDono);
        return "{" + tempDono.getListaEmpresas().toString() + "}";
   }

   public String getAtributoEmpresa(int idEmpresa, String atributo) throws EmpresaNaoCadastradaException, AtributoInvalidoException {
        Empresa tempEmpresa = persistenciaEmpresa.buscar(idEmpresa);
        if (tempEmpresa == null) {
            throw new EmpresaNaoCadastradaException("Empresa nao cadastrada");
        }

        if (atributo == null || atributo.isEmpty()) {
            throw new AtributoInvalidoException("Produto invalido");
        }

        if (Objects.equals(atributo, "dono")) {
            Usuario tempUsuario = persistenciaUsuario.buscar(tempEmpresa.getDono().getId());
            return tempUsuario.getNome();
        }

        String result = tempEmpresa.getAtributo(atributo);
        if (result == null) {
            throw new AtributoInvalidoException("Produto invalido");
        }

        return result;
   }

   public int getIdEmpresa(int idDono, String nome, int indice) throws IndiceInvalidoException, UsuarioNaoPodeCriarEmpresaException, NaoRegistradoException, NomeInvalidoException {
        if (nome == null || nome.isEmpty()) {
            throw new NomeInvalidoException("Nome invalido");
        }

        if (!(persistenciaUsuario.buscar(idDono).getClass().getSimpleName().equals("Dono"))) {
            throw new UsuarioNaoPodeCriarEmpresaException("Dono de empresa nao pode fazer um pedido");
        }

        List<Empresa> companiesOfUser = persistenciaEmpresa.listar()
                .stream()
                .filter(company -> company.getDono().getId() == idDono && company.getNome().equals(nome))
                .toList();

        for (Empresa empresa : companiesOfUser) {
            if (!empresa.getNome().equals(nome)) {
                throw new NaoRegistradoException("Nao existe empresa com esse nome");
            }
        }

        if(getIndexByNome(companiesOfUser, nome) == -1) {
            throw new NaoRegistradoException("Nao existe empresa com esse nome");
        }

        if (indice >= companiesOfUser.size()) {
            throw new IndiceInvalidoException("Indice invalido");
        } else if (indice < 0) {
            throw new IndiceInvalidoException("Indice invalido");
        }

        return companiesOfUser.get(indice).getId();
   }

   public static int getIndexByNome(List<Empresa> empresas, String nome) {
        for (int i = 0; i < empresas.size(); i++) {
            if (empresas.get(i).getNome().equals(nome)) {
                return i;  // Retorna o �ndice da primeira ocorr�ncia
            }
        }
        return -1;  // Retorna -1 se n�o encontrar
   }

   private void testProductInvalid(String nome, float valor, String categoria) throws NomeInvalidoException, ValorInvalidoException, CategoriaInvalidaException {
        if (nome == null || nome.isEmpty()) {
            throw new NomeInvalidoException("Nome invalido");
        }

        if (valor < 0) {
            throw new ValorInvalidoException("Valor invalido");
        }

        if (categoria == null || categoria.isEmpty()) {
            throw new CategoriaInvalidaException("Categoria invalido");
        }

   }

   public int criarProduto(int idEmpresa, String nome, float valor, String categoria) throws ProdutoJaExisteException, NomeInvalidoException, CategoriaInvalidaException, ValorInvalidoException {
        Empresa empresa = persistenciaEmpresa.buscar(idEmpresa);

        testProductInvalid(nome, valor, categoria);

        for (Produto produto : empresa.getListaProdutos()) {
            if (produto.getNome().equals(nome)){
                throw new ProdutoJaExisteException("Ja existe um produto com esse nome para essa empresa");
            }
        }

        Produto produto = new Produto(nome, valor, categoria, empresa.getId());
        persistenciaProduto.salvar(produto);
        empresa.addListaProdutos(produto);
        return produto.getId();
   }

   public void editarProduto(int idProduto, String nome, float valor, String categoria) throws ProdutoNaoCadastradoException, NomeInvalidoException, CategoriaInvalidaException, ValorInvalidoException {
        testProductInvalid(nome, valor, categoria);

        Produto prod = persistenciaProduto.buscar(idProduto);
        if (prod == null) {
            throw new ProdutoNaoCadastradoException("Produto nao cadastrado");
        }

        prod.setNome(nome);
        prod.setValor(valor);
        prod.setCategoria(categoria);

        persistenciaProduto.editar(prod);
   }

   public String getProduto(String nome, int idEmpresa, String atributo) throws AtributoInvalidoException, NaoRegistradoException {
        Empresa empresa = persistenciaEmpresa.buscar(idEmpresa);
        List<Produto> list = empresa.getListaProdutos();

        if (atributo == null || atributo.isEmpty()) {
            throw new AtributoInvalidoException("Produto invalido");
        }

        for (Produto prod : list) {
            if (prod.getNome().equals(nome)) {
                return switch (atributo) {
                    case "nome" -> prod.getNome();
                    case "valor" -> String.format(Locale.US, "%.2f", prod.getValor());
                    case "categoria" -> prod.getCategoria();
                    case "empresa" -> String.valueOf(empresa.getNome());
                    default -> throw new AtributoInvalidoException("Atributo nao existe");
                };
            }
        }
        throw new NaoRegistradoException("Produto nao encontrado");
   }

   public String listarProdutos(int idEmpresa) throws NaoRegistradoException {
        Empresa empresa = persistenciaEmpresa.buscar(idEmpresa);

        if (empresa == null){
            throw new NaoRegistradoException("Empresa nao encontrada");
        }

        return "{" + empresa.getListaProdutos() + "}";
   }

   public int criarPedido(int idCliente, int idEmpresa) throws PedidoVazioException, UsuarioNaoPodeCriarEmpresaException {
        Usuario temp_cliente = persistenciaUsuario.buscar(idCliente);
        List<Pedido> pedidosClienteEmpresa = pedidosClienteEmpresa(idCliente, idEmpresa);

        if (temp_cliente.getClass().getSimpleName().equals("Dono")){
            throw new UsuarioNaoPodeCriarEmpresaException("Dono de empresa nao pode fazer um pedido");
        } else if (!pedidosClienteEmpresa.isEmpty()) {
            throw new PedidoVazioException("O pedido esta vazio");
        } else {
            Empresa temp_comp = persistenciaEmpresa.buscar(idEmpresa);
            Pedido ped = new Pedido(temp_cliente, temp_comp);
            persistenciaPedido.salvar(ped);
            return ped.getNumero();
        }
   }

   public void adicionarProduto(int idPedido, int idProduto) throws NaoRegistradoException, PedidoNaoAbertoException, AlterarPedidoFechadoException {
        Pedido tempPedido = persistenciaPedido.buscar(idPedido);

        if (tempPedido == null) {
            throw new NaoRegistradoException("Nao existe pedido em aberto");
        }

        if (!(tempPedido.getEstado().equals("aberto"))){
            throw new PedidoNaoAbertoException("Nao e possivel adcionar produtos a um pedido fechado");
        }

        List<Produto> prodList = tempPedido.getEmpresa().getListaProdutos();
        for (Produto prod : prodList) {
            if (prod.getId() == idProduto) {
                tempPedido.addListaProdutos(prod);
                return;
            }
        }

        throw new NaoRegistradoException("O produto nao pertence a essa empresa");
   }

   public int getNumeroPedido(int idCliente, int idEmpresa, int indice) {
        String nomeCliente = persistenciaUsuario.buscar(idCliente).getNome();
        String nomeEmpresa = persistenciaEmpresa.buscar(idEmpresa).getNome();

        List<Pedido> pedidosClienteEmpresa = persistenciaPedido.listar()
                .stream()
                .filter(pedido -> pedido.getCliente().getNome().equals(nomeCliente) && pedido.getEmpresa().getNome().equals(nomeEmpresa))
                .toList();

        return pedidosClienteEmpresa.get(indice).getNumero();
   }

   public String getPedidos(int idPedido, String atributo) throws NaoRegistradoException, AtributoInvalidoException {
        if (atributo == null || atributo.isEmpty()) {
            throw new AtributoInvalidoException("Produto invalido");
        }

        Pedido tempPedido = persistenciaPedido.buscar(idPedido);

        if (tempPedido.getNumero() == idPedido) {
            return switch (atributo) {
                case "cliente" -> tempPedido.getCliente().getNome();
                case "empresa" -> tempPedido.getEmpresa().getNome();
                case "estado" -> tempPedido.getEstado();
                case "produtos" -> "{" + tempPedido.getListaProdutos() + "}";
                case "valor" -> String.format(Locale.US, "%.2f", tempPedido.getTotal());
                default -> throw new AtributoInvalidoException("Atributo nao existe");
            };
        }
        throw new NaoRegistradoException("Produto nao encontrado");
   }

   public void fecharPedido(int idPedido) throws NaoRegistradoException, PedidoNaoAbertoException {
        Pedido tempPedido = persistenciaPedido.buscar(idPedido);
        if (tempPedido == null) {
            throw new NaoRegistradoException("Pedido nao encontrado");
        }

        tempPedido.changeEstado();

   }

   public void removerProduto(int idPedido, String produto) throws AtributoInvalidoException, NaoRegistradoException, PedidoNaoAbertoException, AlterarPedidoFechadoException {
        if (produto == null || produto.isEmpty()) {
            throw new AtributoInvalidoException("Produto invalido");
        }

        Pedido ped = persistenciaPedido.buscar(idPedido);
        if (ped.getEstado().equals("preparando")) {
            throw new PedidoNaoAbertoException("Nao e possivel remover produtos de um pedido fechado");
        }

        List<Produto> listProd = ped.getListaProdutos();
        for (Produto prod : listProd) {
            if (prod.getNome().equals(produto)){
                ped.removerListaProdutos(prod);
                persistenciaPedido.atualizar();
                return;
            }
        }

        throw new NaoRegistradoException("Produto nao encontrado");
   }
}