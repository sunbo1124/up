delete from sys_user;
delete from sys_menu;
delete from sys_role;
delete from sys_user_role;
delete from sys_role_menu;
--
-- Data for table public.sys_user (OID = 16416) (LIMIT 0,2)
--
INSERT INTO sys_user (id, login_name, password, name, phone, email, is_builtin, login_time, password_last_modify_time, update_time, create_time)
VALUES (1, 'administrator', '123456', 'wsy', '17601612994', 'wsy2355883@163.com', true, '2015-10-12 14:12:52', '2015-10-12 14:12:52', '2015-10-12 14:12:52', '2015-10-12 14:12:52');

INSERT INTO sys_user (id, login_name, password, name, phone, email, is_builtin, login_time, password_last_modify_time, update_time, create_time)
VALUES (2, 'admin', 'admin', 'wsy', '17601612994', 'wsy2355883@163.com', true, '2015-10-12 14:12:52', '2015-10-12 14:12:52', '2015-10-12 14:12:52', '2015-10-12 14:12:52');

--
-- Data for table public.sys_menu (OID = 16432) (LIMIT 0,6)
--
INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (1, 0, '系统管理', 'javascript:void(0)', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');

INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (2, 0, '车辆管理', 'javascript:void(0)', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');

INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (3, 0, '系统参数', '#/param', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');

INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (4, 0, '日志管理', '#/log', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');

INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (101, 1, '用户管理', 'javascript:void(0)', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');

INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (102, 1, '角色管理', 'javascript:void(0)', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');

INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (103, 1, '机构管理', 'javascript:void(0)', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');

INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (5, 0, '服务注册管理', 'javascript:void(0)', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');

INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (10101, 101, 'VIP用户', 'javascript:void(0)', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');

INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (10102, 101, '普通用户', 'javascript:void(0)', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');

INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (501, 5, 'Dispatcher注册管理', '#/subscription', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');

INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (502, 5, 'Dispatcher注册信息管理', '#/info', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');


INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (6, 0, 'Dubbo服务管理', 'javascript:void(0)', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');
INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (104, 6, 'Dubbo服务提供者管理', '#/dubbo/provider', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');
INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (105, 6, 'Dubbo服务消费者管理', '#/dubbo/consumer', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');
INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (106, 6, 'Dubbo服务管理', '#/dubbo/service', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');


insert into sys_role (id,name,is_builtin,update_time,create_time) VALUES (1, 'admin', true, now(), now());

delete from sys_role_menu;
insert into sys_role_menu (ID,ROLE_ID,MENU_ID,UPDATE_TIME,CREATE_TIME)
select nextval('geely_sequences'), 1, ID, now(), now() from sys_menu;
delete from sys_user_role;
insert into sys_user_role (ID, USER_ID, ROLE_ID, UPDATE_TIME, CREATE_TIME) VALUES (1, 1, 1, now(), now());
insert into sys_user_role (ID, USER_ID, ROLE_ID, UPDATE_TIME, CREATE_TIME) VALUES (2, 2, 1, now(), now());


INSERT INTO "tsc_conf_param" ("id","param_key","param_value","create_time","update_time") VALUES (2,'DISPATCHER_USERNAME','dispatcher-api',now(),now());
INSERT INTO "tsc_conf_param" ("id","param_key","param_value","create_time","update_time") VALUES (3,'DISPATCHER_PASSWORD','dispatcher-api',now(),now());
INSERT INTO "tsc_conf_param" ("id","param_key","param_value","create_time","update_time") VALUES (5,'jestclient.connurl','http://10.166.72.240:9200',now(),now());
INSERT INTO "tsc_conf_param" ("id","param_key","param_value","create_time","update_time") VALUES (4,'DISPATCHER_REQUESTURI','http://10.200.1.185:8080/v1/message-subscription/',now(),now());
INSERT INTO "tsc_conf_param" ("id","param_key","param_value","create_time","update_time") VALUES (1,'zookeeper.address','zookeeper://127.0.0.1:2181',now(),now());
INSERT INTO "tsc_conf_param" ("id","param_key","param_value","create_time","update_time") VALUES (6,'DISPATCHER_IP','10.200.1.185',now(),now());
INSERT INTO "tsc_conf_param" ("id","param_key","param_value","create_time","update_time") VALUES (7,'DISPATCHER_PORT','8080',now(),now());


