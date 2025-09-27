package com.example;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

public class Mian {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://api.waifu.im/search?included_tags=raiden-shogun&included_tags=maid&height=>=2000");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("src\\main\\java\\com\\example\\data.txt"));
        String inputLine = reader.readLine();
        out.write(inputLine.getBytes());
        out.flush();

        reader.close();
        out.close();
    }
}
