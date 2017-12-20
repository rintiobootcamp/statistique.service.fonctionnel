package com.bootcamp.integration;

import com.bootcamp.controllers.StatistiqueController;
import com.jayway.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


import static com.jayway.restassured.RestAssured.given;



import static com.jayway.restassured.RestAssured.given;


public class statistiqueTest {

    private static Logger logger = LogManager.getLogger(StatistiqueController.class);

    /**
     *The Base URI of statistique fonctionnal service,
     * it can be change with the online URIof this service.
     */

    private String BASE_URI = "http://localhost:9093/stat";



    /**
     * The path of the Debat controller, according to this controller implementation
     */
    private String STAT_PATH ="/stats";

    /**
     * The startDate initialize for statistic method, you have
     * make sure that this is correct in one of the value in database
     */

    private long startDate = 0;



    /**
     * The endDate initialize for statistic method, you have
     * make sure that this is correct in one of the value in database
     */

    private long endDate = 0;




    /**
     * A entity of type:
     * <ul>
     *     <li>
     *         PROJET
     *     </li>
     *     <li>
     *         SECTEUR
     *     </li>
     *     <li>
     *         PILIER
     *     </li>
     *     <li>
     *         AXE
     *     </li>
     *     <li>
     *         PROJET
     *     </li>
     * </ul>
     *
     */
    private String entityType = "PROJET";

    /**
     *The given entity type ID
     * you have to specify this ID according to record in your data
     */
    private int entityId = 1;


    /**
     * Get the statistics of the given entity type
     * <b>
     * the comments must exist in the database
     * </b>
     * Note that this method will be the third to execute If every done , it
     * will return a 200 httpStatus code
     *
     * @throws Exception
     */
    @Test
    public void statsByEntityTypeAndID() throws Exception {
        String statsURI = BASE_URI + STAT_PATH +"/"+entityType+"/"+entityId;
        Response response = given()
                .queryParam( "startDate",startDate)
                .queryParam( "endDate",endDate )
                .log().all()
                .contentType("application/json")
                .expect()
                .when()
                .get(statsURI);

        logger.debug(response.getBody().prettyPrint());

        Assert.assertEquals(response.statusCode(), 200);

    }

}
