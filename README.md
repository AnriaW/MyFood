# User Story 1 - Criação de contas
# Permita a um usuário criar uma conta no MyFood, O usuário poderá ser de dois tipos, Cliente e Dono de Restaurante.
#
# Os dados gerais para os usuários são:
# id -> Que deve ser do tipo int e único gerado pelo sistema.
# nome -> Informado na criação do tipo String, pode existir vários usuários com o mesmo nome.
# email -> Informado na criação do tipo String, deve ser único por usuário.
# senha -> Informada na criação do tipo String, pode existir vários usuários com a mesma senha.
#
# Os usuários do tipo cliente devem além dos dados gerais possuir os seguintes dados:
# endereco -> o endereço do usuário no tipo String, a qual as entregas são realizadas.
#
# Os usuários do tipo Dono de Restaurante devem além dos dados gerais possuir os seguintes dados:
# endereco -> o endereço do usuário no tipo String, a qual as entregas são realizadas.
# cpf -> Aceitando uma String que representa o CPF.
#
###################
# Os métodos que são utilizados nos testes podem ser vistos a seguir:
###################
# zerarSistema
# descrição: Apaga todos os dados no banco de dados do sistema.
# retorno: Sem retorno
#
# getAtributoUsuario(int:id, String nome)
# descrição: Obtém os dados de um usuário.
# retorno: Uma String com o valor do atributo.
#
# criarUsuario(String: nome, String: email, String: senha, String: endereco)
# descrição: Cria um usuário do tipo cliente.
# retorno: Sem retorno
#
# criarUsuario(String: nome, String: email, String: senha, String: endereco ,String: cpf)
# descrição:Cria um usuário do tipo dono de restaurante.
# retorno: Sem retorno
#
# login(String: email, String: senha)
# descrição: Válida se um usuário está devidamente cadastrado e se existe com os dados fornecidos
# retorno: Retorna o id do usuário.
#
# encerrarSistema
# descrição: Finaliza a execução do programa
# retorno: Sem retorno


# Apaga toda a base de dados.
zerarSistema

expectError "Usuario nao cadastrado." getAtributoUsuario id=9999 atributo="nome"

criarUsuario nome="Carlos" email="carlos@ufal.com.br" senha="123senha" endereco="Rua Exemplo N 123"

# Se a senha estiver correta, a sessão é aberta e o teste passa sem erros. 
# Se existir algum erro, o programa vai lançar uma exceção e o EasyAccept vai acusar erro nessa linha.
id1=login email="carlos@ufal.com.br" senha="123senha"

###################
# testes de todas as outras combinações importantes para o comando criarUsuario
###################

# Cada usuário está associado a um único email.
expectError "Conta com esse email ja existe" criarUsuario nome="Carlos2" email="carlos@ufal.com.br" senha="123senha" endereco="Rua Exemplo N 123"

# É possível existir mais usuários com o mesmo nome e endereço, desde que o email seja diferente.
criarUsuario nome="CarlosDono" email="carlos2@ufal.com.br" senha="123senha" endereco="Rua Exemplo N 321"  cpf="544.732.410-68"

id2=login email="carlos2@ufal.com.br" senha="123senha"

expect "Carlos" getAtributoUsuario id=${id1} atributo="nome"
expect "carlos@ufal.com.br" getAtributoUsuario id=${id1} atributo="email"
expect "Rua Exemplo N 123" getAtributoUsuario id=${id1} atributo="endereco" 

expect "CarlosDono" getAtributoUsuario id=${id2} atributo="nome"
expect "carlos2@ufal.com.br" getAtributoUsuario id=${id2} atributo="email"
expect "544.732.410-68" getAtributoUsuario id=${id2} atributo="cpf"

# Deve aceitar apenas se tiver 14 caracteres para CPF
expectError "CPF invalido" criarUsuario nome="CarlosDono" email="carlos2@ufal.com.br" senha="123senha" endereco="Rua Exemplo N 321" cpf="7300.65200.198/0001-61"
expectError "CPF invalido" criarUsuario nome="CarlosDono" email="carlos2@ufal.com.br" senha="123senha" endereco="Rua Exemplo N 321" cpf="544.732.4"

# Validando dados ao criar o usuário.
expectError "Nome invalido" criarUsuario nome=  email="carlos@ufal.com.br" senha="123senha" endereco="Rua Exemplo N 123"
expectError "Nome invalido" criarUsuario nome=""  email="carlos@ufal.com.br" senha="123senha" endereco="Rua Exemplo N 123"

expectError "Email invalido" criarUsuario nome="Carlos" email=  senha="123senha" endereco="Rua Exemplo N 123"
expectError "Email invalido" criarUsuario nome="Carlos" email=""  senha="123senha" endereco="Rua Exemplo N 123"

expectError "Senha invalido" criarUsuario nome="Carlos" email="carlos@ufal.com.br" senha=  endereco="Rua Exemplo N 123"
expectError "Senha invalido" criarUsuario nome="Carlos" email="carlos@ufal.com.br" senha=""  endereco="Rua Exemplo N 123"

expectError "Endereco invalido" criarUsuario nome="Carlos" email="carlos@ufal.com.br" senha="123senha" endereco=  
expectError "Endereco invalido" criarUsuario nome="Carlos" email="carlos@ufal.com.br" senha="123senha" endereco=""  

expectError "CPF invalido" criarUsuario nome="CarlosDono" email="carlos2@ufal.com.br" senha="123senha", endereco="Rua Exemplo N 123" cpf=  
expectError "CPF invalido" criarUsuario nome="CarlosDono" email="carlos2@ufal.com.br" senha="123senha", endereco="Rua Exemplo N 123" cpf=""  

expectError "Endereco invalido" criarUsuario nome="CarlosDono" email="carlos2@ufal.com.br" senha="123senha" endereco="" cpf="544.732.410-68"


# Valida se o email esta no formato correto.
expectError "Email invalido" criarUsuario nome="CarlosDono" email="carlos2" senha="123senha" endereco="Rua Exemplo N 321" cpf="544.732.410-68"

###################
# testes de todas as outras combinações importantes para o comando login
###################
expectError "Login ou senha invalidos" login email="carlos@ufal.com.br" senha="abc"
expectError "Login ou senha invalidos" login email="abc" senha="abc"
expectError "Login ou senha invalidos" login email=  senha="abc"
expectError "Login ou senha invalidos" login email="carlos@ufal.com.br" senha=  

encerrarSistema
quit


# User Story 1 - Criação de contas - verificacao de persistencia

id1=login email="carlos@ufal.com.br" senha="123senha"
id2=login email="carlos2@ufal.com.br" senha="123senha"

expect "Carlos" getAtributoUsuario id=${id1} atributo="nome"
expect "carlos@ufal.com.br" getAtributoUsuario id=${id1} atributo="email"
expect "123senha" getAtributoUsuario id=${id1} atributo="senha"
expect "Rua Exemplo N 123" getAtributoUsuario id=${id1} atributo="endereco"

expect "CarlosDono" getAtributoUsuario id=${id2} atributo="nome"
expect "carlos2@ufal.com.br" getAtributoUsuario id=${id2} atributo="email"
expect "123senha" getAtributoUsuario id=${id2} atributo="senha"
expect "544.732.410-68" getAtributoUsuario id=${id2} atributo="cpf"


encerrarSistema
quit

# User Story 2 - Criação de Restaurantes
# Permita que um usuário do tipo dono de empresa crie seu restaurante, mercado ou farmacia no MyFood.
#
# Os dados gerais para os restaurantes são:
# id -> Que deve ser do tipo int e único gerado pelo sistema.
# nome -> Informado na criação do tipo String, não pode existir vários restaurantes com o mesmo nome com donos diferentes.
# endereco-> Informado na criação do tipo String, pode existir vários restaurantes com o mesmo endereço.
# tipoCozinha-> Informada na criação do tipo String, pode existir vários restaurantes com o mesmo tipo.
#
###################
# Os métodos que são utilizados nos testes podem ser vistos a seguir:
###################
# zerarSistema
# descrição: Apaga todos os dados no banco de dados do sistema.
# retorno: Sem retorno
#
# criarEmpresa(String: tipoEmpresa, int dono, String: nome, String endereco, String tipoCozinha)
# descrição: Cria uma nova empresa, do tipo fornecido, no momento apenas Restaurantes são criados.
# retorno:   Retorna o id da empresa
#
# getEmpresasDoUsuario (int idDono)
# descrição: Verifica todas as empresas às quais o usuário é dono.
# retorno:   Retorna uma string contendo os nomes e endereços de todas as empresas.
#
# getIdEmpresa (int idDono, String nome, int indice)
# descrição: Verifica uma empresa a qual esteja na lista do dono.
# retorno:   Retorna o id da empresa com indice informado.
#
# getAtributoEmpresa (int empresa, String atributo)
# descrição: Obtém os dados dos atributos da empresa
# retorno:  Retorna uma string com o valor do atributo.
#
# encerrarSistema
# descrição: Finaliza a execução do programa
# retorno: Sem retorno



# Apaga toda a base de dados.
zerarSistema

criarUsuario nome="Lucas" email="lucas@ufal.com.br"  senha="123senha" endereco="Rua Exemplo N 321"

criarUsuario nome="CarlosDono" email="carlos@ufal.com.br"  senha="123senha" endereco="Rua Exemplo N 321" cpf="544.732.410-68"

criarUsuario nome="RobertoDono" email="roberto@ufal.com.br" senha="123senha" endereco="Rua Exemplo N 321" cpf="544.732.410-68"

id1=login email="lucas@ufal.com.br" senha="123senha"
id2=login email="carlos@ufal.com.br" senha="123senha"
id3=login email="roberto@ufal.com.br" senha="123senha"

###################
# testes de todas as outras combinações importantes para o comando criarEmpresa
###################

eid1=criarEmpresa tipoEmpresa="restaurante"  dono=${id2} nome="Pastelaria do Carlos" endereco="Rua Segura N 987" tipoCozinha="brasileira"

eid2=criarEmpresa tipoEmpresa="restaurante"  dono=${id2} nome="Sushi do Carlos" endereco="Rua Legal N 654" tipoCozinha="japonesa"


# Um dono diferente não pode cadastrar uma empresa com o mesmo nome de uma existente, o dono de um restaurante pode cadastrar uma nova empresa desde que seja em endereço diferente.

expectError "Empresa com esse nome ja existe" criarEmpresa tipoEmpresa="restaurante"  dono=${id3} nome="Pastelaria do Carlos" endereco="Rua Segura N 987" tipoCozinha="brasileira"

expectError "Proibido cadastrar duas empresas com o mesmo nome e local" criarEmpresa tipoEmpresa="restaurante"  dono=${id2} nome="Pastelaria do Carlos" endereco="Rua Segura N 987" tipoCozinha="brasileira"

eid4=criarEmpresa tipoEmpresa="restaurante"  dono=${id2} nome="Pastelaria do Carlos" endereco="Rua Divertida N 1973" tipoCozinha="brasileira"

# Apenas usuários do tipo dono de restaurante podem criar uma empresa.
expectError "Usuario nao pode criar uma empresa" criarEmpresa tipoEmpresa="restaurante"  dono=${id1} nome="Restaurante do Lucas" endereco="Rua Amigavel N 22" tipoCozinha="brasileira"

# Obtendo donos
expectError "Usuario nao pode criar uma empresa" getEmpresasDoUsuario idDono=${id1}
expect "{[[Pastelaria do Carlos, Rua Segura N 987], [Sushi do Carlos, Rua Legal N 654], [Pastelaria do Carlos, Rua Divertida N 1973]]}" getEmpresasDoUsuario idDono=${id2}
expect "{[]}" getEmpresasDoUsuario idDono=${id3}

# Obtendo os dados dos restaurantes. 
eid3=criarEmpresa tipoEmpresa="restaurante"  dono=${id3} nome="Sorveteria do Roberto" endereco="Rua Segura N 987" tipoCozinha="brasileira"

expect "Sorveteria do Roberto" getAtributoEmpresa empresa=${eid3} atributo="nome"
expect "Rua Segura N 987" getAtributoEmpresa empresa=${eid3} atributo="endereco"
expect "brasileira" getAtributoEmpresa empresa=${eid3} atributo="tipoCozinha"
expect "RobertoDono" getAtributoEmpresa empresa=${eid3} atributo="dono"
expectError "Atributo invalido" getAtributoEmpresa empresa=${eid3} atributo="vizinhos"
expectError "Atributo invalido" getAtributoEmpresa empresa=${eid3} atributo=""
expectError "Empresa nao cadastrada" getAtributoEmpresa empresa=9999 atributo="nome"
expectError "Empresa nao cadastrada" getAtributoEmpresa empresa=9999 atributo=""
expect "Atributo invalido" getAtributoEmpresa empresa=${eid3} atributo=  

# Obtendo o index de uma empresa. 

expect ${eid1} getIdEmpresa idDono=${id2} nome="Pastelaria do Carlos" indice=0
expect ${eid4} getIdEmpresa idDono=${id2} nome="Pastelaria do Carlos" indice=1
expect ${eid2} getIdEmpresa idDono=${id2} nome="Sushi do Carlos" indice=0
expectError "Indice maior que o esperado" getIdEmpresa idDono=${id2} nome="Pastelaria do Carlos" indice=2
expectError "Nao existe empresa com esse nome" getIdEmpresa idDono=${id2} nome="Sorveteria do Carlos" indice=0

expectError "Nome invalido" getIdEmpresa idDono=${id2} nome=  indice=2
expectError "Nome invalido" getIdEmpresa idDono=${id2} nome=""  indice=2
expectError "Indice invalido" getIdEmpresa idDono=${id2} nome="Pastelaria do Carlos" indice=-1



encerrarSistema
quit

# User Story 2 - Criação de Restaurantes - verificacao de persistencia

id1=login email="lucas@ufal.com.br" senha="123senha"
id2=login email="carlos@ufal.com.br" senha="123senha"
id3=login email="roberto@ufal.com.br" senha="123senha"

e1=getIdEmpresa idDono=${id3} nome="Sorveteria do Roberto" indice=0

expect "Sorveteria do Roberto" getAtributoEmpresa empresa=${e1} atributo="nome"
expect "Rua Segura N 987" getAtributoEmpresa empresa=${e1} atributo="endereco"
expect "brasileira" getAtributoEmpresa empresa=${e1} atributo="tipoCozinha"
expect "RobertoDono" getAtributoEmpresa empresa=${e1} atributo="dono"
expectError"Atributo invalido" getAtributoEmpresa empresa=${e1} atributo="vizinhos"

expectError "usuario nao pode criar uma empresa" getEmpresasDoUsuario idDono=${id1}
expect "{[[Pastelaria do Carlos, Rua Segura N 987], [Sushi do Carlos, Rua Legal N 654 ], [Pastelaria do Carlos, Rua Divertida N 1973]]}" getEmpresasDoUsuario idDono=${id2}
expect "{[]}" getEmpresasDoUsuario idDono=${id3}

encerrarSistema
quit

# User Story 3 - Criação de Produtos 
# Permita que um usuário do tipo dono de restaurante crie produtos para sua empresa. 
#
# Os dados gerais para os restaurantes são:
# id -> Que deve ser do tipo int e único gerado pelo sistema.
# nome -> Informado na criação do tipo String, o mesmo produto não pode ter o mesmo nome cadastrado no mesmo restaurante. 
# valor-> Informado na criação do tipo float, pode existir vários produtos com o mesmo valor.
# categoria-> Informada na criação do tipo String, pode existir vários restaurantes com a mesma categoria..
#
###################
# Os métodos que são utilizados nos testes podem ser vistos a seguir:
###################
# zerarSistema
# descrição: Apaga todos os dados no banco de dados do sistema.
# retorno: Sem retorno
#
# criarProduto(int: empresa, String: nome, float valor, String categoria)
# descrição: Cria um novo produto para uma determinada empresa.
# retorno:  Retorna o id do produto
#
# editarProduto(int: produto, String: nome, float valor, String categoria)
# descrição: Modifica os valores de um produto com id informado
# retorno:  Sem retorno
#
# getProduto(String  nome, int empresa, String atributo)
# descrição:  obtém os dados de um produto pelo id
# retorno:  retorna uma string com o valor do atributo.
#
# listarProdutos(int empresa)
# descrição:  obtém o nome de todos os produtos de uma empresa.
# retorno: retorna uma string contendo os nomes de todos os produtos existentes para aquela empresa.
#
# encerrarSistema
# descrição: Finaliza a execução do programa
# retorno: Sem retorno


# Apaga toda a base de dados.
zerarSistema

criarUsuario nome="RobertoDono" email="roberto@ufal.com.br"  senha="123senha" endereco="Rua Exemplo N 321" cpf="544.732.410-68"

id1=login email="roberto@ufal.com.br" senha="123senha"

eid1=criarEmpresa tipoEmpresa="restaurante"  dono=${id1} nome="Sorveteria do Roberto" endereco="Rua Segura N 987" tipoCozinha="brasileira"

eid2=criarEmpresa tipoEmpresa="restaurante"  dono=${id1} nome="Pastelaria do Roberto" endereco="Rua Segura N 987" tipoCozinha="brasileira"

eid3=criarEmpresa tipoEmpresa="restaurante"  dono=${id1} nome="Churrascaria do Roberto" endereco="Rua Segura N 987" tipoCozinha="brasileira"


###################
# testes de todas as outras combinações importantes para o comando criarProduto
###################

p1=criarProduto empresa=${eid1} nome="Sorvete morango" valor=1.40  categoria="alimento"

p2=criarProduto empresa=${eid1} nome="Refrigerante" valor=3.00  categoria="bebida"

expectError "Ja existe um produto com esse nome para essa empresa" criarProduto empresa=${eid1} nome="Refrigerante" valor=3.00  categoria="bebida"

p3=criarProduto empresa=${eid2} nome="Refrigerante" valor=3.00  categoria="bebida"

expectError "Nome invalido" criarProduto empresa=${eid2} nome=  valor=3.00  categoria="bebida"
expectError "Nome invalido" criarProduto empresa=${eid2} nome=""  valor=3.00  categoria="bebida"
expectError "Valor invalido" criarProduto empresa=${eid2} nome="Refrigerante" valor=-3.00  categoria="bebida"
expectError "Categoria invalido" criarProduto empresa=${eid2} nome="Refrigerante" valor=3.00  categoria=  
expectError "Categoria invalido" criarProduto empresa=${eid2} nome="Refrigerante" valor=3.00  categoria=""


###################
# testes de todas as combinações importantes para o comando editarProduto
###################

expectError "Nome invalido" editarProduto produto=${p3} nome=  valor=3.00  categoria="bebida"
expectError "Nome invalido" editarProduto produto=${p3} nome=""  valor=3.00  categoria="bebida"
expectError "Valor invalido" editarProduto produto=${p3} nome="Refrigerante" valor=-3.00  categoria="bebida"
expectError "Categoria invalido" editarProduto produto=${p3} nome="Refrigerante" valor=3.00  categoria=  
expectError "Categoria invalido" editarProduto produto=${p3} nome="Refrigerante" valor=3.00  categoria="" 

expectError "Produto nao cadastrado" editarProduto produto=9999 nome="Refrigerante" valor=3.00  categoria="bebida"

editarProduto produto=${p3} nome="Refrigerante de laranja" valor=4.40  categoria="bebida"

###################
# testes de todas as combinações importantes para o comando getProduto
###################

expect "4.40" getProduto nome="Refrigerante de laranja" empresa=${eid2} atributo="valor"
expect "bebida" getProduto nome="Refrigerante de laranja" empresa=${eid2} atributo="categoria"
expect "Pastelaria do Roberto" getProduto nome="Refrigerante de laranja" empresa=${eid2} atributo="empresa"

expectError "Produto nao encontrado" getProduto nome="Pastel" empresa=${eid2} atributo="valor"
expectError "Atributo nao existe" getProduto nome="Refrigerante de laranja" empresa=${eid2} atributo="desconto"

###################
# testes para listar os produtos da empresa.
###################

expect "{[Refrigerante de laranja]}" listarProdutos empresa=${eid2}
expect "{[Sorvete morango, Refrigerante]}" listarProdutos empresa=${eid1}
expect "{[]}" listarProdutos empresa=${eid3}

expectError "Empresa nao encontrada" listarProdutos empresa=9999

encerrarSistema
quit


# User Story 3 - Criação de Produtos  - verificacao de persistencia

id1=login email="roberto@ufal.com.br" senha="123senha"


e1=getIdEmpresa idDono=${id1} nome="Sorveteria do Roberto" indice=0
e2=getIdEmpresa idDono=${id1} nome="Pastelaria do Roberto" indice=0
e3=getIdEmpresa idDono=${id1} nome="Churrascaria do Roberto" indice=0

expect "4.40" getProduto nome="Refrigerante de laranja" empresa=${e2} atributo="valor"
expect "bebida" getProduto nome="Refrigerante de laranja" empresa=${e2} atributo="categoria"
expect "Pastelaria do Roberto" getProduto nome="Refrigerante de laranja" empresa=${e2} atributo="empresa"

expect "{[Refrigerante de laranja]}" listarProdutos empresa=${e2}
expect "{[Sorvete morango, Refrigerante]}" listarProdutos empresa=${e1}
expect "{[]}" listarProdutos empresa=${e3}

encerrarSistema
quit

# User Story 3 - Criação de Produtos  - verificacao de persistencia

id1=login email="roberto@ufal.com.br" senha="123senha"


e1=getIdEmpresa idDono=${id1} nome="Sorveteria do Roberto" indice=0
e2=getIdEmpresa idDono=${id1} nome="Pastelaria do Roberto" indice=0
e3=getIdEmpresa idDono=${id1} nome="Churrascaria do Roberto" indice=0

expect "4.40" getProduto nome="Refrigerante de laranja" empresa=${e2} atributo="valor"
expect "bebida" getProduto nome="Refrigerante de laranja" empresa=${e2} atributo="categoria"
expect "Pastelaria do Roberto" getProduto nome="Refrigerante de laranja" empresa=${e2} atributo="empresa"

expect "{[Refrigerante de laranja]}" listarProdutos empresa=${e2}
expect "{[Sorvete morango, Refrigerante]}" listarProdutos empresa=${e1}
expect "{[]}" listarProdutos empresa=${e3}

encerrarSistema
quit


# User Story 4 - Criação de Pedidos  - verificacao de persistencia

id1=login email="roberto@ufal.com.br" senha="123senha"
id2=login email="carlos@ufal.com.br" senha="123senha"

e1=getIdEmpresa idDono=${id1} nome="Sorveteria do Roberto" indice=0
e2=getIdEmpresa idDono=${id1} nome="Pastelaria do Roberto" indice=0

pe1=getNumeroPedido cliente=${id2} empresa=${e1} indice=0
pe2=getNumeroPedido cliente=${id2} empresa=${e2} indice=0
expect ${pe2} getNumeroPedido cliente=${id2} empresa=${e2} indice=0

expect "Carlos"  getPedidos pedido=${pe1} atributo="cliente"
expect "Sorveteria do Roberto"  getPedidos pedido=${pe1} atributo="empresa"
expect "preparando"  getPedidos pedido=${pe1} atributo="estado"
expect "{Sorvete morango, Sorvete chocolate, Refrigerante}" getPedidos pedido=${pe1} atributo="produtos"
expect "6.20"  getPedidos pedido=${pe1} atributo="valor"

expect "Carlos"  getPedidos pedido=${pe2} atributo="cliente"
expect "Pastelaria do Roberto"  getPedidos pedido=${pe2} atributo="empresa"
expect "aberto"  getPedidos pedido=${pe2} atributo="estado"
expect "{Pastel de queijo, Pastel de queijo}" getPedidos pedido=${pe2} atributo="produtos"
expect "10.00"  getPedidos pedido=${pe2} atributo="valor"