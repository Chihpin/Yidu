package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.dto.ChapterDTO;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.utils.CookieUtils;
import org.yidu.novel.utils.Utils;

/**
 * <p>
 * 章节阅读
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public class ReaderAction extends AbstractPublicBaseAction {

    private static final long serialVersionUID = 1L;
    /**
     * 小说号
     */
    private int articleno;
    /**
     * 章节号
     */
    private int chapterno;

    /**
     * 内容
     */
    private ChapterDTO chapter;

    public int getArticleno() {
        return articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public int getChapterno() {
        return chapterno;
    }

    public void setChapterno(int chapterno) {
        this.chapterno = chapterno;
    }

    public ChapterDTO getChapter() {
        return chapter;
    }

    public void setChapter(ChapterDTO chapter) {
        this.chapter = chapter;
    }

    public String getTempName() {
        return "reader";
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");
        ChapterDTO chapterDto = CacheManager.getObject(CACHE_KEY_CHAPTER_PREFIX + chapterno);
        if (chapterDto == null || chapterDto.getChapterno() == 0) {
            TChapter tchapter = chapterService.getByNo(chapterno);
            if (tchapter != null) {
                TChapter nextchapter = chapterService.getNextChapter(tchapter.getArticleno(), chapterno, true);
                TChapter prechapter = chapterService.getNextChapter(tchapter.getArticleno(), chapterno, false);
                chapterDto = new ChapterDTO();
                BeanUtils.copyProperties(tchapter, chapterDto);
                if (nextchapter != null) {
                    chapterDto.setNextChapterno(nextchapter.getChapterno());
                }
                if (prechapter != null) {
                    chapterDto.setPreChapterno(prechapter.getChapterno());
                }
                CacheManager.putObject(CACHE_KEY_CHAPTER_PREFIX + chapterno, chapterDto);
            } else {
                addActionError(getText("errors.not.exsits.chapter"));
            }
        }
        chapter = chapterDto;
        if (chapter != null && chapter.getChapterno() != 0) {
            chapter.setContent(Utils.getContext(chapter.getArticleno(), chapterno, true));
        }
        // 更新统计信息
        if (articleno != 0) {
            articleService.updateVisitStatistic(articleno);
        }

        // 更新阅读履历
        String articlenos = CookieUtils.getHistoryCookie(ServletActionContext.getRequest());
        if (StringUtils.isNotEmpty(articlenos)) {
            String[] chapters = StringUtils.split(articlenos, ",");
            List<String> chapterList = Arrays.asList(chapters);
            boolean existflag = false;
            int index = 0;
            for (int i = 0; i < chapterList.size(); i++) {
                if (StringUtils.startsWith(chapterList.get(i), articleno + "|")) {
                    existflag = true;
                    index = i;
                    break;
                }
            }
            List<String> tempList = new ArrayList<String>();
            tempList.add(articleno + "|" + chapterno);
            if (existflag) {
                for (int i = 0; i < chapterList.size(); i++) {
                    if (i == index) {
                        continue;
                    }
                    tempList.add(chapterList.get(i));
                }
            } else {
                tempList.addAll(chapterList);
            }

            if (tempList.size() > 10) {
                tempList = tempList.subList(0, 9);
            }
            articlenos = StringUtils.join(tempList, ",");
        } else {
            articlenos = articleno + "|" + chapterno;
        }
        // 添加到cookie里
        Cookie cookie = CookieUtils.addHistoryCookie(articlenos);
        // 添加cookie到response中
        ServletActionContext.getResponse().addCookie(cookie);

        logger.debug("loadData normally end.");
    }

    @Override
    public int getPageType() {
        // TODO Auto-generated method stub
        return YiDuConstants.Pagetype.PAGE_READER;
    }

}
