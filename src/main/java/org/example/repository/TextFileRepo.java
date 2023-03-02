package org.example.repository;

import org.example.domain.Phrase;
import org.example.driver.TextFileDriver;
import java.util.*;

public class TextFileRepo implements Repository {

    private Long nextId = 1l;

    private TextFileDriver tfd = new TextFileDriver();

    private static TextFileRepo textFileRepo = new TextFileRepo();

    private TextFileRepo() {
        ArrayList<String> datum = tfd.fetchAllData();

        if (datum.isEmpty()) {
            nextId = 1l;
        } else {
            nextId = datum
                    .stream()
                    .mapToLong(data -> Long.parseLong(data.split(" / ")[0].trim()))
                    .reduce(Long.MIN_VALUE, (maxVal, curVal) -> maxVal = Math.max(maxVal, curVal)) + 1;
        }
    }

    public static TextFileRepo getPhraseRepo() {
        return textFileRepo;
    }

    @Override
    public long register(String content, String authorName) {
        ArrayList<String> fetchedDatum = tfd.fetchAllData();
        fetchedDatum.add(new Phrase(nextId, content, authorName).toString());

        tfd.overWriteDate(fetchedDatum);
        return nextId++;
    }

    @Override
    public void init() {

    }

    @Override
    public long getNextId() {
        return nextId;
    }

    @Override
    public Phrase find(Long id) {
        String[] tokens = tfd.getObjectWithId(id).split(" / ");
        return new Phrase(Long.parseLong(tokens[0]),  tokens[2], tokens[1]);
    }

    @Override
    public Map<Long, Phrase> list() {
        ArrayList<String> stringedPhrases = tfd.fetchAllData();
        Map<Long, Phrase> result = new TreeMap<>(Collections.reverseOrder());

        for (String line : stringedPhrases) {
            result.put(Phrase.getId(line), Phrase.valueOf(line));
        }

        return result;
    }

    @Override
    public void delete(long id) {
        tfd.overWriteDate(tfd.getFilteredArray(id));
    }

    @Override
    public void update(long i, String content, String authorName) {
        ArrayList<String> filteredArray = tfd.getFilteredArray(i);
        filteredArray.add(new Phrase(i, content, authorName).toString());
        tfd.overWriteDate(filteredArray);
    }
}
