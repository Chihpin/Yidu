CREATE EXTENSION pg_trgm;
create INDEX t_article_articlename_gist_index on t_article using gist (lower(articlename) gist_trgm_ops); 
create INDEX t_article_author_gist_index on t_article using gist (lower(author) gist_trgm_ops); 
CREATE INDEX t_article_articlename_index  ON t_article (articlename);
CREATE INDEX t_chapter_articleno_index  ON t_chapter (articleno);
CREATE INDEX t_chapter_chaptername_index  ON t_chapter (chaptername);