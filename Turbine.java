
import Location

@PlanningEntity
public class Turbine{
    protected Location location;
    protected int demand;


    // Shadow variables
    protected Customer nextCustomer;
    protected Vehicle vehicle;

    public Turbine() {
    }

    public Turbine(long id, Location location, int demand) {
        super(id);
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
