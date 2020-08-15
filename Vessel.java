

public class Vessel {

    protected long id;
    protected int capacity;

    public Vessel() {
    }

    public Vessel(long id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}