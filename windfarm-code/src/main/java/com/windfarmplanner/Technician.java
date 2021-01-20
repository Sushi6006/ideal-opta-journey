package com.windfarmplanner;

public class Technician extends AbstractPersistable {

    protected String type;

    protected Vehicle vehicle;
    protected Base base;

    public Technician() {
    }

    public Technician(Long id, String type) {
        super(id);
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public String getType() {
        return type;
    }

    public void setType(String type) { this.type = type; }

    public Vehicle getVessel() {
        return vehicle;
    }

    public void setVessel(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Base getBase() { return base; }

    public void setBase(Base base) {this.base = base; }
}