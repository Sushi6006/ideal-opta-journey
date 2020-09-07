package com.windfarmplanner;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.lang.Integer;

import com.windfarmplanner.location.Distance;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import com.windfarmplanner.location.Location;
import com.windfarmplanner.location.HubSegmentLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App {

    private final Route solution = new Route();

    private int turbineListSize;
    private int vesselListSize;
    private int hubListSize;
    private int capacity;
    private int baseListSize;
    // private int technicianListSize;
    private Map<Long, Location> locationMap;
    private Map<Long, Base> baseMap;
    // private Map<Long, Technician> technicianMap;

    private List<Base> baseList;
    protected List<HubSegmentLocation> hubLocationList = null;
    protected List<Vessel> vesselList;
    protected List<Turbine> turbineList;
    protected List<HubSegmentLocation> locationList;
//    protected List<Technician> technicianList;

    protected final transient Logger logger = LoggerFactory.getLogger(getClass());


    public static void main(String[] args) {

        // import data
        App app = new App();
        app.read_data();

        app.solve();
    }


    public void read_data() {
        String row;
        BufferedReader csvReader = null;

        logger.info("loading data");
        try {
            //size for variables
            csvReader = new BufferedReader(new FileReader("src/main/java/com/windfarmplanner/data/sizes.csv"));
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if (data[0]=="turbine") {
                    turbineListSize = Integer.parseInt(data[1]);
                }
                else if (data[0]=="vessel") {
                    vesselListSize = Integer.parseInt(data[1]);
                }
                else if (data[0]=="location"){
                    hubListSize = Integer.parseInt(data[1]);
                }
                else if (data[0]=="base") {
                    baseListSize = Integer.parseInt(data[1]);
                }
                // else if (data[0]=="technician") {
                //     technicianListSize = Integer.parseInt(data[1]);
                // }
            }
            csvReader.close();
            // BigInteger a = factorial(turbineListSize + vesselListSize - 1);
            // BigInteger b = factorial(vesselListSize - 1);
            // BigInteger possibleSolutionSize = (a == null || b == null) ? null : a.divide(b);
            // logger.info("VehicleRoutingSolution {} has {} depots, {} vehicles and {} customers with a search space of {}.",
            //         solution.getBaseList().size(),
            //         solution.getVehicleList().size(),
            //         solution.getCustomerList().size(),
            //         getFlooredPossibleSolutionSize(possibleSolutionSize));
            // return solution;
            // }

            baseMap = new LinkedHashMap<>(baseListSize);
            locationMap = new LinkedHashMap<>(hubListSize);
            hubLocationList = new ArrayList<>(hubListSize);
            // technicianMap = new LinkedHashMap<>(technicianListSize);

            baseList = new ArrayList<>(baseListSize);
            vesselList = new ArrayList<>(vesselListSize);
            turbineList = new ArrayList<>(turbineListSize);
            // technicianList = new ArrayList<>(technicianListSize);

            // location
            csvReader = new BufferedReader(new FileReader("src/main/java/com/windfarmplanner/data/location.csv"));
            while ((row = csvReader.readLine()) != null) {
                HubSegmentLocation location = new HubSegmentLocation();
                String[] data = row.split(",");
                location.setId(Long.parseLong(data[0]));
                location.setLatitude(Double.parseDouble(data[1]));
                location.setLongitude(Double.parseDouble(data[2]));
                if (data[0].charAt(0) == '1'){
                    location.setName("Turbine" + data[1]);
                }
                else if (data[0].charAt(0) == '3'){
                    location.setName("Base" + data[1]);
                }
                hubLocationList.add(location);
                locationMap.put(location.getId(), location);
            }
            csvReader.close();
            locationList = new ArrayList<>(hubLocationList.size());
            locationList.addAll(hubLocationList);
            solution.setLocationList(locationList);


            // base
            csvReader = new BufferedReader(new FileReader("src/main/java/com/windfarmplanner/data/base.csv"));

            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                Base base = new Base();
                long id = Long.parseLong(data[0]);
                base.setId(id);
                Location location = locationMap.get(id);
                // System.out.println("location:" + location);
                base.setLocation(location);
                baseList.add(base);
                baseMap.put(base.getId(),base);
            }
            csvReader.close();
            solution.setBaseList(baseList);


            // vessels
            csvReader = new BufferedReader(new FileReader("src/main/java/com/windfarmplanner/data/vessel.csv"));

            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");

                // Vessel vessel = new Vessel();
                Long id = Long.parseLong(data[0]);
                // vessel.setId(id);
                // vessel.setCapacity(Integer.parseInt(data[1]));
                Base base = baseMap.get(id);
                // if (base != null) {
                //     vessel.setBase(base);
                // }
                Vessel vessel = new Vessel(id, Integer.parseInt(data[1]), base);

                this.vesselList.add(vessel);
            }
            csvReader.close();
            solution.setVesselList(this.vesselList);


            // turbines
            csvReader = new BufferedReader(new FileReader("src/main/java/com/windfarmplanner/data/turbine.csv"));
            List<Location> turbineLocationList = new ArrayList<>(turbineListSize);
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");

                Turbine turbine = new Turbine();
                Long id = Long.parseLong(data[0]);
                turbine.setId(id);
                Location location = locationMap.get(id);
                turbine.setLocation(location);
                turbineLocationList.add(location);
                turbine.setDemand(Integer.parseInt(data[1]));
                // turbine.setTechnicianList(data[3]);
                turbineList.add(turbine);
            }
            csvReader.close();
            solution.setTurbineList(this.turbineList);


            csvReader = new BufferedReader(new FileReader("src/main/java/com/windfarmplanner/data/distancemap.csv"));

            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                for (int i = 0; i < turbineListSize; i++) {
                    Distance roadLocation = (Distance) turbineLocationList.get(i);
                    Map<Distance, Double> travelDistanceMap = new LinkedHashMap<>(turbineListSize);
                    for (int j = 0; j < turbineListSize; j++) {
                        double travelDistance = Double.parseDouble(data[j]);
                        if (i == j) {
                            if (travelDistance != 0.0) {
                                throw new IllegalStateException("The travelDistance (" + travelDistance + ") should be zero.");
                            }
                        } else {
                            Distance otherLocation = (Distance) turbineLocationList.get(j);
                            travelDistanceMap.put(otherLocation, travelDistance);
                        }
                    }
                    roadLocation.setTravelDistanceMap(travelDistanceMap);
                }
            }

            // // technician
            // csvReader = new BufferedReader(new FileReader("src/main/java/com/windfarmplanner/data/technician.csv"));

            // while ((row = csvReader.readLine()) != null) {
            //     String[] data = row.split(",");
            //     Technician technician = new Technician();
            //     Long id = Long.parseLong(data[0]);
            //     technician.setId(id);
            //     technician.setType(data[1]);

            //     this.technicianList.add(technician);
            //     technicianMap.put(technician.getId(), technician);
            //     Base base = baseMap.get(id);
            //     technician.setBase(base);

            // }
            // solution.setTechnicianList(this.technicianList);
            // csvReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void solve(){
        // Build the Solver
        logger.info("Build solver");
        SolverFactory<Route> solverFactory = SolverFactory.createFromXmlResource("config.xml");
        Solver<Route> solver = solverFactory.buildSolver();

        // Load a problem with given data
        // logger.info("Load problem with dataset");
        // Route unsolvedRoute = this.createRoute();

        // Solve the problem
        logger.info("Solving");
        // Route solvedRoute = solver.solve(solution);
        solver.solve(solution);
        logger.info("Done.");
    }


    // private Route createRoute() {
    //     return new Route(this.vesselList, this.turbineList);
    // }

}