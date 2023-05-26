create table tb_chat
(
    `id`          int,
    `name`        varchar(32),
    `path`        varchar(255),
    `icon`        varchar(255),
    `description` varchar(900),
    `pid`         int,
    `page_path`   varchar(900),
    `sort_num`    int
);

INSERT INTO `tb_chat`(`id`, `name`, `path`, `icon`, `description`, `pid`, `page_path`, `sort_num`)
VALUES (11, '聊天室', '/im', 'el-icon-chat-dot-square', NULL, NULL, 'Im', 999);

