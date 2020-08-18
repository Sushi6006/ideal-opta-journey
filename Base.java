package windfarm.domain;
import Location;

public class Base {

    private long id;
    protected Location location;

    public Base() {
    }

    public Base(long id, Location location) {
        this.id = id;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public long getDistanceTo(Standstill standstill) {
        return location.getDistanceTo(standstill.getLocation());
    }

    @Override
    public String toString() {
        if (location.getName() == null) {
            return super.toString();
        }
        return location.getName();
    }
}