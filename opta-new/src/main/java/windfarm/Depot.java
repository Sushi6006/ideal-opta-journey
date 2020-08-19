package windfarm;

//import org.optaplanner.examples.common.domain.AbstractPersistable;

//public class Depot extends AbstractPersistable {
public class Depot {

    private long id;
    protected Location location;

    public Depot() {
    }

    public Depot(long id, Location location) {
        this.id = id;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************

    /**
     * @param standstill never null
     * @return a positive number, the distance multiplied by 1000 to avoid floating point arithmetic rounding errors
     */
    public double getDistanceTo(Standstill standstill) {
        return location.getDistanceTo(standstill.getLocation());
    }

    //    @Override
    //    public String toString() {
    //        if (location.getName() == null) {
    //            return super.toString();
    //        }
    //        return location.getName();
    //    }
}
