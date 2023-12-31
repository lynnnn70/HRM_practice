INSERT INTO `users` (`user_name`, `password`) VALUES
                                                 ('admin', 'admin'),
                                                 ('userE', 'passwordE'),
                                                 ('userD', 'passwordD'),
                                                 ('userS', 'passwordS');


INSERT INTO `roles` (`role_name`) VALUES
                                 ('ROLE_ADMIN'),
                                 ('ROLE_USER_E'),
                                 ('ROLE_USER_D'),
                                 ('ROLE_USER_S');

-- 假定 'admin' 的 user_id 為 1, 'userE' 的 user_id 為 2, 'userD' 的 user_id 為 3, 'userS' 的 user_id 為 4
-- 假定 'ROLE_ADMIN' 的 role_id 為 1, 'ROLE_USER_E' 的 role_id 為 2, 'ROLE_USER_D' 的 role_id 為 3, 'ROLE_USER_S' 的 role_id 為 4

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
                                                    (1, 1), -- admin 有 ROLE_ADMIN 角色
                                                    (2, 2), -- userE 有 ROLE_USER_E 角色
                                                    (3, 3), -- userD 有 ROLE_USER_D 角色
                                                    (4, 4), -- userS 有 ROLE_USER_S 角色
                                                    (1, 2), -- admin 有 ROLE_USER_E 角色
                                                    (1, 3), -- admin 有 ROLE_USER_E 角色
                                                    (1, 4); -- admin 有 ROLE_USER_S 角色

INSERT INTO `department`(`dept_name`, `loc`) VALUES
                                            ('財務部','臺灣台北'),
                                            ('研發部','臺灣新竹'),
                                            ('業務部','美國紐約'),
                                            ('生管部','中國上海'),
                                            ('人資部','臺灣台北');

INSERT INTO `employee`(`emp_name`, `emp_job`, `hire_date`, `sal`, `comm`, `dept_id` ) VALUES
                     ('MARTIN','SALESMAN' ,STR_TO_DATE('1993-04-01','%Y-%m-%d'),40000.0,8000.0,3),
                     ('JOICE','ACCOUNTANT' ,STR_TO_DATE('1996-05-16','%Y-%m-%d'),42000.0,2000.0,1),
                     ('MAX','MANAGER' ,STR_TO_DATE('1975-11-25','%Y-%m-%d'),80000.0,8000.0,2),
                     ('JOHN','SPECIALIST' ,STR_TO_DATE('1988-09-05','%Y-%m-%d'),38000.0,3000.0,4),
                     ('KELLY','HUMAN RESOURCES' ,STR_TO_DATE('1991-10-08','%Y-%m-%d'),48000.0,3000.0,3);