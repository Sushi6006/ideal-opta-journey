package com.windfarmplanner;

public class Technician{

    protected int id;
    protected String type;

    protected Vessel vessel;

    public Technician() {
    }

    public Technician(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getType() {
        return type;
    }

    public void setType(String type) { this.type = type; }

    public Vessel getVessel() {
        return vessel;
    }

    public void setVessel(Vessel vessel) {
        this.vessel = vessel;
    }
}