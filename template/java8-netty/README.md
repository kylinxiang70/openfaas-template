# Template: Java8

The Java8 template uses Maven as a build system.

Maven Version: 3.6.3


## Structure
There are three module of this project:

- model - (Library) classes for parsing request/response
- function - (Library) your function code as a developer, you will only ever see this folder
- entrypoint - (App) Netty based HTTP server for re-using the JVM between requests, (SSL support)

## Handler
The handler is written in the `./function/src/main/org/kylin/openfaas/Handler.java` folder

Tests in `./src/test`

## Usage
```bash
1. faas-cli template pull git@github.com:kylin1994/openfaas-template.git

2. faas-cli new --lang java8-netty <func-name>

3. faas-cli build -f <func-name>.yml

4. faas-cli deploy -f <func-name>.yml

or merge 3 and 4:
faas-cli up -f <func-name>.yml
```

SSL support:
```dockerfile
# update Dockerfile
# no ssl, default
ENV fprocess="java -jar -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap entrypoint-1.0-SNAPSHOT.jar"

# ssl support
ENV fprocess="java -jar -Dssl=true -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap entrypoint-1.0-SNAPSHOT.jar"
```
