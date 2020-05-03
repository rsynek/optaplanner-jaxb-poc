package org.optaplanner.core.config.heuristic.selector.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "EntitySelectorType")
public class EntitySelectorConfig {
    @XmlAttribute
    protected String id = null;
    @XmlAttribute
    protected String mimicSelectorRef = null;

    protected Class<?> entityClass = null;

    public EntitySelectorConfig() {

    }

    public String getId() {
        return id;
    }

    public String getMimicSelectorRef() {
        return mimicSelectorRef;
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }
}
