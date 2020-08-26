package com.windfarmplanner;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.lang.Integer;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App {

    private Route solution;
    private int turbineListSize;
    private int vesselListSize;
    private int baseListSize;
    private int technicianListSize;
    private Map<String, Location> locationMap;
    private Map<String, Base> baseMap;
    private Map<String, Technician> technicianMap;

    protected List<Base> baseList;
    protected List<Vessel> vesselList;
    protected List<Turbine> turbineList;
    protected List<Technician> technicianList;

    protected final transient Logger logger = LoggerFactory.getLogger(getClass());


    public void main(String[] args) {

        // import data
        String row;
        BufferedReader csvReader = null;

        logger.info("loading data");
        try {
            //size for variables
            csvReader = new BufferedReader(new FileReader("/data/size.csv"));
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if (data[0]=="turbine"){
                    turbineListSize = Integer.parseInt(data[1]);
                }
                else if (data[0]=="vessel"){
                    vesselListSize = Integer.parseInt(data[1]);
                }
                else if (data[0]=="base"){
                    baseListSize = Integer.parseInt(data[1]);
                }
                else if (data[0]=="technician"){
                    technicianListSize = Integer.parseInt(data[1]);
                }
            }
            csvReader.close();


            //location
            csvReader = new BufferedReader(new FileReader("/data/location.csv"));
            while ((row = csvReader.readLine()) != null) {
                Location location = new Location();
                String[] data = row.split(",");
                locationMap = new LinkedHashMap<>(turbineListSize);
                location.setId(data[0]);
                location.setLatitude(Double.parseDouble(data[1]));
                location.setLongitude(Double.parseDouble(data[2]));

                locationMap.put(location.getId(), location);
            }
            csvReader.close();


            // base
            csvReader = new BufferedReader(new FileReader("/data/base.csv"));

            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                Base base = new Base();
                base.setId(data[0]);

                baseMap.put(base.getId(), base);


                this.baseList.add(base);
            }
            csvReader.close();


            // vessels
            csvReader = new BufferedReader(new FileReader("/data/vessel.csv"));

            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");

                Vessel vessel = new Vessel();
                String id = data[0];
                vessel.setId(id);
                vessel.setCapacity(Integer.parseInt(data[1]));
                Base base = baseMap.get(id);
                vessel.setBase(base);

                this.vesselList.add(vessel);
            }
            csvReader.close();


            // turbines
            csvReader = new BufferedReader(new FileReader("/data/turbine.csv"));

            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");

                Turbine turbine = new Turbine();
                String id = data[0];
                turbine.setId(id);
                Location location = locationMap.get(id);
                turbine.setLocation(location);
                turbine.setDemand(Integer.parseInt(data[1]));
//                turbine.setTechnicianList(data[3]);

                this.turbineList.add(turbine);
            }
            csvReader.close();


            //Technician
            csvReader = new BufferedReader(new FileReader("/data/technician.csv"));

            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");

                Technician technician = new Technician();
                String id = data[0];
                technician.setId(id);
                technician.setType(data[1]);

                this.technicianList.add(technician);
                technicianMap.put(technician.getId(), technician);
                Base base = baseMap.get(id);
                technician.setBase(base);

            }
            csvReader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        // Build the Solver
        logger.info("Bulid solver");
        SolverFactory<Route> solverFactory = SolverFactory.createFromXmlResource("config.xml");
        Solver<Route> solver = solverFactory.buildSolver();

        // Load a problem with given data
        logger.info("Load problem with dataset");
        Route unsolvedRoute = this.createRoute();

        // Solve the problem
        logger.info("Solving");
        Route solvedRoute = solver.solve(unsolvedRoute);

        logger.info("Done.");

    }

    private Route createRoute() {
        return new Route(this.vesselList, this.turbineList);
    }

}