INSERT INTO account (
    fullname,
    username,
    password,
    email,
    status,
    created_by,
    created_date,
    last_modified_by,
    last_modified_date
) VALUES (
    'Administrator',
    'admin',
    'your_admin_password',
    'admin@example.com',
    'ACTIVE',
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
);

INSERT INTO account_role (
    account_id,
    role_id,
    created_by,
    created_date,
    last_modified_by,
    last_modified_date
)
SELECT
    a.id AS account_id,
    r.id AS role_id,
    'system' AS created_by,
    CURRENT_TIMESTAMP AS created_date,
    'system' AS last_modified_by,
    CURRENT_TIMESTAMP AS last_modified_date
FROM
    account a,
    role r
WHERE
    a.username = 'admin' AND r.code = 'ROLE_ADMIN';
