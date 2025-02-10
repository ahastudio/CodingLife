# Hibernate 예제

```sql
CREATE TABLE employments (
    id BIGSERIAL PRIMARY KEY,
    start DATE,
    "end" DATE,
    salary_amount NUMERIC(8, 2),
    salary_currency VARCHAR(3)
);
```
