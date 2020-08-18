package windfarm.domain;
import Location;

@PlanningEntity
public class Turbine implements Standstill {

    protected long id;
    protected Location location;
    protected int demand;

    // Shadow variables
    protected Turbine nextTurbine;
    protected Vessel vessel;

    public Turbine() {

    }

    public Turbine(long id, Location location, int demand) {
        this.id = id;
        this.location = location;
        this.demand = demand;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }
}
