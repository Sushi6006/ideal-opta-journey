package com.windfarmplanner;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.lang.Integer;
// import java.awt.Component;
// import java.util.function.BiConsumer;


// import com.windfarmplanner.location.Distance;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import com.windfarmplanner.location.Location;
import com.windfarmplanner.location.HubSegmentLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App {

    private final RoutingSolution problem = new RoutingSolution();

    private int taskListSize;
    private int vehicleListSize;
    private int hubListSize;
    // private int capacity;
    private int baseListSize;
    // private int technicianListSize;
    private Map<Long, Location> locationMap;
    private Map<Long, Base> baseMap;
    // private Map<Long, Technician> technicianMap;

    private List<Base> baseList;
    protected List<HubSegmentLocation> hubLocationList = null;
    protected List<Vehicle> vehicleList;
    protected List<Task> taskList;
    protected List<HubSegmentLocation> locationList;
    // protected List<Technician> technicianList;

    // protected final transient Logger logger = LoggerFactory.getLogger(getClass());


    public static void main(String[] args) {

        // import data
        App app = new App();
        app.readData();
        app.solve();
    }


    public void readData() {
        // Logger logger = LoggerFactory.getLogger(getClass());
        String row;
        BufferedReader csvReader = null;

        System.out.println("loading data");
        try {
            // technicianList = new ArrayList<>(technicianListSize);

            //size for variables
            csvReader = new BufferedReader(new FileReader("src/main/java/com/windfarmplanner/data/sizes.csv"));
            while ((row = csvReader.readLine()) != null) {
                // System.out.println("sizes");
                String[] data = row.split(",");
                if (data[0].equals("task")) {
                    taskListSize = Integer.parseInt(data[1]);
                    // System.out.println("task"+taskListSize);
                }
                else if (data[0].equals("vehicle")) {
                    vehicleListSize = Integer.parseInt(data[1]);
                }
                else if (data[0].equals("location")) {
                    hubListSize = Integer.parseInt(data[1]);
                    // System.out.println("hub"+hubListSize);
                }
                else if (data[0].equals("base")) {
                    baseListSize = Integer.parseInt(data[1]);
                }
                // else if (data[0] == "technician") {
                //     technicianListSize = Integer.parseInt(data[1]);
                // }
            }
            csvReader.close();

            baseMap = new LinkedHashMap<>(baseListSize);
            locationMap = new LinkedHashMap<>(hubListSize);
            hubLocationList = new ArrayList<>(hubListSize);
            // technicianMap = new LinkedHashMap<>(technicianListSize);

            baseList = new ArrayList<>(baseListSize);
            vehicleList = new ArrayList<>(vehicleListSize);
            taskList = new ArrayList<>(taskListSize);

            // location
            csvReader = new BufferedReader(new FileReader("src/main/java/com/windfarmplanner/data/location.csv"));
            while ((row = csvReader.readLine()) != null) {
                HubSegmentLocation location = new HubSegmentLocation();
                String[] data = row.split(",");
                location.setId(Long.parseLong(data[0]));
                location.setLatitude(Double.parseDouble(data[1]));
                location.setLongitude(Double.parseDouble(data[2]));
                if (data[0].charAt(0) == '1'){
                    location.setName("Task" + data[1]);
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
            problem.setLocationList(locationList);


            // base
            csvReader = new BufferedReader(new FileReader("src/main/java/com/windfarmplanner/data/base.csv"));

            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                Base base = new Base();
                long baseId = Long.parseLong(data[0]);
                base.setId(baseId);
                Location location = locationMap.get(baseId);
                // System.out.println("location:" + location);
                base.setLocation(location);
                baseList.add(base);
                baseMap.put(base.getId(),base);
            }
            csvReader.close();
//            solution.setBaseList(baseList);
            System.out.println("base:"+baseList);


            // vehicles
            csvReader = new BufferedReader(new FileReader("src/main/java/com/windfarmplanner/data/vehicle.csv"));

            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");

                // Vehicle vehicle = new Vehicle();
                Long id = Long.parseLong(data[0]);
                // vehicle.setId(id);
                // vehicle.setCapacity(Integer.parseInt(data[1]));
                Base base = baseMap.get(id);
                // if (base != null) {
                //     vehicle.setBase(base);
                // }
                Vehicle vehicle = new Vehicle(id, Integer.parseInt(data[1]), base);

                this.vehicleList.add(vehicle);
            }
            csvReader.close();
            problem.setVehicleList(this.vehicleList);


            // tasks
            csvReader = new BufferedReader(new FileReader("src/main/java/com/windfarmplanner/data/task.csv"));
            List<Location> taskLocationList = new ArrayList<>(taskListSize);
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");

                Task task = new Task();
                Long id = Long.parseLong(data[0]);
                task.setId(id);
                Location location = locationMap.get(id);
                task.setLocation(location);
                taskLocationList.add(location);
                task.setDemand(Integer.parseInt(data[1]));
                // task.setTechnicianList(data[3]);
                taskList.add(task);
            }

            // MANUALLY INITIALISING SOLUTIONS FOR LS
            // for (int i = 0; i < baseList.size(); i++){
            //     baseList.get(i).setNextTask(taskList.get(i));
            //     Standstill pre = baseList.get(i);
            //     taskList.get(i).setPreviousStandstill(pre);
            // }
            // for (int i = baseList.size() ; i < taskList.size(); i++){
            //     taskList.get(i).setPreviousStandstill(taskList.get(i-1));
            //     taskList.get(i-1).setNextTask(taskList.get(i));
            // }

            csvReader.close();
            problem.setTaskList(this.taskList);
            problem.setBaseList(baseList);
            System.out.println("pre:"+this.taskList.get(1).getPreviousStandstill());

            csvReader = new BufferedReader(new FileReader("src/main/java/com/windfarmplanner/data/distancemap.csv"));

            while ((row = csvReader.readLine()) != null) {
                // System.out.println(hubListSize);
                String[] data = row.split(",");
                for (int i = 0; i < hubListSize; i++) {
                    // Distance roadLocation = (Distance) taskLocationList.get(i);
                    HubSegmentLocation hubSegmentLocation = locationList.get(i);
                    // Map<Distance, Double> travelDistanceMap = new LinkedHashMap<>(taskListSize);
                    Map<HubSegmentLocation, Double> hubTravelDistanceMap = new LinkedHashMap<>(hubListSize);
                    for (int j = 0; j < hubListSize; j++) {
                        double travelDistance = Double.parseDouble(data[j]);
                        // double travelDistanceHub = Double.parseDouble(data[j]);
                        // if (i == j) {
                        //     if (travelDistance != 0.0) {
                        //         throw new IllegalStateException("The travelDistance (" + travelDistance + ") should be zero.");
                        //     }
                        // } else {
                        //     Distance otherLocation = (Distance) taskLocationList.get(j);
                        //     travelDistanceMap.put(otherLocation, travelDistance);
                        HubSegmentLocation otherHubLocation = hubLocationList.get(j);
                        hubTravelDistanceMap.put(otherHubLocation, travelDistance);
                    }
                    // roadLocation.setTravelDistanceMap(travelDistanceMap);
                    hubSegmentLocation.setHubTravelDistanceMap(hubTravelDistanceMap);
                    // System.out.println("app setTravelDistanceMap: "+travelDistanceMap);
                    // System.out.println("app setHubTravelDistanceMap: "+hubTravelDistanceMap);
                }
            }

            // TECHNICIAN
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
        Logger logger = LoggerFactory.getLogger(getClass());
        // Build the Solver
        logger.info("Build solver");
        SolverFactory<RoutingSolution> solverFactory = SolverFactory.createFromXmlResource("config.xml");
        Solver<RoutingSolution> solver = solverFactory.buildSolver();

        // Load a problem with given data
        // logger.info("Load problem with dataset");
        // Route unsolvedRoute = this.createRoute();

        // Solve the problem
        logger.info("Solving");
        // Route solvedRoute = solver.solve(solution);
        RoutingSolution solution = solver.solve(problem);
        logger.info("Done.");

        // FIXME: need to be matched
        for (Vehicle vehicle : solution.getVehicleList()) {
            System.out.println(vehicle);
        }
        for (Task task : solution.getTaskList()) {
            System.out.println(task);
        }

    }


    // private Route createRoute() {
    //     return new Route(this.vehicleList, this.taskList);
    // }

}