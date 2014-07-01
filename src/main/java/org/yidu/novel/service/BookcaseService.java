package org.yidu.novel.service;

import java.util.List;

import org.yidu.novel.bean.BookcaseSearchBean;
import org.yidu.novel.dto.BookcaseDTO;
import org.yidu.novel.entity.TBookcase;

public interface BookcaseService {

    public List<TBookcase> find(final BookcaseSearchBean searchBean);

    public List<BookcaseDTO> findWithArticleInfo(final BookcaseSearchBean searchBean);

    public Integer getCount(final BookcaseSearchBean searchBean);

    public TBookcase getByNo(final int bookcaseno);

    public void delteByNo(final int bookcaseno);

    public void batchdeleteByNo(final String bookcasenos, final int userno);

    public void save(final TBookcase bookcase);

    public TBookcase getByArticleno(final int userno, final int articleno);

    public void deleteByArticleno(int articleno, int userno);

}
