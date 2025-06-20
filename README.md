# javalin-rest-api

Projeto de API REST em Java utilizando Javalin — Assessment de Desenvolvimento de Serviços Web e Testes

---

## Etapa 1: Desenvolvimento REST com Javalin

### 1.1 Configuração do Projeto com Gradle

Para iniciar o projeto, siga os comandos abaixo:

```bash
mkdir javalin-rest-api
cd javalin-rest-api
gradle init --type java-application
```

![build gradle 01](https://github.com/user-attachments/assets/fa32978a-9285-42ad-8811-3d2f46c7fc75)

## 1.2 Adicionando a Dependência do Javalin
"As configurações e dependências podem ser verificadas dentro do arquivo: <strong>build.gradle</strong>"
Localizada no path:

![path build gradle](https://github.com/user-attachments/assets/32ca2735-0d41-4c00-ad9b-2f3c837ca212)


<pre><code>## Projeto Javalin Este projeto Java usa o Javalin para criar uma API REST simples. ### Executar ```bash ./gradlew run ``` ### Endpoints - `GET /hello` → Retorna `"Hello, Javalin!"` - `GET /status` → Retorna JSON com `"status": "ok"` e `"timestamp"` em ISO-8601 - `POST /echo` → Envie um JSON como `{ "mensagem": "..." }` e ele retornará o mesmo - `GET /saudacao/{nome}` → Retorna `{ "mensagem": "Olá, <nome>!" }` - `POST /usuarios` → Envie um JSON com `nome`, `email`, `idade` para armazenar um usuário - `GET /usuarios` → Retorna todos os usuários cadastrados - `GET /usuarios/{email}` → Busca usuário pelo email. Se não encontrar, retorna 404 </code></pre>

![gradlew run 03](https://github.com/user-attachments/assets/aace6e4c-3400-43de-a149-9f57deed0755)

### Primeiras impressões: Demonstração de uso:

![image](https://github.com/user-attachments/assets/cb6d1d3e-1b69-49ca-aad6-b4049012a893) ![image](https://github.com/user-attachments/assets/00494b16-7598-45fc-9145-35150bfc85ff)
![image](https://github.com/user-attachments/assets/c1e34ba3-9dc9-4e50-97be-1827dc79fd51)

## Error 404
![image](https://github.com/user-attachments/assets/dead6f20-b045-48ec-a757-bd89ce0bb392)

## Executando o método POST:
![image](https://github.com/user-attachments/assets/a0a8972f-a0e9-4bd2-9053-05e13b882c39)

## Lista de usuários:
![image](https://github.com/user-attachments/assets/06444d53-8639-4d52-8cd1-eb501ba599c4)

## /tarefas
![image](https://github.com/user-attachments/assets/a596d1ef-1650-4934-8919-f209c1d434b7)
![image](https://github.com/user-attachments/assets/dc71d77e-0c56-4ca2-9b59-8cc80a75f58b)

## buscando usuário pelo e-mail:
![image](https://github.com/user-attachments/assets/0835ccd2-87e4-4a88-afbc-7fb8ac4d1c58)

