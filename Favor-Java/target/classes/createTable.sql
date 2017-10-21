drop table if exists t_user;
CREATE TABLE public.t_user
(
  id numeric,
  name text,
  password text,
  phone text,
  sex "char",
  avatar text,
  signature text,
  introduction text,
  createtime date,
  updatetime date,
  lastlogintime date,
  primary key(id,name,phone)
);