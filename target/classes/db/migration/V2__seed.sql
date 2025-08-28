-- Seed
INSERT INTO child (guardian_id, name, cohort, class_label) VALUES ('u_riya','Aarav','I-III','2-B');
INSERT INTO child_dietary_prefs (child_id, dietary_prefs) VALUES (1,'Veg');
INSERT INTO child_allergens (child_id, allergens) VALUES (1,'Peanut');

INSERT INTO subscription (child_id, plan_id, status, start_date, next_renewal, price, currency)
VALUES (1, 'MONTHLY', 'ACTIVE', CURRENT_DATE::text, (CURRENT_DATE + INTERVAL '30 day')::date::text, 1799, 'INR');

INSERT INTO invoice (subscription_id, period_start, period_end, amount, status, method)
VALUES (1, CURRENT_DATE::text, (CURRENT_DATE + INTERVAL '30 day')::date::text, 1799, 'PAID', 'UPI');

-- Menus for next 7 days
DO $$
DECLARE i int := 0;
BEGIN
  WHILE i < 7 LOOP
    INSERT INTO menu_item (date, cohort, title, items, kcal, protein, carbs, fat, allergens, theme)
    VALUES ((CURRENT_DATE + (i||' day')::interval)::date::text,
            'I-III',
            CASE i
              WHEN 0 THEN 'Protein Paneer Wrap'
              WHEN 1 THEN 'Veggie Pasta Bowl'
              WHEN 2 THEN 'Dal Khichdi + Curd'
              WHEN 3 THEN 'Mini Idli & Sambar'
              WHEN 4 THEN 'Spinach Paratha Roll'
              WHEN 5 THEN 'Veg Pulao + Raita'
              ELSE 'Cheesy Millet Sandwich'
            END,
            'Main,Fruit,Yogurt',
            420, 18, 55, 12,
            CASE i WHEN 1 THEN 'Gluten' WHEN 0 THEN 'Milk' ELSE '' END,
            CASE i WHEN 0 THEN 'Green Monday' WHEN 1 THEN 'Pasta Tuesday' WHEN 2 THEN 'Comfort Wednesday' WHEN 3 THEN 'South Thursday' WHEN 4 THEN 'Protein Friday' WHEN 5 THEN 'Rice Saturday' ELSE 'Sandwich Sunday' END
    );
    i := i + 1;
  END LOOP;
END $$;

-- Deliveries for 7 days
INSERT INTO delivery (child_id, date, route_name, status)
SELECT 1, (CURRENT_DATE + s.i)::date::text, 'R-12', CASE WHEN s.i=0 THEN 'OUT_FOR_DELIVERY' ELSE 'PENDING' END
FROM generate_series(0,6) as s(i);
