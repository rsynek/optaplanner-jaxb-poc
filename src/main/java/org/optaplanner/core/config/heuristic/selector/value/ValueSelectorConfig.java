package org.optaplanner.core.config.heuristic.selector.value;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ValueSelectorType")
public class ValueSelectorConfig {
    @XmlAttribute
    protected String id = null;

    @XmlAttribute
    protected String mimicSelectorRef = null;

    protected Class<?> downcastEntityClass = null;

    @XmlAttribute
    protected String variableName = null;

    public String getId() {
        return id;
    }

    public String getMimicSelectorRef() {
        return mimicSelectorRef;
    }

    public Class<?> getDowncastEntityClass() {
        return downcastEntityClass;
    }

    public String getVariableName() {
        return variableName;
    }
}
