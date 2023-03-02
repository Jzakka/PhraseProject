package org.example.repository;

import org.example.domain.Phrase;
import org.example.driver.JsonFileDriver;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class JsonRepo implements Repository {
    private long nextIdx = 1;
    JsonFileDriver jfd = new JsonFileDriver();

    private static JsonRepo jsonRepo = new JsonRepo();

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
        return jsonRepo;
    }

    @Override
    public long getNextId() {
        return nextIdx;
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
        JSONObject foundOne = jfd.getObjectWithId(id);
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
        JSONArray deletedArray = jfd.getFilteredArray(id);

        jfd.overWriteDate(deletedArray);
    }

    @Override
    public void update(long id, String content, String authorName) {
        JSONArray deletedArray = jfd.getFilteredArray(id);

        deletedArray.add(new Phrase(id, content, authorName).toJson());

        jfd.overWriteDate(deletedArray);
    }
}
