package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;


public class FileHandler {
    


    public static boolean saveToBin(Stage stage, List<? extends Serializable> objects, String fileName) {
        if (stage == null) {
            System.err.println("Stage is null, cannot save file");
            return false;
        }
        
        if (objects == null || objects.isEmpty()) {
            System.err.println("No objects to save");
            return false;
        }
        
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Binary File");
            fileChooser.setInitialFileName(fileName + ".bin");
            
            FileChooser.ExtensionFilter binFilter = new FileChooser.ExtensionFilter("Binary Files (*.bin)", "*.bin");
            fileChooser.getExtensionFilters().add(binFilter);
            
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                    oos.writeObject(objects);
                    System.out.println("Successfully saved " + objects.size() + " objects to " + file.getAbsolutePath());
                    return true;
                } catch (IOException e) {
                    System.err.println("Error writing to file: " + e.getMessage());
                    e.printStackTrace();
                    return false;
                }
            } else {
                System.out.println("File save dialog was cancelled");
                return false;
            }
        } catch (Exception e) {
            System.err.println("Unexpected error in saveToBin: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    

    @SuppressWarnings("unchecked")
    public static List<?> loadFromBin(Stage stage) {
        if (stage == null) {
            System.err.println("Stage is null, cannot load file");
            return null;
        }
        
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Binary File");
            
            FileChooser.ExtensionFilter binFilter = new FileChooser.ExtensionFilter("Binary Files (*.bin)", "*.bin");
            fileChooser.getExtensionFilters().add(binFilter);
            
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    Object obj = ois.readObject();
                    if (obj instanceof List) {
                        List<?> loadedList = (List<?>) obj;
                        System.out.println("Successfully loaded " + loadedList.size() + " objects from " + file.getAbsolutePath());
                        return loadedList;
                    } else {
                        System.err.println("File does not contain a List object");
                        return null;
                    }
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error reading from file: " + e.getMessage());
                    e.printStackTrace();
                    return null;
                }
            } else {
                System.out.println("File open dialog was cancelled");
                return null;
            }
        } catch (Exception e) {
            System.err.println("Unexpected error in loadFromBin: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
