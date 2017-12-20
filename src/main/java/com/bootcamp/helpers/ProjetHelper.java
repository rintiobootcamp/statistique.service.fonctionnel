/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.helpers;

import com.bootcamp.client.LikeClient;
import com.bootcamp.client.NoteClient;
import com.bootcamp.client.SecteurClient;
import com.bootcamp.commons.enums.EntityType;
import com.bootcamp.commons.ws.usecases.pivotone.LikeWS;
import com.bootcamp.commons.ws.usecases.pivotone.NoteWS;
import com.bootcamp.commons.ws.usecases.pivotone.PhaseWS;
import com.bootcamp.commons.ws.usecases.pivotone.ProjetWS;
import com.bootcamp.commons.ws.usecases.pivotone.RegionWS;
import com.bootcamp.commons.ws.usecases.pivotone.SecteurWS;
import com.bootcamp.entities.Phase;
import com.bootcamp.entities.Projet;
import com.bootcamp.entities.Region;
import com.bootcamp.entities.Secteur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ibrahim
 */
public class ProjetHelper {

    LikeClient likeClient = new LikeClient();
    NoteClient noteClient = new NoteClient();

    /**
     * Build the ProjetWS object from a Projet object
     *
     * @param projet
     * @return projetws
     * @throws IOException
     */
    public ProjetWS buildProjetWsObject(Projet projet) throws IOException {
        ProjetWS projetws = new ProjetWS();
        projetws.setId(projet.getId());
        projetws.setReference(projet.getReference());
        projetws.setDateFinReel(projet.getDateFinReel());
        projetws.setNom(projet.getNom());
        projetws.setDescription(projet.getDescription());
        projetws.setDateDebutReel(projet.getDateDebutReel());
        projetws.setDateDebutPrevisionnel(projet.getDateDebutPrevisionnel());
        projetws.setDateFinPrevisionnel(projet.getDateFinPrevisionnel());
        projetws.setBudgetPrevisionnel(projet.getBudgetPrevisionnel());
        projetws.setFinancementPublic(projet.getFinancementPublic());
        projetws.setCoutReel(projet.getCoutReel());
//        projetws.setObjectif(projet.getObjectif());

        projetws.setLikes(getProjectsLikeStatistique(projet.getId()));
        projetws.setNotes(getProjectsNoteStatistique(projet.getId()));
        projetws.setPhases(PhaseHelper.buildPhaseWsList(projet.getPhases()));
//        projetws.setPhaseActuelle(getProjetActualPhase(projet.getPhases()));
        List<RegionWS> regionWSS = new ArrayList<>();
        for (Region region : projet.getRegions()) {
            RegionWS regionWS = RegionHelper.buildRegionWSObject(region);
            regionWSS.add(regionWS);
        }
        projetws.setRegions(regionWSS);

        List<PhaseWS> phaseWSS = new ArrayList<>();
        for (Phase phase : projet.getPhases()) {
            PhaseWS phaseWS = PhaseHelper.buildPhaseWSObject(phase);
            phaseWSS.add(phaseWS);
        }
        projetws.setPhases(phaseWSS);
        projetws.setSecteurId(projet.getIdSecteur());
        projetws.setSecteur(getProjetSector(projet.getIdSecteur()));
        return projetws;
    }

    /**
     * Get the project enable phases list (or steps list)
     *
     * @param phases
     * @return phases list
     */
    public PhaseWS getProjetActualPhase(List<Phase> phases) {
        PhaseWS result = new PhaseWS();
        for (Phase phase : phases) {
            if (phase.isActif()) {
                result = PhaseHelper.buildPhaseWSObject(phase);
            }
        }
        return result;
    }

    /**
     * Get the sector to which the project belong
     *
     * @param idSecteur the sector id
     * @return the sector
     * @throws IOException
     */
    public SecteurWS getProjetSector(int idSecteur) throws IOException {
        SecteurWS secteurWS = new SecteurWS();
        SecteurClient secteurClient = new SecteurClient();
        Secteur secteur = secteurClient.getById(idSecteur);
        secteurWS = SecteurHelper.buildNoParentSecteurWs(secteur, true);
        return secteurWS;
    }

    /**
     * Build the ProjetWS object list from a Projet object list
     *
     * @param projets
     * @return projetWSs
     * @throws IOException
     */
    public List<ProjetWS> buildProjetWSList(List<Projet> projets) throws IOException {
        List<ProjetWS> projetWSs = new ArrayList<>();
        for (Projet projet : projets) {
            projetWSs.add(buildProjetWsObject(projet));
        }
        return projetWSs;
    }

    private LikeWS getProjectsLikeStatistique(int idProject) throws IOException {
        LikeWS likews = likeClient.getClient(EntityType.PROJET.name(), idProject);

        return likews;
    }

    private NoteWS getProjectsNoteStatistique(int idProject) throws IOException {
        NoteWS notews = noteClient.getNote(EntityType.PROJET.name(), idProject);
        return notews;
    }
}
