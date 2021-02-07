package com.windfarmplanner;

import com.windfarmplanner.location.HubSegmentLocation;
import com.windfarmplanner.timewindowed.TimeWindowedRoutingSolution;

import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;

import org.optaplanner.persistence.xstream.api.score.buildin.hardsoft.HardSoftScoreXStreamConverter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamInclude;

@PlanningSolution
@XStreamAlias("WfVehicleRoutingSolution")
@XStreamInclude({
    TimeWindowedRoutingSolution.class
})
public class RoutingSolution extends AbstractPersistable{

    protected String name;
    // planning variable
    protected List<HubSegmentLocation> locationList;
    protected List<Base> baseList;
    protected List<Vehicle> vehicleList;
    // planning entity
    protected List<Task> taskList;
    // planning whatever (variables as well?)
    // private List<Technician> technicianList;
    // private List<Part> partList;

    @XStreamConverter(HardSoftScoreXStreamConverter.class)
    private HardSoftLongScore score;

    // public RoutingSolution() { }

    // public RoutingSolution(List<Vehicle> vehicleList, List<Task> taskList) {
    //     this.vehicleList = vehicleList;
    //     this.taskList = taskList;
    // }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @ProblemFactCollectionProperty
    public List<HubSegmentLocation> getLocationList() { return locationList; }
    public void setLocationList(List<HubSegmentLocation> locationList) { this.locationList = locationList; }

    @ProblemFactCollectionProperty
    public List<Base> getBaseList() { return baseList; }
    public void setBaseList(List<Base> baseList){ this.baseList = baseList; }

    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "vehicleRange")
    public List<Vehicle> getVehicleList() { return vehicleList; }
    public void setVehicleList(List<Vehicle> vehicleList) { this.vehicleList = vehicleList; }

    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "taskRange")
    public List<Task> getTaskList() { return taskList; }
    public void setTaskList(List<Task> taskList) { this.taskList = taskList; }

    // public List<Technician> getTechnicianList() { return technicianList; }
    // public void setTechnicianList(List<Technician> technicianList) { this.technicianList = technicianList; }

    // public List<Part> getPartList() {return partList; }
    // public void setPartList(List<Part> partList) { this.partList = partList; }

    @PlanningScore
    public HardSoftLongScore getScore() { return score; }
    public void setScore(HardSoftLongScore score) { this.score = score; }

    // // ************************************************************************
    // // Complex methods
    // // ************************************************************************

    // public String getDistanceString(NumberFormat numberFormat) {
    //     if (score == null) {
    //         return null;
    //     }
    //     long distance = -score.getSoftScore();
    //     if (distanceUnitOfMeasurement == null) {
    //         return numberFormat.format(((double) distance) / 1000.0);
    //     }
    //     switch (distanceUnitOfMeasurement) {
    //         case "sec": // TODO why are the values 1000 larger?
    //             long hours = distance / 3600000L;
    //             long minutes = distance % 3600000L / 60000L;
    //             long seconds = distance % 60000L / 1000L;
    //             long milliseconds = distance % 1000L;
    //             return hours + "h " + minutes + "m " + seconds + "s " + milliseconds + "ms";
    //         case "km": { // TODO why are the values 1000 larger?
    //             long km = distance / 1000L;
    //             long meter = distance % 1000L;
    //             return km + "km " + meter + "m";
    //         }
    //         case "meter": {
    //             long km = distance / 1000L;
    //             long meter = distance % 1000L;
    //             return km + "km " + meter + "m";
    //         }
    //         default:
    //             return numberFormat.format(((double) distance) / 1000.0) + " " + distanceUnitOfMeasurement;
    //     }
    // }

}