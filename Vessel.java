

public class Vessel{
    protected int capacity;


    public Vessel() {
    }

    public Vessel(long id, int capacity) {
        super(id);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}