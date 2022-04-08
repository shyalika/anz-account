INSERT INTO `account` (`account_number`,`account_name`,`account_type`,`balance_date`,`currency`,`opening_available_balance`)
  VALUES 
(321143048,'AUCurrent433','CURRENT','2018-11-08 00:00:00','AUD',38010.62),
(347786244,'SGCurrent166','CURRENT','2018-11-08 00:00:00','SGD',50664.65),
(585309209,'SGSavings726','SAVINGS','2018-11-08 00:00:00','SGD',84327.51),
(791066619,'AUSavings933','SAVINGS','2018-11-08 00:00:00','AUD',88005.93);

INSERT INTO `transaction` (`uuid`,`value_date`,`debit_amount`,`credit_amount`,`transaction_type`,`transaction_narrative`,`account_number`) VALUES 
('6adb64aa-e904-4b04-81a1-482ea59cdb79','2022-04-07 02:55:44',10000.00,NULL,'DEBIT','',321143048),
('db67eea8-114c-4388-9f27-abd08e1efa1f','2022-04-07 02:55:44',NULL,500.00,'CREDIT','',321143048),
('6adb64aa-e904-4b04-81a1-481ea59cdb79','2022-04-07 02:55:44',110000.00,NULL,'DEBIT','',347786244),
('db67eea8-114c-4388-9f27-abd08e1efa2f','2022-04-07 02:55:44',NULL,800.00,'CREDIT','',347786244),
('6adb64aa-e904-4b04-81a1-482ea69cdb79','2022-04-07 02:55:44',9000.00,NULL,'DEBIT','',347786244),
('db67eea8-114c-4388-9f27-abd08e2efa1f','2022-04-07 02:55:44',NULL,435.00,'CREDIT','',791066619),
('6adb64aa-e904-4b04-81a1-482ea59cdb19','2022-04-07 02:55:44',10200.00,NULL,'DEBIT','',791066619),
('db67eea8-114c-4388-9f27-abd07e1efa1f','2022-04-07 02:55:44',NULL,500.00,'CREDIT','',791066619);