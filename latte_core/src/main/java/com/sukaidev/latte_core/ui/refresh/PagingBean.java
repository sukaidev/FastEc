package com.sukaidev.latte_core.ui.refresh;

/**
 * Created by sukaidev on 2019/05/03.
 */
public class PagingBean {
    // 当前是第几页
    private int pageIndex = 0;
    // 总数据条数
    private int total = 0;
    // 一页显示几条数据
    private int pageSize = 0;
    // 当前已经显示了几条数据
    private int currentCount = 0;
    // 加载延迟
    private int delayed = 0;

    public int getPageIndex() {
        return pageIndex;
    }

    public PagingBean setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public PagingBean setTotal(int total) {
        this.total = total;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PagingBean setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public PagingBean setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
        return this;
    }

    public int getDelayed() {
        return delayed;
    }

    public PagingBean setDelayed(int delayed) {
        this.delayed = delayed;
        return this;
    }

    PagingBean addIndex() {
        pageIndex++;
        return this;
    }
}
