package com.bootcamp.helpers;

import com.bootcamp.commons.ws.usecases.pivotone.RegionWS;
import com.bootcamp.entities.Region;

/**
 * Created Ibrahim on 11/29/17.
 */
public class RegionHelper {

    /**
     * Build the RegionWS object from a Region (location) object
     *
     * @param region
     * @return regionWS
     */
    public static RegionWS buildRegionWSObject(Region region) {
        RegionWS regionWS = new RegionWS();
        regionWS.setLatitude(region.getLatitude());
        regionWS.setLongitude(region.getLongitude());
        regionWS.setType(region.getType());
        regionWS.setNom(region.getNom());
        regionWS.setId(region.getId());

        return regionWS;
    }

}
