package org.example.repository;

import org.example.JsonFileDriver;
import org.example.domain.Phrase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JsonRepo implements Repository {
    private static long nextIdx = 1;
    JsonFileDriver jfd = new JsonFileDriver();

    private JsonRepo() {
        JSONArray jsonContents = jfd.fetchAllData();
        OptionalLong id = IntStream
                .range(0, jsonContents.size())
                .mapToObj(i -> (JSONObject) jsonContents.get(i))
                .mapToLong(o -> (long) o.get("id"))
                .reduce((maxVal, curId) -> maxVal = Math.max(maxVal, curId));
        if (id.isPresent()) {
            nextIdx = id.getAsLong() + 1;
        } else {
            nextIdx = 1;
        }
    }

    public static JsonRepo getPhraseRepo() {
        return new JsonRepo();
    }

    @Override
    public long register(String content, String authorName) throws IOException {
        Map<String, Object> phrase = new HashMap<>();
        phrase.put("id", nextIdx);
        phrase.put("content", content);
        phrase.put("author", authorName);


        JSONArray jsonArray = jfd.fetchAllData();
        JSONObject jsonObject = new JSONObject(phrase);
        jsonArray.add(jsonObject);

        jfd.overWriteDate(jsonArray);

        return nextIdx++;
    }

    @Override
    public void init() {

    }

    @Override
    public Phrase find(Long id) {
        JSONArray filteredArray = (JSONArray) (jfd.fetchAllData()
                .stream()
                .filter(o -> Long.parseLong(((JSONObject) o).get("id").toString()) == id)
                .collect(Collectors.toCollection(JSONArray::new)));
        JSONObject foundOne = (JSONObject) filteredArray.get(0);
        return new Phrase(
                Long.parseLong(foundOne.get("id").toString()),
                foundOne.get("content").toString(),
                foundOne.get("author").toString());
    }

    @Override
    public Map<Long, Phrase> list() {
        JSONArray jsonArray = jfd.fetchAllData();

        Map<Long, Phrase> result = new TreeMap<>(Collections.reverseOrder());

        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            Long id = Long.parseLong(jsonObject.get("id").toString());
            String content = jsonObject.get("content").toString();
            String author = jsonObject.get("author").toString();
            result.put(id, new Phrase(id, content, author));
        }

        return result;
    }

    @Override
    public void delete(long id) {
        JSONArray deletedArray = (JSONArray) (jfd.fetchAllData()
                .stream()
                .filter(o -> Long.parseLong(((JSONObject) o).get("id").toString()) != id)
                .collect(Collectors.toCollection(JSONArray::new)));

        jfd.overWriteDate(deletedArray);
    }

    @Override
    public void update(long id, String content, String authorName) {
        JSONArray deletedArray = (JSONArray) (jfd.fetchAllData()
                .stream()
                .filter(o -> Long.parseLong(((JSONObject) o).get("id").toString()) != id)
                .collect(Collectors.toCollection(JSONArray::new)));

        deletedArray.add(new Phrase(id, content, authorName).toJson());

        jfd.overWriteDate(deletedArray);
    }
}
