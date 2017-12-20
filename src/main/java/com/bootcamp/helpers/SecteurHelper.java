package com.bootcamp.helpers;

import com.bootcamp.commons.ws.usecases.pivotone.SecteurWS;
import com.bootcamp.entities.Axe;
import com.bootcamp.entities.Pilier;
import com.bootcamp.entities.Secteur;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by darextossa on 11/29/17.
 */
public class SecteurHelper {

    /**
     * Build the SecteurWS object from a Secteur object and add the axe parent
     * if needed
     *
     * @param secteur
     * @param addParent
     * @return secteurWS
     * @throws IOException
     */
    public static SecteurWS buildSecteurWsObject(Secteur secteur, Boolean addParent) throws IOException {
        SecteurWS secteurWS = new SecteurWS();
        secteurWS.setId(secteur.getId());
        secteurWS.setDateMiseAJour(secteur.getDateMiseAJour());
        secteurWS.setDateCreation(secteur.getDateCreation());
        secteurWS.setIcone(secteur.getIcone());
        secteurWS.setNom(secteur.getNom());
        if (addParent) {
            secteurWS = addParent(secteur, secteurWS);
        }

        return secteurWS;
    }

    /**
     * Build the SecteurWS object from a Secteur object and add the axe parent
     * if needed
     *
     * @param secteur
     * @param addParent
     * @return secteurWS
     */
    public static SecteurWS buildNoParentSecteurWs(Secteur secteur, Boolean addParent) {
        SecteurWS secteurWS = new SecteurWS();
        secteurWS.setId(secteur.getId());
        secteurWS.setDateMiseAJour(secteur.getDateMiseAJour());
        secteurWS.setDateCreation(secteur.getDateCreation());
        secteurWS.setIcone(secteur.getIcone());
        secteurWS.setNom(secteur.getNom());
        if (addParent) {
            secteurWS = addParent(secteur, secteurWS);
        }
        return secteurWS;
    }

    /**
     * Add the axe parent of a sector to an existing secteurWS
     *
     * @param secteur
     * @param secteurWS
     * @return secteurWS
     */
    public static SecteurWS addParent(Secteur secteur, SecteurWS secteurWS) {
        Axe axe = secteur.getAxe();
        HashMap<String, Object> AxeMap = new HashMap<>();
        if (axe == null) {
            return secteurWS;
        }
        AxeMap.put("id", axe.getId());
        AxeMap.put("dateCreation", axe.getDateCreation());
        AxeMap.put("dateMiseAJour", axe.getDateMiseAJour());
        AxeMap.put("nom", axe.getNom());
        AxeMap.put("description", axe.getDescription());
        AxeMap.put("titre", axe.getTitre());
        AxeMap.put("titreFocus", axe.getTitreFocus());
        AxeMap.put("descriptionFocus", axe.getDescriptionFocus());

        HashMap<String, Object> pilierMap = new HashMap<>();
        Pilier pilier = secteur.getAxe().getPilier();
        pilierMap.put("id", pilier.getId());
        pilierMap.put("dateCreation", pilier.getDateCreation());
        pilierMap.put("dateMiseAJour", pilier.getDateMiseAJour());
        pilierMap.put("nom", pilier.getNom());
        pilierMap.put("description", pilier.getDescription());

        AxeMap.put("pilier", pilierMap);

        secteurWS.setAxe(AxeMap);

        return secteurWS;
    }
}
