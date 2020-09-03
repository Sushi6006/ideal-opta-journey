package com.windfarmplanner;

import org.optaplanner.core.api.domain.lookup.PlanningId;

public abstract class AbstractPersistable {

    protected Long id;

    protected AbstractPersistable() {
    }

    protected AbstractPersistable(long id) {
        this.id = id;
    }

    @PlanningId
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getClass().getName().replaceAll(".*\\.", "") + "-" + this.id;
    }
}

