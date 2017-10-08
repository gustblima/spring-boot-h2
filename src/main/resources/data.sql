INSERT INTO tb_industry(id, title)
    VALUES
    (1, 'Vehicles'),
    (2, 'Beauty'),
    (3, 'Food'),
    (4, 'Media'),
    (5, 'Art'),
    (6, 'Events'),
    (7, 'Sports'),
    (8, 'Maps'),
    (9, 'Transportation'),
    (10, 'Entertainment');

INSERT INTO tb_company(id, name, cnpj, telephone, website, industry_id)
    VALUES
    (1, 'Retrovee', '41138784000104' , '202-555-0162', 'retrovee.com', 1),
    (2, 'Demm', '20483829000108' , '202-555-0193' , 'demm.com', 2),
    (3, 'Neolium', '54744797000160', '202-555-0144' , 'neolium.com', 1),
    (4, 'Vivism', '84254514000163', '202-555-0106', 'vivism.com', 3),
    (5, 'Luper', '11118722000161' , '202-555-0145' , 'luper.com', 1);



