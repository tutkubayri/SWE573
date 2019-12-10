/*
package com.swe.comin.services;

import com.swe.comin.exceptions.EndpointException;
import com.swe.comin.models.Endpoint;
import java.util.ArrayList;
import java.util.HashMap;

public class WikidataService {
    public static void WikidataService(String search){

    String endpointUrl = "https://query.wikidata.org/sparql";

    String querySelect = "SELECT ?v WHERE { ?v ?p \"" + search +"\" }";

        try {
        HashMap data = retrieveData(endpointUrl, querySelect);
        printResult(data, 30);
    } catch (EndpointException eex) {
        eex.printStackTrace();
    }
}

    public static HashMap<String, HashMap> retrieveData(String endpointUrl, String query) throws EndpointException {
        Endpoint sp = new Endpoint(endpointUrl, false);
        HashMap<String, HashMap> rs = sp.query(query);
        return rs;
    }

    public static void printResult(HashMap<String, HashMap> rs , int size) {
        for (String variable : (ArrayList<String>) rs.get("result").get("variables")) {
            System.out.print(String.format("%-"+size+"."+size+"s", variable ) + " | ");
        }
        System.out.print("\n");
        for (HashMap value : (ArrayList<HashMap>) rs.get("result").get("rows")) {
            for (String variable : (ArrayList<String>) rs.get("result").get("variables")) {
                System.out.print(String.format("%-"+size+"."+size+"s", value.get(variable)) + " | ");
            }
            System.out.print("\n");
        }
    }
}
*/
