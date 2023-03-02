package org.example.repository;

import org.example.domain.Phrase;

import java.io.*;
import java.util.Collections;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class FileRepo implements Repository {
    private static long nextIdx = 1;

    private FileReader fr;
    private FileWriter fw;
    private BufferedReader br;
    private BufferedWriter bw;

    private FileRepo() throws IOException {
        fr = new FileReader("data.txt");
        br = new BufferedReader(fr);

        String lastLine = "";
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            lastLine = currentLine;
        }
        if (lastLine.equals("")) {
            nextIdx = 1;
        } else {
            String[] components = lastLine.split("/");
            nextIdx = Long.parseLong(components[0].trim())+1;
        }
        br.close();
    }

    public static FileRepo getPhraseRepo(){
        try {
            return new FileRepo();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long register(String content, String authorName) throws IOException {
        fw = new FileWriter("data.txt", true);
        bw = new BufferedWriter(fw);
        Phrase phrase = new Phrase(nextIdx, content, authorName);
        bw.append(phrase.toString());
        bw.newLine();
        bw.close();
        return nextIdx++;
    }

    @Override
    public void init() {

    }

    @Override
    public Phrase find(Long id) {
        return null;
    }

    @Override
    public Map<Long, Phrase> list() throws IOException {
        Map<Long, Phrase> result = new TreeMap<>(Collections.reverseOrder());
        fr = new FileReader("data.txt");
        br = new BufferedReader(fr);

        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(currentLine, " / ");
            long id = Long.parseLong(st.nextToken().trim());
            String content = st.nextToken();
            String author = st.nextToken();
            result.put(id, new Phrase(id, content, author));
        }

        br.close();
        return result;
    }

    @Override
    public boolean delete(long id) {
        boolean res = false;
        try {
            fr = new FileReader("data.txt");
            br = new BufferedReader(fr);
            fw = new FileWriter("data.txt");
            bw = new BufferedWriter(fw);

            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(currentLine, " / ");
                if (Long.parseLong(st.nextToken()) == id) {
                    currentLine = "";
                    res = true;
                }
                bw.write(currentLine + System.getProperty("line.separator"));
            }

            bw.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean update(long i, String updated_content, String mola) {
        return false;
    }
}
