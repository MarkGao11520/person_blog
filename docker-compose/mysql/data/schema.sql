/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost
 Source Database       : blog

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 11/11/2017 21:37:15 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE IF NOT EXISTS `blog` default character set utf8 collate utf8_bin;
USE blog

-- ----------------------------
--  Table structure for `b_blog`
-- ----------------------------
DROP TABLE IF EXISTS `b_blog`;
CREATE TABLE `b_blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '博客主键',
  `uid` int(11) NOT NULL DEFAULT '1' COMMENT '用户id',
  `keyword` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '关键字',
  `title` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '标题',
  `content` text COLLATE utf8_bin NOT NULL COMMENT '内容',
  `cover_url` varchar(512) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '封面url',
  `release_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '博客发布时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次博客更改时间(默认与release_time相同)',
  PRIMARY KEY (`id`),
  KEY `idx_blog_title_keyword` (`title`,`keyword`),
  KEY `idx_blog_keyword` (`keyword`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='博客表';

-- ----------------------------
--  Table structure for `b_category`
-- ----------------------------
DROP TABLE IF EXISTS `b_category`;
CREATE TABLE `b_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '分类',
  PRIMARY KEY (`id`),
  KEY `idx_category` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='分类表';

-- ----------------------------
--  Table structure for `b_comment`
-- ----------------------------
DROP TABLE IF EXISTS `b_comment`;
CREATE TABLE `b_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) NOT NULL COMMENT '用户id',
  `blog_id` int(11) NOT NULL COMMENT '博客id',
  `content` text COLLATE utf8_bin COMMENT '评论内容',
  `stay_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  `target_id` int(11) DEFAULT NULL COMMENT '论评的目标用户id(默认博主的id)',
  `target_type` int(1) NOT NULL COMMENT '1: 博客文章 ；2: 首页留言板',
  `comment_count` int(255) NOT NULL DEFAULT '0' COMMENT '该评论被评论的数量',
  PRIMARY KEY (`id`),
  KEY `idx_comment` (`blog_id`,`uid`,`stay_time`),
  KEY `idx_comment_stay_time` (`stay_time`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='评论表';

-- ----------------------------
--  Table structure for `b_label`
-- ----------------------------
DROP TABLE IF EXISTS `b_label`;
CREATE TABLE `b_label` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签主键',
  `name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '标签名称',
  PRIMARY KEY (`id`),
  KEY `idx_label_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='标签表';

-- ----------------------------
--  Table structure for `b_log`
-- ----------------------------
DROP TABLE IF EXISTS `b_log`;
CREATE TABLE `b_log` (
  `id` int(11) NOT NULL,
  `log_id` int(11) NOT NULL COMMENT '日志id(为评论记录的id或点赞记录的id)',
  `type` int(1) NOT NULL COMMENT '1:评论 ;2: 点赞',
  `log_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '日志时间',
  `read_status` int(1) DEFAULT '0' COMMENT '0: unread; 1: read',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `b_r_blog_fabulous`
-- ----------------------------
DROP TABLE IF EXISTS `b_r_blog_fabulous`;
CREATE TABLE `b_r_blog_fabulous` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `blog_id` int(11) NOT NULL COMMENT '博客id',
  `uid` int(11) NOT NULL COMMENT '用户id',
  `fabulous_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '点赞时间',
  `target_type` int(11) DEFAULT NULL COMMENT '点赞的类型 1博客内容，2首页留言板"',
  `comment_id` int(11) DEFAULT NULL COMMENT '评论id',
  PRIMARY KEY (`id`),
  KEY `idx_fabulous` (`blog_id`,`fabulous_time`),
  KEY `idx_fabulous_time` (`fabulous_time`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='点赞表';

-- ----------------------------
--  Table structure for `b_r_blog_label`
-- ----------------------------
DROP TABLE IF EXISTS `b_r_blog_label`;
CREATE TABLE `b_r_blog_label` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `blog_id` int(11) NOT NULL COMMENT '博客id',
  `label_id` int(11) NOT NULL COMMENT '标签id',
  PRIMARY KEY (`id`),
  KEY `inx_r_blog_label` (`blog_id`,`label_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='博客标签关联表';

-- ----------------------------
--  Table structure for `b_r_category_blog`
-- ----------------------------
DROP TABLE IF EXISTS `b_r_category_blog`;
CREATE TABLE `b_r_category_blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `blog_id` int(11) NOT NULL COMMENT '博客id',
  `category_id` int(11) NOT NULL COMMENT '分类id',
  PRIMARY KEY (`id`),
  KEY `idx_r_category_blog` (`category_id`,`blog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='分类博客表';

-- ----------------------------
--  Table structure for `b_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `b_user_info`;
CREATE TABLE `b_user_info` (
  `id` int(11) NOT NULL COMMENT 'user id',
  `nike_name` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '昵称',
  `phone` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '手机号',
  `qq` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'qq号',
  `wechat` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '微信',
  `sex` tinyint(1) NOT NULL DEFAULT '0' COMMENT '性别,0男1女',
  `head_pic` varchar(512) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '头像url',
  `is_lock` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0未锁定1锁定',
  PRIMARY KEY (`id`),
  KEY `idx_user_info_nike_name` (`nike_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户信息表';

-- ----------------------------
--  Table structure for `b_visit`
-- ----------------------------
DROP TABLE IF EXISTS `b_visit`;
CREATE TABLE `b_visit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) NOT NULL DEFAULT '-1' COMMENT '访客id',
  `blog_id` int(11) NOT NULL COMMENT '博客id',
  `visit_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '访客时间',
  `visit_ip` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '访客id',
  PRIMARY KEY (`id`),
  KEY `idx_visit` (`blog_id`,`uid`,`visit_time`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='访客表';

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `username` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_login` (`username`,`password`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `sys_user_roles`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_roles`;
CREATE TABLE `sys_user_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `FKdpvc6d7xqpqr43dfuk1s27cqh` (`role_id`),
  KEY `FKd0ut7sloes191bygyf7a3pk52` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;
