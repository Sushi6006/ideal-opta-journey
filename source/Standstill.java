package windfarm;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;
import windfarm.Location;

@PlanningEntity
public interface Standstill {

    /**
     * @return never null
     */
    Location getLocation();

    /**
     * @return sometimes null
     */
    Vessel getVessel();

    /**
     * @return sometimes null
     */
    @InverseRelationShadowVariable(sourceVariableName = "previousStandstill")
    Turbine getNextTurbine();

    void setNextTurbine(Turbine nextTurbine);

}
