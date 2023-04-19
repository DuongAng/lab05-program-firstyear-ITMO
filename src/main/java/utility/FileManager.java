package utility;

import com.thoughtworks.xstream.XStream;
import data.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Operates the file for saving/loading collection.
 */
public class FileManager {
    private final XStream xstream = new XStream();
    private final String fileLocation;

    public FileManager(String[] args) {
        if(args.length > 0){
            this.fileLocation = args[0];
        } else {
            this.fileLocation = "test.xml"; // default value if no command line arguments
        }
//        xstream.processAnnotations(Person.class);
//        xstream.registerConverter(new PersonConverter());
        xstream.registerConverter(new HashMapConverter());
    }

    /**
     * Writes collection to a file.
     * @param collection Collection to write.
     */
    public void writeCollection(HashMap<Integer, Person> collection){
        File file = new File(fileLocation);
        if(!file.isFile()) {
            Console.printerror("Invalid file location!");
            return;
        }
        try(PrintWriter collectionFileWriter = new PrintWriter(file)){
            String xml = xstream.toXML(collection);
            collectionFileWriter.write(xml);
            Console.println("Collection successfully saved to file!");
        }catch (IOException exception){
            Console.printerror("The download file is a directory cannot be opened!");
        }
    }

    /**
     * Reads collection from a file.
     * @return Read collection.
     */
    public HashMap<Integer, Person> readCollection(){
        try(Scanner collectionFileScanner = new Scanner(new File(fileLocation))){
            HashMap<Integer, Person> collection = (HashMap<Integer, Person>)
                    xstream.fromXML(collectionFileScanner.useDelimiter("\\A").next(), new HashMap<Integer, Person>());
            Console.println("Collection successfully uploaded!");
            return collection;
        }catch (FileNotFoundException exception){
            Console.printerror("Boot file not found!");
        }catch (NoSuchElementException exception){
            Console.printerror("Boot file is empty!");
        }catch (IOException exception){
            Console.printerror("Required collection not found in upload file!");
        }catch (IllegalStateException exception){
            Console.printerror("Unexpected error!");
            System.exit(0);
        }
        return new HashMap<>();
    }



    @Override
    public String toString() {
        return "FileManager (class for working with the boot file) ";
    }
}