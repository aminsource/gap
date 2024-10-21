# Chatbot

Chat with LLMs via Open Ai Models.
## Pre-Requisites

* Java 23
* [OpenAI](https://platform.openai.com) API Key (optional)


## Running the application

The application relies on OpenAI API  AI API for providing LLMs.

First, make sure you have an [OpenAI account](https://platform.openai.com/signup).
Then, define an environment variable with the OpenAI API Key associated to your OpenAI account as the value.

```shell
export SPRING_AI_OPENAI_API_KEY=<INSERT KEY HERE>
```

Finally, run the Spring Boot application.

```shell
./gradlew bootRun
```
## Chat with application

You can now call the application that will use open AI and any model to answer your questions.


```shell
http POST :8090/api/v1/openai/chat/42 message="What is suniar?" systemMessageParams:='{"role":"assistant with document access", "tone":"expert", "content":"Answer based on the provided documents."}' useDocument:=false 

```
### Cooking
```shell
curl -X POST http://localhost:8090/api/v1/openai/chat/12345 \
-H "Content-Type: application/json" \
-d '{
  "message": "How to cook ghorme sabzi?",
  "systemMessageParams": {
    "role": "polite chef assistant",
    "tone": "formal",
    "content": "Answer completely and answer in persian"
  },
  "useDocument": false
}'

```
### Hafez

```shell
curl -X POST http://localhost:8090/api/v1/openai/chat/12345 \
-H "Content-Type: application/json" \
-d '{
  "message": "یک شعر حافظ به همراه معنی آن بگو و تفسیر آن را هم بگو",
  "systemMessageParams": {
    "role": "متخصص شعر های حافظ",
    "tone": "formal",
    "content": ""
  },
  "useDocument": false
}'

```
