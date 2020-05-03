package org.optaplanner.core.config.heuristic.selector.move;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.optaplanner.core.config.heuristic.selector.move.composite.CartesianProductMoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.composite.UnionMoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.generic.ChangeMoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.generic.SwapMoveSelectorConfig;

@XmlSeeAlso({ ChangeMoveSelectorConfig.class, SwapMoveSelectorConfig.class, CartesianProductMoveSelectorConfig.class,
        UnionMoveSelectorConfig.class })
@XmlType(name = "MoveSelectorType")
public class MoveSelectorConfig {

}
