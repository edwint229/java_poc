package com.test.serializable;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReaderTest {

    public static void main(String[] args) throws Exception {
        List<String> keys = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/edwin/Desktop/test.csv"),
                "UTF-8"));
        String str = null;
        while ((str = reader.readLine()) != null) {
            String[] split = str.split(",");
            String key = split[4]+"-"+split[9]+"-"+split[11]+"-"+split[6]+"-"+split[8]+"-"+split[10];
//            System.out.println(key);
            if(keys.contains(key)){
                System.out.println(str);
            }else{
                keys.add(key);
            }
        }

        reader.close();
    }
}
