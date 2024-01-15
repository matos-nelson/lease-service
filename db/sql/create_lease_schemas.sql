CREATE TABLE IF NOT EXISTS lease (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  manager_id varchar(255) NOT NULL,
  owner_id bigint NOT NULL,
  property_id bigint NOT NULL,
  start_date date NOT NULL,
  end_date date NOT NULL,
  monthly_rent decimal(38, 2) NOT NULL,
  security_deposit_held decimal(38, 2) NOT NULL,
  pet_deposit_held decimal(38, 2) NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX manager_id_idx (manager_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS tenant (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  lease_id bigint NOT NULL,
  resident_id bigint NOT NULL,
  is_primary bool NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX lease_id_idx (lease_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;