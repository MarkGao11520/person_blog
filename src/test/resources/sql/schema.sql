DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `username` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_login` (`username`,`password`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `sys_role` ;
 CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `sys_user_roles`;
CREATE TABLE `sys_user_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `FKdpvc6d7xqpqr43dfuk1s27cqh` (`role_id`),
  KEY `FKd0ut7sloes191bygyf7a3pk52` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `b_user_info`;
CREATE TABLE `b_user_info` (
  `id` INT NOT NULL COMMENT 'user id',
  `nike_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '昵称',
  `phone` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '手机号',
  `qq` VARCHAR(32) NOT NULL DEFAULT '' COMMENT 'qq号',
  `wechat` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '微信',
  `sex` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '性别,0男1女',
  `head_pic` VARCHAR(512) NOT NULL DEFAULT '' COMMENT '头像url',
  `is_lock` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0未锁定1锁定',
  PRIMARY KEY (`id`),
  KEY `idx_user_info_nike_name` (nike_name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT '用户信息表';

DROP TABLE IF EXISTS `b_blog`;
CREATE TABLE `b_blog`(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '博客主键',
  `uid` INT NOT NULL COMMENT '用户id',
  `keyword` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '关键字',
  `title` VARCHAR(128) NOT NULL COMMENT '标题',
  `content` TEXT NOT NULL COMMENT '内容',
  `cover_url` VARCHAR(512) NOT NULL DEFAULT '' COMMENT '封面url',
  PRIMARY KEY (`id`),
  KEY `idx_blog_title_keyword` (`title`,`keyword`),
  KEY `idx_blog_keyword` (`keyword`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT '博客表';

DROP TABLE IF EXISTS `b_label`;
CREATE TABLE `b_label`(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '标签主键',
  `name` VARCHAR(128) NOT NULL COMMENT '标签名称',
  PRIMARY KEY (`id`),
  KEY `idx_label_name`(`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT '标签表';

DROP TABLE IF EXISTS `b_r_blog_label`;
CREATE TABLE `b_r_blog_label`(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `blog_id` INT NOT NULL COMMENT '博客id',
  `label_id` INT NOT NULL COMMENT '标签id',
  PRIMARY KEY (`id`),
  KEY `inx_r_blog_label` (`blog_id`,`label_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT '博客标签关联表';

DROP TABLE IF EXISTS `b_comment`;
CREATE TABLE `b_comment`(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` INT NOT NULL COMMENT '用户id',
  `blog_id` INT NOT NULL COMMENT '博客id',
  `content` TEXT COMMENT '评论内容',
  `stay_time` TIMESTAMP DEFAULT current_timestamp COMMENT '评论时间',
  PRIMARY KEY (`id`),
  KEY `idx_comment` (`blog_id`,`uid`,`stay_time`),
  KEY `idx_comment_stay_time` (`stay_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT '评论表';

DROP TABLE IF EXISTS `b_category`;
CREATE TABLE `b_category`(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` VARCHAR(128) NOT NULL COMMENT '分类',
  PRIMARY KEY (`id`),
  KEY `idx_category` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT '分类表';

DROP TABLE IF EXISTS `b_r_category_blog`;
CREATE TABLE `b_r_category_blog`(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `blog_id` INT NOT NULL COMMENT '博客id',
  `category_id` INT NOT NULL COMMENT '分类id',
  PRIMARY KEY (`id`),
  KEY `idx_r_category_blog` (`category_id`,`blog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT '分类博客表';

DROP TABLE IF EXISTS `b_r_blog_fabulous`;
CREATE TABLE `b_r_blog_fabulous`(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `blog_id` INT NOT NULL COMMENT '博客id',
  `uid` INT NOT NULL COMMENT '用户id',
  `fabulous_time` TIMESTAMP DEFAULT current_timestamp COMMENT '点赞时间',
  PRIMARY KEY (`id`),
  KEY `idx_fabulous` (`blog_id`,`fabulous_time`),
  KEY `idx_fabulous_time` (`fabulous_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT '点赞表';

DROP TABLE IF EXISTS `b_visit`;
CREATE TABLE `b_visit`(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` INT NOT NULL DEFAULT -1 COMMENT '访客id',
  `blog_id` INT NOT NULL COMMENT '博客id',
  `visit_time` TIMESTAMP DEFAULT current_timestamp COMMENT '访客时间',
  `visit_ip` VARCHAR(128) NOT NULL COMMENT '访客id',
  PRIMARY KEY (`id`),
  KEY `idx_visit` (`blog_id`,`uid`,`visit_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT '访客表';

