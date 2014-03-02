package org.yidu.novel.utils;

import java.util.ArrayList;
import java.util.List;

public class Pagination {

    private int pageSize = 0;
    private int pageNumber = 0;
    private String sortColumn = null;
    private String sortOrder = "ASC";
    private int totalRecords = 0;
    private int pageRecords = 0;
    private int start = 0;
    private int end = 0;
    private int totalPages = 0;
    private String sortClass = null;

    private List<Integer> pageNumberList = new ArrayList<Integer>();

    public boolean getPreviousFlag() {
        if (pageNumber > 1) {
            return true;
        }
        return false;
    }

    public boolean getNextFlag() {
        if (totalPages == pageNumber) {
            return false;
        }
        return true;
    }

    public void setPreperties(int count) {
        totalRecords = count;
        totalPages = (int) Math.ceil(((double) count / (double) pageSize));
        start = ((pageSize * pageNumber) - pageSize);
        end = pageSize;
        if (pageSize == 0) {
            start = 0;
            end = count;
            totalPages = 1;
        }
        if (sortOrder.equals("ASC")) {
            sortClass = "order1";

        } else {
            sortClass = "order2";
        }
    }

    public Pagination(int pageSize, int pageNumber) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getPageRecords() {
        return pageRecords;
    }

    public void setPageRecords(int pageRecords) {
        this.pageRecords = pageRecords;
    }

    public int getStart() {
        if (start != 0) {
            return start;
        } else {
            return (pageNumber - 1) * pageSize;
        }
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        if (end != 0) {
            return end;
        } else {
            return pageNumber * pageSize;
        }
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getSortOrder() {
        return (sortOrder == null) ? "" : sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSortColumn() {
        return (sortColumn == null) ? "" : sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getSortClass() {
        return sortClass;
    }

    public void setSortClass(String sortClass) {
        this.sortClass = sortClass;
    }

    /**
     * @param query
     * @return
     */
    public String getSortInfo() {
        String pageinfo = "";
        if (sortColumn != null && sortColumn.length() > 0) {
            pageinfo += " ORDER BY " + sortColumn + " " + sortOrder;
        }
        return pageinfo;
    }

    public List<Integer> getPageNumberList() {

        if (totalPages <= 10) {
            // 10页之内
            for (int i = 1; i <= totalPages; i++) {
                pageNumberList.add(i);
            }
        } else {
            if (pageNumber < 6) {
                for (int i = 1; i <= 10; i++) {
                    pageNumberList.add(i);
                }
            } else if (totalPages - pageNumber > 5) {
                for (int i = pageNumber - 5; i < pageNumber + 5; i++) {
                    pageNumberList.add(i);
                }
            } else {
                for (int i = totalPages - 9; i <= totalPages; i++) {
                    pageNumberList.add(i);
                }
            }
        }
        return pageNumberList;
    }

    @Override
    public String toString() {
        return "Pagination [pageSize=" + pageSize + ", pageNumber=" + pageNumber + ", sortColumn=" + sortColumn
                + ", sortOrder=" + sortOrder + ", totalRecords=" + totalRecords + ", pageRecords=" + pageRecords
                + ", start=" + start + ", end=" + end + ", totalPages=" + totalPages + ", sortClass=" + sortClass + "]";
    }

}
