CREATE TABLE `user`(
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `gmt_create` TIMESTAMP NOT NULL  DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `gmt_modified` TIMESTAMP NOT NULL DEFAULT now() ON UPDATE now() COMMENT '修改时间',
  `user_id` BIGINT (20) NOT NULL COMMENT '账户Id',
  `user_nick` VARCHAR (128)  COMMENT '昵称',
  `password` VARCHAR (128) COMMENT '密码',
  `entry_password` VARCHAR (255)  COMMENT '加密密码',
  `locked` TINYINT (1) COMMENT '手机号',
  `mobile` VARCHAR (128) COMMENT '昵称',
  `descr` VARCHAR (128) COMMENT '用户状态',
  `salt` VARCHAR (128) COMMENT '盐',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`user_id`)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;