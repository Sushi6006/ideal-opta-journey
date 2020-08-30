package com.windfarmplanner;

public class Part extends AbstractPersistable {
    protected String name;
    protected double weight;

    public Part() {
    }

    public Part(Long id, String name, double weight) {
        super(id);
        this.name = name;
        this.weight = weight;
    }
}


