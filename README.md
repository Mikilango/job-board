# Job Board API

REST API для платформы поиска работы, разработанный на Spring Boot.

## Технологии

- **Java 17**
- **Spring Boot 4.x**
- **Spring Security + JWT**
- **Spring Data JPA + Hibernate**
- **PostgreSQL**
- **Swagger / OpenAPI**
- **Lombok**
- **Maven**

## Функциональность

- Регистрация и авторизация пользователей с JWT токеном
- Три роли: `EMPLOYER`, `APPLICANT`, `ADMIN`
- Полный CRUD для вакансий
- Полнотекстовый поиск вакансий через PostgreSQL (`tsvector`)
- Подача откликов на вакансии
- Загрузка резюме в формате PDF
- Пагинация и сортировка вакансий
- Валидация входящих данных
- Глобальная обработка ошибок

## Структура проекта

```
src/main/java/com/miki/jobboard/
├── controller/       — REST контроллеры
├── service/          — бизнес-логика
│   └── impl/         — реализации сервисов
├── repository/       — JPA репозитории
├── entity/           — JPA сущности
├── dto/              — объекты передачи данных
├── mapper/           — маппинг entity ↔ DTO
├── security/         — JWT фильтр, утилиты
├── exception/        — кастомные исключения
└── config/           — конфигурация Swagger
```

## Сущности

| Сущность | Описание |
|----------|----------|
| `User` | Пользователь системы |
| `Job` | Вакансия |
| `Application` | Отклик на вакансию |
| `File` | Резюме соискателя (PDF) |

## Endpoints

### Аутентификация
| Метод | URL | Доступ |
|-------|-----|--------|
| POST | `/auth/register` | Все |
| POST | `/auth/login` | Все |

### Вакансии
| Метод | URL | Доступ |
|-------|-----|--------|
| GET | `/api/jobs` | Все |
| GET | `/api/jobs/{id}` | Все |
| GET | `/api/jobs/search?query=` | Все |
| GET | `/api/jobs/paged?page=0&size=10&sortBy=createdAt` | Все |
| POST | `/api/jobs` | EMPLOYER |
| PUT | `/api/jobs/{id}` | EMPLOYER |
| DELETE | `/api/jobs/{id}` | EMPLOYER |

### Отклики
| Метод | URL | Доступ |
|-------|-----|--------|
| POST | `/api/applications` | Авторизован |
| GET | `/api/applications/{id}` | Авторизован |
| GET | `/api/applications/job/{jobId}` | Авторизован |
| GET | `/api/applications/user/{userId}` | Авторизован |
| PUT | `/api/applications/{id}/status` | Авторизован |
| DELETE | `/api/applications/{id}` | Авторизован |

### Файлы
| Метод | URL | Доступ |
|-------|-----|--------|
| POST | `/api/files` | Авторизован |
| GET | `/api/files/{id}` | Авторизован |
| GET | `/api/files/application/{applicationId}` | Авторизован |
| DELETE | `/api/files/{id}` | Авторизован |

## Запуск проекта

### Требования
- Java 17+
- PostgreSQL

### Настройка базы данных

```sql
CREATE DATABASE job_board_db;
```

### Настройка `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/job_board_db
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update

jwt.secret=your_secret_key_minimum_32_characters
jwt.expiration=86400000

file.upload-dir=uploads
```

### Запуск

```bash
mvn spring-boot:run
```

### Документация API

После запуска откройте в браузере:

```
http://localhost:8080/swagger-ui/index.html
```

## Примеры запросов

### Регистрация

```json
POST /auth/register
{
  "email": "employer@example.com",
  "password": "Password123",
  "role": "EMPLOYER"
}
```

### Создание вакансии

```json
POST /api/jobs
Authorization: Bearer <token>

{
  "title": "Java Backend Developer",
  "description": "We are looking for a Java developer",
  "salary": 1500,
  "location": "Bishkek"
}
```

### Поиск вакансий

```
GET /api/jobs/search?query=java
```

## Автор

**Mirlanbek** — [GitHub](https://github.com/Mikilango)