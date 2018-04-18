package com.boce.flcp.domain.model;

/**
 * @author xutang
 * @version V1.0
 * @Package com.boce.flcp.domain.model
 * @Description: TODO(工作统计待处理)
 * @date 2017/12/14 14:04
 */
public class PendingWork {
    private int count;
    private String start_time;
    private String end_time;
    private String interval_time;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getInterval_time() {
        return interval_time;
    }

    public void setInterval_time(String interval_time) {
        this.interval_time = interval_time;
    }
}
