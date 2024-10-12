# Chatbot

Chat with LLMs via Ollama.
## Pre-Requisites

* Java 23
* Docker/Podman
* [Mistral AI](https://console.mistral.ai) API Key (optional)
* [OpenAI](https://platform.openai.com) API Key (optional)
* [Ollama](https://ollama.ai) (optional)


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

You can now call the application that will use Ollama and _llama3_ to answer your questions.
This example uses [httpie](https://httpie.io) to send HTTP requests.

```shell
http POST :8090/api/v1/chat/42 message="What is suniar?" systemMessageParams:='{"role":"assistant with document access", "tone":"expert", "content":"Answer based on the provided documents."}' useDocument:=false 

```
```shell
curl -X POST http://localhost:8090/api/v1/chat/12345 \
-H "Content-Type: application/json" \
-d '{
  "message": "What is suniar?",
  "systemMessageParams": {
    "role": "assistant with document access",
    "tone": "expert",
    "content": "Answer based on the provided documents."
  },
  "useDocument": true
}'

```
```shell
curl -X POST http://localhost:8090/api/v1/chat/12345 \
-H "Content-Type: application/json" \
-d '{
  "message": "How to cook ghorme sabzi?",
  "systemMessageParams": {
    "role": "polite chef assistant",
    "tone": "formal",
    "content": "Answer in one sentence."
  },
  "useDocument": false
}'

```

## With Response

```shell
curl -X POST http://localhost:8090/api/v1/chat/response/12345 \
-H "Content-Type: application/json" \
-d '{
  "message": "How to cook ghorme sabzi?",
  "systemMessageParams": {
    "role": "polite chef assistant",
    "tone": "formal",
    "content": "Answer in one sentence."
  },
  "useDocument": false
}'

```
