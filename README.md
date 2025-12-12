# StartDB-ReciclaAlegre  - [Acesso a Wiki](https://github.com/MathPLopes/StartDB-ReciclaAlegre/wiki)
# :recycle: Projeto Recicla Alegre :recycle:
O ReciclAlegre é uma aplicação web criada para aproximar geradores de resíduos e coletores, tornando o descarte de recicláveis mais simples, acessível e sustentável.
O Projeto em sua release atual conta com funcionalidades basicas de cadastro de usuário e solicitações de coleta.  
Versão 2.0.0

## :floppy_disk: Tecnologias Adotadas

- **Java Versão 17**: Backend
- **Spring Boot**: Backend
- **React**: Frontend
- **Typescript**: Frontend
- **MySql**: Persistência de dados em produção
- **H2**: Banco de testes em memoria


## :wrench: Requisitos
Antes de rodar o projeto, verifique se você tem as seguintes ferramentas instaladas:

- [JDK](https://www.oracle.com/java/technologies/downloads/) - Versão LTS mais estável.
- [Maven](https://maven.apache.org/download.cgi) - Apache Maven versão 3.9.11
- [Node.js](https://nodejs.org/en/download) - Node.js Versão mais recente
- [VSCode](https://code.visualstudio.com/Download) - Visual Studio Code ou qualquer outra IDE de sua preferência com suporte a desenvolvimento Java.
- [MySql](https://www.mysql.com/downloads/) - Caso queira usar o Banco de Dados do projeto, ou pode utilizar o H2 com o perfil de teste.
- [Git](https://help.github.com/articles/set-up-git) - Alguma ferramenta de linha de comando para uso do Git

#### Extensões recomendadas no VScode
![ExtensionPackForJava](https://i.ibb.co/QvBjPtYG/Extension-Pack-For-Java.png) 
![SpringBootExtensionPack](https://i.ibb.co/d41VJZ7W/Spring-Boot-Extension-Pack.png)


## :arrow_forward: Rodando a aplicação localmente

1. **Clone o Repositório:**
Em um terminal aberto no seu diretorio de escolha:
```bash
git clone https://github.com/MathPLopes/StartDB-ReciclaAlegre.git
cd StartDB-ReciclaAlegre
```
2. **Rodando o Backend utilizando Maven:**
Vá para o diretorio da aplicação backend:
```bash
cd backend/reciclaalegre
mvn spring-boot:run
```
Parando o backend:
Digite **ctrl + c** no seu terminal para encerrar a execução.

3. **Rodando o Frontend com npm:**
Abra um novo terminal e vá até o diretorio da aplicação frontend:
Iniciando da pasta StartDB-ReciclaAlegre
```bash
cd frontend
npm install // Usar na primeira vez para instalar dependências
npm run dev
```
Parando o frontend:
Digite **ctrl + c** no seu terminal para encerrar a execução.

4. **Acessando a aplicação**:
Você pode agora consultar a documentação pela Swagger UI, referido na proxima seção.
Também pode acessar e testar o front pelo endereço: http://localhost:5173

## :file_folder: Banco de Dados
Por padrão utilizamos H2 Database em memoria para testes, populado na inicialização com amostras de teste.

#### Bancos Suportados
Recicla Alegre suporta:
- **H2 Database (Para testes, Em memoria)**
- **MySql (Persistência de Dados da aplicação)**

#### Alternando os bancos de dados
Você pode mudar o perfil do banco de dados modificando a propriedade ```spring.profiles.active``` em ```application.properties```:

|Banco de Dados          | Configuração de Perfil             |
|------------------------|------------------------------------|
|**H2** (Testes)         |```spring.profiles.active=test```   |
|**MySql** (Persistência)|```spring.profiles.active=homolog```| 

Caso precise de maiores detalhes sobre como configurar as propriedades de aplicação, consulte a [Documentação do Spring Boot](https://docs.spring.io/spring-boot/how-to/properties-and-configuration.html) .

#### Usando e acessando o H2
- Por padrão deve vir o perfil de testes, caso seja necessário confirme no ```application.properties```.
- Você pode acessar o console do H2 com o backend rodando seguindo os passos:
  - **URL no browser**: http://localhost:8080/h2-console
  - **JDBC URL**: ```jdbc:h2:mem:test```
  - **Username**: sa
  - **Password**: deixar em branco

![H2Console](https://i.ibb.co/TxBqypcY/h2console.png)

#### Usando e acessando o MySql
- Mofique o perfil no arquivo ```application.properties``` para o MySql (homolog)
- Rode o banco de dados MySql


## :memo: Funcionalidades e Documentação de API

O projeto utiliza uma API RESTful para o cadastro de usuários e intermediação entre geradores de resíduos e coletores através de solicitações de coleta.

#### Acessando o Swagger
Você pode acessar a interface do Swagger pelo endereço: http://localhost:8080/swagger-ui/index.html


| Metodos        | Endpoint                               | Descrição                              |
|----------------|----------------------------------------|----------------------------------------|
| **Cadastro**   |                                        |                                        |
| **POST**       | ```/api/usuarios ```                   |Cadastro de usuário                     |
| **GET**        | ```/api/usuarios ```                   |Listagem de usuários                    |
| **GET**        | ```/api/usuarios/perfil ```            |Acesso ao perfil do usuário             |
| **DELETE**     | ```/api/usuarios ```                   |Desabilitar conta de usuário            |
| **Auth**       |                                        |                                        |
| **POST**       | ```/api/auth```                        |Login e autenticação de usuário         |
| **Solicitacao**|                                        |                                        |
| **POST**       | ```/api/solicitacoes```                |Criar solicitação de coleta             |
| **POST**       | ```/api/solicitacoes/requisitar/{id}```|Requisitar participação em coleta       |
| **GET**        | ```/api/solicitacoes```                |Listar solicitações ativas na plataforma|
| **GET**        | ```/api/solicitacoes/usuario```        |Listar solicitações do próprio usuário  |
| **PUT**        | ```/api/solicitacoes/atualizar/{id}``` |Editar uma solicitação                  |


## :warning: Bugs ou Sugestões
Caso deseje reportar um bug ou fazer uma sugestão para o projeto, nossa pagina de issues está disponível: https://github.com/MathPLopes/StartDB-ReciclaAlegre/issues

## :books: Onde Encontrar mais documentação

|Acesso                                                                                    |Conteudo                               |
|------------------------------------------------------------------------------------------|---------------------------------------|
|[Processo](https://github.com/MathPLopes/StartDB-ReciclaAlegre/wiki/processo.md)          |Processo inicial de desenvolvimento    | 
|[Colaboradores](https://github.com/MathPLopes/StartDB-ReciclaAlegre/wiki/colaboradores.md)|Pagina sobre os membros do grupo       |
|[Desafios](https://github.com/MathPLopes/StartDB-ReciclaAlegre/wiki/desafios.md)          |Desafios e Aprendizados                |
|[Backend](https://github.com/MathPLopes/StartDB-ReciclaAlegre/wiki/backend.md)            |Informações tecnicas extras do backend |
|[Referências](https://github.com/MathPLopes/StartDB-ReciclaAlegre/wiki/referencias.md)    |Pagina com referências e agradecimentos|
|[Testes](https://github.com/MathPLopes/StartDB-ReciclaAlegre/wiki/testes.md)              |Pagina sobre testes                    |

---

## :bookmark_tabs: Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
