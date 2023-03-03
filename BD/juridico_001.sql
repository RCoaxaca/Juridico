SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base
-- ----------------------------

DROP TABLE IF EXISTS `base`;
CREATE TABLE `base` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` int(11) NOT NULL,
  `acta_id` int(11) NOT NULL,
  `base` varchar(550) NOT NULL,
  `campo_id` int(11) NOT NULL,
  `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `base_el`;
CREATE TABLE `base_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` int(11) NOT NULL,
  `acta_id` int(11) NOT NULL,
  `base` varchar(550) NOT NULL,
  `campo_id` int(11) NOT NULL,
  `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for campo
-- ----------------------------
DROP TABLE IF EXISTS `campo`;
CREATE TABLE `campo` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `campo` varchar(60) NOT NULL,
  `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `campo_el`;
CREATE TABLE `campo_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `campo` varchar(60) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for clausrr
-- ----------------------------
DROP TABLE IF EXISTS `clausrr`;
CREATE TABLE `clausrr` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `clv` varchar(3) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `outdate` datetime NOT NULL,
  `passw` varchar(10) NOT NULL,
  `status` varchar(3) NOT NULL,
  `updates` datetime NOT NULL,
  `usrtype` varchar(3) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `clausrr_el`;
CREATE TABLE `clausrr_el` (
	pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `clv` varchar(3) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `outdate` datetime NOT NULL,
  `passw` varchar(10) NOT NULL,
  `status` varchar(3) NOT NULL,
  `updates` datetime NOT NULL,
  `usrtype` varchar(3) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for docesta
-- ----------------------------
DROP TABLE IF EXISTS `docesta`;
CREATE TABLE `docesta` (
	pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `dc` int(11) NOT NULL,
  `docuestado` varchar(255) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `docesta_el`;
CREATE TABLE `docesta_el` (
	pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `dc` int(11) NOT NULL,
  `docuestado` varchar(255) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for entidades
-- ----------------------------
DROP TABLE IF EXISTS `entidades`;
CREATE TABLE `entidades` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `ID` bigint(20) NOT NULL,
  `clave` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `entidades_el`;
CREATE TABLE `entidades_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `ID` bigint(20) NOT NULL,
  `clave` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for erroresperado
-- ----------------------------
DROP TABLE IF EXISTS `erroresperado`;
CREATE TABLE `erroresperado` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `tipodeerror` varchar(60) NOT NULL,
  `tipoerr_id` bigint(20) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `erroresperado_el`;
CREATE TABLE `erroresperado_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `tipodeerror` varchar(60) NOT NULL,
  `tipoerr_id` bigint(20) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fields
-- ----------------------------
DROP TABLE IF EXISTS `fields`;
CREATE TABLE `fields` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` int(11) NOT NULL,
  `acta_id` int(11) NOT NULL,
  `nombre` varchar(65) NOT NULL,
  `num` int(11) NOT NULL,
  `sexo` char(1) NOT NULL,
  `tipo` varchar(1) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `fields_el`;
CREATE TABLE `fields_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` int(11) NOT NULL,
  `acta_id` int(11) NOT NULL,
  `nombre` varchar(65) NOT NULL,
  `num` int(11) NOT NULL,
  `sexo` char(1) NOT NULL,
  `tipo` varchar(1) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fields2
-- ----------------------------
DROP TABLE IF EXISTS `fields2`;
CREATE TABLE `fields2` (
		pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` int(11) NOT NULL,
  `acta_id` int(11) NOT NULL,
  `nombre` varchar(55) NOT NULL,
  `nombre2` varchar(55) NOT NULL,
  `num` int(11) NOT NULL,
  `tipo` varchar(1) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `fields2_el`;
CREATE TABLE `fields2_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` int(11) NOT NULL,
  `acta_id` int(11) NOT NULL,
  `nombre` varchar(55) NOT NULL,
  `nombre2` varchar(55) NOT NULL,
  `num` int(11) NOT NULL,
  `tipo` varchar(1) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jefeaclaraciones
-- ----------------------------
DROP TABLE IF EXISTS `jefeaclaraciones`;
CREATE TABLE `jefeaclaraciones` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `activo` bit(1) NOT NULL,
  `ape_mat` varchar(70) DEFAULT NULL,
  `ape_pat` varchar(70) NOT NULL,
  `fin` datetime DEFAULT NULL,
  `inicio` datetime NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `titulo` varchar(255) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `jefeaclaraciones_el`;
CREATE TABLE `jefeaclaraciones_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `activo` bit(1) NOT NULL,
  `ape_mat` varchar(70) DEFAULT NULL,
  `ape_pat` varchar(70) NOT NULL,
  `fin` datetime DEFAULT NULL,
  `inicio` datetime NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `titulo` varchar(255) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jefearchivo
-- ----------------------------
DROP TABLE IF EXISTS `jefearchivo`;
CREATE TABLE `jefearchivo` (
		pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `activo` bit(1) NOT NULL,
  `ape_mat` varchar(70) DEFAULT NULL,
  `ape_pat` varchar(70) NOT NULL,
  `fin` datetime DEFAULT NULL,
  `inicio` datetime NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `titulo` varchar(255) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `jefearchivo_el`;
CREATE TABLE `jefearchivo_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `activo` bit(1) NOT NULL,
  `ape_mat` varchar(70) DEFAULT NULL,
  `ape_pat` varchar(70) NOT NULL,
  `fin` datetime DEFAULT NULL,
  `inicio` datetime NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `titulo` varchar(255) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jefejuridico
-- ----------------------------
DROP TABLE IF EXISTS `jefejuridico`;
CREATE TABLE `jefejuridico` (
		pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `activo` bit(1) NOT NULL,
  `ape_mat` varchar(70) DEFAULT NULL,
  `ape_pat` varchar(70) NOT NULL,
  `fin` datetime DEFAULT NULL,
  `inicio` datetime NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `titulo` varchar(255) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `jefejuridico_el`;
CREATE TABLE `jefejuridico_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `activo` bit(1) NOT NULL,
  `ape_mat` varchar(70) DEFAULT NULL,
  `ape_pat` varchar(70) NOT NULL,
  `fin` datetime DEFAULT NULL,
  `inicio` datetime NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `titulo` varchar(255) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for localidades
-- ----------------------------
DROP TABLE IF EXISTS `localidades`;
CREATE TABLE `localidades` (
		pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `loc_clave` int(11) NOT NULL,
  `localidad` varchar(60) NOT NULL,
  `municipio` bigint(20) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `localidades_el`;
CREATE TABLE `localidades_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `loc_clave` int(11) NOT NULL,
  `localidad` varchar(60) NOT NULL,
  `municipio` bigint(20) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for localidadofi
-- ----------------------------
DROP TABLE IF EXISTS `localidadofi`;
CREATE TABLE `localidadofi` (
		pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `localidad` bigint(20) NOT NULL,
  `munpio` bigint(20) NOT NULL,
  `oficialia` bigint(20) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `localidadofi_el`;
CREATE TABLE `localidadofi_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `localidad` bigint(20) NOT NULL,
  `munpio` bigint(20) NOT NULL,
  `oficialia` bigint(20) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for movimiento
-- ----------------------------
DROP TABLE IF EXISTS `movimiento`;
CREATE TABLE `movimiento` (
		pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `entrada` datetime DEFAULT NULL,
  `fecha` datetime NOT NULL,
  `numero_expediente` varchar(255) NOT NULL,
  `salida` datetime DEFAULT NULL,
  `usuarioentrega` varchar(255) NOT NULL,
  `usuariopresta_id` bigint(20) DEFAULT NULL,
  `usuariorecibe_id` bigint(20) DEFAULT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `movimiento_el`;
CREATE TABLE `movimiento_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `entrada` datetime DEFAULT NULL,
  `fecha` datetime NOT NULL,
  `numero_expediente` varchar(255) NOT NULL,
  `salida` datetime DEFAULT NULL,
  `usuarioentrega` varchar(255) NOT NULL,
  `usuariopresta_id` bigint(20) DEFAULT NULL,
  `usuariorecibe_id` bigint(20) DEFAULT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for nacionalidad
-- ----------------------------
DROP TABLE IF EXISTS `nacionalidad`;
CREATE TABLE `nacionalidad` (
		pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `clave` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `nacionalidad_el`;
CREATE TABLE `nacionalidad_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `clave` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for nota
-- ----------------------------
DROP TABLE IF EXISTS `nota`;
CREATE TABLE `nota` (
		pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `dic` varchar(20) DEFAULT NULL,
  `expano` int(11) NOT NULL,
  `expro` int(11) NOT NULL,
  `idn` char(1) NOT NULL,
  `namofi` varchar(50) DEFAULT NULL,
  `nota` mediumtext NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `nota_el`;
CREATE TABLE `nota_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `dic` varchar(20) DEFAULT NULL,
  `expano` int(11) NOT NULL,
  `expro` int(11) NOT NULL,
  `idn` char(1) NOT NULL,
  `namofi` varchar(50) DEFAULT NULL,
  `nota` mediumtext NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for opcion
-- ----------------------------
DROP TABLE IF EXISTS `opcion`;
CREATE TABLE `opcion` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `error_id` bigint(20) DEFAULT NULL,
  `exapro` int(11) NOT NULL,
  `tablaid` int(11) NOT NULL,
  `tipo` varchar(40) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `opcion_el`;
CREATE TABLE `opcion_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `error_id` bigint(20) DEFAULT NULL,
  `exapro` int(11) NOT NULL,
  `tablaid` int(11) NOT NULL,
  `tipo` varchar(40) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for papeleta
-- ----------------------------
DROP TABLE IF EXISTS `papeleta`;
CREATE TABLE `papeleta` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `expano` int(11) NOT NULL,
  `expro` int(11) NOT NULL,
  `nota` mediumtext NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `papeleta_el`;
CREATE TABLE `papeleta_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `expano` int(11) NOT NULL,
  `expro` int(11) NOT NULL,
  `nota` mediumtext NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for resguardo
-- ----------------------------
DROP TABLE IF EXISTS `resguardo`;
CREATE TABLE `resguardo` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `entrega` varchar(255) NOT NULL,
  `fecha_entrada` datetime NOT NULL,
  `numero_expediente` varchar(255) NOT NULL,
  `papeleta` bit(1) NOT NULL,
  `resolucion` bit(1) NOT NULL,
  `ubicacion` varchar(255) NOT NULL,
  `usuario_id` bigint(20) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `resguardo_el`;
CREATE TABLE `resguardo_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `entrega` varchar(255) NOT NULL,
  `fecha_entrada` datetime NOT NULL,
  `numero_expediente` varchar(255) NOT NULL,
  `papeleta` bit(1) NOT NULL,
  `resolucion` bit(1) NOT NULL,
  `ubicacion` varchar(255) NOT NULL,
  `usuario_id` bigint(20) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `authority` varchar(255) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `role_el`;
CREATE TABLE `role_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `authority` varchar(255) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for scaact
-- ----------------------------
DROP TABLE IF EXISTS `scaact`;
CREATE TABLE `scaact` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
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
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `scaact_el`;
CREATE TABLE `scaact_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
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
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for scadto
-- ----------------------------
DROP TABLE IF EXISTS `scadto`;
CREATE TABLE `scadto` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `clv` int(11) NOT NULL,
  `clvreg` int(11) NOT NULL,
  `descc` varchar(30) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `scadto_el`;
CREATE TABLE `scadto_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `clv` int(11) NOT NULL,
  `clvreg` int(11) NOT NULL,
  `descc` varchar(30) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for scaerr
-- ----------------------------
DROP TABLE IF EXISTS `scaerr`;
CREATE TABLE `scaerr` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
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
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `scaerr_el`;
CREATE TABLE `scaerr_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
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
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for scampo
-- ----------------------------
DROP TABLE IF EXISTS `scampo`;
CREATE TABLE `scampo` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `descrip` varchar(60) NOT NULL,
  `distrito_id` bigint(20) NOT NULL,
  `mpo` int(11) NOT NULL,
  `oficialia_id` bigint(20) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `scampo_el`;
CREATE TABLE `scampo_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `descrip` varchar(60) NOT NULL,
  `distrito_id` bigint(20) NOT NULL,
  `mpo` int(11) NOT NULL,
  `oficialia_id` bigint(20) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for scamposcaofi
-- ----------------------------
DROP TABLE IF EXISTS `scamposcaofi`;
CREATE TABLE `scamposcaofi` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `municipio` bigint(20) NOT NULL,
  `oficialia` bigint(20) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `scamposcaofi_el`;
CREATE TABLE `scamposcaofi_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `municipio` bigint(20) NOT NULL,
  `oficialia` bigint(20) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for scaofi
-- ----------------------------
DROP TABLE IF EXISTS `scaofi`;
CREATE TABLE `scaofi` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `clv_id` bigint(20) NOT NULL,
  `clv2` int(11) NOT NULL,
  `descrip` varchar(60) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `scaofi_el`;
CREATE TABLE `scaofi_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `clv_id` bigint(20) NOT NULL,
  `clv2` int(11) NOT NULL,
  `descrip` varchar(60) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for scaprn
-- ----------------------------
DROP TABLE IF EXISTS `scaprn`;
CREATE TABLE `scaprn` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `mimp` char(1) NOT NULL,
  `tlet` int(11) NOT NULL,
  `toja` char(1) NOT NULL,
  `usrid` varchar(255) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `scaprn_el`;
CREATE TABLE `scaprn_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `mimp` char(1) NOT NULL,
  `tlet` int(11) NOT NULL,
  `toja` char(1) NOT NULL,
  `usrid` varchar(255) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for scapro
-- ----------------------------
DROP TABLE IF EXISTS `scapro`;
CREATE TABLE `scapro` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `prog` int(11) NOT NULL,
  `year` int(11) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `scapro_el`;
CREATE TABLE `scapro_el` (
  pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `prog` int(11) NOT NULL,
  `year` int(11) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for scapro2
-- ----------------------------
DROP TABLE IF EXISTS `scapro2`;
CREATE TABLE `scapro2` (
	pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `prog` int(11) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `scapro2_el`;
CREATE TABLE `scapro2_el` (
	pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `prog` int(11) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for scasol
-- ----------------------------
DROP TABLE IF EXISTS `scasol`;
CREATE TABLE `scasol` (
	pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
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
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `scasol_el`;
CREATE TABLE `scasol_el` (
	pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
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
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tcorrect
-- ----------------------------
DROP TABLE IF EXISTS `tcorrect`;
CREATE TABLE `tcorrect` (
	pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `tcorrect` varchar(255) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tcorrect_el`;
CREATE TABLE `tcorrect_el` (
	pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `tcorrect` varchar(255) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for terror
-- ----------------------------
DROP TABLE IF EXISTS `terror`;
CREATE TABLE `terror` (
	pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `donde` varchar(60) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `terror_el`;
CREATE TABLE `terror_el` (
	pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `donde` varchar(60) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tipoactas
-- ----------------------------
DROP TABLE IF EXISTS `tipoactas`;
CREATE TABLE `tipoactas` (
	pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` int(11) NOT NULL,
  `tipoacta` varchar(30) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tipoactas_el`;
CREATE TABLE `tipoactas_el` (
	pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` int(11) NOT NULL,
  `tipoacta` varchar(30) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tipoerror
-- ----------------------------
DROP TABLE IF EXISTS `tipoerror`;
CREATE TABLE `tipoerror` (
	pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `pant` int(11) NOT NULL,
  `tipoerror` varchar(60) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tipoerror_el`;
CREATE TABLE `tipoerror_el` (
	pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `pant` int(11) NOT NULL,
  `tipoerror` varchar(60) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
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
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `user_el`;
CREATE TABLE `user_el` (
	pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
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
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
	pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (pkid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_role_el`;
CREATE TABLE `user_role_el` (
	pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (pkid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for venta
-- ----------------------------
DROP TABLE IF EXISTS `venta`;
CREATE TABLE `venta` (
	pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` int(11) NOT NULL,
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
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `venta_el`;
CREATE TABLE `venta_el` (
	pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` int(11) NOT NULL,
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
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for year
-- ----------------------------
DROP TABLE IF EXISTS `year`;
CREATE TABLE `year` (
	pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `xyear` int(11) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `year_el`;
CREATE TABLE `year_el` (
	pkid bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `xyear` int(11) NOT NULL,
    `usuario_creacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





