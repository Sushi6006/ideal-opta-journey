package windfarm.domain;

public class Vessel implements Standstill {

    protected long id;
    protected int capacity;
    protected Base base;

    protected Turbine nextTurbine;

    public Vessel() {
    }

    public Vessel(long id, int capacity, Base base) {
        this.id = id;
        this.capacity = capacity;
        this.base = base;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    @Override
    public Turbine getNextTurbine() {
        return nextTurbine;
    }

    @Override
    public void setNextTurbine(Turbine nextTurbine) {
        this.nextTurbine = nextTurbine;
    }

    public long getDistanceTo(Standstill standstill) {
        return base.getDistanceTo(standstill);
    }

    @Override
    public String toString() {
        Location location = getLocation();
        if (location.getName() == null) {
            return super.toString();
        }
        return location.getName() + "/" + super.toString();
    }
}