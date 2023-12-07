# HRM_practice
Table

User

| Data Type | Column Name | Default       |
|-----------|-------------|---------------|
| int       | USERID      | NOT NULL (AI) |
| char (20) | USERNAME    | NOT NULL      |
| char (30) | PASSWORD    | NOT NULL      |

User Role

| Data Type | Column Name | Default       |
|-----------|-------------|---------------|
| int       | USERID      | NOT NULL      |
| int       | ROLEID      | NOT NULL      |


Role

| Data Type | Column Name | Default       |
|-----------|-------------|---------------|
| int       | ROLEID      | NOT NULL (AI) |
| char (20) | ROLENAME    | NOT NULL      |

Employee

| Data Type | Column Name | Default       |
|-----------|-------------|---------------|
| int       | EMPNO       | NOT NULL (AI) |
| int       | DEPTNO      | NOT NULL (FK) |
| char (30) | ENAME       | NOT NULL      |
| char (10) | JOB         | NOT NULL      |
| date      | HIREDATE    | NOT NULL      |
| decimal   | SAL         | NOT NULL      |
| decimal   | COMM        | -             |

Department

| Data Type | Column Name | Default       |
|-----------|-------------|---------------|
| int       | DEPTNO      | NOT NULL (AI) |
| char (10) | DNAME       | NOT NULL      |
| char (20) | LOC         | NOT NULL      |

