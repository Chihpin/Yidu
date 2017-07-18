1. 不带参数启动， 默认为采集规则中指定的所有小说， 即同-ca参数
2. 正常采集规则中指定的所有小说使用参数 -ca启动
3. 如果只采集部分小说， 可使用-c 111,222,333 或者-c 111-222启动采集器， 前者意思为采集目标站章节号为111,222,333的小说， 后者意思为采集目标站章节号从111到222之间的所有小说
4. 如果采集中出现了一些空章节， 或者小说封面、简介、进度、分类错误， 可以使用修复采集 -ra启动， 可指定修复内容
5. 如果只修复部分小说， 可使用-r 111,222,333 或者-c 111-222启动采集器， 参数含义类似-c， 只是-c为采集指定小说， -r为修复指定小说
6. 使用-r或-ra进行修复采集时可同时制定需要修复的选项， -cover,-intro,-top,-sub,-keywords,-degree,-etxt,-txt，以上参数分别对应封面图片， 小说简介， 小说大类，小说细类， 关键词， 写作进度， 空章节(只修复空章节)， txt文本(无论本地是否存在对应的章节内容， 只要指定txt则重新采集)
7. 如果启动需要单独指定采集时使用的规则， 可使用-rule xxxx.xml参数(必须同时制定-ca -c -ra -r四个命令之一)， 其中xxxx.xml为rules文件夹下的规则名， 注意这里的参数需要使用文件全名， 即带了后缀的， 如果不知道怎么看文件后缀， 请使用谷歌或者度娘


配置易读：
1. 配置jdbc.properties，将#for postgresql下的四行开始的#去掉，同时将#for mysql下的四行行首加上#
2. 将site.ini中的local_program值修改为yidu
3. 将collect.ini中的create_html设置为false

配置杰奇：
1. 配置jdbc.properties，将#for postgresql下的四行行首加上#，同时将#for mysql下的四行行首的#去掉
2. 将site.ini中的local_program值修改为jieqi
3. 将collect.ini中的create_html设置为true

配置site.ini:
主要配置项包括： local_program、txt_file、cover_dir
其中local_program参考[配置易读]、[配置杰奇]
txt_file为你的txt文件路径路径路径路径， 配置的时候需要注意不要把占位符删掉
cover_dir为你的封面图片所在目录目录目录目录，  配置的时候注意占位符


采集器默认为单线程执行， 如果有多线程需求， 请按照以下配置：
1. 在数据库中增加唯一索引
create unique index unique_index_articleno_chaptername ON t_chapter(articleno,chaptername);
create unique index unique_index_articlename_author ON t_article(articlename,author);
2. 将collect.ini中的concurrent_novel_task设置为大于1的数字

多线程采集优势：
理论上采集速度可以无限提升

多线程采集风险：
1. 会消耗更多的系统资源
2. 多线程采集速度过快， 由于网络延时性， 可能会采到很多空章节

解决方案：
同时开启修复采集