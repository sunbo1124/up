CREATE TABLE public.sys_user (
  id INTEGER NOT NULL,
  login_name VARCHAR(100) NOT NULL,
  password VARCHAR(200) NOT NULL,
  name VARCHAR(32),
  sex INTEGER DEFAULT 1,
  phone VARCHAR(32),
  email VARCHAR(64),
  is_builtin BOOLEAN DEFAULT false NOT NULL,
  login_time TIMESTAMP(0) WITHOUT TIME ZONE,
  password_last_modify_time TIMESTAMP(0) WITHOUT TIME ZONE,
  update_time TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  create_time TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  CONSTRAINT SYS_USER_LOGIN_NAME_INDEX UNIQUE(login_name),
  CONSTRAINT SYS_USER_PKEY PRIMARY KEY(id)
) 
WITH (oids = false);

CREATE TABLE public.sys_menu (
  id INTEGER NOT NULL,
  pid INTEGER DEFAULT 0 NOT NULL,
  name VARCHAR(256) NOT NULL,
  url VARCHAR(200) NOT NULL,
  icon VARCHAR(100),
  order_num INTEGER DEFAULT 0 NOT NULL,
  type INTEGER,
  update_time TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  create_time TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  CONSTRAINT sys_menu_pkey PRIMARY KEY(id)
) 
WITH (oids = false);

CREATE TABLE public.sys_role (
  id INTEGER NOT NULL,
  name VARCHAR(100) NOT NULL,
  is_builtin BOOLEAN DEFAULT false NOT NULL,
  update_time TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  create_time TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  CONSTRAINT sys_role_NAME_key UNIQUE(name),
  CONSTRAINT sys_role_pkey PRIMARY KEY(id)
) 
WITH (oids = false);

CREATE TABLE public.sys_role_menu (
  id INTEGER NOT NULL,
  role_id INTEGER NOT NULL,
  menu_id INTEGER NOT NULL,
  update_time TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  create_time TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  CONSTRAINT sys_role_menu_pkey PRIMARY KEY(id)
) 
WITH (oids = false);


CREATE TABLE public.sys_user_role (
  id INTEGER NOT NULL,
  user_id INTEGER NOT NULL,
  role_id INTEGER NOT NULL,
  update_time TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  create_time TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  CONSTRAINT sys_user_role_pkey PRIMARY KEY(ID)
) 
WITH (oids = false);

drop table if exists tsc_conf_param;
CREATE TABLE public.tsc_conf_param (
  id INTEGER NOT NULL,
  param_key VARCHAR(50) NOT NULL,
  param_value VARCHAR(500) NOT NULL,
  create_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  update_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  CONSTRAINT tsc_conf_param_param_key_key UNIQUE(param_key),
  CONSTRAINT tsc_conf_param_pkey PRIMARY KEY(id)
) 
WITH (oids = false);
--
-- Definition for sequence geely_sequences (OID = 16454) : 
--
drop sequence if exists geely_sequences;
CREATE SEQUENCE public.geely_sequences
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
   
drop sequence if exists tsc_conf_param_sequence;
CREATE SEQUENCE public.tsc_conf_param_sequence
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
    
--- service mapping
drop table if exists sys_service_app_context_path;
create table sys_service_app_context_path(
	app_name VARCHAR(100) NOT NULL,
	context VARCHAR(100) NOT NULL,
	service_id VARCHAR(50) NOT NULL,
	path VARCHAR(200) NOT NULL,
	ip VARCHAR(200) NOT NULL,
	port INTEGER NOT NULL,
	CONSTRAINT sys_service_app_context_path_pkey PRIMARY KEY(service_id)
);
