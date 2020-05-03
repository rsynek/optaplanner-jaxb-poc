package org.optaplanner.core.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.jupiter.api.Test;
import org.optaplanner.core.config.heuristic.selector.entity.EntitySelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.MoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.composite.CartesianProductMoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.composite.UnionMoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.generic.ChangeMoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.generic.SwapMoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.value.ValueSelectorConfig;
import org.optaplanner.core.config.localsearch.LocalSearchPhaseConfig;
import org.optaplanner.core.config.localsearch.LocalSearchType;

import static org.assertj.core.api.Assertions.assertThat;

public class SolverConfigTest {

    private final JAXBContext jaxbContext;

    public SolverConfigTest() throws JAXBException {
        this.jaxbContext = JAXBContext.newInstance(SolverConfig.class);
    }

    @Test
    public void deserializeSolverConfig() throws JAXBException, IOException {
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        SolverConfig solverConfig;
        try (InputStream testSolverConfigStream = SolverConfigTest.class.getResourceAsStream("solverConfig.xml")) {
            solverConfig = (SolverConfig) unmarshaller.unmarshal(testSolverConfigStream);
        }

        File tempFile = Files.createTempFile("solverConfig", ".xml").toFile();

        // serialize and deserialize back
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(solverConfig, tempFile);
        solverConfig = (SolverConfig) unmarshaller.unmarshal(tempFile);

        assertThat(solverConfig).isNotNull();
        assertThat(solverConfig.phaseConfigList)
                .hasSize(1)
                .first()
                .isInstanceOf(LocalSearchPhaseConfig.class);

        LocalSearchPhaseConfig localSearchPhaseConfig = (LocalSearchPhaseConfig) solverConfig.phaseConfigList.get(0);
        assertThat(localSearchPhaseConfig.getLocalSearchType()).isEqualTo(LocalSearchType.LATE_ACCEPTANCE);

        assertThat(localSearchPhaseConfig.getMoveSelectorConfig())
                .isNotNull()
                .isInstanceOf(UnionMoveSelectorConfig.class);

        List<MoveSelectorConfig> unionMoveSelectorConfigList = ((UnionMoveSelectorConfig) localSearchPhaseConfig
                .getMoveSelectorConfig()).getMoveSelectorConfigList();
        assertThat(unionMoveSelectorConfigList).hasSize(2);
        assertThat(unionMoveSelectorConfigList.get(0)).isInstanceOf(CartesianProductMoveSelectorConfig.class);
        assertThat(unionMoveSelectorConfigList.get(1)).isInstanceOf(SwapMoveSelectorConfig.class);

        // assert the unionMoveSelector > cartesianProductMoveSelector
        List<MoveSelectorConfig> cartesianProductMoveSelectorConfigList = ((CartesianProductMoveSelectorConfig) unionMoveSelectorConfigList
                .get(0)).getMoveSelectorConfigList();
        assertThat(cartesianProductMoveSelectorConfigList)
                .hasSize(2)
                .hasOnlyElementsOfType(ChangeMoveSelectorConfig.class);
        // assert the unionMoveSelector > cartesianProductMoveSelector > changeMoveSelector (first)
        ChangeMoveSelectorConfig firstMoveSelectorConfig = (ChangeMoveSelectorConfig) cartesianProductMoveSelectorConfigList
                .get(0);
        EntitySelectorConfig firstEntitySelectorConfig = firstMoveSelectorConfig.getEntitySelectorConfig();
        assertThat(firstEntitySelectorConfig.getId()).isEqualTo("cartesianProductEntitySelector");
        assertThat(firstEntitySelectorConfig.getEntityClass().getName())
                .isEqualTo("org.optaplanner.examples.examination.domain.Exam");
        ValueSelectorConfig firstValueSelectorConfig = firstMoveSelectorConfig.getValueSelectorConfig();
        assertThat(firstValueSelectorConfig.getVariableName()).isEqualTo("room");

        // assert the unionMoveSelector > cartesianProductMoveSelector > changeMoveSelector (second)
        ChangeMoveSelectorConfig secondMoveSelectorConfig = (ChangeMoveSelectorConfig) cartesianProductMoveSelectorConfigList
                .get(1);
        assertThat(secondMoveSelectorConfig.getEntitySelectorConfig().getMimicSelectorRef())
                .isEqualTo("cartesianProductEntitySelector");
        ValueSelectorConfig secondValueSelectorConfig = secondMoveSelectorConfig.getValueSelectorConfig();
        assertThat(secondValueSelectorConfig.getVariableName()).isEqualTo("period");
        assertThat(secondValueSelectorConfig.getDowncastEntityClass().getName())
                .isEqualTo("org.optaplanner.examples.examination.domain.LeadingExam");

        // assert the unionMoveSelector > swapMoveSelector
        SwapMoveSelectorConfig swapMoveSelectorConfig = (SwapMoveSelectorConfig) unionMoveSelectorConfigList.get(1);
        assertThat(swapMoveSelectorConfig.getEntitySelectorConfig().getEntityClass().getName())
                .isEqualTo("org.optaplanner.examples.examination.domain.LeadingExam");
    }
}
