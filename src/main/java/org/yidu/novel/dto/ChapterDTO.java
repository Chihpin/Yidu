package org.yidu.novel.dto;

import org.yidu.novel.entity.TChapter;

public class ChapterDTO extends TChapter {

    private static final long serialVersionUID = -9171385880720383954L;
    private int nextChapterno;
    private int preChapterno;
    private String content;

    public int getNextChapterno() {
        return nextChapterno;
    }

    public void setNextChapterno(int nextChapterno) {
        this.nextChapterno = nextChapterno;
    }

    public int getPreChapterno() {
        return preChapterno;
    }

    public void setPreChapterno(int preChapterno) {
        this.preChapterno = preChapterno;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
