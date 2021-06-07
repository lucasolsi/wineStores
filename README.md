## Running the application

To run this API you'll need to execute the following command on your terminal:

    mvn spring-boot:run

## API Doc

A Swagger UI document for this API can be found in:

    http://localhost:8080/wine/swagger-ui/index.html

## Technologies involved

- Spring Boot
- Spring Data JPA
- Lombok
- ModelMapper
- Swagger
- Maven
- H2 Database

## Utilities

In order to help you, there are included a Postman collection and environment.

## Operations

### Create Store

Operation sent with HTTP method **POST** to http://{{api-host}}:{{port}}/wine/store and the request body should follow
this pattern:

```json
{
  "codigo_loja": "LOJA_PINHEIROS",
  "faixa_inicio": 10000000,
  "faixa_fim": 20000000
}
```

As response, the store should be returned.

```json
{
  "id": 1,
  "codigo_loja": "LOJA_PINHEIROS",
  "faixa_inicio": 10000000,
  "faixa_fim": 20000000
}
```

### List all stores

Operation sent with HTTP method **GET** to http://{{api-host}}:{{port}}/wine/store?page=0&limit=5. Since this is a
paginated request, sending it exactly as shown above, it will return the first 5 stores created. Removing the page and
limit parameters, by default the API returns the first page and 5 results.

Example response:

```json
[
  {
    "id": 1,
    "codigo_loja": "LOJA_PINHEIROS",
    "faixa_inicio": 10000000,
    "faixa_fim": 20000000
  },
  {
    "id": 2,
    "codigo_loja": "LOJA_PINHEIROS",
    "faixa_inicio": 20000001,
    "faixa_fim": 30000000
  }
]
```

