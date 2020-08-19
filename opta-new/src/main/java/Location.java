package org.optaplanner.examples.windfarm;
/*from optaplanner example vehiclerouting Location class*/

public class Location {

    protected int id;
    protected double latitude;
    protected double longitude;

    public Location() {
    }

    public Location(int id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************

    /**
     * The distance's unit of measurement depends on the {@link VehicleRoutingSolution}'s {@link DistanceType}.
     * It can be in miles or km, but for most cases it's in the TSPLIB's unit of measurement.
     *
     * @param location never null
     * @return a positive number, the distance multiplied by 1000 to avoid floating point arithmetic rounding errors
     */
    public double getDistanceTo(Location location) {
        double latitudeDifference = location.latitude - latitude;
        double longitudeDifference = location.longitude - longitude;
        return Math.sqrt(
                (latitudeDifference * latitudeDifference) + (longitudeDifference * longitudeDifference));
    }

    /**
     * The angle relative to the direction EAST.
     *
     * @param location never null
     * @return in Cartesian coordinates
     */
    public double getAngle(Location location) {
        // Euclidean distance (Pythagorean theorem) - not correct when the surface is a sphere
        double latitudeDifference = location.latitude - latitude;
        double longitudeDifference = location.longitude - longitude;
        return Math.atan2(latitudeDifference, longitudeDifference);
    }

    //    @Override
    //    public String toString() {
    //        if (name == null) {
    //            return super.toString();
    //        }
    //        return name;
    //    }

}
