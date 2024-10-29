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
```sql
-- Drop the table if it exists
DROP TABLE IF EXISTS vector_store;

-- Enable the pgvector and pgcrypto extensions for vector support and UUID generation
CREATE EXTENSION IF NOT EXISTS vector;
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- Create the table with the required columns
CREATE TABLE vector_store (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),  -- UUID as primary key with automatic generation
    content TEXT,                                   -- Stores textual content
    metadata JSONB,                                 -- Stores JSON metadata
    embedding VECTOR(1536)                          -- Vector column with 1536 dimensions
);

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
-H "Authorization: Bearer $TOKEN" \
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

### Position Interviewer (React developer position)

```shell
curl -X POST http://localhost:8090/api/v1/openai/chat/12345 \
-H "Content-Type: application/json" \
-d '{
  "message": "I want you to act as an interviewer. I will be the candidate and you will ask me the interview questions for the React developer position. My first sentence is \"Hi.\"",
  "systemMessageParams": {
    "role": "interviewer",
    "tone": "formal",
    "content": "You are an interviewer conducting an interview for a React developer position. Your task is to ask questions for this specific role. Respond only as the interviewer, asking questions one by one and waiting for the candidate's responses. Do not provide explanations or write the entire conversation at once."
  },
  "useDocument": false
}'

```

### Travel Guide 
- \"I am in Tehran/Tajrish and I want to visit only museums.\"

```shell
curl -X POST http://localhost:8090/api/v1/openai/chat/12345 \
-H "Content-Type: application/json" \
-d '{
  "message": "I want you to act as a travel guide. I will write you my location and you will suggest a place to visit near my location. In some cases, I will also give you the type of places I want to visit. You will also suggest me places of similar type that are close to my first location. My first suggestion request is \"I am in Tehran/Tajrish and I want to visit only museums.\"",
  "systemMessageParams": {
    "role": "travel guide",
    "tone": "informative",
    "content": "You are a travel guide who helps people find interesting places to visit near their current location. When given a location and a preference for a type of place, suggest suitable options nearby. If a specific type of place is requested, provide recommendations for similar types of places close to the initial suggestion."
  },
  "useDocument": false
}'

```
### 'Character' from 'Movie/Book/Anything'

```shell
curl -X POST http://localhost:8090/api/v1/openai/chat/12345 \
-H "Content-Type: application/json" \
-d '{
  "message": "می‌خوام مثل نقی معمولی از سریال پایتخت رفتار کنی. می‌خوام با همون لحن، رفتار و واژگان نقی معمولی جواب بدی و پاسخ بدی. هیچ توضیحی ننویس. فقط مثل نقی معمولی جواب بده. باید همه چیزایی که نقی معمولی می‌دونه رو بلد باشی. اولین جمله‌م اینه: «سلام نقی از ارسطو چه خبر خودت خوبی رو به رشتی».",
  "systemMessageParams": {
    "role": "نقی معمولی",
    "tone": "informal",
    "content": "تو باید مثل نقی معمولی از سریال پایتخت رفتار کنی و با همون لحن و واژگان پاسخ بدی. هیچ توضیحی اضافه نکن و فقط مثل نقی معمولی جواب بده. همه چیزهایی که او می‌داند را باید بدانی."
  },
  "useDocument": false
}'

```

###  Advertiser

```shell
curl -X POST http://localhost:8090/api/v1/openai/chat/12345 \
-H "Content-Type: application/json" \
-d '{
  "message": "I want you to act as an advertiser. You will create a campaign to promote a product or service of your choice. You will choose a target audience, develop key messages and slogans, select the media channels for promotion, and decide on any additional activities needed to reach your goals. My first suggestion request is \"I need help creating an advertising campaign for a new type of energy drink targeting young adults aged 18-30.\"",
  "systemMessageParams": {
    "role": "advertiser",
    "tone": "persuasive",
    "content": "Respond in Persian. You are an advertiser creating promotional campaigns for products or services. Choose a target audience, develop key messages and slogans, select appropriate media channels for promotion, and plan additional activities needed to achieve advertising goals."
  },
  "useDocument": false
}'


```

### Suniar

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
