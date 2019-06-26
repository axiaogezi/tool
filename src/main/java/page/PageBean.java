package page;

import java.io.Serializable;

public class PageBean implements Serializable {

    private static final long serialVersionUID = -6106070173032145583L;

    private int pageNum = 1;
    private int pageSize = 20;

    public PageBean(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageBean() {
    }

    public int getPageNum() {
        return pageNum;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
