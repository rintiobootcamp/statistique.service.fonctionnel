package com.bootcamp.helpers;

import com.bootcamp.commons.ws.usecases.pivotone.AxeWS;
import com.bootcamp.commons.ws.usecases.pivotone.SecteurWS;
import com.bootcamp.entities.Axe;
import com.bootcamp.entities.Pilier;
import com.bootcamp.entities.Projet;
import com.bootcamp.entities.Secteur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by darextossa on 11/29/17.
 */
public class AxeHelper {

    /**
     * Build the AxeWS object from the given Axe object and Projet object list
     * and then add the axe parent if needed
     *
     * @param axe
     * @param projets
     * @param addParent
     * @return axeWS
     * @throws IOException
     */
    public static AxeWS buildAxewsObject(Axe axe, List<Projet> projets, Boolean addParent) throws IOException {
        AxeWS axeWS = new AxeWS();

        axeWS.setId(axe.getId());
        axeWS.setDateCreation(axe.getDateCreation());
        axeWS.setNom(axe.getNom());
        axeWS.setDescription(axe.getDescription());
        axeWS.setDescriptionFocus(axe.getDescriptionFocus());
        axeWS.setTitre(axe.getTitre());
        axeWS.setTitreFocus(axe.getTitreFocus());
        axeWS.setDateMiseAJour(axe.getDateMiseAJour());

        if (addParent) {
            axeWS = addParent(axe, axeWS);
        }

        List<SecteurWS> secteurWSS = new ArrayList<>();
        for (Secteur secteur : axe.getSecteurs()) {
            SecteurWS secteurWS = SecteurHelper.buildSecteurWsObject(secteur, false);
            secteurWSS.add(secteurWS);
        }

        return axeWS;
    }

    /**
     * Add the pillar parent of an axe to an existing axeWS
     *
     * @param axe
     * @param axeWS
     * @return
     */
    public static AxeWS addParent(Axe axe, AxeWS axeWS) {
        Pilier pilier = axe.getPilier();
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", pilier.getId());
        map.put("dateCreation", pilier.getDateCreation());
        map.put("dateMiseAJour", pilier.getDateMiseAJour());
        map.put("nom", pilier.getNom());
        map.put("description", pilier.getDescription());

        axeWS.setPilier(map);

        return axeWS;
    }

    /**
     * Build the AxeWS object list from the given Axe object list and Projet
     * object list
     *
     * @param axes
     * @param projetList
     * @return axeWSS
     * @throws IOException
     */
    public static List<AxeWS> buildAxes(List<Axe> axes, List<Projet> projetList) throws IOException {
        List<AxeWS> axeWSS = new ArrayList<>();
        for (Axe axe : axes) {
            AxeWS axeWS = buildAxewsObject(axe, projetList, true);
            axeWSS.add(axeWS);
        }
        return axeWSS;
    }

}
