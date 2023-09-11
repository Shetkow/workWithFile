package org.example.game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

private static StringBuilder configBulder = new StringBuilder();
public static final String PATH_TO_GAMES = "/home/paul/Games";
    public static void main(String[] args) {
        List<String> nameDir = Arrays.asList(
                "src",
                "res",
                "savegames",
                "temp"
        );
        createDir(PATH_TO_GAMES,nameDir);
        createDir(PATH_TO_GAMES+"/src",Arrays.asList("main","test"));
        createDir(PATH_TO_GAMES + "/res",Arrays.asList("drawables","vectors","icons"));
        List<String> filesName = new ArrayList<>(Arrays.asList(
                "Main.java",
                "Utils.java"

        ));
        filesMaker(PATH_TO_GAMES+"/src/main",filesName);
        filesName.clear();
        filesName.add("temp.txt");
        filesMaker(PATH_TO_GAMES+"/temp",filesName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_TO_GAMES + "/temp/temp.txt"))){
            writer.write(configBulder.toString());

        }catch (IOException e){
            e.getMessage();
        }

    }
public static void createDir(String path,List<String> list){
        list.forEach(d->{
            File dirs = new File(path,d);
            if(!dirs.exists()){
                if(dirs.mkdir()){
                    configBulder.append(dirs.getName() +" Создан");
                    configBulder.append("\n");
                    System.out.println(dirs.getName() + " create in " + dirs.getAbsolutePath());
                }
            }else {
                configBulder.append("каталог уже был создан");
                configBulder.append("\n");
            }
        });
}
public static void filesMaker(String path,List<String> list){
        for(String str:list){
            File files = new File(path,str);
            try {
                if(!files.exists()){
if(files.createNewFile()){
    configBulder.append(files.getName()+" Создан");
    configBulder.append("\n");
    System.out.println(files.getName() + "create in " + files.getAbsolutePath());

}else {
    configBulder.append("files are already created");
    configBulder.append("\n");
}
                }
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
}



}