
import Location

public class Base{

    protected Location location;

    public Base() {
    }

    public Base(long id, Location location, int demand) {
        super(id);
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}