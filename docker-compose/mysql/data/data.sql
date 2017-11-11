BEGIN;

USE blog;

INSERT INTO `sys_role`
VALUES
('1', 'ROLE_ADMIN'),
('2', 'ROLE_USER');

INSERT INTO `sys_user`
VALUES
('1', '$2a$10$JXHWa.lciqvKRaTyK4qTr.ctD9oSWHt4l3YqXadvfktwnHwvFOQ.i', 'admin');

INSERT INTO `sys_user_roles`
VALUES
('1', '1', '1'),('1','2','2');

insert into `blog`.`b_user_info` ( `id`, `is_lock`, `nike_name`) values ( '1', '0', 'admin');
COMMIT;

