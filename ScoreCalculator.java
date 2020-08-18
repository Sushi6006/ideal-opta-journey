package windfarm.domain;

public class ScoreCalculator implements EasyScoreCalculator<Route> {

    @Override
    public HardSoftLongScore calculateScore(Route route) {
        
        // boolean timeWindowed = route instanceof TimeWindowedVehicleRoutingSolution;
        
        List<Turbine> turbineList = route.getTurbineList();
        List<Vessel> vesselList = route.getVesselList();
        
        long hardScore = 0L;
        long softScore = 0L;
        
        // calculation
        

        // Score constraint arrivalAfterDueTimeAtDepot is a built-in hard constraint in VehicleRoutingImporter
        return HardSoftLongScore.of(hardScore, softScore);
    }

}