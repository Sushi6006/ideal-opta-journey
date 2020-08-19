package org.optaplanner.examples.windfarm;


import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;


public class App {

    private Route solution;
    private int turbineListSize;
    private int vesselListSize;
    private int baseListSize;
    private Map<Integer, Location> locationMap;
    private Map<Integer, Base> baseMap;

    private List<Depot> depotList;
    protected List<Base> baseList;
    protected List<Vessel> vesselList;
    protected List<Turbine> turbineList;


    public void main(String[] args) {

        // import data
        String row;
        BufferedReader csvReader = null;
        try {
            //size for variables
            csvReader = new BufferedReader(new FileReader("/data/size.csv"));
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if (data[0]=="turbine"){
                    turbineListSize = Integer.parseInt(data[1]);
                }
                if (data[0]=="vessel"){
                    vesselListSize = Integer.parseInt(data[1]);
                }
                if (data[0]=="base"){
                    baseListSize = Integer.parseInt(data[1]);
                }
            }
            csvReader.close();

            //location
            csvReader = new BufferedReader(new FileReader("/data/location.csv"));
            while ((row = csvReader.readLine()) != null) {
                Location location = new Location();
                String[] data = row.split(",");
                locationMap = new LinkedHashMap<>(turbineListSize);
                location.setId(Integer.parseInt(data[0]));
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
                base.setId(Integer.parseInt(data[0]));

                baseMap.put(base.getId(), base);

                this.baseList.add(base);

            }
            csvReader.close();

            // vessels
            csvReader = new BufferedReader(new FileReader("/data/vessel.csv"));

            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");

                Vessel vessel = new Vessel();
                int id = Integer.parseInt(data[0]);
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
                int id = Integer.parseInt(data[0]);
                turbine.setId(id);
                Location location = locationMap.get(id);
                turbine.setLocation(location);
                turbine.setDemand(Integer.parseInt(data[1]));

                this.turbineList.add(turbine);
            }
            csvReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Build the Solver
        SolverFactory<Route> solverFactory = SolverFactory.createFromXmlResource("config.xml");
        Solver<Route> solver = solverFactory.buildSolver();

        // Load a problem with given data
        Route unsolvedRoute = this.createRoute();

        // Solve the problem
        Route solvedRoute = solver.solve(unsolvedRoute);

        // Display the result
        // System.out.println("\nSolved cloudBalance with given data:\n");
        System.out.println("Hello, world!");

    }

    private Route createRoute() {
        return new Route(this.vesselList, this.turbineList);
    }

}
