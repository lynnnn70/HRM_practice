# HRM_practice
# Table
## Users

| Data Type | Column Name | Default       | Describe |
|-----------|-------------|---------------|----------|
| bigint    | user_id     | NOT NULL (AI) | 使用者 Id   |
| char (20) | user_name   | NOT NULL      | 使用者 姓名   |
| char (30) | password    | NOT NULL      | 使用者 密碼   |

## User Roles

| Data Type | Column Name | Default          | Describe |
|-----------|-------------|------------------|----------|
| bigint    | user_id     | NOT NULL (PK,FK) | 使用者 Id   |
| int       | role_id     | NOT NULL (PK,FK) | 角色權限 編號  |


## Roles

| Data Type | Column Name | Default       | Describe |
|-----------|-------------|---------------|----------|
| bigint    | role_id     | NOT NULL (AI) | 角色權限 編號  |
| char (20) | role_name   | NOT NULL      | 角色權限 名稱  |

## Department

| Data Type | Column Name | Default       | Describe |
|-----------|-------------|---------------|----------|
| bigint    | dept_id     | NOT NULL (AI) | 部門 編號    |
| char (10) | dept_name   | NOT NULL      | 部門 名稱    |
| char (20) | loc         | NOT NULL      | 部門 地點    |

## Employee

| Data Type | Column Name | Default       | Describe      |
|-----------|-------------|---------------|---------------|
| bigint    | emp_id      | NOT NULL (AI) | 員工 編號         |
| bigint    | dept_id     | NOT NULL (FK) | 部門 編號         |
| char (30) | emp_name      | NOT NULL      | 員工 姓名         |
| char (30) | emp_job       | NOT NULL      | 員工 職稱         |
| date      | hire_date   | NOT NULL      | 入職日           |
| decimal   | sal         | NOT NULL      | 薪資            |
| decimal   | comm        | -             | 佣金 commission |

 
