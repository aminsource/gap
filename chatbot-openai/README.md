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
http --raw "Who Are you" :8090/api/v1/openai/chat/42
```
```shell
http --raw "Who Are you" :8090/api/v1/openai/chat/options/42 model=="gpt-4o" Temperature=="1.0"
```

