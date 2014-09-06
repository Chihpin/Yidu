package org.yidu.novel.service;

import java.util.List;

import org.yidu.novel.bean.ReviewSearchBean;
import org.yidu.novel.entity.TReview;

public interface ReviewService {

    public TReview getByNo(final int reviewno);

    public void delteByNo(final int reviewno);

    public void save(final TReview review);

    public List<TReview> find(final ReviewSearchBean searchBean);

    public int getCount(final ReviewSearchBean searchBean);

}
