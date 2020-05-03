package org.optaplanner.core.config.localsearch;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

import org.optaplanner.core.config.PhaseConfig;
import org.optaplanner.core.config.heuristic.selector.move.MoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.composite.CartesianProductMoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.composite.UnionMoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.generic.ChangeMoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.generic.SwapMoveSelectorConfig;

@XmlType(name = "LocalSearchType")
public class LocalSearchPhaseConfig extends PhaseConfig {

    @XmlElement
    protected LocalSearchType localSearchType = null;

    @XmlElements({
            @XmlElement(name = "unionMoveSelector", type = UnionMoveSelectorConfig.class),
            @XmlElement(name = "cartesianProductMoveSelector", type = CartesianProductMoveSelectorConfig.class),
            @XmlElement(name = "changeMoveSelector", type = ChangeMoveSelectorConfig.class),
            @XmlElement(name = "swapMoveSelector", type = SwapMoveSelectorConfig.class)
    })
    protected MoveSelectorConfig moveSelectorConfig = null;

    public LocalSearchType getLocalSearchType() {
        return localSearchType;
    }

    public MoveSelectorConfig getMoveSelectorConfig() {
        return moveSelectorConfig;
    }
}
