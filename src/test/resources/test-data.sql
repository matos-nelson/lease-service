-- auth_user
INSERT INTO LEASE(id, manager_id, owner_id, property_id, start_date, end_date, monthly_rent, security_deposit_held, pet_deposit_held)
VALUES (100, 'auth_user', 200, 300, '2020-10-01', '2021-10-01', 1000, 1500, 250);

INSERT INTO TENANT(id, lease_id, resident_id, is_primary)
VALUES(400, 100, 500, true);

-- test_user
INSERT INTO LEASE(id, manager_id, owner_id, property_id, start_date, end_date, monthly_rent, security_deposit_held, pet_deposit_held)
VALUES (200, 'test_user', 600, 700, '2020-10-01', '2021-10-01', 1000, 1500, 250);

INSERT INTO TENANT(id, lease_id, resident_id, is_primary)
VALUES(800, 200, 900, true);