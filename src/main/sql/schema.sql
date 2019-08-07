CREATE  DATABASE seckill;

use seckill;

create table seckill(
`seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id ',
`name` varchar (120) NOT NULL COMMENT '商品名称',
`number` int NOT NULL COMMENT '库存数量',
`start_time` timestamp NOT NULL COMMENT '秒杀开启时间',
`end_time` timestamp NOT NULL COMMENT '秒杀结束时间',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)engine=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

insert into seckill(name,number,start_time,end_time)
values
('1000元秒杀mate30',100,'2019-08-08 00:00:00','2019-08-09 00:00:00'),
('100元秒杀cc9',200,'2019-08-08 00:00:00','2019-08-09 00:00:00'),
('200元秒杀a30',300,'2019-08-08 00:00:00','2019-08-09 00:00:00'),
('200元秒杀nova5',50,'2019-08-08 00:00:00','2019-08-09 00:00:00');

create table success_killed(
`seckill_id` bigint NOT NULL  COMMENT '秒杀商品id ',
`user_phone` bigint NOT NULL COMMENT '用户手机号',
`state` tinyint NOT NULL DEFAULT -1 COMMENT '状态标识，-1：无效，0：成功',
`create_time` timestamp NOT NULL   COMMENT '创建时间',
PRIMARY KEY (seckill_id,user_phone),
key inx_create_time(create_time)
)engine=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

mysql -uroot -p123456