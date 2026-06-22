ALTER TABLE companies ADD COLUMN recruiter_id UUID;

ALTER TABLE companies
    ADD CONSTRAINT fk_company_recruiter
        FOREIGN KEY (recruiter_id)
            REFERENCES users (id)
            ON DELETE RESTRICT;

CREATE UNIQUE INDEX idx_company_recruiter_unique
    ON companies (recruiter_id)
    WHERE recruiter_id IS NOT NULL;
