package org.example.driver;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.stream.Collectors;

public class JsonFileDriver implements ValidatableDriver {
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
        FileWriter fw;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter("data.json");
            bw = new BufferedWriter(fw);
            bw.write(jsonArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Filter whose id is not id
    public JSONArray getFilteredArray(long id) {
        return (JSONArray) (fetchAllData()
                .stream()
                .filter(o -> Long.parseLong(((JSONObject) o).get("id").toString()) != id)
                .collect(Collectors.toCollection(JSONArray::new)));
    }

    public JSONObject getObjectWithId(long id) {
        return (JSONObject) ((JSONArray) (fetchAllData()
                .stream()
                .filter(o -> Long.parseLong(((JSONObject) o).get("id").toString()) == id)
                .collect(Collectors.toCollection(JSONArray::new)))).get(0);
    }

    @Override
    public boolean exists(long id) {
        return fetchAllData()
                .stream()
                .anyMatch(o -> Long.parseLong(((JSONObject) o).get("id").toString()) == id);
    }
}
