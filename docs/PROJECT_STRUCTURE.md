# Project Structure Review

This repository is organized as a two-application monorepo:

```text
EasyHireportal/
|-- backend/        Spring Boot REST API
|-- frontend/       React/Vite web client
|-- docs/           Planning and architecture documentation
`-- README.md       Root setup and navigation guide
```

## Findings Fixed

- Added a root `README.md` so the repository has a clear entry point.
- Replaced the default Vite frontend README with EasyHire-specific setup notes.
- Added backend and frontend `.env.example` files to document required runtime configuration.
- Changed the frontend API client to read `VITE_API_BASE_URL` instead of hardcoding localhost.
- Changed backend CORS configuration to read allowed origins from environment-backed application properties.
- Filled out `application-prod.yaml` so the production profile is deployable from environment variables.
- Normalized frontend route imports to the configured `@/` alias.
- Removed stale scaffold comments from frontend bootstrap files.

## Intended Boundaries

- Backend code stays inside `backend/src/main/java/com/easyhire`.
- Database changes are introduced only through `backend/src/main/resources/db/migration`.
- Frontend route screens stay in `frontend/src/pages`.
- Reusable frontend elements stay in `frontend/src/components`, grouped by feature or shared UI responsibility.
- Product and architecture documents stay in `docs`.

## Follow-Up Plan

1. Keep root-level documentation focused on setup and navigation.
2. Keep detailed product, deployment, API, and architecture notes in `docs`.
3. Add CI once Node.js and Java runtimes are standardized for the project.
4. Expand backend tests beyond context loading as services and controllers evolve.
