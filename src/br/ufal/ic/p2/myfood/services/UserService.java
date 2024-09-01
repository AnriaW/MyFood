package br.ufal.ic.p2.myfood.service;

import br.ufal.ic.p2.myfood.model.DonoDeRestaurante;
import br.ufal.ic.p2.myfood.model.User;
import br.ufal.ic.p2.myfood.utils.XMLUtils;
import br.ufal.ic.p2.myfood.exceptions.*;

import java.util.List;

public class UserService {
    private List<User> users;

    public UserService() {
        this.users = XMLUtils.loadUsersFromXML();
    }

    public void resetSystem() {
        users.clear();
        XMLUtils.saveUsersToXML(users);
    }

    public void createCliente(String nome, String email, String senha, String endereco) throws Exception {
        validateUserInputs(nome, email, senha, endereco, null);
        Cliente cliente = new Cliente(nome.trim(), email.trim(), senha, endereco.trim());
        users.add(cliente);
        XMLUtils.saveUsersToXML(users);
    }

    public void createDonoDeRestaurante(String nome, String email, String senha, String endereco, String cpf) throws Exception {
        validateUserInputs(nome, email, senha, endereco, cpf);
        DonoDeRestaurante dono = new DonoDeRestaurante(nome.trim(), email.trim(), senha, endereco.trim(), cpf.trim());
        users.add(dono);
        XMLUtils.saveUsersToXML(users);
    }

    public int login(String email, String senha) throws Exception {
        for (User user : users) {
            if (user.getEmail().equals(email.trim()) && user.getSenha().equals(senha)) {
                return user.getId();
            }
        }
        throw new LoginInvalidException("Login ou senha inválidos");
    }

    public String getUserAttribute(int id, String atributo) throws Exception {
        for (User user : users) {
            if (user.getId() == id) {
                return getAttributeFromUser(user, atributo);
            }
        }
        throw new UserNotFoundException("Usuário não cadastrado.");
    }

    private String getAttributeFromUser(User user, String atributo) throws Exception {
        switch (atributo) {
            case "nome": return user.getNome();
            case "email": return user.getEmail();
            case "senha": return user.getSenha();
            case "endereco": return user.getEndereco();
            case "cpf":
                if (user instanceof DonoDeRestaurante) {
                    return ((DonoDeRestaurante) user).getCpf();
                }
                break;
        }
        throw new IllegalArgumentException("Atributo inválido");
    }

    private void validateUserInputs(String nome, String email, String senha, String endereco, String cpf) throws Exception {
        if (nome == null || nome.trim().isEmpty()) throw new InvalidNameException("Nome inválido");
        if (email == null || !email.contains("@") || email.trim().isEmpty()) throw new InvalidEmailException("Email inválido");
        if (senha == null || senha.trim().isEmpty()) throw new InvalidPasswordException("Senha inválida");
        if (endereco == null || endereco.trim().isEmpty()) throw new InvalidAddressException("Endereço inválido");
        if (cpf != null && (cpf.length() != 14)) throw new InvalidCPFException("CPF inválido");

        checkForDuplicateEmail(email);
    }

    private void checkForDuplicateEmail(String email) throws DuplicateEmailException {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                throw new DuplicateEmailException("Conta com esse email já existe");
            }
        }
    }
}
