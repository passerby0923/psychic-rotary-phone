-- 员工表：用于管理街道办员工信息
CREATE TABLE employee (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,  -- 员工ID，主键
    name VARCHAR(50) NOT NULL,                   -- 员工姓名
    age INT NOT NULL,                            -- 员工年龄
    gender CHAR(1),                              -- 性别 ('M' 男, 'F' 女)
    department VARCHAR(100),                     -- 部门
    position VARCHAR(100),                       -- 职位
    hire_date DATE,                              -- 入职日期
    salary DECIMAL(10, 2),                       -- 工资
    phone VARCHAR(20),                           -- 联系电话
    email VARCHAR(100)                           -- 电子邮件
);

-- 居民表：用于管理街道办居民信息
CREATE TABLE resident (
    resident_id INT PRIMARY KEY AUTO_INCREMENT,  -- 居民ID，主键
    name VARCHAR(50) NOT NULL,                   -- 居民姓名
    id_card VARCHAR(18) NOT NULL UNIQUE,         -- 身份证号
    gender CHAR(1),                              -- 性别 ('M' 男, 'F' 女)
    age INT,                                     -- 年龄
    phone VARCHAR(20),                           -- 联系电话
    address VARCHAR(200)                         -- 住址
);

-- 房屋表：用于存储街道办管理的房屋信息
CREATE TABLE house (
    house_id INT PRIMARY KEY AUTO_INCREMENT,     -- 房屋ID，主键
    address VARCHAR(200) NOT NULL,               -- 房屋地址
    owner_name VARCHAR(100),                     -- 业主名
    house_type VARCHAR(50),                      -- 房屋类型（如：住宅、商业）
    area DECIMAL(10, 2),                         -- 房屋面积
    build_year INT                               -- 建造年份
);

-- 居民福利申请表：用于管理居民的福利申请
CREATE TABLE welfare_application (
    application_id INT PRIMARY KEY AUTO_INCREMENT,  -- 申请ID，主键
    applicant_name VARCHAR(100),                    -- 申请人姓名
    welfare_type VARCHAR(100),                      -- 福利类型
    apply_date DATE NOT NULL,                       -- 申请日期
    status VARCHAR(50) DEFAULT '申请中',             -- 申请状态（如：申请中，已批准，已拒绝）
    comments TEXT,                                  -- 备注
    file_path VARCHAR(255)                          -- 文件存储路径
);


-- 公告表：用于管理街道办的公告信息
CREATE TABLE announcement (
    announcement_id INT PRIMARY KEY AUTO_INCREMENT,  -- 公告ID，主键
    title VARCHAR(200) NOT NULL,                     -- 公告标题
    content TEXT NOT NULL,                           -- 公告内容
    publish_date DATE NOT NULL                       -- 发布日期
);

-- 留言表：居民可在线留言，管理员查看并回复
CREATE TABLE message (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,   -- 留言ID，主键
    nick_name VARCHAR(255) NULL,            -- 昵称
    content TEXT NULL,                      -- 留言内容
    create_time DATETIME NULL,              -- 留言时间
    reply_content TEXT NULL,                -- 回复内容
    reply_time DATETIME NULL                -- 回复时间
);




-- 用户表：用于管理系统登录用户，区分管理员和普通居民角色
    CREATE TABLE user (
        user_id INT PRIMARY KEY AUTO_INCREMENT,           -- 用户ID，主键
        username VARCHAR(50) NOT NULL UNIQUE,             -- 用户名，唯一
        password VARCHAR(100) NOT NULL,                   -- 密码
        role VARCHAR(20) NOT NULL CHECK (role IN ('admin', 'resident', 'super_admin')),  -- 角色：'admin' 表示管理员, 'resident' 表示居民用户, 'super_admin' 表示超级管理员
        resident_id INT DEFAULT NULL,                     -- 对应的居民ID（如果角色是居民），允许为空
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,   -- 创建时间
        FOREIGN KEY (resident_id) REFERENCES resident(resident_id)  -- 关联居民表
    );

-- 插入管理员用户
INSERT INTO user (username, password, role)
VALUES ('admin_user', 'password123', 'admin');

-- 插入超级管理员用户
INSERT INTO user (username, password, role)
VALUES ('super_admin_user', 'password123', 'super_admin');

-- 插入普通用户1的信息到居民表
INSERT INTO resident (name, id_card, gender, age, phone, address)
VALUES ('张三', '110105199001011234', 'M', 30, '13800138000', '北京市朝阳区');

-- 插入普通用户2的信息到居民表
INSERT INTO resident (name, id_card, gender, age, phone, address)
VALUES ('李四', '110105199002021422', 'F', 28, '13800138001', '北京市海淀区');

-- 现在resident表中有了普通用户1和普通用户2的记录
-- 接下来插入用户信息到用户表
-- 插入普通用户1
INSERT INTO user (username, password, role, resident_id)
VALUES ('resident_user1', 'password123', 'resident', (SELECT resident_id FROM resident WHERE name = '张三'));

-- 插入普通用户2
INSERT INTO user (username, password, role, resident_id)
VALUES ('resident_user2', 'password123', 'resident', (SELECT resident_id FROM resident WHERE name = '李四'));

