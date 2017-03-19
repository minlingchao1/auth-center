CREATE TABLE `authorization`(
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `gmt_create` TIMESTAMP NOT NULL  DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `gmt_modified` TIMESTAMP NOT NULL DEFAULT now() ON UPDATE now() COMMENT '修改时间',
  `user_id` BIGINT (20) NOT NULL COMMENT '用户id',
  `app_id` BIGINT (20) NOT NULL COMMENT '应用id',
  `role_ids` VARCHAR (128) COMMENT '权限',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`user_id`,`app_id`)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;