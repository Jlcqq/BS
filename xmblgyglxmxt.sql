/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : xmblgyglxmxt

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2025-01-10 19:38:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键ID',
  `username` varchar(255) character set utf8 collate utf8_unicode_ci default NULL COMMENT '用户名',
  `password` varchar(255) default NULL COMMENT '密码',
  `name` varchar(255) default NULL COMMENT '姓名',
  `sex` varchar(255) default NULL COMMENT '性别',
  `age` int(11) default NULL COMMENT '年龄',
  `tel` varchar(255) default NULL COMMENT '联系方式',
  `img` varchar(255) default NULL COMMENT '图片',
  `role` varchar(255) default NULL COMMENT '角色',
  `delstatus` int(11) default NULL COMMENT '逻辑删除字段',
  PRIMARY KEY  USING BTREE (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='后台用户信息表';

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '123', '刘雨声', '女', '33', '13899999999', 'http://localhost:8090/fles/1736395007570-2655b5144fca07b0.jpg', '管理员', '0');
INSERT INTO `admin` VALUES ('7', 'sz003', '123', '刘可', '男', '23', '13899001123', 'http://localhost:8090/fles/1736499537124-post-2.jpg', '员工', '0');
INSERT INTO `admin` VALUES ('8', 'sz001', '123', '张学明', '男', '23', '18100223355', 'http://localhost:8090/fles/1736499529798-20200112205321.jpg', '员工', '0');
INSERT INTO `admin` VALUES ('9', 'yg001', '123', '张丽萍', '女', '34', '13899001123', 'http://localhost:8090/fles/1736506386887-1577462390018.jpg', '员工', '0');
INSERT INTO `admin` VALUES ('10', 'yg002', '123', '张少鹏', '男', '44', '13899004466', 'http://localhost:8090/fles/1736506430770-project-single-2.jpg', '员工', '0');
INSERT INTO `admin` VALUES ('11', 'yg003', '123', '张超', '男', '33', '18199002233', 'http://localhost:8090/fles/1736508646169-post-3.jpg', '员工', '0');

-- ----------------------------
-- Table structure for `imgadv`
-- ----------------------------
DROP TABLE IF EXISTS `imgadv`;
CREATE TABLE `imgadv` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键ID',
  `fileimg` varchar(255) default NULL COMMENT '图片',
  `url` varchar(255) default NULL COMMENT '跳转链接',
  PRIMARY KEY  USING BTREE (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='轮播图';

-- ----------------------------
-- Records of imgadv
-- ----------------------------
INSERT INTO `imgadv` VALUES ('15', 'http://localhost:8090/fles/1736395066244-project-single-2.jpg', 'xinxiDateil?id=3');
INSERT INTO `imgadv` VALUES ('16', 'http://localhost:8090/fles/1736395043259-carousel-2.jpg', 'xinxiDateil?id=4');
INSERT INTO `imgadv` VALUES ('17', 'http://localhost:8090/fles/1736395036233-carousel-1.jpg', 'xinxiDateil?id=5');
INSERT INTO `imgadv` VALUES ('18', 'http://localhost:8090/fles/1736508871439-gy-02.jpg', 'xinxiDateil?id=6');

-- ----------------------------
-- Table structure for `links`
-- ----------------------------
DROP TABLE IF EXISTS `links`;
CREATE TABLE `links` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键ID',
  `name` varchar(255) default NULL COMMENT '名称',
  `url` varchar(255) default NULL COMMENT '跳转链接',
  PRIMARY KEY  USING BTREE (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='友情链接';

-- ----------------------------
-- Records of links
-- ----------------------------
INSERT INTO `links` VALUES ('1', '百度', 'https://www.baidu.com');
INSERT INTO `links` VALUES ('2', '淘宝', 'https://www.taobao.com/');
INSERT INTO `links` VALUES ('3', 'B站', 'https://www.bilibili.com');

-- ----------------------------
-- Table structure for `member`
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键ID',
  `username` varchar(255) default NULL COMMENT '用户名',
  `password` varchar(255) default NULL COMMENT '密码',
  `name` varchar(255) default NULL COMMENT '姓名',
  `sex` varchar(255) default NULL COMMENT '性别',
  `age` int(11) default NULL COMMENT '年龄',
  `tel` varchar(255) default NULL COMMENT '联系方式',
  `img` varchar(255) default NULL COMMENT '图片',
  `role` varchar(255) default NULL COMMENT '角色',
  `delstatus` int(11) default NULL COMMENT '逻辑删除',
  PRIMARY KEY  USING BTREE (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='普通用户信息表';

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('5', 'yh001', '123', '张琴', '女', '22', '13900990011', 'http://localhost:8090/fles/1736494576809-service-2.jpg', '用户', '0');
INSERT INTO `member` VALUES ('9', 'yh003', '123', '张桦', '男', '22', '18100990022', 'http://localhost:8090/fles/1736499973269-post-1.jpg', '用户', '0');
INSERT INTO `member` VALUES ('10', 'yh004', '123', '刘涛', '女', '22', '18100997788', 'http://localhost:8090/fles/1732516478368-1553144336914.jpg', '用户', '1');
INSERT INTO `member` VALUES ('11', 'yh005', '123', '刘秀', '女', '23', '13899001123', 'http://localhost:8090/fles/1734430988212-avatar-3.jpg', '用户', '1');
INSERT INTO `member` VALUES ('12', 'yh002', '123', '张彩', '女', '37', '13900998844', 'http://localhost:8090/fles/1736508338733-service-2.jpg', '用户', '0');

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键ID',
  `memberid` int(11) default NULL COMMENT '用户ID',
  `content` varchar(255) default NULL COMMENT '留言内容',
  `savetime` varchar(255) default NULL COMMENT '留言时间',
  `hfcontent` varchar(255) default NULL COMMENT '回复内容',
  `hftime` varchar(255) default NULL COMMENT '回复时间',
  PRIMARY KEY  USING BTREE (`id`),
  KEY `mess_mid` USING BTREE (`memberid`),
  CONSTRAINT `mess_mid` FOREIGN KEY (`memberid`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='在线留言; InnoDB free: 5120 kB; (`memberid`) REFER `zzzz';

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键ID',
  `fileimg` varchar(255) default NULL COMMENT '图片',
  `title` varchar(255) default NULL COMMENT '标题',
  `content` longtext COMMENT '内容',
  `savetime` varchar(255) default NULL COMMENT '发布时间',
  PRIMARY KEY  USING BTREE (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='新闻公告';

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('4', 'http://localhost:8090/fles/1736402203660-1697969157862-维也纳酒店-豪华间.jpg', '中国大饭店公馆公寓：城市中的静谧绿洲', '<p>中国大饭店公馆公寓，坐落于繁华都市之中，却仿佛是一片静谧的绿洲，为宾客提供了一片远离喧嚣、享受宁静的居住空间。这里，您可以放下繁忙的生活节奏，沉浸于舒适与宁静之中，尽享尊贵与惬意的入住体验。</p><h5><strong>一、宁静致远，舒适宜人</strong></h5><p>公馆公寓位于城市的中心地带，但周围环境优雅宁静，仿佛与世隔绝。步入其中，您会被这里的静谧氛围所深深吸引，感受到一种难以言喻的舒适与放松。宽敞明亮的客房、精致的室内装饰以及高品质的家具和床品，都为您打造了一个宁静舒适的居住空间。</p><h5><strong>二、豪华设施，一应俱全</strong></h5><p>公馆公寓不仅提供舒适的居住环境，更拥有各种豪华设施，满足您的各种需求。室内恒温泳池、健身房、瑜伽室等休闲设施让您在忙碌之余能够放松身心，保持健康的生活状态。此外，会议室、宴会厅等商务设施也为商务人士提供了便捷的商务活动空间。</p><h5><strong>三、尊贵服务，贴心周到</strong></h5><p>在中国大饭店公馆公寓，您将享受到尊贵而贴心的服务。我们的服务团队经过专业培训，始终以宾客的需求和满意度为出发点，为您提供无微不至的关怀。从入住前的咨询预订到入住后的客房清洁、餐饮服务，我们都将竭尽全力满足您的需求，让您感受到家的温馨与舒适。</p><h5><strong>四、优越位置，便捷出行</strong></h5><p>公馆公寓地理位置优越，位于城市的核心区域，交通便捷，出行无忧。无论是前往商务区还是旅游景点，您都能轻松抵达。同时，周边丰富的购物、餐饮和娱乐设施也为您的居住提供了极大的便利。</p><h5><strong>五、独特体验，品味非凡</strong></h5><p>除了舒适的居住环境和便捷的设施外，公馆公寓还注重为宾客提供独特的体验。我们定期举办各类文化活动和社交聚会，让您在享受尊贵入住的同时，也能结交志同道合的朋友，拓展社交圈子。此外，您还可以前往周边的文化景点和历史遗迹游览，领略中华文化的独特魅力。</p>', '2025-01-09 13:57:02');
INSERT INTO `news` VALUES ('5', 'http://localhost:8090/fles/1736402268263-1697969335922-全季酒店-豪华间.jpg', '品味非凡，尊贵入住：中国大饭店公馆公寓邀您共赏', '<p>中国大饭店公馆公寓，作为一处彰显尊贵与品味的居住之所，诚挚邀请您前来体验非凡的入住之旅。这里不仅提供奢华的居住环境，更将尊贵服务融入每一个细节，让您尽享宾至如归的舒适与惬意。</p><h5><strong>一、非凡品味，尽显尊贵</strong></h5><p>公馆公寓的室内设计独具匠心，将现代简约与经典奢华完美融合。从精选的家具、细腻的布艺到艺术品的点缀，每一处都彰显出非凡的品味与尊贵。步入其中，您将被这里的精致与典雅所深深吸引，仿佛置身于一个充满艺术气息的私人府邸。</p><h5><strong>二、尊贵服务，贴心周到</strong></h5><p>在中国大饭店公馆公寓，您将享受到专业而贴心的五星级服务。我们的服务团队经过严格培训，始终秉持“宾客至上”的理念，为您提供细致入微的关怀。无论是入住前的咨询预订，还是入住后的客房清洁、餐饮服务，我们都将竭尽全力满足您的需求，确保您的入住体验舒适而愉快。</p><h5><strong>三、便捷设施，尽享舒适</strong></h5><p>公馆公寓内设有各类便捷设施，让您在享受尊贵入住的同时，也能感受到家的温馨与舒适。宽敞明亮的客房配备高品质床品和家具，让您在繁忙的工作之余能够放松身心;室内恒温泳池、健身房、瑜伽室等休闲设施则让您在享受奢华的同时，也能保持健康的体魄。此外，我们还提供会议室、宴会厅等商务设施，满足您的商务需求。</p><h5><strong>四、优越地理位置，便捷出行</strong></h5><p>公馆公寓位于北京CBD核心区域，紧邻东三环和地铁一号线、二号线，交通便捷，出行无忧。无论您是前往商务区还是旅游景点，都能轻松抵达。同时，周边丰富的购物、餐饮和娱乐设施也为您的居住提供了极大的便利。</p><h5><strong>五、独特文化体验，品味非凡</strong></h5><p>除了舒适的居住环境和便捷的设施外，公馆公寓还注重为宾客提供独特的文化体验。我们定期举办各类文化活动和社交聚会，让您在享受尊贵入住的同时，也能感受到中华文化的博大精深。此外，您还可以前往周边的文化景点和历史遗迹游览，领略中华文化的独特魅力。</p>', '2025-01-09 13:58:00');
INSERT INTO `news` VALUES ('6', 'http://localhost:8090/fles/1736402336407-20200112205207.jpg', '雅诗阁来福士服务公寓-出租、长租、短租、月租', '<p>在北京雅诗阁来福士中心服务公寓，您可以尽情享受豪华而优雅的生活，能时刻感受这里交通的便利。服务公寓位于北京活力地区——东城区东直门，毗邻市内大量商业、娱乐、购物及餐饮场所。服务公寓周围环绕着许多文化旅游胜地：著名的簋街(饮食街)近在咫尺。前往被列为联合国教科文组织世界遗产的故宫博物院大约需要6公里车程。东直门交通枢纽就在服务公寓的对角处，这个交通枢纽是地铁二号线与十三号线的转换站，为您提供贯穿整座城市的便利交通网络。您也能在此交通枢纽搭乘机场快线轨道直接前往北京首都国际机场，大约需要24公里车程。</p><p>服务公寓内也备有不少设施，您可选择在设备完善的健身房内锻炼身体、在恒温游泳池和水力按摩浴池中放松身心，甚至可以在活动室里举办一场鸡尾酒会。高端新颖的生活便利设施、广泛的商务和娱乐器材配备，以及个性化服务时刻为您提供各种生活上的支持。无论您在公寓的任何地方，您都可以通过无线网络连接，自在地上网与全世界相连。居住在北京雅诗阁来福士中心服务公寓，您可以随心所欲地按照自己的方式，一边尽情享受生活与工作，一边发现北京的精彩与魅力。</p>', '2025-01-09 13:59:23');
INSERT INTO `news` VALUES ('7', 'http://localhost:8090/fles/1736402388049-room-1.jpg', '橡树公馆公寓-出租、长租、短租、月租11', '<p>北京橡树公馆公寓地处东三环三元桥的远洋新干线，紧邻老国展中心、佳程广场、南银大厦，毗邻燕莎商圈和望京商圈，靠近美国、马来西亚、日本和韩国使馆区。北京橡树公馆公寓设施齐全，房间设计温馨舒适，配备超大平面电视和多种国际卫星频道，提供给宾客种类繁多的娱乐选择。宾客还可在配备完整的西式厨房中烹饪拿手美味，或是到健身室、按摩室、SPA放松身心。</p>', '2025-01-10 19:34:58');

-- ----------------------------
-- Table structure for `protype`
-- ----------------------------
DROP TABLE IF EXISTS `protype`;
CREATE TABLE `protype` (
  `id` int(11) NOT NULL auto_increment COMMENT 'ID主键',
  `name` varchar(255) collate utf8_unicode_ci default NULL COMMENT '名称',
  `delstatus` int(11) default NULL COMMENT '逻辑删除字段',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='协会分类信息表';

-- ----------------------------
-- Records of protype
-- ----------------------------
INSERT INTO `protype` VALUES ('3', '单间类', '0');
INSERT INTO `protype` VALUES ('4', 'Loft类', '0');
INSERT INTO `protype` VALUES ('5', '一室一厅', '0');
INSERT INTO `protype` VALUES ('6', '二室一厅', '0');
INSERT INTO `protype` VALUES ('9', '其它类', '0');
INSERT INTO `protype` VALUES ('10', 'ces ', '1');

-- ----------------------------
-- Table structure for `shenqing`
-- ----------------------------
DROP TABLE IF EXISTS `shenqing`;
CREATE TABLE `shenqing` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键ID',
  `memberid` int(11) default NULL COMMENT '用户ID',
  `xinxiid` int(11) default NULL COMMENT '公寓ID',
  `bhno` varchar(255) collate utf8_unicode_ci default NULL COMMENT '申请单号',
  `name` varchar(255) collate utf8_unicode_ci default NULL COMMENT '申请人姓名',
  `tel` varchar(255) collate utf8_unicode_ci default NULL COMMENT '联系方式',
  `scnum` int(11) default NULL COMMENT '申请入住时长',
  `price` decimal(10,1) default NULL COMMENT '单价（元/月）',
  `total` decimal(10,1) default NULL COMMENT '合计金额',
  `fkstatus` varchar(255) collate utf8_unicode_ci default NULL COMMENT '付款状态',
  `status` varchar(255) collate utf8_unicode_ci default NULL COMMENT '审核状态',
  `savetime` varchar(255) collate utf8_unicode_ci default NULL COMMENT '申请日期',
  PRIMARY KEY  (`id`),
  KEY `fav+mid` (`memberid`),
  KEY `sq_xid` (`xinxiid`),
  CONSTRAINT `shenqing_ibfk_1` FOREIGN KEY (`memberid`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sq_xid` FOREIGN KEY (`xinxiid`) REFERENCES `xinxi` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='公寓申请信息表';

-- ----------------------------
-- Records of shenqing
-- ----------------------------
INSERT INTO `shenqing` VALUES ('4', '9', '6', '32751211', '张桦', '18100990022', '6', '1900.0', '11400.0', '已付款', '已退房', '2025-01-10 17:06:04');
INSERT INTO `shenqing` VALUES ('6', '12', '6', '15538763', '张彩', '13900998844', '8', '1900.0', '15200.0', '已付款', '已退房', '2025-01-10 19:29:51');

-- ----------------------------
-- Table structure for `shuju`
-- ----------------------------
DROP TABLE IF EXISTS `shuju`;
CREATE TABLE `shuju` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键ID',
  `xinxiid` int(11) default NULL COMMENT '公寓ID',
  `memberid` int(11) default NULL COMMENT '申请用户ID',
  `bhno` varchar(255) default NULL COMMENT '申请单号',
  `typename` varchar(255) default NULL COMMENT '违规类型',
  `content` longtext COMMENT '详细内容',
  `savetime` varchar(255) default NULL COMMENT '创建日期',
  PRIMARY KEY  USING BTREE (`id`),
  KEY `shuju_xid` (`xinxiid`),
  CONSTRAINT `shuju_xid` FOREIGN KEY (`xinxiid`) REFERENCES `xinxi` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='违规信息表';

-- ----------------------------
-- Records of shuju
-- ----------------------------
INSERT INTO `shuju` VALUES ('1', '6', '9', '32751211', '设施损坏', '私自拆除房间衣柜', '2025-01-10 18:38:41');
INSERT INTO `shuju` VALUES ('2', '6', '12', '15538763', '擅自转租', '建房子转租给亲戚11', '2025-01-10 19:33:19');

-- ----------------------------
-- Table structure for `xinxi`
-- ----------------------------
DROP TABLE IF EXISTS `xinxi`;
CREATE TABLE `xinxi` (
  `id` int(11) NOT NULL auto_increment COMMENT 'ID主键',
  `filename` varchar(255) collate utf8_unicode_ci default NULL COMMENT '图片',
  `name` varchar(255) collate utf8_unicode_ci default NULL COMMENT '名称',
  `typeid` int(11) default NULL COMMENT '分类ID',
  `price` decimal(10,1) default NULL COMMENT '价格',
  `lcnum` varchar(255) collate utf8_unicode_ci default NULL COMMENT '楼层',
  `cxname` varchar(255) collate utf8_unicode_ci default NULL COMMENT '朝向',
  `mjnum` varchar(20) collate utf8_unicode_ci default NULL COMMENT '面积',
  `addr` varchar(255) collate utf8_unicode_ci default NULL COMMENT '地址',
  `content` longtext collate utf8_unicode_ci COMMENT '介绍',
  `status` varchar(255) collate utf8_unicode_ci default NULL COMMENT '状态',
  `savetime` varchar(255) collate utf8_unicode_ci default NULL COMMENT '创建日期',
  `delstatus` varchar(255) collate utf8_unicode_ci default NULL COMMENT '逻辑删除字段',
  PRIMARY KEY  (`id`),
  KEY `xinxi_tid` (`typeid`),
  CONSTRAINT `xinxi_tid` FOREIGN KEY (`typeid`) REFERENCES `protype` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='公寓信息表';

-- ----------------------------
-- Records of xinxi
-- ----------------------------
INSERT INTO `xinxi` VALUES ('2', '[\"http://localhost:8090/fles/1736477914546-gy-01.jpg\",\"http://localhost:8090/fles/1736477929444-room-1.jpg\",\"http://localhost:8090/fles/1736477929469-room-2.jpg\"]', '金隅环贸国际公寓', '5', '2500.0', '5', '朝南', '60', '北三环东路36号', '<p>高端服务式公寓，隶属于金隅集团，是环球贸易中心综合项目的重要组成部分。该公寓以其优越的地理位置、高品质的居住环境和完善的配套设施，吸引了众多国际高端商务人士及其家庭入住。</p>', '空闲', '2025-01-09 20:16:56', '0');
INSERT INTO `xinxi` VALUES ('3', '[\"http://localhost:8090/fles/1736478121232-1697969036210-如家酒店-标准间.jpg\",\"http://localhost:8090/fles/1736478128083-20200112205207.jpg\"]', '华润有巢公邸公寓', '6', '3300.0', '6', '朝南', '78', '曙光西里甲5号院凤凰汇购物中心F1', '<p>2018年央企华润置地发布了“有巢”品牌，华润置地是央企华润集团旗下地产业务平台，行业里宅地产和商业地产开发运营领头人。此次出品定位为致力于解决城市租住需求、提高租住品质、创新租住体验的长租公寓业务平台。</p><p>华润有巢公邸公寓位于集商业，居住，贸易和娱乐为一体的燕莎区域，北三环寓东三环的交汇处都市核心区，无论去机场，国贸还是三里屯都方便至极。<br/>华润有巢公邸非常注重品质与体验，公寓户型有1-3居，每套精心布局的居室，餐厅、客厅、卧房都彰显构思巧妙，区域分明而又浑然一体。在这个充满生活能量的有机空间里，亦有引领现代生活科技的生活娱乐、资讯与安全系统，令阁下尽享舒畅安逸。</p>', '空闲', '2025-01-10 11:03:14', '0');
INSERT INTO `xinxi` VALUES ('4', '[\"http://localhost:8090/fles/1736478257974-gy-02.jpg\",\"http://localhost:8090/fles/1736478257969-gy-03.jpg\",\"http://localhost:8090/fles/1736478257963-gy-04.jpg\"]', '国贸公寓', '6', '3800.0', '8', '朝南', '84', '建国门外大街1号', '<p>国贸公寓，位于北京市朝阳区国贸核心区域，是一处集高端住宅、商务办公、休闲娱乐于一体的综合性公寓。该公寓由南、北两座高层公寓楼组成，共提供多种户型选择，满足不同人群的居住需求。国贸公寓以其卓越的地理位置、完善的配套设施和优质的服务，吸引了众多商务人士和跨国企业高管的青睐。</p><p><strong>二、地理位置</strong></p><p><strong>地址：</strong>北京市朝阳区建国门外大街1号(部分信息指出位于光华路甲8号和乔大厦D座，但主要以建国门外大街1号为准)。</p><p><strong>交通：</strong>紧邻地铁一号线、十号线交汇的国贸站，周边公交线路众多，交通极为便利。</p><p><strong>周边设施：</strong>周边汇聚了众多国际知名企业、金融机构、购物中心、餐厅、咖啡馆等，生活配套设施齐全。</p><p><strong>三、公寓设施</strong></p><p><strong>户型：</strong>提供从单间到多居室等多种户型，面积不等，满足不同住户的居住需求。</p><p><strong>装修：</strong>采用现代简约的设计风格，内部装修豪华而温馨，配备全套家具家电，拎包即可入住。</p><p><strong>公共设施：</strong>设有室内恒温游泳池、健身房、瑜伽室、桑拿房等休闲娱乐设施，以及住客休闲厅、多功能会议室等商务空间。</p>', '空闲', '2025-01-10 11:04:57', '0');
INSERT INTO `xinxi` VALUES ('5', '[\"http://localhost:8090/fles/1736478376671-gy-05.jpg\",\"http://localhost:8090/fles/1736478376659-gy-06.jpg\",\"http://localhost:8090/fles/1736478376667-gy-07.jpg\"]', '佳兆业铂域行政公寓', '4', '2800.0', '7', '朝南', '55', '建国路86号', '<p>佳兆业铂域行政公寓，坐落于北京市朝阳区繁华的国贸商圈核心地带——建国路86号佳兆业广场南塔，是城市精英与高端商旅人士的理想居所。公寓以其卓越的地理位置、奢华的居住体验、完善的配套设施及贴心的服务，赢得了市场的广泛赞誉。</p><p><strong>一、基本信息</strong></p><p><strong>名称：</strong>佳兆业铂域行政公寓</p><p><strong>位置：</strong>北京市朝阳区建国路86号佳兆业广场南塔</p><p><strong>开发商：</strong>佳兆业集团</p><p><strong>开业时间：</strong>2018年</p><p><strong>联系电话：</strong>010-85408888(可能随时间变化，请以官方最新信息为准)</p><p><strong>二、地理位置</strong></p><p>佳兆业铂域行政公寓位于北京市的核心地带——国贸商圈，地处长安街与大望路交汇处的东南侧。公寓周边商务氛围浓厚，紧邻新光天地、华贸中心等高端购物中心，同时周边写字楼林立，为商务出行提供了极大的便利。此外，公寓距离世界文化遗产故宫约15分钟车程，距离北京首都国际机场约30分钟车程，交通网络四通八达。</p>', '空闲', '2025-01-10 11:06:54', '0');
INSERT INTO `xinxi` VALUES ('6', '[\"http://localhost:8090/fles/1736478496101-gy-08.jpg\",\"http://localhost:8090/fles/1736478496108-gy-09.jpg\",\"http://localhost:8090/fles/1736478496104-gy-010.jpg\"]', '雅诗阁来福士中心公寓', '3', '1900.0', '4', '朝南', '45', '北京市东城区东直门南大街1号', '<h5>一、基本信息</h5><p>雅诗阁来福士中心服务公寓是由雅诗阁集团管理的高端服务公寓品牌，致力于为全球商旅人士及家庭旅客提供舒适、便捷、高品质的居住体验。公寓通常位于城市的核心区域，紧邻交通枢纽和商业中心，周边设施齐全，生活便利。</p><h5>二、地理位置</h5><p>北京雅诗阁来福士中心服务公寓：位于北京市东城区东直门南大街1-2号，地处东直门交通枢纽，地铁二号线、十三号线和机场快线四通八达，方便前往城市的各个角落。</p><p>深圳雅诗阁来福士广场服务公寓：位于深圳市南山区登良路与南海大道交界，与超甲级写字楼、高级购物商场、高端住宅共同组成深圳最多元化的“都市客厅”综合体项目——深圳来福士广场。</p><p>其他城市的雅诗阁来福士中心服务公寓也均位于各自城市的繁华地段，交通便利，周边设施完善。</p><h5>三、设施与服务</h5><p><strong>房间设施：</strong></p><p>提供从单房公寓到三房套房等多种房型，满足不同居住需求。</p><p>每套公寓均配备宽敞独立的居住空间、设施齐全的厨房、高品质影音设备及高速无线网络。</p><p>部分公寓还设有独立客厅、餐厅区及观景阳台，让住客尽享城市美景。</p><p><strong>公共区域：</strong></p><p>设有健身房、室内恒温泳池、瑜伽房等休闲健身设施，供住客放松身心。</p><p>部分公寓还提供儿童游戏室、儿童嬉水池等儿童娱乐设施，关爱儿童成长。</p>', '空闲', '2025-01-10 11:08:52', '0');
INSERT INTO `xinxi` VALUES ('7', '[\"http://localhost:8090/fles/1736508705566-1577462390018.jpg\",\"http://localhost:8090/fles/1736508714203-1581511346049.jpg\",\"http://localhost:8090/fles/1736508714209-1697969020241-如家酒店-大床房.jpg\"]', '测试公寓信息', '9', '3400.0', '5', '朝南', '89', '测试111', '<p>测试公寓信息测试公寓信息测试公寓信息测试公寓信息测试公寓信息测试公寓信息测试公寓信息测试公寓信息测试公寓信息测试公寓信息测试公寓信息测试公寓信息测试公寓信息测试公寓信息测试公寓信息测试公寓信息测试公寓信息测试公寓信息测试公寓信息测试公寓信息</p>', '空闲', '2025-01-10 19:32:13', '1');
