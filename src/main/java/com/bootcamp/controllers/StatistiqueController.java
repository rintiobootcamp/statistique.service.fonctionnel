package com.bootcamp.controllers;


import com.bootcamp.services.Stat;

import com.bootcamp.services.StatGlobal;
import com.bootcamp.services.StatistiqueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;



/**
 * Created by Hilaire and reviewed by Moh.
=======
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.QueryParam;

/**
 * Created by Bignon .
>>>>>>> c35e913fafebb2c06028bdea15249bcaab208029
 */
@RestController("StatistiqueController")
@RequestMapping("/stats")
@Api(value = "statistique API", description = "statistique API")
@CrossOrigin(origins = "*")
public class StatistiqueController {

    @Autowired
    StatistiqueService statistiqueService;


    /**
     * Methode pour obtenir les statisques globales sur un type d'entite en donnant son id
     * @param entityType
     * @param entityId
     * @param startDate
     * @param endDate
     * @return
     * @throws IOException 
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{entityType}/{entityId}")
    @ApiOperation(value=" Get all statistics about an entity",notes="Get all statistics about an entity")
    public ResponseEntity<Stat> getStatForEntity(@PathVariable("entityType") String entityType, @PathVariable("entityId") int entityId, @RequestParam("startDate") long startDate, @RequestParam("endDate") long endDate) throws IOException{
        Stat stat= statistiqueService.getStatistique(entityType,entityId,startDate,endDate);
        return new ResponseEntity<>(stat,HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{entityType}")
    @ApiOperation(value=" Get all statistics about an entityType",notes="Get all statistics about an entityType")
    public ResponseEntity<StatGlobal> getAllStatByEntity(@PathVariable("entityType") String entityType,@RequestParam("startDate") long startDate, @RequestParam("endDate") long endDate) throws IOException{
        StatGlobal stat = statistiqueService.getStatistiqueAll(entityType,startDate,endDate);
        return new ResponseEntity<>(stat,HttpStatus.OK);

    }

}
