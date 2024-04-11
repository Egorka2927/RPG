package nl.rug.oop.rpg;

import java.io.*;

/**
 * Serializer class.
 */
public class Serializer {

    /**
     * Method to quick save.
     * @param room current room.
     * @param fileName file name.
     */
    public static void quickSave(Room room, String fileName) {
        File saveDirectory = new File("savedgames");
        saveDirectory.mkdir();
        try (FileOutputStream fileOutput = new FileOutputStream(saveDirectory + File.separator + fileName);
             ObjectOutputStream roomOutput = new ObjectOutputStream(fileOutput)) {
            roomOutput.writeObject(room);
            roomOutput.flush();
            fileOutput.flush();
            System.out.println("Saved successfully");
        } catch(FileNotFoundException fileNotFoundException) {
            System.err.println("File is not found!");
        } catch (IOException ioException) {
            System.err.println("IO exception!");
            ioException.printStackTrace();
        }

    }

    /**
     * Method to quick load.
     * @param fileName file name
     * @return the room you saved.
     * @throws IOException input output exception.
     * @throws ClassNotFoundException if class does not exist.
     */
    public static Room quickLoad(String fileName) throws IOException, ClassNotFoundException {
        try (FileInputStream fileInput = new FileInputStream(fileName);
            ObjectInputStream objectInput1 = new ObjectInputStream(fileInput)) {
            Room room = (Room)objectInput1.readObject();
            return room;
        }
    }
}
