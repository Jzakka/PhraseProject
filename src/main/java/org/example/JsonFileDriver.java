package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.stream.Collectors;

public class JsonFileDriver {
    public JSONArray fetchAllData() {
        FileReader fr = null;
        try {
            fr = new FileReader("data.json");
            JSONParser jsonParser = new JSONParser();

            JSONArray jsonArray = (JSONArray) jsonParser.parse(fr);

            return jsonArray;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void overWriteDate(JSONArray jsonArray) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter("data.json");
            bw = new BufferedWriter(fw);
            bw.write(jsonArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
