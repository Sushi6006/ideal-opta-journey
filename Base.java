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
}