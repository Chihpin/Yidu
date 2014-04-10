/* Drop Tables */

DROP TABLE IF EXISTS t_system_block;
DROP TABLE IF EXISTS t_bookcase;
DROP TABLE IF EXISTS t_chapter;
DROP TABLE IF EXISTS t_message;
DROP TABLE IF EXISTS t_review;
DROP TABLE IF EXISTS t_credit_history;
DROP TABLE IF EXISTS t_user;
DROP TABLE IF EXISTS t_article;


/* Create Tables */

CREATE TABLE t_system_block
(
    blockno serial NOT NULL,
    blockid varchar(32),
    blockname varchar(32),
    type smallint,
    category int,
    sortcol varchar(32),
    isasc boolean,
    limitnum int,
    content text,
    target smallint,
    deleteflag boolean DEFAULT 'false',
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
    chapterid int,
    chaptername varchar(100),
    lastvisit timestamp,
    createtime timestamp,
    deleteflag boolean DEFAULT 'false',
    PRIMARY KEY (bookcaseno)
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
    deleteflag boolean DEFAULT 'false',
    PRIMARY KEY (messageno)
) WITHOUT OIDS;


CREATE TABLE t_review
(
    reviweno serial NOT NULL,
    userno int,
    username varchar(50),
    articleno int,
    articlename varchar(100),
    title varchar(30),
    review varchar(500),
    email varchar(60),
    postdate timestamp,
    deleteflag boolean DEFAULT 'false',
    PRIMARY KEY (reviweno)
) WITHOUT OIDS;


CREATE TABLE t_credit_history
(
    credithistoryno serial NOT NULL,
    userno int,
    loginid varchar(32),
    articleno int,
    articlename varchar(100),
    chapterno int,
    chaptername varchar(100),
    timestamp timestamp,
    creditpoint int,
    deleteflag boolean DEFAULT 'false',
    PRIMARY KEY (credithistoryno)
) WITHOUT OIDS;


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
    type smallint,
    votecount int,
    realname varchar(10),
    id varchar(18),
    mobileno varchar(11),
    branch varchar(50),
    bankno varchar(20),
    alipayacount varchar(50),
    category int,
    subcategory int,
    deleteflag boolean DEFAULT 'false',
    PRIMARY KEY (userno)
) WITHOUT OIDS;


CREATE TABLE t_chapter
(
    chapterno serial NOT NULL,
    articleno int,
    articlename varchar(100),
    chaptertype smallint,
    chaptername varchar(100),
    size int DEFAULT 0,
    isvip boolean,
    postdate timestamp,
    publishtime timestamp,
    ispublish boolean DEFAULT 'false',
    lastchecktime timestamp,
    deleteflag boolean DEFAULT 'false',
    PRIMARY KEY (chapterno)
) WITHOUT OIDS;


CREATE TABLE t_article
(
    articleno serial NOT NULL,
    articlename varchar(100),
    initial char(1),
    keywords varchar(500),
    authorid int,
    author varchar(50),
    category int DEFAULT 0,
    subcategory int,
    intro text,
    lastchapterno int,
    lastchapter varchar(50),
    chapters int,
    size int DEFAULT 0,
    fullflag boolean,
    imgflag smallint,
    firstflag boolean DEFAULT 'false',
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
    monthvote int DEFAULT 0,
    allvote int DEFAULT 0,
    deleteflag boolean DEFAULT 'false',
    publicflag int,
    PRIMARY KEY (articleno)
) WITHOUT OIDS;
