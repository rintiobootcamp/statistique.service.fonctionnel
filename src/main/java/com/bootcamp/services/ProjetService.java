package com.bootcamp.services;

//import com.bootcamp.client.AxeClient;
//import com.bootcamp.client.ProjetClient;
import com.bootcamp.client.ProjetClient;
import com.bootcamp.client.SecteurClient;
import com.bootcamp.commons.ws.usecases.pivotone.ProjetWS;
import com.bootcamp.entities.Projet;
import com.bootcamp.helpers.ProjetHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bignon.
 */
@Component
public class ProjetService {

    ProjetClient projetClient;
    ProjetHelper projetHelper = new ProjetHelper();

    /**
     * Loading Projet Web Service client
     */
    @PostConstruct
    public void init() {

        projetClient = new ProjetClient();
    }

    /**
     * Get all the projects in the database
     *
     * @return projects list
     * @throws IOException
     */
    public List<ProjetWS> getAll() throws IOException {
        List<Projet> projets = projetClient.findAll();
        List<ProjetWS> result = new ArrayList<ProjetWS>();
        for (Projet current : projets) {
            ProjetWS projetWS = new ProjetWS();
            projetWS = projetHelper.buildProjetWsObject(current);
            result.add(projetWS);
        }
        return result;
    }

    /**
     * Get a project knowing its id
     *
     * @param idProjet the project id
     * @return a project
     * @throws IOException
     */
    public ProjetWS getProjet(int idProjet) throws IOException {
        Projet projet = projetClient.getById(idProjet);
        return projetHelper.buildProjetWsObject(projet);
    }
}
