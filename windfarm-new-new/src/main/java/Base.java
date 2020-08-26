public class Base {

    private String id;
    protected Location location;

    public Base() {
    }

    public Base(String id, Location location) {
        this.id = id;
        this.location = location;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getDistanceTo(Standstill standstill) {
        return location.getDistanceTo(standstill.getLocation());
    }

    @Override
    public String toString() {
        if (location.getId() == null) {
            return super.toString();
        }
        return location.getId();
    }
}