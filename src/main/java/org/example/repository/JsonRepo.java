package org.example.repository;

import org.example.domain.Phrase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class JsonRepo implements Repository {
    private static long nextIdx = 1;
    private FileReader fr;
    private FileWriter fw;
    JSONParser jsonParser = new JSONParser();

    private JsonRepo() throws IOException, ParseException {
        fr = new FileReader("data.json");
        JSONArray jsonContents = (JSONArray) jsonParser.parse(fr);
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

        fr.close();
    }

    public static JsonRepo getPhraseRepo() {
        try {
            return new JsonRepo();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long register(String content, String authorName) throws IOException {
        Map<String, Object> phrase = new HashMap<>();
        phrase.put("id", nextIdx);
        phrase.put("content", content);
        phrase.put("author", authorName);

        fr = new FileReader("data.json");
        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) jsonParser.parse(fr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject(phrase);
        jsonArray.add(jsonObject);

        fw = new FileWriter("data.json");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(jsonArray.toJSONString());

        bw.close();
        fr.close();
        return nextIdx++;
    }

    @Override
    public void init() {

    }

    @Override
    public Phrase find(Long id) throws IOException {
        fr = new FileReader("data.json");
        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) jsonParser.parse(fr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            Long jsonId = Long.parseLong(jsonObject.get("id").toString());
            String content = jsonObject.get("content").toString();
            String author = jsonObject.get("author").toString();
            if (jsonId == id) {
                return new Phrase(jsonId, content, author);
            }
        }

        fr.close();
        return null;
    }

    @Override
    public Map<Long, Phrase> list() throws IOException {
        fr = new FileReader("data.json");
        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) jsonParser.parse(fr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Map<Long, Phrase> result = new TreeMap<>(Collections.reverseOrder());

        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            Long id = Long.parseLong(jsonObject.get("id").toString());
            String content = jsonObject.get("content").toString();
            String author = jsonObject.get("author").toString();
            result.put(id, new Phrase(id, content, author));
        }

        fr.close();
        return result;
    }

    @Override
    public boolean delete(long id) throws IOException {
        boolean res = false;
        fr = new FileReader("data.json");
        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) jsonParser.parse(fr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            Long jsonId = Long.parseLong(jsonObject.get("id").toString());

            if (jsonId == id) {
                jsonArray.remove(o);
                res = true;
                break;
            }
        }
        fw = new FileWriter("data.json");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(jsonArray.toJSONString());

        bw.close();
        fr.close();
        return res;
    }

    @Override
    public boolean update(long id, String content, String authorName) throws IOException {
        boolean res = false;
        fr = new FileReader("data.json");
        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) jsonParser.parse(fr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Map<Long, Phrase> result = new TreeMap<>(Collections.reverseOrder());

        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            Long jsonId = Long.parseLong(jsonObject.get("id").toString());

            if (jsonId == id) {
                jsonArray.remove(o);
                res = true;
                break;
            }
        }
        if (!res) {
            return res;
        }
        Map<String, Object> phrase = new HashMap<>();
        phrase.put("id", id);
        phrase.put("content", content);
        phrase.put("author", authorName);
        jsonArray.add(new JSONObject(phrase));

        fw = new FileWriter("data.json");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(jsonArray.toJSONString());

        bw.close();
        fr.close();
        return res;
    }
}
