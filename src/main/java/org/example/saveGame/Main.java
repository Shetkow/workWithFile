package org.example.saveGame;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    static String path = "/home/paul/Games/savegames";

    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(100, 20, 30, 456.555);
        GameProgress game2 = new GameProgress(156, 10, 3, 234.324);
        GameProgress game3 = new GameProgress(99, 2, 56, 567.34);
        saveGames("/home/paul/Games/savegames/game1.dat", game1);
        saveGames("/home/paul/Games/savegames/game2.dat", game2);
        saveGames("/home/paul/Games/savegames/game3.dat", game3);
        ArrayList<String> arrL = new ArrayList<>();
        arrL.add("/home/paul/Games/savegames/game1.dat");
        arrL.add("/home/paul/Games/savegames/game2.dat");
        arrL.add("/home/paul/Games/savegames/game3.dat");
        zipFieles("/home/paul/Games/savegames/saveZips.zip", arrL);
        arrL.stream()
                .forEach(x -> {
                    File file = new File(x);
                    if (file.delete()) {
                        System.out.println(file.getName() + "Delete");
                    }
                });


        File game1D = new File("/home/paul/Games/savegames/game1.dat");
        File game2D = new File("/home/paul/Games/savegames/game2.dat");
        File game3D = new File("/home/paul/Games/savegames/game3.dat");

        openZip("/home/paul/Games/savegames/saveZips.zip");
        System.out.println(openProgress(game2D.getName()));


    }

    public static void saveGames(String url, GameProgress gp) {
        try (FileOutputStream fos = new FileOutputStream(url); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gp);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }

    public static void zipFieles(String path, List<String> arraysList) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (String file : arraysList) {
                File files = new File(file);
                try {
                    FileInputStream fous = new FileInputStream(file);
                    ZipEntry zip = new ZipEntry(files.getName());

                    zout.putNextEntry(zip);
                    byte[] buffer = new byte[fous.available()];
                    fous.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                    fous.close();

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void openZip(String path) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(path))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fouat = new FileOutputStream(name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fouat.write(c);
                }
                fouat.flush();
                zin.closeEntry();
                fouat.close();

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static GameProgress openProgress(String path) {
        GameProgress gameProgress = null;
        try (FileInputStream fint = new FileInputStream(path); ObjectInputStream ois = new ObjectInputStream(fint)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return gameProgress;
    }

}
