DROP TABLE IF EXISTS t_user;
DROP TABLE IF EXISTS t_system_config;
DROP TABLE IF EXISTS t_review;
DROP TABLE IF EXISTS t_chapter;
DROP TABLE IF EXISTS t_system_block;
DROP TABLE IF EXISTS t_bookcase;
DROP TABLE IF EXISTS t_article;
DROP TABLE IF EXISTS t_message;


CREATE TABLE t_user
(
    userno serial NOT NULL,
    loginid varchar(32) NOT NULL,
    password varchar(32),
    username varchar(50),
    email varchar(60),
    regdate timestamp,
    sex smallint,
    qq varchar(15),
    lastlogin timestamp,
    lineno varchar(32),
    -- 10:normal user
    -- 20:auther
    -- 30:system administrator
    type smallint,
    votecount int,
    realname varchar(10),
    id varchar(18),
    mobileno varchar(11),
    branch varchar(50),
    bankno varchar(20),
    alipayacount varchar(50),
    deleteflag boolean,
    PRIMARY KEY (userno)
) WITHOUT OIDS;


CREATE TABLE t_system_config
(
    configno serial NOT NULL,
    name varchar(32),
    title varchar(32),
    description text,
    type smallint,
    options varchar(64),
    -- 显示控制
    -- 积分设置
    -- 邮件设置
    -- 内容检查设置
    -- 网站基本信息
    -- 数据库设置
    -- 显示控制
    -- 文件参数
    -- 积分设置
    catname varchar(32),
    PRIMARY KEY (configno)
) WITHOUT OIDS;


CREATE TABLE t_review
(
    reviweno serial NOT NULL,
    articleno int,
    review varchar(500),
    username varchar(50),
    email varchar(60),
    createtime timestamp,
    PRIMARY KEY (reviweno)
) WITHOUT OIDS;


CREATE TABLE t_chapter
(
    chapterno serial NOT NULL,
    articleno int,
    articlename varchar(100),
    chaptertype smallint,
    chaptername varchar(100),
    size int,
    isvip boolean,
    postdate timestamp,
    PRIMARY KEY (chapterno)
) WITHOUT OIDS;


CREATE TABLE t_system_block
(
    blockno serial NOT NULL,
    blockid varchar(32),
    blockname varchar(32),
    -- 10:aritcleList
    -- 20:custerartileList
    -- 20:html
    type smallint,
    category int,
    sortcol varchar(32),
    isasc boolean,
    limitnum int,
    content text,
    -- 1:articleList
    -- 2:info
    -- 3:contentList
    -- 4:reader
    -- 5:user page
    -- 6:index page
    -- 7:all page
    target smallint,
    PRIMARY KEY (blockno)
) WITHOUT OIDS;


CREATE TABLE t_bookcase
(
    bookcaseno serial NOT NULL,
    articleno int,
    articlename varchar(100),
    category int,
    userno int,
    username varchar(50),
    chapterno int,
    chaptername varchar(100),
    lastvisit timestamp,
    createtime timestamp,
    PRIMARY KEY (bookcaseno)
) WITHOUT OIDS;


CREATE TABLE t_article
(
    articleno serial NOT NULL,
    articlename varchar(100),
    initial char(1),
    keywords varchar(500),
    authorid int,
    author varchar(50),
    category int,
    subcategory int,
    intro text,
    lastchapterno int,
    lastchapter varchar(50),
    chapters int,
    size int,
    fullflag boolean,
    -- 0：不存在
    -- 1：JPG
    -- 2：GIF
    -- 3：PNG
    -- 10：other（l.jpg）
    imgflag smallint,
    agent varchar(32),
    firstflag boolean DEFAULT 'false',
    -- 1:专属作品
    -- 2:驻站作品
    -- 3:授权作品
    -- 4:暂未授权
    permission int,
    authorflag boolean DEFAULT 'false',
    postdate timestamp,
    lastupdate timestamp,
    dayvisit int DEFAULT 0,
    weekvisit int DEFAULT 0,
    monthvisit int DEFAULT 0,
    allvisit int DEFAULT 0,
    dayvote int DEFAULT 0,
    weekvote int DEFAULT 0,
    monthvote int,
    allvote int,
    PRIMARY KEY (articleno)
) WITHOUT OIDS;


CREATE TABLE t_message
(
    messageno serial NOT NULL,
    userno int,
    loginid varchar(32),
    touserno int,
    tologinid varchar(32),
    title varchar(32),
    content varchar(255),
    category smallint,
    isread boolean,
    postdate timestamp with time zone,
    PRIMARY KEY (messageno)
) WITHOUT OIDS;