/*
Navicat PGSQL Data Transfer

Source Server         : localhost
Source Server Version : 90404
Source Host           : 127.0.0.1:5432
Source Database       : admin
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90404
File Encoding         : 65001

Date: 2017-07-04 17:49:34
*/


-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_menu";
CREATE TABLE "public"."sys_menu" (
"id" int4 NOT NULL,
"pid" int4 DEFAULT 0 NOT NULL,
"name" varchar(256) COLLATE "default" NOT NULL,
"url" varchar(200) COLLATE "default" NOT NULL,
"icon" varchar(100) COLLATE "default",
"order_num" int4 DEFAULT 0 NOT NULL,
"type" int4,
"update_time" timestamp(6) NOT NULL,
"create_time" timestamp(6) NOT NULL,
"locale" varchar(32) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO "public"."sys_menu" VALUES ('0', '0', '服务控制管理系统', 'javascript:void(0)', '', '10', '1', '2016-03-15 15:59:05', '2016-03-15 15:59:05', 'CN');
INSERT INTO "public"."sys_menu" VALUES ('1', '0', '应用管理', 'javascript:void(0)', 'entypo-layout ', '10', '1', '2016-12-02 09:23:27.446', '2016-03-15 15:59:05', 'CN');
INSERT INTO "public"."sys_menu" VALUES ('10', '0', 'Service Controller System', 'javascript:void(0)', '', '10', '1', '2016-03-15 15:59:05', '2016-03-15 15:59:05', 'US');
INSERT INTO "public"."sys_menu" VALUES ('11', '0', 'System management', 'javascript:void(0)', 'entypo-layout ', '10', '1', '2016-03-15 15:59:05', '2016-03-15 15:59:05', 'US');
INSERT INTO "public"."sys_menu" VALUES ('101', '1', '用户管理', '#/user', '', '1', '1', '2016-03-15 15:59:05', '2016-03-15 15:59:05', 'CN');
INSERT INTO "public"."sys_menu" VALUES ('102', '1', '角色管理', '#/role', '', '2', '1', '2016-03-15 15:59:05', '2016-03-15 15:59:05', 'CN');
INSERT INTO "public"."sys_menu" VALUES ('103', '1', '分配角色', '#/allotRole', '', '3', '1', '2016-03-15 15:59:05', '2016-03-15 15:59:05', 'CN');
INSERT INTO "public"."sys_menu" VALUES ('104', '1', '菜单管理', '#/menu', '', '4', '1', '2016-03-15 15:59:05', '2016-03-15 15:59:05', 'CN');
INSERT INTO "public"."sys_menu" VALUES ('105', '1', '分配菜单', '#/allotmenu', '', '5', '1', '2016-03-15 15:59:05', '2016-03-15 15:59:05', 'CN');
INSERT INTO "public"."sys_menu" VALUES ('1101', '11', 'User management', '#/user', '', '1', '1', '2016-03-15 15:59:05', '2016-03-15 15:59:05', 'US');
INSERT INTO "public"."sys_menu" VALUES ('1102', '11', 'Role management', '#/role', '', '2', '1', '2016-03-15 15:59:05', '2016-03-15 15:59:05', 'US');
INSERT INTO "public"."sys_menu" VALUES ('1103', '11', 'Allot Role', '#/allotRole', '', '3', '1', '2016-03-15 15:59:05', '2016-03-15 15:59:05', 'US');
INSERT INTO "public"."sys_menu" VALUES ('1104', '11', 'Menu management', '#/menu', '', '4', '1', '2016-03-15 15:59:05', '2016-03-15 15:59:05', 'US');
INSERT INTO "public"."sys_menu" VALUES ('1105', '11', 'Allot Menu', '#/allotmenu', '', '5', '1', '2016-03-15 15:59:05', '2016-03-15 15:59:05', 'US');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_role";
CREATE TABLE "public"."sys_role" (
"id" int4 NOT NULL,
"name" varchar(100) COLLATE "default" NOT NULL,
"is_builtin" bool DEFAULT false NOT NULL,
"update_time" timestamp(6) NOT NULL,
"create_time" timestamp(6) NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO "public"."sys_role" VALUES ('1', 'admin', 't', '2016-03-08 10:35:29', '2016-03-07 10:35:33');
INSERT INTO "public"."sys_role" VALUES ('2', '普通用户', 'f', '2016-08-16 15:14:07.033', '2016-08-16 15:14:07.033');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_role_menu";
CREATE TABLE "public"."sys_role_menu" (
"id" int4 NOT NULL,
"role_id" int4 NOT NULL,
"menu_id" int4 NOT NULL,
"update_time" timestamp(6) NOT NULL,
"create_time" timestamp(6) NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO "public"."sys_role_menu" VALUES ('1', '1', '1', '2016-01-06 12:50:00', '2016-01-06 12:50:00');
INSERT INTO "public"."sys_role_menu" VALUES ('10', '1', '10', '2016-01-06 12:50:00', '2016-01-06 12:50:00');
INSERT INTO "public"."sys_role_menu" VALUES ('11', '1', '11', '2016-01-06 12:50:00', '2016-01-06 12:50:00');
INSERT INTO "public"."sys_role_menu" VALUES ('22', '1', '103', '2016-01-06 12:50:00', '2016-01-06 12:50:00');
INSERT INTO "public"."sys_role_menu" VALUES ('24', '1', '105', '2016-01-06 12:50:00', '2016-01-06 12:50:00');
INSERT INTO "public"."sys_role_menu" VALUES ('51', '1', '1101', '2016-01-06 12:50:00', '2016-01-06 12:50:00');
INSERT INTO "public"."sys_role_menu" VALUES ('52', '1', '1102', '2016-01-06 12:50:00', '2016-01-06 12:50:00');
INSERT INTO "public"."sys_role_menu" VALUES ('53', '1', '1103', '2016-01-06 12:50:00', '2016-01-06 12:50:00');
INSERT INTO "public"."sys_role_menu" VALUES ('54', '1', '1104', '2016-01-06 12:50:00', '2016-01-06 12:50:00');
INSERT INTO "public"."sys_role_menu" VALUES ('55', '1', '1105', '2016-01-06 12:50:00', '2016-01-06 12:50:00');
INSERT INTO "public"."sys_role_menu" VALUES ('69', '1', '101', '2016-01-06 12:50:00', '2016-01-06 12:50:00');
INSERT INTO "public"."sys_role_menu" VALUES ('70', '1', '102', '2016-01-06 12:50:00', '2016-01-06 12:50:00');
INSERT INTO "public"."sys_role_menu" VALUES ('71', '1', '104', '2016-01-06 12:50:00', '2016-01-06 12:50:00');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_user";
CREATE TABLE "public"."sys_user" (
"id" int4 NOT NULL,
"login_name" varchar(100) COLLATE "default" NOT NULL,
"password" varchar(200) COLLATE "default" NOT NULL,
"name" varchar(32) COLLATE "default",
"sex" int4 DEFAULT 1,
"phone" varchar(32) COLLATE "default",
"email" varchar(64) COLLATE "default",
"is_builtin" bool DEFAULT false NOT NULL,
"login_time" timestamp(6),
"password_last_modify_time" timestamp(6),
"update_time" timestamp(6) NOT NULL,
"create_time" timestamp(6) NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO "public"."sys_user" VALUES ('1', 'administrator', 'e10adc3949ba59abbe56e057f20f883e', 'wsy', '1', '', '', 't', '2015-10-12 14:12:52', '2015-10-12 14:12:52', '2015-10-12 14:12:52', '2015-10-12 14:12:52');
INSERT INTO "public"."sys_user" VALUES ('2', 'logger', 'e10adc3949ba59abbe56e057f20f883e', 'logger', '0', '', '', 'f', null, '2016-08-16 15:37:17.072', '2016-08-16 15:37:17.072', '2016-08-16 15:13:53');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_user_role";
CREATE TABLE "public"."sys_user_role" (
"id" int4 NOT NULL,
"user_id" int4 NOT NULL,
"role_id" int4 NOT NULL,
"update_time" timestamp(6) NOT NULL,
"create_time" timestamp(6) NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO "public"."sys_user_role" VALUES ('1', '1', '1', '2016-04-08 18:01:29.156025', '2016-04-08 18:01:29.156025');
INSERT INTO "public"."sys_user_role" VALUES ('2', '2', '2', '2016-04-08 18:01:29.156025', '2016-04-08 18:01:29.156025');

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table sys_menu
-- ----------------------------
ALTER TABLE "public"."sys_menu" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table sys_role
-- ----------------------------
CREATE UNIQUE INDEX "sys_role_name_key" ON "public"."sys_role" USING btree (name);

-- ----------------------------
-- Primary Key structure for table sys_role
-- ----------------------------
ALTER TABLE "public"."sys_role" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_role_menu
-- ----------------------------
ALTER TABLE "public"."sys_role_menu" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table sys_user
-- ----------------------------
CREATE UNIQUE INDEX "sys_user_login_name_index" ON "public"."sys_user" USING btree (login_name);

-- ----------------------------
-- Primary Key structure for table sys_user
-- ----------------------------
ALTER TABLE "public"."sys_user" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_user_role
-- ----------------------------
ALTER TABLE "public"."sys_user_role" ADD PRIMARY KEY ("id");
