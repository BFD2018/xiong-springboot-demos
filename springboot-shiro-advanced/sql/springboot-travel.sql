/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.7.32 : Database - springboot_travel
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`springboot_travel` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `springboot_travel`;

/*Table structure for table `t_perm` */

DROP TABLE IF EXISTS `t_perm`;

CREATE TABLE `t_perm` (
  `perm_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限字符串',
  PRIMARY KEY (`perm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `t_perm` */

insert  into `t_perm`(`perm_id`,`name`) values (1,'user:*'),(2,'user:add'),(3,'user:delete'),(4,'user:update'),(5,'user:get'),(6,'seller:*'),(7,'seller:add'),(8,'seller:update'),(9,'seller:get'),(10,'seller:delete'),(11,'route:*'),(12,'route:add'),(13,'route:update'),(14,'route:get'),(15,'route:delete'),(16,'*:*');

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名',
  `name_zh` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色中文名',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `t_role` */

insert  into `t_role`(`role_id`,`name`,`name_zh`) values (1,'admin','超级管理员'),(2,'vip1','会员1'),(3,'vip2','会员2'),(4,'vip3','会员3'),(5,'customer','普通用户'),(6,'guest','游客'),(7,'userManager','用户管理员'),(8,'routeManager','路线管理员');

/*Table structure for table `t_role_perm` */

DROP TABLE IF EXISTS `t_role_perm`;

CREATE TABLE `t_role_perm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `perm_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `t_role_perm` */

insert  into `t_role_perm`(`id`,`role_id`,`perm_id`) values (1,1,1),(2,1,6),(3,1,11),(4,2,1),(5,3,6),(6,4,11),(7,5,14),(8,5,9),(9,6,14),(10,7,1),(11,8,11);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) CHARACTER SET utf8 NOT NULL COMMENT '登录用户名',
  `password` varchar(128) CHARACTER SET utf8 NOT NULL COMMENT 'md5加密',
  `nickname` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(256) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户头像',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `sex` tinyint(1) DEFAULT '1' COMMENT '1-男，0-女',
  `telephone` varchar(18) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机号',
  `email` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮箱',
  `status` tinyint(1) DEFAULT '1' COMMENT '激活状态，1-邮件激活，0-未激活',
  `salt` varchar(256) CHARACTER SET utf8 DEFAULT NULL COMMENT '盐',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `t_user` */

insert  into `t_user`(`user_id`,`username`,`password`,`nickname`,`avatar`,`birthday`,`sex`,`telephone`,`email`,`status`,`salt`) values (1,'root','63a51e7c882654e6c2166ea1aac7f35e','站长','https://pics7.baidu.com/feed/f7246b600c33874425a8462cbc90eafed62aa09c.jpeg','2022-01-03',1,'15990076961','135166@qq.com',0,'3'),(2,'唐僧','925cbf40de823f1be80ddc9ef5a4f76c','用户管理员','http://img.netbian.com/file/2021/1120/small146330836ef7c4f756fa8930947314b71637341157.jpg','2004-02-10',1,'15990076961','xxoo@gmail.com',1,'8137'),(3,'孙悟空','925cbf40de823f1be80ddc9ef5a4f76c','路线管理员','https://dd-static.jd.com/ddimg/jfs/t1/160208/19/26450/799181/61e78526E99384bee/7f0bd06e714ea719.png','2021-07-06',1,'15800001111','sunwukong@qq.com',1,'8137'),(4,'张华','123456','小华子','https://dd-static.jd.com/ddimg/jfs/t1/103080/7/22247/421502/61e68b49E78463384/a4a062afdb3111bb.png','2010-01-11',1,'15866663210','',1,NULL),(5,'麦克丽','123456','mick','https://dd-static.jd.com/ddimg/jfs/t1/67409/19/18949/732645/61e68c24Eec697c12/1418a624d43a2552.png','2008-12-30',0,'15622541897','mickLI@gmail.com',1,NULL),(8,'猪八戒','123456','天蓬元帅','https://dd-static.jd.com/ddimg/jfs/t1/207576/27/16284/252132/61e7b6ecE30193a1f/8e6740d65c12c899.png','2019-03-14',1,'13200002222','zhubajie@qq.com',1,NULL),(9,'张歆艺','123c81fcacb9db8ede9783ee9ff1e85f','艺米米','https://dd-static.jd.com/ddimg/jfs/t1/171831/3/27119/69197/61f256ebEbb6fa965/6fe9b244b34190c5.png','2003-01-06',0,'15636552210','zhangxingyi@qq.com',1,'8757'),(10,'米雪儿','3329ecd01edddd69eac6577455987792','雪儿','https://dd-static.jd.com/ddimg/jfs/t1/219501/3/11675/47046/61f256bbEe4bdc7a6/367b79153510c633.png','2008-01-15',0,'15833601123','mixueer@163.com',1,'1355');

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `t_user_role` */

insert  into `t_user_role`(`id`,`user_id`,`role_id`) values (1,1,1),(2,2,7),(3,3,8);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
