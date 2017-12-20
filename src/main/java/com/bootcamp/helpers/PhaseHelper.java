package com.bootcamp.helpers;

import com.bootcamp.commons.ws.usecases.pivotone.PhaseWS;
import com.bootcamp.entities.Phase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darextossa on 11/29/17.
 */
public class PhaseHelper {

    /**
     * Build the PhaseWS object from a Phase object
     *
     * @param phase
     * @return phaseWS
     */
    public static PhaseWS buildPhaseWSObject(Phase phase) {
        PhaseWS phaseWS = new PhaseWS();
        phaseWS.setDateFin(phase.getDateFin());
        phaseWS.setDateDebut(phase.getDateDebut());
        phaseWS.setNom(phase.getNom());
        phaseWS.setId(phase.getId());
        phaseWS.setActif(phase.isActif());
        return phaseWS;
    }

    /**
     * Build the PhaseWS object list from a Phase object list
     *
     * @param phases
     * @return phaseWS list
     */
    public static List<PhaseWS> buildPhaseWsList(List<Phase> phases) {
        List<PhaseWS> result = new ArrayList<>();
        for (Phase phase : phases) {
            result.add(buildPhaseWSObject(phase));
        }
        return result;
    }

}
