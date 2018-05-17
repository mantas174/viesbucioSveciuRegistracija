INSERT INTO `kambariai` (`kambario_numeris`)
SELECT * FROM (SELECT '1') AS tmp
WHERE NOT EXISTS (
    SELECT `kambario_numeris` FROM `kambariai` WHERE `kambario_numeris` = '1'
) LIMIT 1;

INSERT INTO `kambariai` (`kambario_numeris`)
SELECT * FROM (SELECT '2') AS tmp
WHERE NOT EXISTS (
    SELECT `kambario_numeris` FROM `kambariai` WHERE `kambario_numeris` = '2'
) LIMIT 1;

INSERT INTO `kambariai` (`kambario_numeris`)
SELECT * FROM (SELECT '3') AS tmp
WHERE NOT EXISTS (
    SELECT `kambario_numeris` FROM `kambariai` WHERE `kambario_numeris` = '3'
) LIMIT 1;

INSERT INTO `kambariai` (`kambario_numeris`)
SELECT * FROM (SELECT '4') AS tmp
WHERE NOT EXISTS (
    SELECT `kambario_numeris` FROM `kambariai` WHERE `kambario_numeris` = '4'
) LIMIT 1;

INSERT INTO `kambariai` (`kambario_numeris`)
SELECT * FROM (SELECT '5') AS tmp
WHERE NOT EXISTS (
    SELECT `kambario_numeris` FROM `kambariai` WHERE `kambario_numeris` = '5'
) LIMIT 1;

ALTER TABLE `sveciai`
 CHANGE `pavarde`
  `pavarde` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
 CHANGE `vardas`
  `vardas` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL;


