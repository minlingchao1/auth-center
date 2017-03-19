CREATE TABLE `resource`(
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `gmt_create` TIMESTAMP NOT NULL  DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `gmt_modified` TIMESTAMP NOT NULL DEFAULT now() ON UPDATE now() COMMENT '修改时间',
  `name` VARCHAR (128) NOT NULL COMMENT '资源名称',
  `type` INT (11) NOT NULL COMMENT '类型',
  `priority` INT (11) COMMENT '优先级',
  `parent_id` BIGINT (20) COMMENT '父id',
  `parent_ids` VARCHAR (128) COMMENT '父ids',
  `permission` VARCHAR (128) COMMENT '权限',
  `avaliable` TINYINT (1) COMMENT '是否可用',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;