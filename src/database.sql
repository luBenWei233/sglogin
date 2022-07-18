CREATE TABLE `course` (
  `id` char(36) NOT NULL COMMENT '教程主键',
  `coursetype` int DEFAULT NULL COMMENT '教程类型',
  `title` varchar(200) DEFAULT NULL COMMENT '教程标题',
  `defaultif` int DEFAULT NULL COMMENT '没有ID时的默认教程：0、否，1、是',
  `valid` int DEFAULT NULL COMMENT '是否发布：0、否，1、是',
  `releasedate` datetime DEFAULT NULL COMMENT '发布时间',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `createplayerid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人',
  `updatedate` datetime DEFAULT NULL COMMENT '更新时间',
  `updateplayerid` varchar(36) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
#line#
CREATE TABLE `course_sub` (
  `id` char(36) NOT NULL COMMENT '教程子集主键',
  `content` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  `type` int NOT NULL COMMENT '内容类型：1、文本，2、图片，3、html',
  `sort` int NOT NULL COMMENT '顺序',
  `courseid` char(36) NOT NULL COMMENT '父id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
#line#
CREATE TABLE `ip_limit` (
  `playername` varchar(100) NOT NULL COMMENT '玩家账号',
  `ip` varchar(15) NOT NULL COMMENT 'ip',
  `regdate` datetime NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`playername`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
#line#
CREATE TABLE `notice` (
  `id` char(36) NOT NULL COMMENT '公告表主键',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告标题',
  `content` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告内容',
  `fileid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '公告文件',
  `valid` int DEFAULT NULL COMMENT '是否显示：0、隐藏，1、显示',
  `iftop` int DEFAULT NULL COMMENT '是否置顶：0、否，1、是',
  `createplayerid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间（如果没有定时发布，则显示为发布时间）',
  `updateplayerid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '修改人',
  `updatedate` datetime DEFAULT NULL COMMENT '修改时间',
  `timing` datetime DEFAULT NULL COMMENT '定时发部（并向玩家显示为创建时间）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
#line#
CREATE TABLE `player` (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '玩家主键，UUID',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '玩家登陆账号、高版本MC可支持中文（到小写）',
  `password` varchar(70) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `nickname` varchar(100) DEFAULT NULL COMMENT '玩家在游戏中隐藏真实名称后的昵称',
  `realname` varchar(100) NOT NULL COMMENT '我的世界是不区分大小写的',
  `regDate` datetime NOT NULL COMMENT '注册账号时间',
  `email` varchar(100) DEFAULT NULL COMMENT '绑定的邮箱',
  `x` float DEFAULT NULL,
  `y` float DEFAULT NULL,
  `z` float DEFAULT NULL,
  `yaw` float DEFAULT NULL,
  `pitch` float DEFAULT NULL,
  `world` varchar(100) DEFAULT NULL COMMENT '玩家下线时所在的世界',
  `realip` varchar(15) DEFAULT NULL COMMENT '玩家注册时的ID',
  `lastDate` datetime DEFAULT NULL COMMENT '玩家最近一次登陆的时间',
  `lastip` varchar(15) DEFAULT NULL COMMENT '玩家最近一次登陆的IP',
  `exitDate` datetime DEFAULT NULL COMMENT '玩家最近一次离线时间',
  `banif` int DEFAULT NULL COMMENT '是否封号：0正常、1封禁、2永封',
  `banstart` datetime DEFAULT NULL COMMENT '起封时间',
  `banend` datetime DEFAULT NULL COMMENT '封禁结束时间',
  `online` int DEFAULT NULL COMMENT '玩家是否在线：0、不在，1、在线',
  `adminif` int DEFAULT NULL COMMENT '是否是管理员：0、不是，1、是',
  `countTime` int DEFAULT NULL COMMENT '总在线时长，分钟',
  `banid` int DEFAULT NULL COMMENT '被办理由',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
#line#
CREATE TABLE `player_ban` (
  `id` char(36) NOT NULL COMMENT '玩家封禁主键',
  `playerid` char(36) NOT NULL COMMENT '玩家主键',
  `handleid` char(36) DEFAULT NULL COMMENT '处理人id',
  `startdate` datetime DEFAULT NULL COMMENT '封禁开始时间',
  `enddate` datetime DEFAULT NULL COMMENT '封禁结束时间',
  `bantime` varchar(30) DEFAULT NULL COMMENT '封禁时间，分钟',
  `reasonid` int DEFAULT NULL COMMENT '与字典表关联',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
#line#
CREATE TABLE `player_log` (
  `id` char(36) NOT NULL COMMENT '玩家登陆日志',
  `playerid` char(36) NOT NULL COMMENT '玩家id',
  `ip` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'ip',
  `logindate` datetime NOT NULL COMMENT '玩家登陆时间',
  `exitdate` datetime NOT NULL COMMENT '玩家退出时间',
  `logintime` int NOT NULL COMMENT '玩家单次在线时间，小于1分钟不记录（分钟）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
#line#
CREATE TABLE `sys_config` (
  `id` varchar(40) NOT NULL COMMENT '系统表主键，请勿修改本主键',
  `value` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '值',
  `updatedate` datetime DEFAULT NULL COMMENT '更新时间',
  `updateplayerid` char(36) DEFAULT NULL COMMENT '更新人员',
  `change` int NOT NULL COMMENT '0:不可以修改、1:可以修改',
  `remarks` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
#line#
CREATE TABLE `sys_databook` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '字典表主键',
  `groupid` int DEFAULT NULL COMMENT '字典类型',
  `value` varchar(1000) DEFAULT NULL COMMENT '字典值',
  `valid` int DEFAULT NULL COMMENT '0:隐藏、1:使用',
  `createplayerid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人id',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateplayerid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新人',
  `updatedate` datetime DEFAULT NULL COMMENT '更新时间',
  `parentid` int DEFAULT NULL COMMENT '与本表主键关联，如不是子级，请不要有值！',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
#line#
CREATE TABLE `sys_file` (
  `fileid` char(36) NOT NULL COMMENT '文件主键',
  `keyid` char(36) NOT NULL COMMENT '其它表与本字段关联',
  `filename` varchar(300) DEFAULT NULL COMMENT '超过300自动截断，原始文件名',
  `realname` varchar(50) DEFAULT NULL COMMENT '修改后的文件名（fileid+后缀）',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '保存在服务器上的文件路径（相对路径）',
  `url` varchar(200) DEFAULT NULL COMMENT '互联网访问地址（定制或你自己开发时可以用存储桶）',
  `serverid` varchar(50) DEFAULT NULL COMMENT '服务器代号，为了方便文件迁移',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `createplayerid` varchar(36) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`fileid`,`keyid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
#line#
CREATE TABLE `sys_file_bak` (
  `fileid` char(36) NOT NULL COMMENT '文件主键',
  `keyid` char(36) NOT NULL COMMENT '其它表与本字段关联',
  `filename` varchar(300) DEFAULT NULL COMMENT '超过300自动截断，原始文件名',
  `realname` varchar(50) DEFAULT NULL COMMENT '修改后的文件名（fileid+后缀）',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '保存在服务器上的文件路径（相对路径）',
  `url` varchar(200) DEFAULT NULL COMMENT '互联网访问地址（定制或你自己开发时可以用存储桶）',
  `serverid` varchar(50) DEFAULT NULL COMMENT '服务器代号，为了方便文件迁移',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `createplayerid` int DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`fileid`,`keyid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
#line#
CREATE TABLE `sys_group` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '字典类型主键',
  `value` varchar(60) NOT NULL COMMENT '字典类型值',
  `sort` int DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;