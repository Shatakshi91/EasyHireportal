# EasyHire Frontend

React + TypeScript + Vite client for the EasyHire Portal.

## Source Layout

```text
src/
|-- components/   Shared UI and feature components
|-- hooks/        Reusable React hooks
|-- lib/          API client and shared utilities
|-- pages/        Route-level screens
|-- store/        Client-side auth state
|-- types/        API and domain types
`-- utils/        UI helper functions
```

## Environment

Create `frontend/.env.local` when you need to override the API URL:

```bash
VITE_API_BASE_URL=http://localhost:8080/api/v1
```

## Scripts

```bash
npm install
npm run dev
npm run build
npm run lint
```

`npm run build` runs TypeScript project checks and creates the production Vite bundle.
