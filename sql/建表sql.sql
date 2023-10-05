CREATE TABLE `goods` (
                         `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
                         `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
                         `price` double(10,2) DEFAULT NULL COMMENT '商品价格',
  `number` int(255) DEFAULT NULL COMMENT '商品数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;