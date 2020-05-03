package org.optaplanner.core.config;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.optaplanner.core.config.localsearch.LocalSearchPhaseConfig;

@XmlRootElement(name = "solver")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solverType", propOrder = {
        "phaseConfigList"
})
public class SolverConfig {

    @XmlElements({
            @XmlElement(name = "localSearch", type = LocalSearchPhaseConfig.class)
    })
    protected List<PhaseConfig> phaseConfigList = null;
}
