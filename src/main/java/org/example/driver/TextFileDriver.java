package org.example.driver;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TextFileDriver implements ValidatableDriver {
    public ArrayList<String> fetchAllData() {
        ArrayList<String> stringDatum = new ArrayList<>();
        BufferedReader br = null;
        try {
            FileReader fr = new FileReader("data.txt");
             br = new BufferedReader(fr);

            String currentLine = "";
            while ((currentLine = br.readLine()) != null) {
                stringDatum.add(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringDatum;
    }

    public void overWriteDate(ArrayList<String> datum) {
        BufferedWriter bw = null;
        try {
            StringBuilder overWriteData = new StringBuilder();
            datum.forEach(data -> overWriteData.append(data).append("\n"));

            FileWriter fw = new FileWriter("data.txt");
            bw = new BufferedWriter(fw);

            bw.write(overWriteData.toString());
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

    public ArrayList<String> getFilteredArray(long id) {
        return fetchAllData()
                .stream()
                .filter(stringedObject -> Long.parseLong(stringedObject.split(" / ")[0]) != id)
                .collect(Collectors.toCollection(ArrayList<String>::new));
    }

    public String getObjectWithId(long id) {
        return fetchAllData()
                .stream()
                .filter(stringedObject -> Long.parseLong(stringedObject.split(" / ")[0]) == id)
                .collect(Collectors.toCollection(ArrayList<String>::new)).get(0);
    }

    @Override
    public boolean exists(long id) {
        return fetchAllData()
                .stream()
                .anyMatch(stringedObject -> Long.parseLong(stringedObject.split(" / ")[0]) == id);
    }
}
