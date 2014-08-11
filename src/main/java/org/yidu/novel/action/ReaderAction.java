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
    private Integer articleno;
    /**
     * 章节号
     */
    private Integer chapterno;
    
    /**
     * 分段阅读 - 截止章节号
     */
    private Integer toChapterno;

    /**
     * 内容
     */
    private ChapterDTO chapter;
    
    /**
     * 全文阅读内容
     */
    private List<ChapterDTO> fullReadChapterList;
    
    public List<ChapterDTO> getFullReadChapterList() {
		return fullReadChapterList;
	}

	public void setFullReadChapterList(List<ChapterDTO> fullReadChapterList) {
		this.fullReadChapterList = fullReadChapterList;
	}

	public Integer getArticleno() {
        return articleno;
    }

    public void setArticleno(Integer articleno) {
        this.articleno = articleno;
    }

    public Integer getChapterno() {
        return chapterno;
    }

    public void setChapterno(Integer chapterno) {
        this.chapterno = chapterno;
    }

    public Integer getToChapterno() {
		return toChapterno;
	}

	public void setToChapterno(Integer toChapterno) {
		this.toChapterno = toChapterno;
	}

	public ChapterDTO getChapter() {
        return chapter;
    }

    public void setChapter(ChapterDTO chapter) {
        this.chapter = chapter;
    }

    public String getTempName() {
    	if(toChapterno != null && toChapterno > chapterno) {
    		return "reader2";
    	}
        return "reader";
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");
        if(toChapterno != null && toChapterno > chapterno) {
        	fullReadChapterList = new ArrayList<ChapterDTO>();
        	List<TChapter> segChapterList = chapterService.getChapterInSegement(articleno,chapterno, toChapterno);
        	for(TChapter tchapter : segChapterList) {
        		ChapterDTO chapterDto = new ChapterDTO();
                BeanUtils.copyProperties(tchapter, chapterDto);
                if (chapterDto != null && chapterDto.getChapterno() != 0) {
                	chapterDto.setContent(Utils.getContext(chapterDto, true, false));
                }
                // 更新统计信息
                if (articleno != 0) {
                    articleService.updateVisitStatistic(articleno);
                }
                fullReadChapterList.add(chapterDto);
        	}
        	chapter = new ChapterDTO();
        	BeanUtils.copyProperties(segChapterList.get(0), chapter);
        	chapter.setChaptername(chapter.getChaptername() + " - " + segChapterList.get(segChapterList.size()-1).getChaptername());
        } else {
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
                chapter.setContent(Utils.getContext(chapter, true, true));
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
        }

        logger.debug("loadData normally end.");
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_READER;
    }

}
