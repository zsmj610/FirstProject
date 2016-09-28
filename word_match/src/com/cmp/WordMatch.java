package com.cmp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.*;

/**
 * Created by Z on 2016/9/28.
 */
public class WordMatch {
    private static final int WORDNUM = 8;

    private static void readWordFile(String filePath, String[] str) {
        try {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                for (int i = 0; i < WORDNUM; i++) {
                    str[i] = bufferedReader.readLine();
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }

    private static String readTextFile(String filePath) {
        StringBuilder text = new StringBuilder();
        try {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader br = new BufferedReader(read);
                String lineText = null;
                while((lineText = br.readLine())!=null){
                    text.append(lineText);
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return text.toString();
    }

    public static void main(String[] args) {
        String[] words = new String[WORDNUM];
        String wordFilePath = "word.txt";
        String textFilePath = "text.txt";
        readWordFile(wordFilePath, words);
        String text = readTextFile(textFilePath);
        //System.out.println(text);
        for (int i = 0; i < WORDNUM; i++) {
            String regEx = words[i];
            Pattern p = Pattern.compile(' '+regEx+' ');
            Matcher m = p.matcher(text);
            boolean result = m.find();
            System.out.println(words[i]+":"+result);
        }
    }
}