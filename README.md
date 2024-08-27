# O Programa

### **1. Estrutura Geral do Programa**

#### **1.1. Pacotes**
- **`br.ufal.myfood`**: Pacote principal do sistema.
  - **`models`**: Contém classes de modelo que representam os dados (e.g., `Usuario`, `Empresa`, `Produto`, `Pedido`).
  - **`services`**: Contém classes de serviço que gerenciam a lógica de negócios (e.g., `UsuarioService`, `EmpresaService`, `ProdutoService`, `PedidoService`).
  - **`persistence`**: Contém classes responsáveis por persistir dados (e.g., `XMLPersistence`, `DataLoader`, `DataSaver`).
  - **`facade`**: Contém a classe `MyFoodFacade`, que serve como uma interface simplificada para o usuário.
  - **`exceptions`**: Contém classes de exceção personalizadas para o sistema (e.g., `UsuarioNaoEncontradoException`, `EmpresaNaoEncontradaException`).

---

### **2. Classes Principais**

#### **2.1. Modelo (Models)**
- **`Usuario`**
  - Atributos:
    - `String nome`
    - `String email`
    - `String senha`
    - `String endereco`
    - `String cpf`
    - `boolean isDono`
  - Métodos:
    - Getters e Setters
    - Validações

- **`Empresa`**
  - Atributos:
    - `int id`
    - `String nome`
    - `String endereco`
    - `String tipoCozinha`
    - `Usuario dono`
    - `List<Produto> produtos`
  - Métodos:
    - Getters e Setters
    - Adicionar/Remover Produto

- **`Produto`**
  - Atributos:
    - `int id`
    - `String nome`
    - `float valor`
    - `String categoria`
  - Métodos:
    - Getters e Setters

- **`Pedido`**
  - Atributos:
    - `int numero`
    - `Usuario cliente`
    - `Empresa empresa`
    - `String estado` (e.g., "aberto", "preparando", "fechado")
    - `List<Produto> produtos`
  - Métodos:
    - Getters e Setters
    - Adicionar/Remover Produto
    - Calcular Valor Total

---

#### **2.2. Serviços (Services)**
- **`UsuarioService`**
  - Responsável por:
    - Criar e gerenciar usuários.
    - Autenticação e login de usuários.
    - Validação de dados do usuário.

- **`EmpresaService`**
  - Responsável por:
    - Criar e gerenciar empresas.
    - Associar empresas aos donos.
    - Gerenciar produtos de uma empresa.

- **`ProdutoService`**
  - Responsável por:
    - Criar e editar produtos.
    - Validar nome e categoria dos produtos.

- **`PedidoService`**
  - Responsável por:
    - Criar e gerenciar pedidos.
    - Adicionar e remover produtos de um pedido.
    - Fechar pedidos e mudar seu estado.

---

#### **2.3. Persistência (Persistence)**
- **`XMLPersistence`**
  - Responsável por:
    - Salvar e carregar dados em arquivos XML.
    - Garantir a persistência dos dados entre execuções.

---

#### **2.4. Fachada (Facade)**
- **`MyFoodFacade`**
  - Exponha métodos públicos que encapsulam a lógica do sistema, facilitando a interação do usuário com o sistema.
  - Métodos como:
    - `login(String email, String senha)`
    - `criarUsuario(...)`
    - `criarEmpresa(...)`
    - `criarProduto(...)`
    - `criarPedido(...)`
    - `fecharPedido(int numero)`
    - `listarEmpresasDoUsuario(int usuarioId)`
    - `getNumeroPedido(int clienteId, int empresaId, int indice)`

---

### **3. Fluxo de Execução**
1. **Criação de Usuários**: O usuário se registra no sistema através da fachada, que delega a criação e validação do usuário ao `UsuarioService`.
   
2. **Login**: Usuários fazem login e são autenticados pelo `UsuarioService`. A sessão pode ser mantida através de uma referência ao usuário autenticado.

3. **Criação de Empresas**: Um usuário autenticado que é um dono pode criar uma empresa através da fachada, que utiliza o `EmpresaService`.

4. **Gerenciamento de Produtos**: O dono da empresa pode criar, editar e listar produtos de sua empresa, usando o `ProdutoService`.

5. **Criação de Pedidos**: Um cliente pode criar um pedido em uma empresa específica, adicionar produtos ao pedido, e finalmente fechar o pedido. O `PedidoService` gerencia toda essa lógica.

6. **Persistência**: Todos os dados criados e manipulados são persistidos no sistema usando a classe `XMLPersistence`, garantindo que os dados sejam salvos em um formato que pode ser carregado posteriormente.

---

### **4. Diagrama de Classes**
Um diagrama UML ajudaria a visualizar essas relações entre classes e seus métodos/atributos, mas como o ambiente aqui é textual, o esboço acima descreve essas relações de forma estruturada.

---

### **5. Considerações Finais**
Esse esquema cobre os principais componentes e a lógica do sistema. A partir disso, você pode detalhar cada serviço e modelo, bem como implementar as exceções e a lógica de validação necessárias para garantir a integridade dos dados e a segurança do sistema. Se precisar de mais detalhes ou uma revisão em alguma parte específica, é só avisar!



# *Organização*

Claro! Vou esboçar um esquema de pastas para o projeto e listar as classes que devem estar em cada pasta. A estrutura de pastas ajuda a organizar o código e facilita a manutenção e expansão do sistema.

### **Estrutura de Pastas e Classes**

```
myfood/
│
├── src/
│   ├── br/
│   │   ├── ufal/
│   │   │   ├── myfood/
│   │   │   │   ├── models/
│   │   │   │   │   ├── Usuario.java
│   │   │   │   │   ├── Empresa.java
│   │   │   │   │   ├── Produto.java
│   │   │   │   │   ├── Pedido.java
│   │   │   │   │
│   │   │   │   ├── services/
│   │   │   │   │   ├── UsuarioService.java
│   │   │   │   │   ├── EmpresaService.java
│   │   │   │   │   ├── ProdutoService.java
│   │   │   │   │   ├── PedidoService.java
│   │   │   │   │
│   │   │   │   ├── persistence/
│   │   │   │   │   ├── XMLPersistence.java
│   │   │   │   │   ├── DataLoader.java
│   │   │   │   │   ├── DataSaver.java
│   │   │   │   │
│   │   │   │   ├── facade/
│   │   │   │   │   ├── MyFoodFacade.java
│   │   │   │   │
│   │   │   │   ├── exceptions/
│   │   │   │   │   ├── UsuarioNaoEncontradoException.java
│   │   │   │   │   ├── EmpresaNaoEncontradaException.java
│   │   │   │   │   ├── ProdutoNaoEncontradoException.java
│   │   │   │   │   ├── PedidoNaoEncontradoException.java
│   │   │   │   │   ├── ProdutoJaCadastradoException.java
│   │   │   │   │   ├── ValorInvalidoException.java
│   │   │   │   │   ├── CategoriaInvalidaException.java
│   │   │   │   │   ├── NomeInvalidoException.java
│   │   │   │   │   ├── AtributoInvalidoException.java
│   │   │   │   │
│   │   │   │   └── utils/
│   │   │   │       ├── Validator.java
│   │   │   │       ├── Formatter.java
│   │   │   │
│   │   │   └── resources/
│   │   │       ├── data/
│   │   │       │   ├── empresa_data.xml
│   │   │       │   ├── produto_data.xml
│   │   │       │   ├── pedido_data.xml
│   │   │       │   └── usuario_data.xml
│   │   │       └── config/
│   │   │           ├── config.properties
│   │   │           └── logging.properties
│   │
└── test/
    ├── br/
    │   ├── ufal/
    │   │   ├── myfood/
    │   │   │   ├── models/
    │   │   │   │   ├── UsuarioTest.java
    │   │   │   │   ├── EmpresaTest.java
    │   │   │   │   ├── ProdutoTest.java
    │   │   │   │   └── PedidoTest.java
    │   │   │   ├── services/
    │   │   │   │   ├── UsuarioServiceTest.java
    │   │   │   │   ├── EmpresaServiceTest.java
    │   │   │   │   ├── ProdutoServiceTest.java
    │   │   │   │   └── PedidoServiceTest.java
    │   │   │   ├── persistence/
    │   │   │   │   ├── XMLPersistenceTest.java
    │   │   │   │   ├── DataLoaderTest.java
    │   │   │   │   └── DataSaverTest.java
    │   │   │   ├── facade/
    │   │   │   │   └── MyFoodFacadeTest.java
    │   │   │   └── exceptions/
    │   │   │       └── ExceptionTest.java
    │   │   └── utils/
    │   │       ├── ValidatorTest.java
    │   │       └── FormatterTest.java
    └── resources/
        ├── test_data/
        │   ├── empresa_data_test.xml
        │   ├── produto_data_test.xml
        │   ├── pedido_data_test.xml
        │   └── usuario_data_test.xml
        └── test_config/
            ├── test_config.properties
            └── test_logging.properties
```

### **Descrição das Pastas e Classes**

#### **1. `src/br/ufal/myfood/`**

- **`models/`**: Contém as classes que representam os dados principais do sistema.
  - **`Usuario.java`**: Representa um usuário do sistema.
  - **`Empresa.java`**: Representa uma empresa (restaurante).
  - **`Produto.java`**: Representa um produto oferecido por uma empresa.
  - **`Pedido.java`**: Representa um pedido realizado por um cliente.

- **`services/`**: Contém a lógica de negócios e as operações principais do sistema.
  - **`UsuarioService.java`**: Manipula e valida dados de usuários.
  - **`EmpresaService.java`**: Manipula e valida dados de empresas e seus produtos.
  - **`ProdutoService.java`**: Manipula e valida dados de produtos.
  - **`PedidoService.java`**: Manipula e valida pedidos e seus produtos.

- **`persistence/`**: Contém a lógica de persistência de dados.
  - **`XMLPersistence.java`**: Responsável por salvar e carregar dados em XML.
  - **`DataLoader.java`**: Carrega dados de arquivos XML.
  - **`DataSaver.java`**: Salva dados em arquivos XML.

- **`facade/`**: Fornece uma interface simplificada para interagir com o sistema.
  - **`MyFoodFacade.java`**: Encapsula a lógica do sistema e expõe métodos para usuários e clientes interagirem com o sistema.

- **`exceptions/`**: Contém exceções personalizadas para o sistema.
  - **`UsuarioNaoEncontradoException.java`**: Exceção para usuário não encontrado.
  - **`EmpresaNaoEncontradaException.java`**: Exceção para empresa não encontrada.
  - **`ProdutoNaoEncontradoException.java`**: Exceção para produto não encontrado.
  - **`PedidoNaoEncontradoException.java`**: Exceção para pedido não encontrado.
  - **`ProdutoJaCadastradoException.java`**: Exceção para produto já cadastrado.
  - **`ValorInvalidoException.java`**: Exceção para valor inválido.
  - **`CategoriaInvalidaException.java`**: Exceção para categoria inválida.
  - **`NomeInvalidoException.java`**: Exceção para nome inválido.
  - **`AtributoInvalidoException.java`**: Exceção para atributo inválido.

- **`utils/`**: Contém utilitários e helpers para o sistema.
  - **`Validator.java`**: Contém métodos para validação de dados.
  - **`Formatter.java`**: Contém métodos para formatação de dados.

#### **2. `resources/`**

- **`data/`**: Contém arquivos XML para persistência dos dados.
  - **`empresa_data.xml`**: Dados das empresas.
  - **`produto_data.xml`**: Dados dos produtos.
  - **`pedido_data.xml`**: Dados dos pedidos.
  - **`usuario_data.xml`**: Dados dos usuários.

- **`config/`**: Contém arquivos de configuração.
  - **`config.properties`**: Configurações gerais do sistema.
  - **`logging.properties`**: Configurações de logging.

#### **3. `test/`**

- **`br/ufal/myfood/`**: Contém testes para o sistema, estruturado de maneira semelhante ao diretório `src`.

  - **`models/`**: Testes para classes de modelo.
    - **`UsuarioTest.java`**: Testes para a classe `Usuario`.
    - **`EmpresaTest.java`**: Testes para a classe `Empresa`.
    - **`ProdutoTest.java`**: Testes para a classe `Produto`.
    - **`PedidoTest.java`**: Testes para a classe `Pedido`.



  - **`services/`**: Testes para serviços.
    - **`UsuarioServiceTest.java`**: Testes para o `UsuarioService`.
    - **`EmpresaServiceTest.java`**: Testes para o `EmpresaService`.
    - **`ProdutoServiceTest.java`**: Testes para o `ProdutoService`.
    - **`PedidoServiceTest.java`**: Testes para o `PedidoService`.

  - **`persistence/`**: Testes para a persistência de dados.
    - **`XMLPersistenceTest.java`**: Testes para `XMLPersistence`.
    - **`DataLoaderTest.java`**: Testes para `DataLoader`.
    - **`DataSaverTest.java`**: Testes para `DataSaver`.

  - **`facade/`**: Testes para a fachada.
    - **`MyFoodFacadeTest.java`**: Testes para `MyFoodFacade`.

  - **`exceptions/`**: Testes para exceções personalizadas.
    - **`ExceptionTest.java`**: Testes para exceções.

  - **`utils/`**: Testes para utilitários.
    - **`ValidatorTest.java`**: Testes para `Validator`.
    - **`FormatterTest.java`**: Testes para `Formatter`.

- **`resources/`**: Contém dados de teste e configurações.
  - **`test_data/`**: Dados de teste em XML.
    - **`empresa_data_test.xml`**: Dados de teste para empresas.
    - **`produto_data_test.xml`**: Dados de teste para produtos.
    - **`pedido_data_test.xml`**: Dados de teste para pedidos.
    - **`usuario_data_test.xml`**: Dados de teste para usuários.

  - **`test_config/`**: Configurações de teste.
    - **`test_config.properties`**: Configurações de teste.
    - **`test_logging.properties`**: Configurações de logging para testes.

Esse esquema deve fornecer uma estrutura clara para o desenvolvimento e a organização do seu projeto. Se precisar de mais detalhes ou ajustes, é só avisar!