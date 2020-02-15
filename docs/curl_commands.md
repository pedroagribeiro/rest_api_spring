# Curl Commands to interact with the REST API

Para utilizar a aplicação basta correrem a classe **Application** e usarem
estes comandos no terminal. Tentem usá-los por uma ordem decente para obterem
resultados.

### Get a list of the registered users

```bash
curl -i -H "Content-Type: application/json" -X GET localhost:8080/users
```

### Add a user

```bash
curl -i -H "Content-Type: application/json" -X POST -d '{
    "username": "tuga_gamer_77",
    "email": "joao_games77@gmail.com",
    "phone_number": "9177777777",
    "premium": true
}' localhost:8080/users
```

### Get a user

```bash
curl -i -H "Content-Type: application/json" -X GET localhost:8080/users/1
```

### Remove a user

```bash
curl -i -H "Content-Type: application/json" -X DELETE localhost:8080/users/1
```

### Update an user

```bash
curl -i -H "Content-Type: application/json"  -X POST -d '{
    "username": "tiago_minecrafter",
    "email": "minecraft_is_life@gmail.com",
    "phone_number": "916666666",
    "premium": false
}' localhost:8080/users/1
```