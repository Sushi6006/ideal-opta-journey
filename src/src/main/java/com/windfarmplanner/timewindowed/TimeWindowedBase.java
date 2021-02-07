package com.windfarmplanner.timewindowed;

import com.windfarmplanner.Base;
import com.windfarmplanner.location.Location;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("WfTimeWindowedBase")
public class TimeWindowedBase extends Base {

    private long readyTime;
    private long dueTime;

    public TimeWindowedBase() { }

    public TimeWindowedBase(long id, Location location, long readyTime, long dueTime) {
        super(id, location);
        this.readyTime = readyTime;
        this.dueTime = dueTime;
    }

    public long getReadyTime() { return readyTime; }

    public void setReadyTime(long readyTime) { this.readyTime = readyTime; }

    public long getDueTime() { return dueTime; }

    public void setDueTime(long dueTime) { this.dueTime = dueTime; }

}
