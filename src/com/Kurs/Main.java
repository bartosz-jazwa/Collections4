package com.Kurs;

import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        randFloat();
        Map<String,String > myMap = new HashMap<>();
        myMap.put("1234", "Jan Kowalski");
        myMap.put("2222", "Adam Nowak");
        System.out.println(getName("2222",myMap));

        Map<String,String> dictionary = new HashMap<>();
        dictionary.put("kot", "cat");
        dictionary.put("pies", "dog");
        dictionary.put("koń", "horse");
        String sentence = "byl kot jest pies bedzie koń";
        String trans = translator(sentence,dictionary);
        System.out.println(trans);

        String uniq = "abrakadabra";
        System.out.println(charCount(uniq));
        Map<Character,List<Integer>> someMap = stringToMap(uniq);
        someMap.forEach((c,l)-> {
            System.out.print(c+" ");
            l.forEach(i-> System.out.print(i+" "));
            System.out.println(" ");
        });

        System.out.println(mapToString(someMap));

        List<String > fileList = openFile("c:\\a.txt");
        fileList.forEach(System.out::println);
    }

    public static void randFloat(){
        List<Float> myList = new ArrayList<Float>();
        double myRandomValue;
        do {
            myRandomValue = Math.random();
            float myValue = (float) myRandomValue*100;
            myList.add(myValue);
        }while (myRandomValue>0.01);

        myList.forEach(v-> System.out.println(v*Math.PI));
    }

    public static String getName (String id, Map<String,String > idNameMap){
        if(idNameMap.containsKey(id)){
            String name= idNameMap.get(id.toLowerCase());
            return name;
        }
        return null;
    }

    public static String translator(String originalSentence, Map<String ,String > dictionary){
        String[] sentSplit = originalSentence.split(" ");
        String[] transTab = new String[sentSplit.length];
        for (int i = 0; i <transTab.length ; i++) {
            if (dictionary.containsKey(sentSplit[i])){
                transTab[i] = dictionary.get(sentSplit[i]);
            }else {
                transTab[i]= sentSplit[i];
            }

        }
        StringBuilder translation= new StringBuilder();
        for (int i = 0; i <sentSplit.length; i++) {
            translation.append(transTab[i]);
            translation.append(" ");
        }
        return translation.toString();
    }

    public static long charCount(String s){

        char[] chars = s.toCharArray();
        List<Character> list = new ArrayList<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i <chars.length; i++) {
            set.add(chars[i]);
        }
        long counter = list.stream()
                .distinct()
                .count();

        return set.size();
    }

    public static Map<Character,List<Integer>> stringToMap(String s){
        List<Integer> indexList;
        Map<Character,List<Integer>> result = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i <chars.length; i++) {
            if (result.containsKey(chars[i])){
                indexList = result.get(chars[i]);
                indexList.add(i);
                result.put(chars[i],indexList);
            }else{
                indexList = new ArrayList<>();
                indexList.add(i);
                result.put(chars[i],indexList);
            }
        }
        return result;
    }

    public static String mapToString(Map<Character,List<Integer>> m){
        List<Integer> indexes = new ArrayList<>();
        final int[] size = {0};
        m.forEach((c,l)->{
            size[0] +=l.size();
        });

        char[] chars = new char[size[0]];
        List<Character> characterList = new ArrayList<>();
        m.forEach((c,l)->
        {
            l.forEach(i->{
                chars[i]=c;
            });
        });
        StringBuilder result= new StringBuilder();
        for (char c:chars) {
            result.append(c);

        }
        return result.toString();
    }

    public static List<String> openFile(String filePath){
        Scanner scanner=null;
        File file;
        int fileLength = 0;
        file = new File(filePath);
        fileLength=(int)file.length();
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String> fileLines = new ArrayList<>(fileLength);
        while (scanner.hasNext()){
            fileLines.add(scanner.nextLine());
        }

        return fileLines;
    }
}
