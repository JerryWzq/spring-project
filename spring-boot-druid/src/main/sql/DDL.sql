CREATE TABLE `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `city_name` varchar(255) DEFAULT NULL COMMENT '名称',
  `p_id` int(11) NOT NULL COMMENT '省份id',
  `size` varchar(15) NOT NULL DEFAULT 'BIG' COMMENT '城市大小：SMALL,BIG,LARGE',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='市级信息';

CREATE TABLE `province` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `province_code` varchar(255) DEFAULT NULL COMMENT '代码',
  `province_name` varchar(255) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='省级信息';