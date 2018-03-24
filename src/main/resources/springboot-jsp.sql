-- 建三个数据库 分别是 test test01 test01

-- 再在每个数据中建1张users表

CREATE TABLE `users` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(12) COLLATE utf8_bin DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

