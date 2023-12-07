Users

| Data Type | Column Name | Default       |
|-----------|-------------|---------------|
| bigint    | user_id     | NOT NULL (AI) |
| char (20) | user_name   | NOT NULL      |
| char (30) | password    | NOT NULL      |

User Roles

| Data Type | Column Name | Default          |
|-----------|-------------|------------------|
| bigint    | user_id     | NOT NULL (PK,FK) |
| int       | role_id     | NOT NULL (PK,FK) |


Roles

| Data Type | Column Name | Default       |
|-----------|-------------|---------------|
| bigint    | role_id     | NOT NULL (AI) |
| char (20) | role_name   | NOT NULL      |

Department

| Data Type | Column Name | Default       |
|-----------|-------------|---------------|
| bigint    | dept_id     | NOT NULL (AI) |
| char (10) | dept_name   | NOT NULL      |
| char (20) | loc         | NOT NULL      | 

Employee

| Data Type | Column Name | Default       |
|-----------|-------------|---------------|
| bigint    | emp_id      | NOT NULL (AI) |
| bigint    | dept_id     | NOT NULL (FK) |
| char (30) | e_name      | NOT NULL      |
| char (30) | e_job       | NOT NULL      |
| date      | hire_date   | NOT NULL      |
| decimal   | sal         | NOT NULL      |
| decimal   | comm        | -             |

 