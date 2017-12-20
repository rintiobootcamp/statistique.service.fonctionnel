package com.bootcamp.controllers;

import com.bootcamp.commons.ws.usecases.pivotone.ProjetWS;
import com.bootcamp.services.ProjetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by Bignon .
 */
@RestController("ProjetController")
@RequestMapping("/projets")
@Api(value = "Projet API", description = "Projet API")
@CrossOrigin(origins = "*")
public class ProjectController {

    @Autowired
    ProjetService projetService;

    /**
     * Get all the projects in the database
     *
     * @return projects list
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = " Get all projets", notes = "Get all the projets")
    public ResponseEntity<List<ProjetWS>> getAll() throws IOException {
        List<ProjetWS> projets = projetService.getAll();
        return new ResponseEntity<List<ProjetWS>>(projets, HttpStatus.OK);

    }

    /**
     * Get a project in the database knowing its id
     *
     * @param id
     * @return the project
     * @throws IOException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = " Get one projet", notes = "Get particular projet by it id")
    public ResponseEntity<ProjetWS> getById(@PathVariable int id) throws IOException {
        ProjetWS projet = projetService.getProjet(id);
        return new ResponseEntity<ProjetWS>(projet, HttpStatus.OK);

    }
}
