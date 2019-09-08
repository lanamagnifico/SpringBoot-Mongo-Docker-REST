# SpringBoot-Mongo-Docker-REST
Simple containerized Spring-boot REST application
## Prerequisites
### Docker
[Docker installation](https://docs.docker.com/install/linux/docker-ce/ubuntu/)
### Docker-compose
[Docker-compose installation](https://docs.docker.com/compose/install/)
###Maven
[Maven installation](https://maven.apache.org/install.html)
## Building
Clone repository:
```bash
git clone https://github.com/vitolpvv/SpringBoot-Mongo-Docker-REST.git
```
Go to created folder:
```bash
cd SpringBoot-Mongo-Docker-REST
```
Run:
```bash
mvn package
```
'target' folder will be created.
## Containerization
Run:
```bash
docker-compose up -d
```
It will create two Docker containers:

'mongo' - mongodb container listening on port 27017.

'restmongoapp' - Spring-boot REST app container listening on port 8080.

After that REST service accessible on localhost:8080.
## Usage
Application provides single REST endpoint - /phones

Phone Entity:
```text
id: Long (auto-generated)
model: String
manufacturer: String
parameters: Map<String, String>
```
### Create:
POST /phones
```bash
curl -d '{"model":"iPhone X", "manufacturer":"Apple", "parameters":{"length":"200mm","width":"100mm","weight":"100g"}}' -H "Content-Type: application/json" -X POST localhost:8080/phones
```
Response 201 CREATED:
```text
{
    "code":201,
    "description":"Created",
    "message":"Entity created"
}
```
For updating existing entity 'id' field must be provided.
### Get all:
GET /phones
```text
curl localhost:8080/phones
```
Response 200 OK:
```text
[{
    "id":1,
    "model":"iPhone X",
    "manufacturer":"Apple",
    "parameters":{}
}]
```

Response contains 'id', 'model' and 'manufacturer' fields filled only. All other fields will be empty.
#### Filtering
Query parameters can be used to filter list:
```bash
curl localhost:8080/phones?manufacturer=Google
```
Response 200 OK:
```text
[]
```
Filtering parameters can be combined. Result list will contains entities that matches all parameters.
### Get by ID:
GET /phones/{id}
```bash
curl localhost:8080/phones/1
```
Response 200 OK:
```text
{
    "id":1,
    "model":"iPhone X",
    "manufacturer":"Apple",
    "parameters":{
        "length":"200mm",
        "width":"100mm",
        "weight":"100g"
    }
}
```
Response 404 NOT FOUND:
```text
{
    "code":404,
    "description":"Not Found",
    "message":"Entity not found"
}
```
### Delete:
DELETE /phones/{id}
```bash
curl localhost:8080/phones/1 -X DELETE
```
Response 200 OK:
```text
{
    "code":200,
    "description":"OK",
    "message":"Entity deleted"
}
```
Response 404 NOT FOUND:
```text
{
    "code":404,
    "description":"Not Found",
    "message":"Entity not found"
}
```