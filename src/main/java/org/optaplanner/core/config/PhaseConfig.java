package org.optaplanner.core.config;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.optaplanner.core.config.localsearch.LocalSearchPhaseConfig;

@XmlType(name = "phaseType")
@XmlSeeAlso({ LocalSearchPhaseConfig.class })
public abstract class PhaseConfig {
}
