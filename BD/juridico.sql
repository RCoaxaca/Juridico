/*
Navicat MySQL Data Transfer

Source Server         : server122
Source Server Version : 50539
Source Host           : 172.19.2.122:3306
Source Database       : juridico

Target Server Type    : MYSQL
Target Server Version : 50539
File Encoding         : 65001

Date: 2015-12-15 09:18:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base
-- ----------------------------
DROP TABLE IF EXISTS `base`;
CREATE TABLE `base` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `acta_id` int(11) NOT NULL,
  `base` varchar(550) NOT NULL,
  `campo_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2E06D11A43F16` (`campo_id`),
  KEY `FK2E06D1DA0615CA` (`acta_id`),
  CONSTRAINT `FK2E06D11A43F16` FOREIGN KEY (`campo_id`) REFERENCES `fields` (`id`),
  CONSTRAINT `FK2E06D1DA0615CA` FOREIGN KEY (`acta_id`) REFERENCES `tipoactas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=185 DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaBase`;
DELIMITER ;;
CREATE TRIGGER `insertaBase` AFTER INSERT ON `base` FOR EACH ROW BEGIN
INSERT INTO juridico_001.base SET id=NEW.id,acta_id=NEW.acta_id,
base=NEW.base,campo_id=NEW.campo_id,usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `updateBase`;
DELIMITER ;;
CREATE TRIGGER `updateBase` AFTER UPDATE ON `base` FOR EACH ROW BEGIN
INSERT INTO juridico_001.base SET id=NEW.id,acta_id=NEW.acta_id,
base=NEW.base,campo_id=NEW.campo_id,usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `deleteBase`;
DELIMITER ;;
CREATE TRIGGER `deleteBase` AFTER DELETE ON `base` FOR EACH ROW BEGIN
INSERT INTO juridico_001.base_el SET id=OLD.id,acta_id=OLD.acta_id,
base=OLD.base,campo_id=OLD.campo_id,usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


-- ----------------------------
-- Table structure for campo
-- ----------------------------
DROP TABLE IF EXISTS `campo`;
CREATE TABLE `campo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `campo` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaCampo`;
DELIMITER ;;
CREATE TRIGGER `insertaCampo` AFTER INSERT ON `campo` FOR EACH ROW BEGIN
INSERT INTO juridico_001.campo SET id=NEW.id,version=NEW.version,
campo=NEW.campo,usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateCampo`;
DELIMITER ;;
CREATE TRIGGER `updateCampo` AFTER UPDATE ON `campo` FOR EACH ROW BEGIN
INSERT INTO juridico_001.campo SET id=NEW.id,version=NEW.version,
campo=NEW.campo,usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `deleteCampo`;
DELIMITER ;;
CREATE TRIGGER `deleteCampo` AFTER DELETE ON `campo` FOR EACH ROW BEGIN
INSERT INTO juridico_001.campo_el SET id=OLD.id,version=OLD.version,
campo=OLD.campo,usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


-- ----------------------------
-- Table structure for clausrr
-- ----------------------------
DROP TABLE IF EXISTS `clausrr`;
CREATE TABLE `clausrr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clv` varchar(3) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `outdate` datetime NOT NULL,
  `passw` varchar(10) NOT NULL,
  `status` varchar(3) NOT NULL,
  `updates` datetime NOT NULL,
  `usrtype` varchar(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaClausrr`;
DELIMITER ;;
CREATE TRIGGER `insertaClausrr` AFTER INSERT ON `clausrr` FOR EACH ROW BEGIN
INSERT INTO juridico_001.clausrr SET id=NEW.id,clv=NEW.clv,
nombre=NEW.nombre,outdate=NEW.outdate, passw=NEW.passw, status=NEW.status,
updates=NEW.updates, usrtype=NEW.usrtype,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `updateClausrr`;
DELIMITER ;;
CREATE TRIGGER `updateClausrr` AFTER UPDATE ON `clausrr` FOR EACH ROW BEGIN
INSERT INTO juridico_001.clausrr SET id=NEW.id,clv=NEW.clv,
nombre=NEW.nombre,outdate=NEW.outdate, passw=NEW.passw, status=NEW.status,
updates=NEW.updates, usrtype=NEW.usrtype,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `deleteClausrr`;
DELIMITER ;;
CREATE TRIGGER `deleteClausrr` AFTER DELETE ON `clausrr` FOR EACH ROW BEGIN
INSERT INTO juridico_001.clausrr_el SET id=OLD.id,clv=OLD.clv,
nombre=OLD.nombre,outdate=OLD.outdate, passw=OLD.passw, status=OLD.status,
updates=OLD.updates, usrtype=OLD.usrtype,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


-- ----------------------------
-- Table structure for docesta
-- ----------------------------
DROP TABLE IF EXISTS `docesta`;
CREATE TABLE `docesta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `dc` int(11) NOT NULL,
  `docuestado` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaDocesta`;
DELIMITER ;;
CREATE TRIGGER `insertaDocesta` AFTER INSERT ON `docesta` FOR EACH ROW BEGIN
INSERT INTO juridico_001.docesta SET id=NEW.id,version=NEW.version,
dc=NEW.dc,docuestado=NEW.docuestado,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `updateDocesta`;
DELIMITER ;;
CREATE TRIGGER `updateDocesta` AFTER UPDATE ON `docesta` FOR EACH ROW BEGIN
INSERT INTO juridico_001.docesta SET id=NEW.id,version=NEW.version,
dc=NEW.dc,docuestado=NEW.docuestado,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `deleteDocesta`;
DELIMITER ;;
CREATE TRIGGER `deleteDocesta` AFTER DELETE ON `docesta` FOR EACH ROW BEGIN
INSERT INTO juridico_001.docesta_el SET id=OLD.id,version=OLD.version,
dc=OLD.dc,docuestado=OLD.docuestado,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


-- ----------------------------
-- Table structure for entidades
-- ----------------------------
DROP TABLE IF EXISTS `entidades`;
CREATE TABLE `entidades` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `clave` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaEntidades`;
DELIMITER ;;
CREATE TRIGGER `insertaEntidades` AFTER INSERT ON `entidades` FOR EACH ROW BEGIN
INSERT INTO juridico_001.entidades SET ID=NEW.ID,clave=NEW.clave,
nombre=NEW.nombre,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateEntidades`;
DELIMITER ;;
CREATE TRIGGER `updateEntidades` AFTER UPDATE ON `entidades` FOR EACH ROW BEGIN
INSERT INTO juridico_001.entidades SET ID=NEW.ID,clave=NEW.clave,
nombre=NEW.nombre,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `deleteEntidades`;
DELIMITER ;;
CREATE TRIGGER `deleteEntidades` AFTER DELETE ON `entidades` FOR EACH ROW BEGIN
INSERT INTO juridico_001.entidades_el SET ID=OLD.ID,clave=OLD.clave,
nombre=OLD.nombre,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


-- ----------------------------
-- Table structure for erroresperado
-- ----------------------------
DROP TABLE IF EXISTS `erroresperado`;
CREATE TABLE `erroresperado` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tipodeerror` varchar(60) NOT NULL,
  `tipoerr_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4B1CF2A5B60E24AC` (`tipoerr_id`),
  CONSTRAINT `FK4B1CF2A5B60E24AC` FOREIGN KEY (`tipoerr_id`) REFERENCES `tipoerror` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaErrorEsperado`;
DELIMITER ;;
CREATE TRIGGER `insertaErrorEsperado` AFTER INSERT ON `erroresperado` FOR EACH ROW BEGIN
INSERT INTO juridico_001.erroresperado SET id=NEW.id,tipodeerror=NEW.tipodeerror,
tipoerr_id=NEW.tipoerr_id,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `updateErrorEsperado`;
DELIMITER ;;
CREATE TRIGGER `updateErrorEsperado` AFTER UPDATE ON `erroresperado` FOR EACH ROW BEGIN
INSERT INTO juridico_001.erroresperado SET id=NEW.id,tipodeerror=NEW.tipodeerror,
tipoerr_id=NEW.tipoerr_id,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `deleteErrorEsperado`;
DELIMITER ;;
CREATE TRIGGER `deleteErrorEsperado` AFTER DELETE ON `erroresperado` FOR EACH ROW BEGIN
INSERT INTO juridico_001.erroresperado_el SET id=OLD.id,tipodeerror=OLD.tipodeerror,
tipoerr_id=OLD.tipoerr_id,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

-- ----------------------------
-- Table structure for fields
-- ----------------------------
DROP TABLE IF EXISTS `fields`;
CREATE TABLE `fields` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `acta_id` int(11) NOT NULL,
  `nombre` varchar(65) NOT NULL,
  `num` int(11) NOT NULL,
  `sexo` char(1) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7D6A6AD9DA0615CA` (`acta_id`),
  CONSTRAINT `FK7D6A6AD9DA0615CA` FOREIGN KEY (`acta_id`) REFERENCES `tipoactas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=603 DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaFields`;
DELIMITER ;;
CREATE TRIGGER `insertaFields` AFTER INSERT ON `fields` FOR EACH ROW BEGIN
INSERT INTO juridico_001.fields SET id=NEW.id,acta_id=NEW.acta_id,
nombre=NEW.nombre, num=NEW.num, sexo=NEW.sexo, tipo=NEW.tipo,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateFields`;
DELIMITER ;;
CREATE TRIGGER `updateFields` AFTER UPDATE ON `fields` FOR EACH ROW BEGIN
INSERT INTO juridico_001.fields SET id=NEW.id,acta_id=NEW.acta_id,
nombre=NEW.nombre, num=NEW.num, sexo=NEW.sexo, tipo=NEW.tipo,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `deleteFields`;
DELIMITER ;;
CREATE TRIGGER `deleteFields` AFTER DELETE ON `fields` FOR EACH ROW BEGIN
INSERT INTO juridico_001.fields_el SET id=OLD.id,acta_id=OLD.acta_id,
nombre=OLD.nombre, num=OLD.num, sexo=OLD.sexo, tipo=OLD.tipo,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


-- ----------------------------
-- Table structure for fields2
-- ----------------------------
DROP TABLE IF EXISTS `fields2`;
CREATE TABLE `fields2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `acta_id` int(11) NOT NULL,
  `nombre` varchar(55) NOT NULL,
  `nombre2` varchar(55) NOT NULL,
  `num` int(11) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2FE2F079DA0615CA` (`acta_id`),
  CONSTRAINT `FK2FE2F079DA0615CA` FOREIGN KEY (`acta_id`) REFERENCES `tipoactas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaFields2`;
DELIMITER ;;
CREATE TRIGGER `insertaFields2` AFTER INSERT ON `fields2` FOR EACH ROW BEGIN
INSERT INTO juridico_001.fields2 SET id=NEW.id,acta_id=NEW.acta_id,
nombre=NEW.nombre, nombre2=NEW.nombre2, num=NEW.num, tipo=NEW.tipo,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateFields2`;
DELIMITER ;;
CREATE TRIGGER `updateFields2` AFTER UPDATE ON `fields2` FOR EACH ROW BEGIN
INSERT INTO juridico_001.fields2 SET id=NEW.id,acta_id=NEW.acta_id,
nombre=NEW.nombre, nombre2=NEW.nombre2, num=NEW.num, tipo=NEW.tipo,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `deleteFields2`;
DELIMITER ;;
CREATE TRIGGER `deleteFields2` AFTER DELETE ON `fields2` FOR EACH ROW BEGIN
INSERT INTO juridico_001.fields2_el SET id=OLD.id,acta_id=OLD.acta_id,
nombre=OLD.nombre, nombre2=OLD.nombre2, num=OLD.num, tipo=OLD.tipo,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

-- ----------------------------
-- Table structure for jefeaclaraciones
-- ----------------------------
DROP TABLE IF EXISTS `jefeaclaraciones`;
CREATE TABLE `jefeaclaraciones` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `activo` bit(1) NOT NULL,
  `ape_mat` varchar(70) DEFAULT NULL,
  `ape_pat` varchar(70) NOT NULL,
  `fin` datetime DEFAULT NULL,
  `inicio` datetime NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaJefeAclaraciones`;
DELIMITER ;;
CREATE TRIGGER `insertaJefeAclaraciones` AFTER INSERT ON `jefeaclaraciones` FOR EACH ROW BEGIN
INSERT INTO juridico_001.jefeaclaraciones SET id=NEW.id,version=NEW.version,
activo=NEW.activo, ape_mat=NEW.ape_mat, ape_pat=NEW.ape_pat, fin=NEW.fin, inicio=NEW.inicio,
nombre=NEW.nombre, titulo=NEW.titulo,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateJefeAclaraciones`;
DELIMITER ;;
CREATE TRIGGER `updateJefeAclaraciones` AFTER UPDATE ON `jefeaclaraciones` FOR EACH ROW BEGIN
INSERT INTO juridico_001.jefeaclaraciones SET id=NEW.id,version=NEW.version,
activo=NEW.activo, ape_mat=NEW.ape_mat, ape_pat=NEW.ape_pat, fin=NEW.fin, inicio=NEW.inicio,
nombre=NEW.nombre, titulo=NEW.titulo,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `deleteJefeAclaraciones`;
DELIMITER ;;
CREATE TRIGGER `deleteJefeAclaraciones` AFTER DELETE ON `jefeaclaraciones` FOR EACH ROW BEGIN
INSERT INTO juridico_001.jefeaclaraciones_el SET id=OLD.id,version=OLD.version,
activo=OLD.activo, ape_mat=OLD.ape_mat, ape_pat=OLD.ape_pat, fin=OLD.fin, inicio=OLD.inicio,
nombre=OLD.nombre, titulo=OLD.titulo,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

-- ----------------------------
-- Table structure for jefearchivo
-- ----------------------------
DROP TABLE IF EXISTS `jefearchivo`;
CREATE TABLE `jefearchivo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `activo` bit(1) NOT NULL,
  `ape_mat` varchar(70) DEFAULT NULL,
  `ape_pat` varchar(70) NOT NULL,
  `fin` datetime DEFAULT NULL,
  `inicio` datetime NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaJefeArchivo`;
DELIMITER ;;
CREATE TRIGGER `insertaJefeArchivo` AFTER INSERT ON `jefearchivo` FOR EACH ROW BEGIN
INSERT INTO juridico_001.jefearchivo SET id=NEW.id,version=NEW.version,
activo=NEW.activo, ape_mat=NEW.ape_mat, ape_pat=NEW.ape_pat, fin=NEW.fin, inicio=NEW.inicio,
nombre=NEW.nombre, titulo=NEW.titulo,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateJefeArchivo`;
DELIMITER ;;
CREATE TRIGGER `updateJefeArchivo` AFTER UPDATE ON `jefearchivo` FOR EACH ROW BEGIN
INSERT INTO juridico_001.jefearchivo SET id=NEW.id,version=NEW.version,
activo=NEW.activo, ape_mat=NEW.ape_mat, ape_pat=NEW.ape_pat, fin=NEW.fin, inicio=NEW.inicio,
nombre=NEW.nombre, titulo=NEW.titulo,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `deleteJefeArchivo`;
DELIMITER ;;
CREATE TRIGGER `deleteJefeArchivo` AFTER DELETE ON `jefearchivo` FOR EACH ROW BEGIN
INSERT INTO juridico_001.jefearchivo_el SET id=OLD.id,version=OLD.version,
activo=OLD.activo, ape_mat=OLD.ape_mat, ape_pat=OLD.ape_pat, fin=OLD.fin, inicio=OLD.inicio,
nombre=OLD.nombre, titulo=OLD.titulo,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

-- ----------------------------
-- Table structure for jefejuridico
-- ----------------------------
DROP TABLE IF EXISTS `jefejuridico`;
CREATE TABLE `jefejuridico` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `activo` bit(1) NOT NULL,
  `ape_mat` varchar(70) DEFAULT NULL,
  `ape_pat` varchar(70) NOT NULL,
  `fin` datetime DEFAULT NULL,
  `inicio` datetime NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaJefeJuridico`;
DELIMITER ;;
CREATE TRIGGER `insertaJefeJuridico` AFTER INSERT ON `jefejuridico` FOR EACH ROW BEGIN
INSERT INTO juridico_001.jefejuridico SET id=NEW.id,version=NEW.version,
activo=NEW.activo, ape_mat=NEW.ape_mat, ape_pat=NEW.ape_pat, fin=NEW.fin, inicio=NEW.inicio,
nombre=NEW.nombre, titulo=NEW.titulo,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `updateJefeJuridico`;
DELIMITER ;;
CREATE TRIGGER `updateJefeJuridico` AFTER UPDATE ON `jefejuridico` FOR EACH ROW BEGIN
INSERT INTO juridico_001.jefejuridico SET id=NEW.id,version=NEW.version,
activo=NEW.activo, ape_mat=NEW.ape_mat, ape_pat=NEW.ape_pat, fin=NEW.fin, inicio=NEW.inicio,
nombre=NEW.nombre, titulo=NEW.titulo,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `deleteJefeJuridico`;
DELIMITER ;;
CREATE TRIGGER `deleteJefeJuridico` AFTER DELETE ON `jefejuridico` FOR EACH ROW BEGIN
INSERT INTO juridico_001.jefejuridico_el SET id=OLD.id,version=OLD.version,
activo=OLD.activo, ape_mat=OLD.ape_mat, ape_pat=OLD.ape_pat, fin=OLD.fin, inicio=OLD.inicio,
nombre=OLD.nombre, titulo=OLD.titulo,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

-- ----------------------------
-- Table structure for localidades
-- ----------------------------
DROP TABLE IF EXISTS `localidades`;
CREATE TABLE `localidades` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `loc_clave` int(11) NOT NULL,
  `localidad` varchar(60) NOT NULL,
  `municipio` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK791906D72E9944D9` (`municipio`),
  CONSTRAINT `FK791906D72E9944D9` FOREIGN KEY (`municipio`) REFERENCES `scampo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12733 DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaLocalidades`;
DELIMITER ;;
CREATE TRIGGER `insertaLocalidades` AFTER INSERT ON `localidades` FOR EACH ROW BEGIN
INSERT INTO juridico_001.localidades SET id=NEW.id,loc_clave=NEW.loc_clave,
localidad=NEW.localidad, municipio=NEW.municipio,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateLocalidades`;
DELIMITER ;;
CREATE TRIGGER `updateLocalidades` AFTER UPDATE ON `localidades` FOR EACH ROW BEGIN
INSERT INTO juridico_001.localidades SET id=NEW.id,loc_clave=NEW.loc_clave,
localidad=NEW.localidad, municipio=NEW.municipio,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `deleteLocalidades`;
DELIMITER ;;
CREATE TRIGGER `deleteLocalidades` AFTER DELETE ON `localidades` FOR EACH ROW BEGIN
INSERT INTO juridico_001.localidades_el SET id=OLD.id,loc_clave=OLD.loc_clave,
localidad=OLD.localidad, municipio=OLD.municipio,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

-- ----------------------------
-- Table structure for localidadofi
-- ----------------------------
DROP TABLE IF EXISTS `localidadofi`;
CREATE TABLE `localidadofi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `localidad` bigint(20) NOT NULL,
  `munpio` bigint(20) NOT NULL,
  `oficialia` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAA07F8692E85270D` (`oficialia`),
  KEY `FKAA07F869E07258FC` (`munpio`),
  KEY `FKAA07F86957CCEE6F` (`localidad`),
  CONSTRAINT `FKAA07F8692E85270D` FOREIGN KEY (`oficialia`) REFERENCES `scaofi` (`id`),
  CONSTRAINT `FKAA07F86957CCEE6F` FOREIGN KEY (`localidad`) REFERENCES `localidades` (`id`),
  CONSTRAINT `FKAA07F869E07258FC` FOREIGN KEY (`munpio`) REFERENCES `scampo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaLocalidadofi`;
DELIMITER ;;
CREATE TRIGGER `insertaLocalidadofi` AFTER INSERT ON `localidadofi` FOR EACH ROW BEGIN
INSERT INTO juridico_001.localidadofi SET id=NEW.id,localidad=NEW.localidad,
munpio=NEW.munpio, oficialia=NEW.oficialia,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateLocalidadofi`;
DELIMITER ;;
CREATE TRIGGER `updateLocalidadofi` AFTER UPDATE ON `localidadofi` FOR EACH ROW BEGIN
INSERT INTO juridico_001.localidadofi SET id=NEW.id,localidad=NEW.localidad,
munpio=NEW.munpio, oficialia=NEW.oficialia,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `deleteLocalidadofi`;
DELIMITER ;;
CREATE TRIGGER `deleteLocalidadofi` AFTER DELETE ON `localidadofi` FOR EACH ROW BEGIN
INSERT INTO juridico_001.localidadofi_el SET id=OLD.id,localidad=OLD.localidad,
munpio=OLD.munpio, oficialia=OLD.oficialia,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

-- ----------------------------
-- Table structure for movimiento
-- ----------------------------
DROP TABLE IF EXISTS `movimiento`;
CREATE TABLE `movimiento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `entrada` datetime DEFAULT NULL,
  `fecha` datetime NOT NULL,
  `numero_expediente` varchar(255) NOT NULL,
  `salida` datetime DEFAULT NULL,
  `usuarioentrega` varchar(255) NOT NULL,
  `usuariopresta_id` bigint(20) DEFAULT NULL,
  `usuariorecibe_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA121BC954C33C267` (`usuariorecibe_id`),
  KEY `FKA121BC95E5F2EE06` (`usuariopresta_id`),
  CONSTRAINT `FKA121BC954C33C267` FOREIGN KEY (`usuariorecibe_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKA121BC95E5F2EE06` FOREIGN KEY (`usuariopresta_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaMovimiento`;
DELIMITER ;;
CREATE TRIGGER `insertaMovimiento` AFTER INSERT ON `movimiento` FOR EACH ROW BEGIN
INSERT INTO juridico_001.movimiento SET id=NEW.id,version=NEW.version,
entrada=NEW.entrada, fecha=NEW.fecha, numero_expediente=NEW.numero_expediente, 
salida=NEW.salida, usuarioentrega=NEW.usuarioentrega, usuariopresta_id=NEW.usuariopresta_id,
usuariorecibe_id=NEW.usuariorecibe_id,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateMovimiento`;
DELIMITER ;;
CREATE TRIGGER `updateMovimiento` AFTER UPDATE ON `movimiento` FOR EACH ROW BEGIN
INSERT INTO juridico_001.movimiento SET id=NEW.id,version=NEW.version,
entrada=NEW.entrada, fecha=NEW.fecha, numero_expediente=NEW.numero_expediente, 
salida=NEW.salida, usuarioentrega=NEW.usuarioentrega, usuariopresta_id=NEW.usuariopresta_id,
usuariorecibe_id=NEW.usuariorecibe_id,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `deleteMovimiento`;
DELIMITER ;;
CREATE TRIGGER `deleteMovimiento` AFTER DELETE ON `movimiento` FOR EACH ROW BEGIN
INSERT INTO juridico_001.movimiento_el SET id=OLD.id,version=OLD.version,
entrada=OLD.entrada, fecha=OLD.fecha, numero_expediente=OLD.numero_expediente, 
salida=OLD.salida, usuarioentrega=OLD.usuarioentrega, usuariopresta_id=OLD.usuariopresta_id,
usuariorecibe_id=OLD.usuariorecibe_id,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

-- ----------------------------
-- Table structure for nacionalidad
-- ----------------------------
DROP TABLE IF EXISTS `nacionalidad`;
CREATE TABLE `nacionalidad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clave` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaNacionalidad`;
DELIMITER ;;
CREATE TRIGGER `insertaNacionalidad` AFTER INSERT ON `nacionalidad` FOR EACH ROW BEGIN
INSERT INTO juridico_001.nacionalidad SET id=NEW.id,clave=NEW.clave,
nombre=NEW.nombre,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateNacionalidad`;
DELIMITER ;;
CREATE TRIGGER `updateNacionalidad` AFTER UPDATE ON `nacionalidad` FOR EACH ROW BEGIN
INSERT INTO juridico_001.nacionalidad SET id=NEW.id,clave=NEW.clave,
nombre=NEW.nombre,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `deleteNacionalidad`;
DELIMITER ;;
CREATE TRIGGER `deleteNacionalidad` AFTER DELETE ON `nacionalidad` FOR EACH ROW BEGIN
INSERT INTO juridico_001.nacionalidad_el SET id=OLD.id,clave=OLD.clave,
nombre=OLD.nombre,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


-- ----------------------------
-- Table structure for nota
-- ----------------------------
DROP TABLE IF EXISTS `nota`;
CREATE TABLE `nota` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dic` varchar(20) DEFAULT NULL,
  `expano` int(11) NOT NULL,
  `expro` int(11) NOT NULL,
  `idn` char(1) NOT NULL,
  `namofi` varchar(50) DEFAULT NULL,
  `nota` mediumtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaNota`;
DELIMITER ;;
CREATE TRIGGER `insertaNota` AFTER INSERT ON `nota` FOR EACH ROW BEGIN
INSERT INTO juridico_001.nota SET id=NEW.id,dic=NEW.dic,
expano=NEW.expano, expro=NEW.expro, idn=NEW.idn, namofi=NEW.namofi, nota=NEW.nota,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateNota`;
DELIMITER ;;
CREATE TRIGGER `updateNota` AFTER UPDATE ON `nota` FOR EACH ROW BEGIN
INSERT INTO juridico_001.nota SET id=NEW.id,dic=NEW.dic,
expano=NEW.expano, expro=NEW.expro, idn=NEW.idn, namofi=NEW.namofi, nota=NEW.nota,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;



DROP TRIGGER IF EXISTS `deleteNota`;
DELIMITER ;;
CREATE TRIGGER `deleteNota` AFTER DELETE ON `nota` FOR EACH ROW BEGIN
INSERT INTO juridico_001.nota_el SET id=OLD.id,dic=OLD.dic,
expano=OLD.expano, expro=OLD.expro, idn=OLD.idn, namofi=OLD.namofi, nota=OLD.nota,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

-- ----------------------------
-- Table structure for opcion
-- ----------------------------
DROP TABLE IF EXISTS `opcion`;
CREATE TABLE `opcion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `error_id` bigint(20) DEFAULT NULL,
  `exapro` int(11) NOT NULL,
  `tablaid` int(11) NOT NULL,
  `tipo` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC3C2CE2636596F59` (`error_id`),
  CONSTRAINT `FKC3C2CE2636596F59` FOREIGN KEY (`error_id`) REFERENCES `tcorrect` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaOpcion`;
DELIMITER ;;
CREATE TRIGGER `insertaOpcion` AFTER INSERT ON `opcion` FOR EACH ROW BEGIN
INSERT INTO juridico_001.opcion SET id=NEW.id,version=NEW.version,
error_id=NEW.error_id, exapro=NEW.exapro, tablaid=NEW.tablaid, tipo=NEW.tipo,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateOpcion`;
DELIMITER ;;
CREATE TRIGGER `updateOpcion` AFTER UPDATE ON `opcion` FOR EACH ROW BEGIN
INSERT INTO juridico_001.opcion SET id=NEW.id,version=NEW.version,
error_id=NEW.error_id, exapro=NEW.exapro, tablaid=NEW.tablaid, tipo=NEW.tipo,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `deleteOpcion`;
DELIMITER ;;
CREATE TRIGGER `deleteOpcion` AFTER DELETE ON `opcion` FOR EACH ROW BEGIN
INSERT INTO juridico_001.opcion_el SET id=OLD.id,version=OLD.version,
error_id=OLD.error_id, exapro=OLD.exapro, tablaid=OLD.tablaid, tipo=OLD.tipo,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

-- ----------------------------
-- Table structure for papeleta
-- ----------------------------
DROP TABLE IF EXISTS `papeleta`;
CREATE TABLE `papeleta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expano` int(11) NOT NULL,
  `expro` int(11) NOT NULL,
  `nota` mediumtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TRIGGER IF EXISTS `insertaPapeleta`;
DELIMITER ;;
CREATE TRIGGER `insertaPapeleta` AFTER INSERT ON `papeleta` FOR EACH ROW BEGIN
INSERT INTO juridico_001.papeleta SET id=NEW.id,expano=NEW.expano,expro=NEW.expro,
nota=NEW.nota,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updatePapeleta`;
DELIMITER ;;
CREATE TRIGGER `updatePapeleta` AFTER UPDATE ON `papeleta` FOR EACH ROW BEGIN
INSERT INTO juridico_001.papeleta SET id=NEW.id,expano=NEW.expano,expro=NEW.expro,
nota=NEW.nota,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `deletePapeleta`;
DELIMITER ;;
CREATE TRIGGER `deletePapeleta` AFTER DELETE ON `papeleta` FOR EACH ROW BEGIN
INSERT INTO juridico_001.papeleta_el SET id=OLD.id,expano=OLD.expano,expro=OLD.expro,
nota=OLD.nota,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;



-- ----------------------------
-- Table structure for resguardo
-- ----------------------------
DROP TABLE IF EXISTS `resguardo`;
CREATE TABLE `resguardo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `entrega` varchar(255) NOT NULL,
  `fecha_entrada` datetime NOT NULL,
  `numero_expediente` varchar(255) NOT NULL,
  `papeleta` bit(1) NOT NULL,
  `resolucion` bit(1) NOT NULL,
  `ubicacion` varchar(255) NOT NULL,
  `usuario_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7C1E770A30134943` (`usuario_id`),
  CONSTRAINT `FK7C1E770A30134943` FOREIGN KEY (`usuario_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaResguardo`;
DELIMITER ;;
CREATE TRIGGER `insertaResguardo` AFTER INSERT ON `resguardo` FOR EACH ROW BEGIN
INSERT INTO juridico_001.resguardo SET id=NEW.id,version=NEW.version,entrega=NEW.entrega,
fecha_entrada=NEW.fecha_entrada,numero_expediente=NEW.numero_expediente, papeleta=NEW.papeleta,
resolucion=NEW.resolucion, ubicacion=NEW.ubicacion, usuario_id=NEW.usuario_id,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateResguardo`;
DELIMITER ;;
CREATE TRIGGER `updateResguardo` AFTER UPDATE ON `resguardo` FOR EACH ROW BEGIN
INSERT INTO juridico_001.resguardo SET id=NEW.id,version=NEW.version,entrega=NEW.entrega,
fecha_entrada=NEW.fecha_entrada,numero_expediente=NEW.numero_expediente, papeleta=NEW.papeleta,
resolucion=NEW.resolucion, ubicacion=NEW.ubicacion, usuario_id=NEW.usuario_id,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `deleteResguardo`;
DELIMITER ;;
CREATE TRIGGER `deleteResguardo` AFTER DELETE ON `resguardo` FOR EACH ROW BEGIN
INSERT INTO juridico_001.resguardo_el SET id=OLD.id,version=OLD.version,entrega=OLD.entrega,
fecha_entrada=OLD.fecha_entrada,numero_expediente=OLD.numero_expediente, papeleta=OLD.papeleta,
resolucion=OLD.resolucion, ubicacion=OLD.ubicacion, usuario_id=OLD.usuario_id,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `authority` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `authority` (`authority`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaRole`;
DELIMITER ;;
CREATE TRIGGER `insertaRole` AFTER INSERT ON `role` FOR EACH ROW BEGIN
INSERT INTO juridico_001.role SET id=NEW.id,version=NEW.version,authority=NEW.authority,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateRole`;
DELIMITER ;;
CREATE TRIGGER `updateRole` AFTER UPDATE ON `role` FOR EACH ROW BEGIN
INSERT INTO juridico_001.role SET id=NEW.id,version=NEW.version,authority=NEW.authority,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;



DROP TRIGGER IF EXISTS `deleteRole`;
DELIMITER ;;
CREATE TRIGGER `deleteRole` AFTER DELETE ON `role` FOR EACH ROW BEGIN
INSERT INTO juridico_001.role_el SET id=OLD.id,version=OLD.version,authority=OLD.authority,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

-- ----------------------------
-- Table structure for scaact
-- ----------------------------
DROP TABLE IF EXISTS `scaact`;
CREATE TABLE `scaact` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bases` varchar(80) NOT NULL,
  `datofalta_id` bigint(20) DEFAULT NULL,
  `donde_id` bigint(20) DEFAULT NULL,
  `dto_id` bigint(20) DEFAULT NULL,
  `expano` int(11) NOT NULL,
  `exppro` int(11) NOT NULL,
  `fechaacta` datetime DEFAULT NULL,
  `loc_id` bigint(20) DEFAULT NULL,
  `mab1` varchar(40) DEFAULT NULL,
  `mab1ap1` varchar(40) DEFAULT NULL,
  `mab1ap2` varchar(40) DEFAULT NULL,
  `mab1nac_id` bigint(20) DEFAULT NULL,
  `mab2ap1` varchar(40) DEFAULT NULL,
  `mab2ap2` varchar(40) DEFAULT NULL,
  `mab2nac_id` bigint(20) DEFAULT NULL,
  `map1` varchar(40) DEFAULT NULL,
  `map2` varchar(40) DEFAULT NULL,
  `mba2` varchar(255) DEFAULT NULL,
  `medad` int(11) DEFAULT NULL,
  `mnac_id` bigint(20) DEFAULT NULL,
  `mnom` varchar(40) DEFAULT NULL,
  `mpo_id` bigint(20) DEFAULT NULL,
  `numacta` int(11) DEFAULT NULL,
  `pab1` varchar(40) DEFAULT NULL,
  `pab1ap1` varchar(40) DEFAULT NULL,
  `pab1ap2` varchar(40) DEFAULT NULL,
  `pab1nac_id` bigint(20) DEFAULT NULL,
  `pab2` varchar(40) DEFAULT NULL,
  `pab2ap1` varchar(40) DEFAULT NULL,
  `pab2ap2` varchar(40) DEFAULT NULL,
  `pab2nac_id` bigint(20) DEFAULT NULL,
  `pap1` varchar(40) DEFAULT NULL,
  `pap2` varchar(40) DEFAULT NULL,
  `pedad` int(11) DEFAULT NULL,
  `pnac_id` bigint(20) DEFAULT NULL,
  `pnombre` varchar(255) DEFAULT NULL,
  `procede` varchar(2) NOT NULL,
  `tipoerresp` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC9DDF6E14B1627D` (`pab2nac_id`),
  KEY `FKC9DDF6E1E02C17FA` (`mpo_id`),
  KEY `FKC9DDF6E1F2EED3DF` (`mab1nac_id`),
  KEY `FKC9DDF6E123101EC` (`pnac_id`),
  KEY `FKC9DDF6E127D50F20` (`mab2nac_id`),
  KEY `FKC9DDF6E166D557BB` (`donde_id`),
  KEY `FKC9DDF6E169E1C045` (`tipoerresp`),
  KEY `FKC9DDF6E16BA9BBF1` (`datofalta_id`),
  KEY `FKC9DDF6E1CFCB273C` (`pab1nac_id`),
  KEY `FKC9DDF6E1637E5029` (`mnac_id`),
  KEY `FKC9DDF6E15E2F8480` (`loc_id`),
  KEY `FKC9DDF6E1D108B71A` (`dto_id`),
  CONSTRAINT `FKC9DDF6E123101EC` FOREIGN KEY (`pnac_id`) REFERENCES `nacionalidad` (`id`),
  CONSTRAINT `FKC9DDF6E127D50F20` FOREIGN KEY (`mab2nac_id`) REFERENCES `nacionalidad` (`id`),
  CONSTRAINT `FKC9DDF6E14B1627D` FOREIGN KEY (`pab2nac_id`) REFERENCES `nacionalidad` (`id`),
  CONSTRAINT `FKC9DDF6E15E2F8480` FOREIGN KEY (`loc_id`) REFERENCES `localidades` (`id`),
  CONSTRAINT `FKC9DDF6E1637E5029` FOREIGN KEY (`mnac_id`) REFERENCES `nacionalidad` (`id`),
  CONSTRAINT `FKC9DDF6E166D557BB` FOREIGN KEY (`donde_id`) REFERENCES `terror` (`id`),
  CONSTRAINT `FKC9DDF6E169E1C045` FOREIGN KEY (`tipoerresp`) REFERENCES `erroresperado` (`id`),
  CONSTRAINT `FKC9DDF6E16BA9BBF1` FOREIGN KEY (`datofalta_id`) REFERENCES `campo` (`id`),
  CONSTRAINT `FKC9DDF6E1CFCB273C` FOREIGN KEY (`pab1nac_id`) REFERENCES `nacionalidad` (`id`),
  CONSTRAINT `FKC9DDF6E1D108B71A` FOREIGN KEY (`dto_id`) REFERENCES `scadto` (`id`),
  CONSTRAINT `FKC9DDF6E1E02C17FA` FOREIGN KEY (`mpo_id`) REFERENCES `scampo` (`id`),
  CONSTRAINT `FKC9DDF6E1F2EED3DF` FOREIGN KEY (`mab1nac_id`) REFERENCES `nacionalidad` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaScaact`;
DELIMITER ;;
CREATE TRIGGER `insertaScaact` AFTER INSERT ON `scaact` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scaact SET id=NEW.id,bases=NEW.bases,
datofalta_id=NEW.datofalta_id, donde_id=NEW.donde_id, dto_id=NEW.dto_id, expano=NEW.expano,
exppro=NEW.exppro, fechaacta=NEW.fechaacta, loc_id=NEW.loc_id, mab1=NEW.mab1,
mab1ap1=NEW.mab1ap1, mab1ap2=NEW.mab1ap2,mab1nac_id=NEW.mab1nac_id,
mab2ap1=NEW.mab2ap1, mab2ap2=NEW.mab2ap2, mab2nac_id=NEW.mab2nac_id, map1=NEW.map1,
map2=NEW.map2, mba2=NEW.mba2, medad=NEW.medad, mnac_id=NEW.mnac_id,
mnom=NEW.mnom,mpo_id=NEW.mpo_id, numacta=NEW.numacta, pab1=NEW.pab1, 
pab1ap1=NEW.pab1ap1, pab1ap2=NEW.pab1ap2, pab1nac_id=NEW.pab1nac_id, pap1=NEW.pap1,
pap2=NEW.pap2, pedad=NEW.pedad, pnac_id=NEW.pnac_id, pnombre=NEW.pnombre,
procede=NEW.procede,tipoerresp=NEW.tipoerresp,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateScaact`;
DELIMITER ;;
CREATE TRIGGER `updateScaact` AFTER UPDATE ON `scaact` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scaact SET id=NEW.id,bases=NEW.bases,
datofalta_id=NEW.datofalta_id, donde_id=NEW.donde_id, dto_id=NEW.dto_id, expano=NEW.expano,
exppro=NEW.exppro, fechaacta=NEW.fechaacta, loc_id=NEW.loc_id, mab1=NEW.mab1,
mab1ap1=NEW.mab1ap1, mab1ap2=NEW.mab1ap2,mab1nac_id=NEW.mab1nac_id,
mab2ap1=NEW.mab2ap1, mab2ap2=NEW.mab2ap2, mab2nac_id=NEW.mab2nac_id, map1=NEW.map1,
map2=NEW.map2, mba2=NEW.mba2, medad=NEW.medad, mnac_id=NEW.mnac_id,
mnom=NEW.mnom,mpo_id=NEW.mpo_id, numacta=NEW.numacta, pab1=NEW.pab1, 
pab1ap1=NEW.pab1ap1, pab1ap2=NEW.pab1ap2, pab1nac_id=NEW.pab1nac_id, pap1=NEW.pap1,
pap2=NEW.pap2, pedad=NEW.pedad, pnac_id=NEW.pnac_id, pnombre=NEW.pnombre,
procede=NEW.procede,tipoerresp=NEW.tipoerresp,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `deleteScaact`;
DELIMITER ;;
CREATE TRIGGER `deleteScaact` AFTER DELETE ON `scaact` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scaact_el SET id=OLD.id,bases=OLD.bases,
datofalta_id=OLD.datofalta_id, donde_id=OLD.donde_id, dto_id=OLD.dto_id, expano=OLD.expano,
exppro=OLD.exppro, fechaacta=OLD.fechaacta, loc_id=OLD.loc_id, mab1=OLD.mab1,
mab1ap1=OLD.mab1ap1, mab1ap2=OLD.mab1ap2,mab1nac_id=OLD.mab1nac_id,
mab2ap1=OLD.mab2ap1, mab2ap2=OLD.mab2ap2, mab2nac_id=OLD.mab2nac_id, map1=OLD.map1,
map2=OLD.map2, mba2=OLD.mba2, medad=OLD.medad, mnac_id=OLD.mnac_id,
mnom=OLD.mnom,mpo_id=OLD.mpo_id, numacta=OLD.numacta, pab1=OLD.pab1, 
pab1ap1=OLD.pab1ap1, pab1ap2=OLD.pab1ap2, pab1nac_id=OLD.pab1nac_id, pap1=OLD.pap1,
pap2=OLD.pap2, pedad=OLD.pedad, pnac_id=OLD.pnac_id, pnombre=OLD.pnombre,
procede=OLD.procede,tipoerresp=OLD.tipoerresp,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;




-- ----------------------------
-- Table structure for scadto
-- ----------------------------
DROP TABLE IF EXISTS `scadto`;
CREATE TABLE `scadto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clv` int(11) NOT NULL,
  `clvreg` int(11) NOT NULL,
  `descc` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaScadto`;
DELIMITER ;;
CREATE TRIGGER `insertaScadto` AFTER INSERT ON `scadto` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scadto SET id=NEW.id,clv=NEW.clv,clvreg=NEW.clvreg,
descc=NEW.descc,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateScadto`;
DELIMITER ;;
CREATE TRIGGER `updateScadto` AFTER UPDATE ON `scadto` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scadto SET id=NEW.id,clv=NEW.clv,clvreg=NEW.clvreg,
descc=NEW.descc,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `deleteScadto`;
DELIMITER ;;
CREATE TRIGGER `deleteScadto` AFTER DELETE ON `scadto` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scadto_el SET id=OLD.id,clv=OLD.clv,clvreg=OLD.clvreg,
descc=OLD.descc,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


-- ----------------------------
-- Table structure for scaerr
-- ----------------------------
DROP TABLE IF EXISTS `scaerr`;
CREATE TABLE `scaerr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `base` varchar(100) NOT NULL,
  `campo_id` int(11) DEFAULT NULL,
  `contiene` varchar(100) NOT NULL,
  `debeser` varchar(100) NOT NULL,
  `donde` int(11) NOT NULL,
  `expano` int(11) NOT NULL,
  `expro` int(11) NOT NULL,
  `procede` varchar(2) NOT NULL,
  `tcorrect_id` bigint(20) NOT NULL,
  `terror_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC9DE07B4DC0BCEC7` (`tcorrect_id`),
  KEY `FKC9DE07B41A43F16` (`campo_id`),
  KEY `FKC9DE07B45595A93A` (`terror_id`),
  CONSTRAINT `FKC9DE07B41A43F16` FOREIGN KEY (`campo_id`) REFERENCES `fields` (`id`),
  CONSTRAINT `FKC9DE07B45595A93A` FOREIGN KEY (`terror_id`) REFERENCES `erroresperado` (`id`),
  CONSTRAINT `FKC9DE07B4DC0BCEC7` FOREIGN KEY (`tcorrect_id`) REFERENCES `tipoerror` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaScaerr`;
DELIMITER ;;
CREATE TRIGGER `insertaScaerr` AFTER INSERT ON `scaerr` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scaerr SET id=NEW.id,base=NEW.base,campo_id=NEW.campo_id,
contiene=NEW.contiene,debeser=NEW.debeser,donde=NEW.donde,expano=NEW.expano,
expro=NEW.expro, procede=NEW.procede, tcorrect_id=NEW.tcorrect_id, terror_id=NEW.terror_id,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;



DROP TRIGGER IF EXISTS `updateScaerr`;
DELIMITER ;;
CREATE TRIGGER `updateScaerr` AFTER UPDATE ON `scaerr` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scaerr SET id=NEW.id,base=NEW.base,campo_id=NEW.campo_id,
contiene=NEW.contiene,debeser=NEW.debeser,donde=NEW.donde,expano=NEW.expano,
expro=NEW.expro, procede=NEW.procede, tcorrect_id=NEW.tcorrect_id, terror_id=NEW.terror_id,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;



DROP TRIGGER IF EXISTS `deleteScaerr`;
DELIMITER ;;
CREATE TRIGGER `deleteScaerr` AFTER DELETE ON `scaerr` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scaerr_el SET id=OLD.id,base=OLD.base,campo_id=OLD.campo_id,
contiene=OLD.contiene,debeser=OLD.debeser,donde=OLD.donde,expano=OLD.expano,
expro=OLD.expro, procede=OLD.procede, tcorrect_id=OLD.tcorrect_id, terror_id=OLD.terror_id,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


-- ----------------------------
-- Table structure for scampo
-- ----------------------------
DROP TABLE IF EXISTS `scampo`;
CREATE TABLE `scampo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descrip` varchar(60) NOT NULL,
  `distrito_id` bigint(20) NOT NULL,
  `mpo` int(11) NOT NULL,
  `oficialia_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9343119BB6625F31` (`oficialia_id`),
  KEY `FK9343119B7052C0C1` (`distrito_id`),
  CONSTRAINT `FK9343119B7052C0C1` FOREIGN KEY (`distrito_id`) REFERENCES `scadto` (`id`),
  CONSTRAINT `FK9343119BB6625F31` FOREIGN KEY (`oficialia_id`) REFERENCES `scaofi` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=571 DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaSCampo`;
DELIMITER ;;
CREATE TRIGGER `insertaSCampo` AFTER INSERT ON `scampo` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scampo SET id=NEW.id,descrip=NEW.descrip,distrito_id=NEW.distrito_id,
mpo=NEW.mpo,oficialia_id=NEW.oficialia_id,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateSCampo`;
DELIMITER ;;
CREATE TRIGGER `updateSCampo` AFTER UPDATE ON `scampo` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scampo SET id=NEW.id,descrip=NEW.descrip,distrito_id=NEW.distrito_id,
mpo=NEW.mpo,oficialia_id=NEW.oficialia_id,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `deleteSCampo`;
DELIMITER ;;
CREATE TRIGGER `deleteSCampo` AFTER DELETE ON `scampo` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scampo_el SET id=OLD.id,descrip=OLD.descrip,distrito_id=OLD.distrito_id,
mpo=OLD.mpo,oficialia_id=OLD.oficialia_id,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

-- ----------------------------
-- Table structure for scamposcaofi
-- ----------------------------
DROP TABLE IF EXISTS `scamposcaofi`;
CREATE TABLE `scamposcaofi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `municipio` bigint(20) NOT NULL,
  `oficialia` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKF0365D1C2E85270D` (`oficialia`),
  KEY `FKF0365D1C2E9944D9` (`municipio`),
  CONSTRAINT `FKF0365D1C2E85270D` FOREIGN KEY (`oficialia`) REFERENCES `scaofi` (`id`),
  CONSTRAINT `FKF0365D1C2E9944D9` FOREIGN KEY (`municipio`) REFERENCES `scampo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaScamposcaofi`;
DELIMITER ;;
CREATE TRIGGER `insertaScamposcaofi` AFTER INSERT ON `scamposcaofi` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scamposcaofi SET id=NEW.id,municipio=NEW.municipio,oficialia=NEW.oficialia,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;



DROP TRIGGER IF EXISTS `updateScamposcaofi`;
DELIMITER ;;
CREATE TRIGGER `updateScamposcaofi` AFTER UPDATE ON `scamposcaofi` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scamposcaofi SET id=NEW.id,municipio=NEW.municipio,oficialia=NEW.oficialia,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `deleteScamposcaofi`;
DELIMITER ;;
CREATE TRIGGER `deleteScamposcaofi` AFTER DELETE ON `scamposcaofi` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scamposcaofi_el SET id=OLD.id,municipio=OLD.municipio,oficialia=OLD.oficialia,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;



-- ----------------------------
-- Table structure for scaofi
-- ----------------------------
DROP TABLE IF EXISTS `scaofi`;
CREATE TABLE `scaofi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clv_id` bigint(20) NOT NULL,
  `clv2` int(11) NOT NULL,
  `descrip` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK934317E1CEE6510C` (`clv_id`),
  CONSTRAINT `FK934317E1CEE6510C` FOREIGN KEY (`clv_id`) REFERENCES `scadto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaScaofi`;
DELIMITER ;;
CREATE TRIGGER `insertaScaofi` AFTER INSERT ON `scaofi` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scaofi SET id=NEW.id,clv_id=NEW.clv_id,clv2=NEW.clv2,
descrip=NEW.descrip,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateScaofi`;
DELIMITER ;;
CREATE TRIGGER `updateScaofi` AFTER UPDATE ON `scaofi` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scaofi SET id=NEW.id,clv_id=NEW.clv_id,clv2=NEW.clv2,
descrip=NEW.descrip,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;



DROP TRIGGER IF EXISTS `deleteScaofi`;
DELIMITER ;;
CREATE TRIGGER `deleteScaofi` AFTER DELETE ON `scaofi` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scaofi_el SET id=OLD.id,clv_id=OLD.clv_id,clv2=OLD.clv2,
descrip=OLD.descrip,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;



-- ----------------------------
-- Table structure for scaprn
-- ----------------------------
DROP TABLE IF EXISTS `scaprn`;
CREATE TABLE `scaprn` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mimp` char(1) NOT NULL,
  `tlet` int(11) NOT NULL,
  `toja` char(1) NOT NULL,
  `usrid` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TRIGGER IF EXISTS `insertaScaprn`;
DELIMITER ;;
CREATE TRIGGER `insertaScaprn` AFTER INSERT ON `scaprn` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scaprn SET id=NEW.id,mimp=NEW.mimp,tlet=NEW.tlet,
toja=NEW.toja,usrid=NEW.usrid,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `updateScaprn`;
DELIMITER ;;
CREATE TRIGGER `updateScaprn` AFTER UPDATE ON `scaprn` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scaprn SET id=NEW.id,mimp=NEW.mimp,tlet=NEW.tlet,
toja=NEW.toja,usrid=NEW.usrid,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `deleteScaprn`;
DELIMITER ;;
CREATE TRIGGER `deleteScaprn` AFTER DELETE ON `scaprn` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scaprn_el SET id=OLD.id,mimp=OLD.mimp,tlet=OLD.tlet,
toja=OLD.toja,usrid=OLD.usrid,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


-- ----------------------------
-- Table structure for scapro
-- ----------------------------
DROP TABLE IF EXISTS `scapro`;
CREATE TABLE `scapro` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `prog` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaScapro`;
DELIMITER ;;
CREATE TRIGGER `insertaScapro` AFTER INSERT ON `scapro` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scapro SET id=NEW.id,version=NEW.version,prog=NEW.prog,
year=NEW.year,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `updateScapro`;
DELIMITER ;;
CREATE TRIGGER `updateScapro` AFTER UPDATE ON `scapro` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scapro SET id=NEW.id,version=NEW.version,prog=NEW.prog,
year=NEW.year,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `deleteScapro`;
DELIMITER ;;
CREATE TRIGGER `deleteScapro` AFTER DELETE ON `scapro` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scapro_el SET id=OLD.id,version=OLD.version,prog=OLD.prog,
year=OLD.year,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;



-- ----------------------------
-- Table structure for scapro2
-- ----------------------------
DROP TABLE IF EXISTS `scapro2`;
CREATE TABLE `scapro2` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `prog` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaScapro2`;
DELIMITER ;;
CREATE TRIGGER `insertaScapro2` AFTER INSERT ON `scapro2` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scapro2 SET id=NEW.id,version=NEW.version,prog=NEW.prog,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `updateScapro2`;
DELIMITER ;;
CREATE TRIGGER `updateScapro2` AFTER UPDATE ON `scapro2` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scapro2 SET id=NEW.id,version=NEW.version,prog=NEW.prog,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `deleteScapro2`;
DELIMITER ;;
CREATE TRIGGER `deleteScapro2` AFTER DELETE ON `scapro2` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scapro2_el SET id=OLD.id,version=OLD.version,prog=OLD.prog,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


-- ----------------------------
-- Table structure for scasol
-- ----------------------------
DROP TABLE IF EXISTS `scasol`;
CREATE TABLE `scasol` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `anexo` varchar(250) DEFAULT NULL,
  `ap1_intere` varchar(40) DEFAULT NULL,
  `ap2_intere` varchar(40) DEFAULT NULL,
  `apema` varchar(255) NOT NULL,
  `apepa` varchar(255) NOT NULL,
  `cap_id` bigint(20) NOT NULL,
  `condonado` varchar(2) NOT NULL,
  `dic2_id` bigint(20) DEFAULT NULL,
  `dicc_id` bigint(20) DEFAULT NULL,
  `dto_id` bigint(20) DEFAULT NULL,
  `estado_id` bigint(20) DEFAULT NULL,
  `expano` int(11) NOT NULL,
  `expro` int(11) NOT NULL,
  `fchact` date DEFAULT NULL,
  `fchcam` datetime DEFAULT NULL,
  `fchsol` datetime DEFAULT NULL,
  `fechasol` datetime NOT NULL,
  `folio` int(11) NOT NULL,
  `folioexp` varchar(255) DEFAULT NULL,
  `isprint` int(11) NOT NULL,
  `loc_id` bigint(20) DEFAULT NULL,
  `mpo_id` bigint(20) DEFAULT NULL,
  `nom_intere` varchar(40) DEFAULT NULL,
  `nomb` varchar(255) NOT NULL,
  `numact` int(11) NOT NULL,
  `obser` text,
  `ofi_id` bigint(20) DEFAULT NULL,
  `ofi_recibido` int(11) NOT NULL,
  `proced` bit(1) DEFAULT NULL,
  `promov` varchar(65) DEFAULT NULL,
  `sexintere` varchar(1) NOT NULL,
  `typact_id` int(11) DEFAULT NULL,
  `val_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC9DE3BDFA1B22E16` (`estado_id`),
  KEY `FKC9DE3BDF4D8496C` (`dicc_id`),
  KEY `FKC9DE3BDFE3062A3A` (`ofi_id`),
  KEY `FKC9DE3BDFE02C17FA` (`mpo_id`),
  KEY `FKC9DE3BDF4C2033D` (`dic2_id`),
  KEY `FKC9DE3BDF71000C70` (`val_id`),
  KEY `FKC9DE3BDF37A372B2` (`typact_id`),
  KEY `FKC9DE3BDF5E2F8480` (`loc_id`),
  KEY `FKC9DE3BDF5095CA1F` (`cap_id`),
  KEY `FKC9DE3BDFD108B71A` (`dto_id`),
  CONSTRAINT `FKC9DE3BDF37A372B2` FOREIGN KEY (`typact_id`) REFERENCES `tipoactas` (`id`),
  CONSTRAINT `FKC9DE3BDF4C2033D` FOREIGN KEY (`dic2_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKC9DE3BDF4D8496C` FOREIGN KEY (`dicc_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKC9DE3BDF5095CA1F` FOREIGN KEY (`cap_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKC9DE3BDF5E2F8480` FOREIGN KEY (`loc_id`) REFERENCES `localidades` (`id`),
  CONSTRAINT `FKC9DE3BDF71000C70` FOREIGN KEY (`val_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKC9DE3BDFA1B22E16` FOREIGN KEY (`estado_id`) REFERENCES `docesta` (`id`),
  CONSTRAINT `FKC9DE3BDFD108B71A` FOREIGN KEY (`dto_id`) REFERENCES `scadto` (`id`),
  CONSTRAINT `FKC9DE3BDFE02C17FA` FOREIGN KEY (`mpo_id`) REFERENCES `scampo` (`id`),
  CONSTRAINT `FKC9DE3BDFE3062A3A` FOREIGN KEY (`ofi_id`) REFERENCES `scaofi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaScasol`;
DELIMITER ;;
CREATE TRIGGER `insertaScasol` AFTER INSERT ON `scasol` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scasol SET id=NEW.id,anexo=NEW.anexo,
ap1_intere=NEW.ap1_intere, ap2_intere=NEW.ap2_intere, apema=NEW.apema, apepa=NEW.apepa,
cap_id=NEW.cap_id, condonado=NEW.condonado, dic2_id=NEW.dic2_id, dicc_id=NEW.dicc_id,
dto_id=NEW.dto_id, estado_id=NEW.estado_id,expano=NEW.expano,
expro=NEW.expro, fchact=NEW.fchact, fchcam=NEW.fchcam, fchsol=NEW.fchsol,
fechasol=NEW.fechasol, folio=NEW.folio, folioexp=NEW.folioexp, isprint=NEW.isprint,
loc_id=NEW.loc_id,mpo_id=NEW.mpo_id, nom_intere=NEW.nom_intere, nomb=NEW.nomb, 
numact=NEW.numact, obser=NEW.obser, ofi_id=NEW.ofi_id, ofi_recibido=NEW.ofi_recibido,
proced=NEW.proced, promov=NEW.promov, sexintere=NEW.sexintere, typact_id=NEW.typact_id,
val_id=NEW.val_id,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateScasol`;
DELIMITER ;;
CREATE TRIGGER `updateScasol` AFTER UPDATE ON `scasol` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scasol SET id=NEW.id,anexo=NEW.anexo,
ap1_intere=NEW.ap1_intere, ap2_intere=NEW.ap2_intere, apema=NEW.apema, apepa=NEW.apepa,
cap_id=NEW.cap_id, condonado=NEW.condonado, dic2_id=NEW.dic2_id, dicc_id=NEW.dicc_id,
dto_id=NEW.dto_id, estado_id=NEW.estado_id,expano=NEW.expano,
expro=NEW.expro, fchact=NEW.fchact, fchcam=NEW.fchcam, fchsol=NEW.fchsol,
fechasol=NEW.fechasol, folio=NEW.folio, folioexp=NEW.folioexp, isprint=NEW.isprint,
loc_id=NEW.loc_id,mpo_id=NEW.mpo_id, nom_intere=NEW.nom_intere, nomb=NEW.nomb, 
numact=NEW.numact, obser=NEW.obser, ofi_id=NEW.ofi_id, ofi_recibido=NEW.ofi_recibido,
proced=NEW.proced, promov=NEW.promov, sexintere=NEW.sexintere, typact_id=NEW.typact_id,
val_id=NEW.val_id,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `deleteScasol`;
DELIMITER ;;
CREATE TRIGGER `deleteScasol` AFTER DELETE ON `scasol` FOR EACH ROW BEGIN
INSERT INTO juridico_001.scasol_el SET id=OLD.id,anexo=OLD.anexo,
ap1_intere=OLD.ap1_intere, ap2_intere=OLD.ap2_intere, apema=OLD.apema, apepa=OLD.apepa,
cap_id=OLD.cap_id, condonado=OLD.condonado, dic2_id=OLD.dic2_id, dicc_id=OLD.dicc_id,
dto_id=OLD.dto_id, estado_id=OLD.estado_id,expano=OLD.expano,
expro=OLD.expro, fchact=OLD.fchact, fchcam=OLD.fchcam, fchsol=OLD.fchsol,
fechasol=OLD.fechasol, folio=OLD.folio, folioexp=OLD.folioexp, isprint=OLD.isprint,
loc_id=OLD.loc_id,mpo_id=OLD.mpo_id, nom_intere=OLD.nom_intere, nomb=OLD.nomb, 
numact=OLD.numact, obser=OLD.obser, ofi_id=OLD.ofi_id, ofi_recibido=OLD.ofi_recibido,
proced=OLD.proced, promov=OLD.promov, sexintere=OLD.sexintere, typact_id=OLD.typact_id,
val_id=OLD.val_id,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


-- ----------------------------
-- Table structure for tcorrect
-- ----------------------------
DROP TABLE IF EXISTS `tcorrect`;
CREATE TABLE `tcorrect` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `tcorrect` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TRIGGER IF EXISTS `insertaTCorrect`;
DELIMITER ;;
CREATE TRIGGER `insertaTCorrect` AFTER INSERT ON `tcorrect` FOR EACH ROW BEGIN
INSERT INTO juridico_001.tcorrect SET id=NEW.id,version=NEW.version,tcorrect=NEW.tcorrect,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateTCorrect`;
DELIMITER ;;
CREATE TRIGGER `updateTCorrect` AFTER UPDATE ON `tcorrect` FOR EACH ROW BEGIN
INSERT INTO juridico_001.tcorrect SET id=NEW.id,version=NEW.version,tcorrect=NEW.tcorrect,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `deleteTCorrect`;
DELIMITER ;;
CREATE TRIGGER `deleteTCorrect` AFTER DELETE ON `tcorrect` FOR EACH ROW BEGIN
INSERT INTO juridico_001.tcorrect_el SET id=OLD.id,version=OLD.version,tcorrect=OLD.tcorrect,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


-- ----------------------------
-- Table structure for terror
-- ----------------------------
DROP TABLE IF EXISTS `terror`;
CREATE TABLE `terror` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `donde` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaTError`;
DELIMITER ;;
CREATE TRIGGER `insertaTError` AFTER INSERT ON `terror` FOR EACH ROW BEGIN
INSERT INTO juridico_001.terror SET id=NEW.id,donde=NEW.donde,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateTError`;
DELIMITER ;;
CREATE TRIGGER `updateTError` AFTER UPDATE ON `terror` FOR EACH ROW BEGIN
INSERT INTO juridico_001.terror SET id=NEW.id,donde=NEW.donde,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `deleteTError`;
DELIMITER ;;
CREATE TRIGGER `deleteTError` AFTER DELETE ON `terror` FOR EACH ROW BEGIN
INSERT INTO juridico_001.terror_el SET id=OLD.id,donde=OLD.donde,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;



-- ----------------------------
-- Table structure for tipoactas
-- ----------------------------
DROP TABLE IF EXISTS `tipoactas`;
CREATE TABLE `tipoactas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipoacta` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaTipoActas`;
DELIMITER ;;
CREATE TRIGGER `insertaTipoActas` AFTER INSERT ON `tipoactas` FOR EACH ROW BEGIN
INSERT INTO juridico_001.tipoactas SET id=NEW.id,tipoacta=NEW.tipoacta,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateTipoActas`;
DELIMITER ;;
CREATE TRIGGER `updateTipoActas` AFTER UPDATE ON `tipoactas` FOR EACH ROW BEGIN
INSERT INTO juridico_001.tipoactas SET id=NEW.id,tipoacta=NEW.tipoacta,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `deleteTipoActas`;
DELIMITER ;;
CREATE TRIGGER `deleteTipoActas` AFTER DELETE ON `tipoactas` FOR EACH ROW BEGIN
INSERT INTO juridico_001.tipoactas_el SET id=OLD.id,tipoacta=OLD.tipoacta,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

-- ----------------------------
-- Table structure for tipoerror
-- ----------------------------
DROP TABLE IF EXISTS `tipoerror`;
CREATE TABLE `tipoerror` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pant` int(11) NOT NULL,
  `tipoerror` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaTipoError`;
DELIMITER ;;
CREATE TRIGGER `insertaTipoError` AFTER INSERT ON `tipoerror` FOR EACH ROW BEGIN
INSERT INTO juridico_001.tipoerror SET id=NEW.id,pant=NEW.pant,
tipoerror=NEW.tipoerror,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateTipoError`;
DELIMITER ;;
CREATE TRIGGER `updateTipoError` AFTER UPDATE ON `tipoerror` FOR EACH ROW BEGIN
INSERT INTO juridico_001.tipoerror SET id=NEW.id,pant=NEW.pant,
tipoerror=NEW.tipoerror,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `deleteTipoError`;
DELIMITER ;;
CREATE TRIGGER `deleteTipoError` AFTER DELETE ON `tipoerror` FOR EACH ROW BEGIN
INSERT INTO juridico_001.tipoerror_el SET id=OLD.id,pant=OLD.pant,
tipoerror=OLD.tipoerror,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `account_expired` bit(1) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `apellma` varchar(255) NOT NULL,
  `apellpa` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `password_expired` bit(1) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaUser`;
DELIMITER ;;
CREATE TRIGGER `insertaUser` AFTER INSERT ON `user` FOR EACH ROW BEGIN
INSERT INTO juridico_001.user SET id=NEW.id,version=NEW.version,
account_expired=NEW.account_expired, account_locked=NEW.account_locked, apellma=NEW.apellma, apellpa=NEW.apellpa,
enabled=NEW.enabled, nombre=NEW.nombre, password=NEW.password, password_expired=NEW.password_expired,
username=NEW.username,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateUser`;
DELIMITER ;;
CREATE TRIGGER `updateUser` AFTER UPDATE ON `user` FOR EACH ROW BEGIN
INSERT INTO juridico_001.user SET id=NEW.id,version=NEW.version,
account_expired=NEW.account_expired, account_locked=NEW.account_locked, apellma=NEW.apellma, apellpa=NEW.apellpa,
enabled=NEW.enabled, nombre=NEW.nombre, password=NEW.password, password_expired=NEW.password_expired,
username=NEW.username,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `deleteUser`;
DELIMITER ;;
CREATE TRIGGER `deleteUser` AFTER DELETE ON `user` FOR EACH ROW BEGIN
INSERT INTO juridico_001.user_el SET id=OLD.id,version=OLD.version,
account_expired=OLD.account_expired, account_locked=OLD.account_locked, apellma=OLD.apellma, apellpa=OLD.apellpa,
enabled=OLD.enabled, nombre=OLD.nombre, password=OLD.password, password_expired=OLD.password_expired,
username=OLD.username,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `FK143BF46AF42AEBA6` (`role_id`),
  KEY `FK143BF46A9955AF86` (`user_id`),
  CONSTRAINT `FK143BF46A9955AF86` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK143BF46AF42AEBA6` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaUser_role`;
DELIMITER ;;
CREATE TRIGGER `insertaUser_role` AFTER INSERT ON `user_role` FOR EACH ROW BEGIN
INSERT INTO juridico_001.user_role SET role_id=NEW.role_id,user_id=NEW.user_id,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateUser_role`;
DELIMITER ;;
CREATE TRIGGER `updateUser_role` AFTER UPDATE ON `user_role` FOR EACH ROW BEGIN
INSERT INTO juridico_001.user_role SET role_id=NEW.role_id,user_id=NEW.user_id,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `deleteUser_role`;
DELIMITER ;;
CREATE TRIGGER `deleteUser_role` AFTER DELETE ON `user_role` FOR EACH ROW BEGIN
INSERT INTO juridico_001.user_role_el SET role_id=OLD.role_id,user_id=OLD.user_id,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

-- ----------------------------
-- Table structure for venta
-- ----------------------------
DROP TABLE IF EXISTS `venta`;
CREATE TABLE `venta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `apema` varchar(255) NOT NULL,
  `apepa` varchar(255) NOT NULL,
  `cap_id` bigint(20) NOT NULL,
  `dic_id` bigint(20) DEFAULT NULL,
  `estado_id` bigint(20) DEFAULT NULL,
  `fechasol` datetime NOT NULL,
  `folio` int(11) NOT NULL,
  `folioexp` varchar(255) DEFAULT NULL,
  `nomb` varchar(255) NOT NULL,
  `obser` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6AE6A4CA1B22E16` (`estado_id`),
  KEY `FK6AE6A4C52B575F3` (`dic_id`),
  KEY `FK6AE6A4C5095CA1F` (`cap_id`),
  CONSTRAINT `FK6AE6A4C5095CA1F` FOREIGN KEY (`cap_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK6AE6A4C52B575F3` FOREIGN KEY (`dic_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK6AE6A4CA1B22E16` FOREIGN KEY (`estado_id`) REFERENCES `docesta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaVenta`;
DELIMITER ;;
CREATE TRIGGER `insertaVenta` AFTER INSERT ON `venta` FOR EACH ROW BEGIN
INSERT INTO juridico_001.venta SET id=NEW.id,version=NEW.version,
apema=NEW.apema, apepa=NEW.apepa, cap_id=NEW.cap_id, dic_id=NEW.dic_id,
estado_id=NEW.estado_id, fechasol=NEW.fechasol, folio=NEW.folio, folioexp=NEW.folioexp,
nomb=NEW.nomb, obser=NEW.obser,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `updateVenta`;
DELIMITER ;;
CREATE TRIGGER `updateVenta` AFTER UPDATE ON `venta` FOR EACH ROW BEGIN
INSERT INTO juridico_001.venta SET id=NEW.id,version=NEW.version,
apema=NEW.apema, apepa=NEW.apepa, cap_id=NEW.cap_id, dic_id=NEW.dic_id,
estado_id=NEW.estado_id, fechasol=NEW.fechasol, folio=NEW.folio, folioexp=NEW.folioexp,
nomb=NEW.nomb, obser=NEW.obser,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `deleteVenta`;
DELIMITER ;;
CREATE TRIGGER `deleteVenta` AFTER DELETE ON `venta` FOR EACH ROW BEGIN
INSERT INTO juridico_001.venta_el SET id=OLD.id,version=OLD.version,
apema=OLD.apema, apepa=OLD.apepa, cap_id=OLD.cap_id, dic_id=OLD.dic_id,
estado_id=OLD.estado_id, fechasol=OLD.fechasol, folio=OLD.folio, folioexp=OLD.folioexp,
nomb=OLD.nomb, obser=OLD.obser,
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

-- ----------------------------
-- Table structure for year
-- ----------------------------
DROP TABLE IF EXISTS `year`;
CREATE TABLE `year` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `xyear` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TRIGGER IF EXISTS `insertaYear`;
DELIMITER ;;
CREATE TRIGGER `insertaYear` AFTER INSERT ON `year` FOR EACH ROW BEGIN
INSERT INTO juridico_001.year SET id=NEW.id,version=NEW.version,
xyear=NEW.xyear, 
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `updateYear`;
DELIMITER ;;
CREATE TRIGGER `updateYear` AFTER UPDATE ON `year` FOR EACH ROW BEGIN
INSERT INTO juridico_001.year SET id=NEW.id,version=NEW.version,
xyear=NEW.xyear, 
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `deleteYear`;
DELIMITER ;;
CREATE TRIGGER `deleteYear` AFTER DELETE ON `year` FOR EACH ROW BEGIN
INSERT INTO juridico_001.year_el SET id=OLD.id,version=OLD.version,
xyear=OLD.xyear, 
usuario_creacion=user(),fecha_creacion=current_timestamp();
END
;;
DELIMITER ;



