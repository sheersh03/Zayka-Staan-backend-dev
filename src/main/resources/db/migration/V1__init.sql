-- Schema
CREATE TABLE child ( id BIGSERIAL PRIMARY KEY, guardian_id VARCHAR(64), name VARCHAR(64), cohort VARCHAR(16), class_label VARCHAR(16) );
CREATE TABLE child_dietary_prefs (child_id BIGINT REFERENCES child(id), dietary_prefs VARCHAR(64));
CREATE TABLE child_allergens (child_id BIGINT REFERENCES child(id), allergens VARCHAR(64));
CREATE TABLE subscription ( id BIGSERIAL PRIMARY KEY, child_id BIGINT REFERENCES child(id), plan_id VARCHAR(16), status VARCHAR(16), start_date VARCHAR(16), next_renewal VARCHAR(16), price INT, currency VARCHAR(8) );
CREATE TABLE menu_item ( id BIGSERIAL PRIMARY KEY, date VARCHAR(16), cohort VARCHAR(16), title VARCHAR(128), items VARCHAR(256), kcal INT, protein INT, carbs INT, fat INT, allergens VARCHAR(128), theme VARCHAR(64) );
CREATE TABLE selection_tbl ( id BIGSERIAL PRIMARY KEY, child_id BIGINT, date VARCHAR(16), status VARCHAR(16), chosen_item_id BIGINT );
CREATE TABLE delivery ( id BIGSERIAL PRIMARY KEY, child_id BIGINT, date VARCHAR(16), route_name VARCHAR(16), status VARCHAR(32), delivered_at VARCHAR(64), exception_reason VARCHAR(128) );
CREATE TABLE feedback ( id BIGSERIAL PRIMARY KEY, child_id BIGINT, date VARCHAR(16), rating INT, tags VARCHAR(128), comment TEXT );
CREATE TABLE invoice ( id BIGSERIAL PRIMARY KEY, subscription_id BIGINT, period_start VARCHAR(16), period_end VARCHAR(16), amount INT, status VARCHAR(16), method VARCHAR(16) );
