package org.optaplanner.core.config.heuristic.selector.move.composite;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

import org.optaplanner.core.config.heuristic.selector.move.MoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.generic.ChangeMoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.generic.SwapMoveSelectorConfig;

@XmlType(name = "CartesianProductMoveSelectorType")
public class CartesianProductMoveSelectorConfig extends MoveSelectorConfig {

    @XmlElements({
            @XmlElement(name = "unionMoveSelector", type = UnionMoveSelectorConfig.class),
            @XmlElement(name = "cartesianProductMoveSelector", type = CartesianProductMoveSelectorConfig.class),
            @XmlElement(name = "changeMoveSelector", type = ChangeMoveSelectorConfig.class),
            @XmlElement(name = "swapMoveSelector", type = SwapMoveSelectorConfig.class)
    })
    private List<MoveSelectorConfig> moveSelectorConfigList = null;

    public List<MoveSelectorConfig> getMoveSelectorConfigList() {
        return moveSelectorConfigList;
    }
}
