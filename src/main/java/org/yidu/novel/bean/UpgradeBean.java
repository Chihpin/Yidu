package org.yidu.novel.bean;

public class UpgradeBean {

    public static final class FileType {
        public static final int STATIC = 1;
        public static final int CLASS = 2;
        public static final int PROPERTIES = 3;
        public static final int XML = 4;
        public static final int JSP = 5;
        public static final int FTL = 6;
        public static final int SQL = 7;
    }

    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件类型
     */
    private int type;
    /**
     * 文件路径
     */
    private String path;
    /**
     * propteries文件的key
     */
    private String key;
    /**
     * propteries文件的内容
     */
    private String content;
    /**
     * sql
     */
    private String sql;

    // TODO XML文件的更新

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

}
