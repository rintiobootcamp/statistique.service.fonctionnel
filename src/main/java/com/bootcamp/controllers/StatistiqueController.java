package com.bootcamp.controllers;

import com.bootcamp.services.CensureStatService;
import com.bootcamp.services.Stat;

import com.bootcamp.services.StatGlobal;
import com.bootcamp.services.StatistiqueService;
import helpers.Input;
import helpers.Output;
import helpers.ReturnMostViewProjects;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Hilaire and reviewed by Moh. ======= import java.util.HashMap;
 * import java.util.List;
 *
 * import javax.ws.rs.QueryParam;
 *
 * /**
 * Created by Bignon . >>>>>>> c35e913fafebb2c06028bdea15249bcaab208029
 */
@RestController("StatistiqueController")
@RequestMapping("/stats")
@Api(value = "statistique API", description = "statistique API")
@CrossOrigin(origins = "*")
public class StatistiqueController {

    @Autowired
    StatistiqueService statistiqueService;

    @Autowired
    CensureStatService censureStatService;

    /**
     * Methode pour obtenir les statisques globales sur un type d'entite en
     * donnant son id
     *
     * @param entityType
     * @param entityId
     * @param startDate
     * @param endDate
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{entityType}/{entityId}")
    @ApiOperation(value = " Get all statistics about an entity", notes = "Get all statistics about an entity")
    public ResponseEntity<Stat> getStatForEntity(@PathVariable("entityType") String entityType, @PathVariable("entityId") int entityId, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) throws IOException {
        Stat stat = statistiqueService.getStatistique(entityType, entityId, startDate, endDate);
        return new ResponseEntity<>(stat, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/projects/mostViewed")
    @ApiOperation(value = " Get four projects most viewed", notes = "Get four projects most viewed")
    public ResponseEntity<List<ReturnMostViewProjects>> getTheMostViewProjects() throws IOException {
        List<ReturnMostViewProjects> retours = statistiqueService.constructReturnMostViewProjects();
        return new ResponseEntity<>(retours, HttpStatus.OK);

    }


    @RequestMapping(method = RequestMethod.GET, value = "/{entityType}")
    @ApiOperation(value = " Get all statistics about an entityType", notes = "Get all statistics about an entityType")
    public ResponseEntity<StatGlobal> getAllStatByEntity(@PathVariable("entityType") String entityType, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) throws IOException {
        StatGlobal stat = statistiqueService.getStatistiqueAll(entityType, startDate, endDate);
        return new ResponseEntity<>(stat, HttpStatus.OK);

    }

    /**
     * Methode pour obtenir les statisques globales sur un type d'entite en
     * donnant son id
     *
     * @param entityType
     * @param entityId
     * @param startDate
     * @param endDate
     * @param pas
     * @return
     * @throws IOException
     * @throws java.text.ParseException
     */
    @RequestMapping(method = RequestMethod.GET, value = "/list/{entityType}/{entityId}")
    @ApiOperation(value = " Get all statistics about an entity", notes = "Get all statistics about an entity")
    public ResponseEntity<List<Stat>> getStatForEntityByPas(@PathVariable("entityType") String entityType, @PathVariable("entityId") int entityId, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, @RequestParam("pas") int pas) throws IOException, ParseException {
        List<Stat> stats = statistiqueService.getStatistiqueByPas(entityType, entityId, startDate, endDate, pas);
        return new ResponseEntity<>(stats, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/list/{entityType}")
    @ApiOperation(value = " Get all statistics about an entityType", notes = "Get all statistics about an entityType")
    public ResponseEntity<List<StatGlobal>> getAllStatByEntityByPas(@PathVariable("entityType") String entityType, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, @RequestParam("pas") int pas) throws IOException, ParseException {
        List<StatGlobal> stats = statistiqueService.getStatistiqueAllByPas(entityType, startDate, endDate, pas);
        return new ResponseEntity<>(stats, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = " Get all statistics about censure of an entity", notes = "Get all statistics about censure of an entity")
    public ResponseEntity<Output> getCensureStatForEntity(@RequestBody Input  input) throws IOException {
        Output output = censureStatService.getCensures(input.getEntity(),input.getId());

        return new ResponseEntity<>(output, HttpStatus.OK);

    }

}
