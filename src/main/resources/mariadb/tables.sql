create database upbias_quotes;


CREATE TABLE `assetclasses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `datasources` (
  `id` bigint(20) NOT NULL,
  `source` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `securities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `symbol` varchar(50) NOT NULL,
  `ticker` varchar(50) NOT NULL,
  `description` varchar(250) NOT NULL,
  `desc2` varchar(50) DEFAULT NULL,
  `desc3` varchar(150) DEFAULT NULL,
  `currency` varchar(5) NOT NULL,
  `ext` varchar(250) NOT NULL,
  `assetClass` bigint(20) NOT NULL,
  `tradeable` tinyint(4) NOT NULL DEFAULT '1',
  `autoDownload` tinyint(4) NOT NULL DEFAULT '1',
  `datasource` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `symbol_UNIQUE` (`symbol`),
  UNIQUE KEY `ticker_UNIQUE` (`ticker`),
  KEY `id` (`id`,`symbol`),
  KEY `assetclassesFK_idx` (`assetClass`),
  KEY `datasourcesFK_idx` (`datasource`),
  CONSTRAINT `assetClassFK` FOREIGN KEY (`assetClass`) REFERENCES `assetclasses` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `datasourcesFK` FOREIGN KEY (`datasource`) REFERENCES `datasources` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 PACK_KEYS=1 DELAY_KEY_WRITE=1;


CREATE TABLE `quotesadj` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `symbol` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `open` double NOT NULL,
  `high` double NOT NULL,
  `low` double NOT NULL,
  `close` double NOT NULL,
  `closeadj` double NOT NULL,
  `volume` double NOT NULL,
  `dividend` double NOT NULL DEFAULT '0',
  `split` double NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `symboldate` (`symbol`,`date`),
  KEY `date` (`date`),
  KEY `symbol` (`symbol`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
