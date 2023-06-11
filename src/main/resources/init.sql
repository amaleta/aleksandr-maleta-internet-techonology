INSERT INTO car (model, registration_number, type, year)
VALUES ('Toyota Corolla', 'ABC123', 'Sedan', 2022),
       ('Honda Civic', 'DEF456', 'Coupe', 2021),
       ('Nissan Rogue', 'GHI789', 'SUV', 2020),
       ('Ford Mustang', 'JKL012', 'Sports car', 2019),
       ('Chevrolet Camaro', 'MNO345', 'Convertible', 2018),
       ('Golf 5', 'BEG9999', 'Convertible', 2019);

INSERT INTO customer (address, email, name, phone)
VALUES ('456 Oak St, Anytown, USA', 'janedoe@example.com', 'Jane Doe', '+1 222-555-5555'),
       ('789 Maple St, Anytown, USA', 'bobsmith@example.com', 'Bob Smith', '+1 123-555-5555'),
       ('321 Pine St, Anytown, USA', 'alicejones@example.com', 'Alice Jones', '+1 444-555-5555'),
       ('654 Elm St, Anytown, USA', 'tombrown@example.com', 'Tom Brown', '+1 777-555-5555'),
       ('1434 Elm St, Anytown, USA', 'tombrown@example.com', 'Tom Green', '+1 777-444-5555'),
       ('32423 Elm St, TargetTown, USA', 'testman@example.com', 'Tom Blue', '+1 888-444-5555');

INSERT INTO driver (address, email, license_number, name, phone, car_id)
VALUES ('123 Main St, Anytown, USA', 'davidlee@example.com', 'ABC123', 'David Lee',
        '+1 333-555-5555', 1),
       ('456 Oak St, Anytown, USA', 'emilywang@example.com', 'XYZ789', 'Emily Wang',
        '+1 234-555-5555', 2),
       ('789 Maple St, Anytown, USA', 'jackchen@example.com', 'DEF456', 'Jack Chen',
        '+1 554-555-5555', 3),
       ('321 Pine St, Anytown, USA', 'sophiazhang@example.com', 'GHI789', 'Sophia Zhang',
        '+1 324-555-5555', 4),
       ('654 Elm St, Anytown, USA', 'ryankim@example.com', 'JKL012', 'Ryan Kim', '+1 654-555-5555',
        5),
       ('2334 Elm St, TargetTown, USA', 'kim@example.com', 'JKL012', 'Tom Kim', '+1 234-555-5555',
        6);
INSERT INTO parcel (delivery_date, height, length, recipient_address, sender_address, sent_date,
                    weight, width)
VALUES ('2018-05-01', 10.0, 20.0, '123 Main St, Anytown, USA', '789 Maple St, Anytown, USA',
        '2022-04-30', 5.0, 15.0),
       ('2019-05-02', 5.0, 10.0, '456 Oak St, Anytown, USA', '654 Elm St, Anytown, USA',
        '2022-05-01', 2.0, 8.0),
       ('2020-05-03', 15.0, 30.0, '789 Maple St, Anytown, USA', '123 Main St, Anytown, USA',
        '2022-05-02', 10.0, 25.0),
       ('2021-05-04', 7.0, 14.0, '321 Pine St, Anytown, USA', '456 Oak St, Anytown, USA',
        '2022-05-03', 3.0, 9.0),
       ('2022-05-05', 12.0, 24.0, '654 Elm St, Anytown, USA', '321 Pine St, Anytown, USA',
        '2022-05-04', 8.0, 20.0),
       ('2023-05-05', 13.0, 20.0, '444 Elm St, TargetTown, USA', '322 Pine St, TargetTown, USA',
        '2023-05-04', 10.0, 10.0);

INSERT INTO delivery_order (order_date, status, customer_id, driver_id, parcel_id)
VALUES ('2022-05-01', 'PENDING', 1, 1, 1),
       ('2022-05-02', 'CREATED', 2, 2, 2),
       ('2022-05-03', 'CANCELED', 3, 3, 3),
       ('2022-05-04', 'IN_DELIVERY', 4, 4, 4),
       ('2022-05-05', 'DELIVERED', 5, 5, 5),
       ('2023-05-05', 'DELIVERED', 6, 6, 6);

INSERT INTO payment (amount, payment_date, payment_method, customer_id, order_id)
VALUES (50.0, '2022-05-01', 'CASH', 1, 1),
       (25.0, '2022-05-02', 'CARD', 2, 2),
       (100.0, '2022-05-03', 'PAYPAL', 3, 3),
       (30.0, '2022-05-04', 'CARD', 4, 4),
       (80.0, '2022-05-05', 'PAYPAL', 5, 5),
       (80.0, '2022-05-05', 'CASH', 6, 6);

INSERT INTO review (rating, review_date, review_text, customer_id, order_id)
VALUES (4, '2022-05-01', 'Great service!', 1, 1),
       (5, '2022-05-02', 'Amazing delivery!', 2, 2),
       (3, '2022-05-03', 'Could be better', 3, 3),
       (4, '2022-05-04', 'Good overall', 4, 4),
       (5, '2022-05-05', 'Perfect!', 5, 5),
       (4, '2023-05-05', 'Okay', 6, 6);
