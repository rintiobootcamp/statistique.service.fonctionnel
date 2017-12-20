//
//import com.bootcamp.commons.ws.usecases.pivotone.ProjetWS;
//import com.bootcamp.entities.Phase;
//import com.bootcamp.entities.Projet;
//import com.bootcamp.entities.Region;
//import com.bootcamp.helpers.ProjetHelper;
//import com.google.gson.Gson;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
///**
// *
// * @author edwigegédéon
// */
//public class AppMain {
//    public static void main(String[] args) throws IOException {
//        ProjetHelper ph = new ProjetHelper();
//        Projet projet = new Projet();
//        projet.setId(0);
//        projet.setNom("project");
//        Phase phase = new Phase();
//        phase.setNom("phase");
//        List<Phase> phases = new ArrayList<>();
//        phases.add(phase);
//        Region region = new Region();
//        region.setNom("region");
//        List<Region> regions = new ArrayList<>();
//        regions.add(region);
//        projet.setPhases(phases);
//        projet.setRegions(regions);
//
//        ProjetWS pws = ph.buildProjetWsObject(projet);
//
//        Gson  gson = new Gson();
//        System.out.println("\n aollo \n"+ gson.toJson(pws));
//
//    }
//}
