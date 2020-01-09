-- 用户表增加密码, 盐值
ALTER TABLE uac_user ADD COLUMN `password` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '密码' AFTER `name`;

ALTER TABLE uac_user ADD COLUMN `salt` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '盐值' AFTER `password`;