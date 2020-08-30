package com.windfarmplanner;

import java.io.Serializable;
import org.optaplanner.core.api.domain.lookup.PlanningId;

public abstract class AbstractPersistable implements Serializable {

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

    public String toString() {
        return this.getClass().getName().replaceAll(".*\\.", "") + "-" + this.id;
    }
}
