# Chatbot

Chat with LLMs via Ollama.

## Running the application

The application relies on Ollama for providing LLMs. You can either run Ollama locally on your laptop, or rely on the Testcontainers support in Spring Boot to spin up an Ollama service automatically.

### Ollama as a native application

First, make sure you have [Ollama](https://ollama.ai) installed on your laptop.
Then, use Ollama to run the _llama3_ or _mistral_ large language model.
```shell
ollama run llama3.1:8b
```
or
```shell
ollama run mistral
```


Finally, run the Spring Boot application.

```shell
./gradlew bootRun
```

### Ollama as a dev service with Testcontainers

The application relies on the native Testcontainers support in Spring Boot to spin up an Ollama service with a _mistral_ model at startup time.

```shell
./gradlew bootTestRun
```

## Calling the application

You can now call the application that will use Ollama and _mistral_ to answer your questions.
This example uses [httpie](https://httpie.io) to send HTTP requests.

```shell
http --raw "My name is Hooman Amini." :8090/api/v1/chat/42
```

```shell
http --raw "What's my name?" :8090/api/v1/chat/42
```

```shell
http --raw "I was counting on your discretion. Please, do not share my name" :8090/api/v1/chat/42
```

```shell
http --raw "What's my name?" :8090/api/v1/chat/42
```

```shell
http --raw "Alright, then. Give me the recipe for a martini. Shaken, not stirred." :8090/api/v1/chat/42
```

# With Response

```shell
http --raw "My name is Hooman Amini." :8090/api/v1/chat/response/42
```

# SUNIAR FAQ

```shell
http  :8090/api/v1/suniar/faq?message="What sports are being included in the 2024 Summer Olympics?"
```