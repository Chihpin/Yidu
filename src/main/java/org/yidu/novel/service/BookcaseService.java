package org.yidu.novel.service;

import java.util.List;

import org.yidu.novel.bean.BookcaseSearchBean;
import org.yidu.novel.dto.BookcaseDTO;
import org.yidu.novel.entity.TBookcase;

public interface BookcaseService {

    public List<TBookcase> find(final BookcaseSearchBean searchBean);

    public List<BookcaseDTO> findBySql(final BookcaseSearchBean searchBean);

    public Integer getCount(final BookcaseSearchBean searchBean);

    public TBookcase getByNo(final int bookcaseno);

    public void delteByNo(final int bookcaseno);

    public void save(final TBookcase bookcase);

    public TBookcase getByArticleno(final int userno, final int articleno);

}
