# Desafio-Caju

## Estrutura
```
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───caju
│   │   │           └───desafio
│   │   │               │   DesafioApplication.java
│   │   │               │
│   │   │               ├───api
│   │   │               │   │   AuthorizationTransactionController.java
│   │   │               │   │
│   │   │               │   └───dto
│   │   │               │           PutRequestTransaction.java
│   │   │               │
│   │   │               ├───config
│   │   │               │       GlobalExceptionHandler.java
│   │   │               │
│   │   │               ├───dataprovider
│   │   │               │   ├───entity
│   │   │               │   │       AccountEntity.java
│   │   │               │   │       MerchantEntity.java
│   │   │               │   │
│   │   │               │   └───repository
│   │   │               │           AccountRepository.java
│   │   │               │           MerchantRepository.java
│   │   │               │
│   │   │               ├───domain
│   │   │               │       Account.java
│   │   │               │       MccType.java
│   │   │               │       MerchantService.java
│   │   │               │       Transaction.java
│   │   │               │       TransactionResponse.java
│   │   │               │       TransactionService.java
│   │   │               │       TransactionStatus.java
│   │   │               │
│   │   │               ├───mapper
│   │   │               │       AccountMapper.java
│   │   │               │       TransactionMapper.java
│   │   │               │
│   │   │               └───service
│   │   │                       AuthorizationTransactionService.java
│   │   │                       CachedMerchantService.java
│   │   │
│   │   └───resources
│   │           application.properties
│   │           data.sql
```

## Como usar

1. Entre na pasta do projeto:

    ```sh
    cd /caminho/para/seu/projeto
    ```

2. Compile e construa o projeto:

    ```sh
    mvn clean install
    ```

3. Inicie a aplicação:

    ```sh
    mvn spring-boot:run
    ```

4. Acesse o endpoint de transação via PUT:

    ```
    PUT http://localhost:8080/
    ```

   **Payload:**

    ```json
    {
        "account": "1",
        "totalAmount": 100.00,
        "mcc": 5411,
        "merchant": "UBER EATS                   SAO PAULO BR"
    }
    ```

## Inicialização da Aplicação

Ao iniciar a aplicação, ela cria tabelas e as popula em um banco H2 em memória. Você terá:

- Uma conta de ID 1 com 300 reais em cada saldo (food, meal e cash).
- A tabela `merchants` também será populada.

Para detalhes sobre a criação e povoamento do banco de dados, consulte o arquivo `resources/data.sql`.

## Algumas Considerações Sobre o Projeto

1. **Proteção do Domínio**

    - **Mapeamento:** Implementamos um mapper para a transformação entre DTO <-> Domain e Entity <-> Domain. Isso evita a poluição do domínio com anotações de Hibernate ou Jackson, mantendo a lógica de negócio limpa e independente de frameworks específicos.
    - **TransactionResponse:** Este DTO é exposto por conveniência devido à sua estrutura ser idêntica à resposta esperada do serviço. Caso haja mudanças na estrutura da resposta, a transformação será ajustada conforme necessário.

2. **Implementação de Cache**

    - Implementamos cache para a sobrecarga do MCC (Merchant Category Code) e armazenamento do mapeamento de cada cliente. Utilizamos um hash para maior performance, ao invés de uma lista.

3. **Validações Globais**

    - Validações de entrada são realizadas via `RestControllerAdvice`. Seguindo as regras do desafio, o cliente receberá o código 07 sem detalhes específicos sobre o erro.

4. **Sobre o L4**

    - Para sistemas distribuídos e altamente escaláveis, recomenda-se implementar uma trava distribuída usando Redis, por exemplo. Isso pode ser feito utilizando o [Redisson](https://github.com/redisson/redisson/wiki/8.-Distributed-locks-and-synchronizers).
    - Outra opção, menos popular mas comum em sistemas mais antigos, é utilizar procedures de banco de dados para gerenciar a lógica da transação, controlando o acesso a essas procedures.
    - Se não houver necessidade de escalabilidade e você estiver lidando com uma única instância e múltiplas threads, você pode usar o próprio Java para sincronização, como mostrado no [Baeldung sobre locks concorrentes](https://www.baeldung.com/java-concurrent-locks).

---