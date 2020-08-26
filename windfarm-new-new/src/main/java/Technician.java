public class Technician{

    protected String id;
    protected String type;

    protected Vessel vessel;
    protected Base base;

    public Technician() {
    }

    public Technician(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id; }

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

    public Base getBase() { return base; }

    public void setBase(Base base) {this.base = base; }
}