```sql
CREATE TABLE vector_store (
    id UUID PRIMARY KEY,
    content TEXT NOT NULL,
    metadata JSONB,
    embedding VECTOR(768) NOT NULL
);

```
