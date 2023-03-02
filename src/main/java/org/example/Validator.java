package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Validator {
    JsonFileDriver jfd = new JsonFileDriver();

    public void validateId(long id) throws RuntimeException {
        JSONArray jsonArray = jfd.fetchAllData();
        JSONArray deletedArray = (JSONArray) (jsonArray
                .stream()
                .filter(o -> Long.parseLong(((JSONObject) o).get("id").toString()) == id)
                .collect(Collectors.toCollection(JSONArray::new)));
        if (deletedArray.size() == 0) {
            throw new NoSuchElementException(String.format("%d번 명언은 존재하지 않습니다.", id));
        }
    }
}
