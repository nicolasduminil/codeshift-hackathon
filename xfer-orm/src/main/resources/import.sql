--
INSERT INTO BANK_ADDRESSES (BANK_ADDRESS_ID, STREET_NAME, STREET_NUMBER, PO_BOX, CITY_NAME, ZIP_CODE, COUNTRY_NAME, BANK_ID) VALUES (nextval('bankAddressId_seq'), 'av. de Paris', '18', 'Cedex', 'Montmorency', '95800', 'France', null);
INSERT INTO BANK_ADDRESSES (BANK_ADDRESS_ID, STREET_NAME, STREET_NUMBER, PO_BOX, CITY_NAME, ZIP_CODE, COUNTRY_NAME, BANK_ID) VALUES (nextval('bankAddressId_seq'), 'blvd. Waterloo', '381', 'INGPOBOX', 'Waterloo', 'B1000', 'Belgium', null);
--

--
INSERT INTO BANKS (BANK_ID, BANK_NAME) VALUES (nextval('bankId_seq'), 'Société Générale');
INSERT INTO BANKS (BANK_ID, BANK_NAME) VALUES (nextval('bankId_seq'), 'ING');
--

--
INSERT INTO BANK_ADDRESSES (BANK_ADDRESS_ID, STREET_NAME, STREET_NUMBER, PO_BOX, CITY_NAME, ZIP_CODE, COUNTRY_NAME, BANK_ID) VALUES (nextval('bankAddressId_seq'), 'av. de Paris', '18', 'Cedex', 'Montmorency', '95800', 'France', (select BANK_ID from BANKS where BANK_NAME = 'Société Générale'));
INSERT INTO BANK_ADDRESSES (BANK_ADDRESS_ID, STREET_NAME, STREET_NUMBER, PO_BOX, CITY_NAME, ZIP_CODE, COUNTRY_NAME, BANK_ID) VALUES (nextval('bankAddressId_seq'), 'blvd. Waterloo', '381', 'INGPOBOX', 'Waterloo', 'B1000', 'Belgium', (select BANK_ID from BANKS where BANK_NAME = 'ING'));
--

--
INSERT INTO BANK_ACCOUNTS (BANK_ACCOUNT_ID, ACCOUNT_ID, ACCOUNT_TYPE, SORT_CODE, ACCOUNT_NUMBER, TRANS_CODE, BANK_ID) VALUES (nextval('bankAccountId_seq'), 'SG01', 'CHECKING', '04004', '00050525712', '03000', (select BANK_ID from BANKS where BANK_NAME = 'Société Générale'));
INSERT INTO BANK_ACCOUNTS (BANK_ACCOUNT_ID, ACCOUNT_ID, ACCOUNT_TYPE, SORT_CODE, ACCOUNT_NUMBER, TRANS_CODE, BANK_ID) VALUES (nextval('bankAccountId_seq'), 'ING01', 'SAVINGS', '08080', '425091789', '7BRE0', (select BANK_ID from BANKS where BANK_NAME = 'ING'));
--

--
INSERT INTO MONEY_TRANSFER_ORDERS (MONEY_TRANSFER_ID, MONEY_TRANSFER_AMOUNT, MONEY_TRANSFER_ORDER_REF, SOURCE_BANK_ACCOUNT_ID, TARGET_BANK_ACCOUNT_ID) VALUES (nextval('bankId_seq'), 1000.00,'Tech Repairs', (select BANK_ACCOUNT_ID from BANK_ACCOUNTS where ACCOUNT_NUMBER = '00050525712'), (select BANK_ACCOUNT_ID from BANK_ACCOUNTS where ACCOUNT_NUMBER = '425091789'));
INSERT INTO MONEY_TRANSFER_ORDERS (MONEY_TRANSFER_ID, MONEY_TRANSFER_AMOUNT, MONEY_TRANSFER_ORDER_REF, SOURCE_BANK_ACCOUNT_ID, TARGET_BANK_ACCOUNT_ID) VALUES (nextval('bankId_seq'), 800.00,'Taxes and Licences', (select BANK_ACCOUNT_ID from BANK_ACCOUNTS where ACCOUNT_NUMBER = '00050525712'), (select BANK_ACCOUNT_ID from BANK_ACCOUNTS where ACCOUNT_NUMBER = '425091789'));
INSERT INTO MONEY_TRANSFER_ORDERS (MONEY_TRANSFER_ID, MONEY_TRANSFER_AMOUNT, MONEY_TRANSFER_ORDER_REF, SOURCE_BANK_ACCOUNT_ID, TARGET_BANK_ACCOUNT_ID) VALUES (nextval('bankId_seq'), 1234.00,'Advertising', (select BANK_ACCOUNT_ID from BANK_ACCOUNTS where ACCOUNT_NUMBER = '00050525712'), (select BANK_ACCOUNT_ID from BANK_ACCOUNTS where ACCOUNT_NUMBER = '425091789'));
INSERT INTO MONEY_TRANSFER_ORDERS (MONEY_TRANSFER_ID, MONEY_TRANSFER_AMOUNT, MONEY_TRANSFER_ORDER_REF, SOURCE_BANK_ACCOUNT_ID, TARGET_BANK_ACCOUNT_ID) VALUES (nextval('bankId_seq'), 2000.00,'Meal and Entertainment', (select BANK_ACCOUNT_ID from BANK_ACCOUNTS where ACCOUNT_NUMBER = '00050525712'), (select BANK_ACCOUNT_ID from BANK_ACCOUNTS where ACCOUNT_NUMBER = '425091789'));
INSERT INTO MONEY_TRANSFER_ORDERS (MONEY_TRANSFER_ID, MONEY_TRANSFER_AMOUNT, MONEY_TRANSFER_ORDER_REF, SOURCE_BANK_ACCOUNT_ID, TARGET_BANK_ACCOUNT_ID) VALUES (nextval('bankId_seq'), 1500.00,'Other expanses', (select BANK_ACCOUNT_ID from BANK_ACCOUNTS where ACCOUNT_NUMBER = '00050525712'), (select BANK_ACCOUNT_ID from BANK_ACCOUNTS where ACCOUNT_NUMBER = '425091789'));
--

