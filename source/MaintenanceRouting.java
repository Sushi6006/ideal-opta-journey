package windfarm;

import windfarm.Base;
import windfarm.Vessel;
import windfarm.Turbine;
import windfarm.Route;
import windfarm.Standstill;
import windfarm.ScoreCalculator;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.*;
import java.lang.Integer;
import java.lang.reflect.Array;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;


public class MaintenanceRouting {

    protected List<Base> baseList;
    protected List<Vessel> vesselList;
    protected List<Turbine> turbineList;

    public void main(String[] args) {
        
        // import data

        // base
        String row;
        BufferedReader csvReader = new BufferedReader(new FileReader("/data/base.csv"));
        while ((row = csvReader.readLine()) != null) {
            int[] data = Arrays.asList(row.split(",")).stream().mapToInt(Integer::parseInt).toArray();
            
            this.baseList.add(new Base(data[0], new Location(data[1], data[2])));
        }
        csvReader.close();

        // vessels
        csvReader = new BufferedReader(new FileReader("/data/vessel.csv"));
        while ((row = csvReader.readLine()) != null) {
            int[] data = Arrays.asList(row.split(",")).stream().mapToInt(Integer::parseInt).toArray();
            this.vesselList.add(new Vessel(data));
        }
        csvReader.close();
        
        // turbines
        csvReader = new BufferedReader(new FileReader("/data/turbine.csv"));
        while ((row = csvReader.readLine()) != null) {
            int[] data = Arrays.asList(row.split(",")).stream().mapToInt(Integer::parseInt).toArray();
            this.turbineList.add(new Turbine(data));
        }
        csvReader.close();

        // Build the Solver
        SolverFactory<Route> solverFactory = SolverFactory.createFromXmlResource("config.xml");
        Solver<Route> solver = solverFactory.buildSolver();

        // Load a problem with given data
        Route unsolvedRoute = this.createRoute();

        // Solve the problem
        Route solvedRoute = solver.solve(unsolvedRoute);

        // Display the result
        System.out.println("\nSolved cloudBalance with given data:\n"
                + toDisplayString(solvedRoute));

    }

    private Route creatRoute() {
        return new Route(this.vesselList, this.turbineList);
    }

}