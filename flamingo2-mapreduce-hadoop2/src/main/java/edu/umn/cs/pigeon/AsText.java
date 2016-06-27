/*******************************************************************
 * Copyright (C) 2014 by Regents of the University of Minnesota.   *
 * *
 * This Software is released under the Apache License, Version 2.0 *
 * http://www.apache.org/licenses/LICENSE-2.0                      *
 *******************************************************************/
package edu.umn.cs.pigeon;

import com.esri.core.geometry.ogc.OGCGeometry;
import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

import java.io.IOException;

/**
 * Returns the Well-Known Text (WKT) representation of a geometry object.
 * @author Ahmed Eldawy
 *
 */
public class AsText extends EvalFunc<String> {

    private ESRIGeometryParser geometryParser = new ESRIGeometryParser();

    @Override
    public String exec(Tuple t) throws IOException {
        if (t.size() != 1)
            throw new IOException("ST_AsText expects one geometry argument");
        OGCGeometry geom = geometryParser.parseGeom(t.get(0));
        try {
            return geom.asText();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}