# EasyHire Portal

EasyHire Portal is a role-based internship hiring platform with a Spring Boot API, a React/Vite frontend, PostgreSQL persistence, Flyway migrations, and JWT authentication.

## Project Structure

```text
EasyHireportal/
|-- backend/        Spring Boot API, security, services, JPA entities, Flyway migrations
|-- frontend/       React + TypeScript + Vite web application
|-- docs/           Product, architecture, security, API, database, and deployment documents
`-- README.md       Repository entry point and local setup guide
```

## Backend

The backend follows a layered structure under `backend/src/main/java/com/easyhire`:

```text
config/          Application configuration, CORS, OpenAPI, JWT properties
controller/      REST API endpoints
dto/             Request and response contracts
entity/          JPA entities and enums
exception/       API exception handling
repository/      Spring Data repositories
security/        JWT filter, token service, auth entry point
service/         Business logic
specification/   Dynamic query specifications
```

Configuration lives in `backend/src/main/resources`:

```text
application.yaml       Shared defaults
application-dev.yaml   Local development profile
application-prod.yaml  Production profile driven by environment variables
db/migration/          Flyway schema migrations
```

Copy `backend/.env.example` into your local environment manager and provide real values before running the API.

## Frontend

The frontend source lives under `frontend/src`:

```text
components/      Reusable UI, layout, auth, candidate, recruiter, and public components
hooks/           React query and utility hooks
lib/             Shared client utilities, including the API client
pages/           Route-level screens
store/           Zustand auth store
types/           Shared TypeScript API/domain types
utils/           Presentation helpers
```

Copy `frontend/.env.example` to `frontend/.env.local` for local development when the API URL differs from the default.

## Local Development

Backend:

```bash
cd backend
./mvnw spring-boot:run
```

Frontend:

```bash
cd frontend
npm install
npm run dev
```

The default local API is `http://localhost:8080/api/v1`, and the default frontend origin is `http://localhost:5173`.

## Verification

Run these before opening a pull request or deploying:

```bash
cd backend
./mvnw test
```

```bash
cd frontend
npm run build
```

## Documentation

Detailed product, architecture, API, database, security, deployment, and roadmap notes are maintained in `docs/`.
