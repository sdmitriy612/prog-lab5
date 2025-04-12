package ru.ifmo.se.collection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ifmo.se.collection.flat.*;
import ru.ifmo.se.io.formatters.Serializator;
import ru.ifmo.se.io.formatters.XMLSerializator;

import java.time.LocalDateTime;
import java.util.HashSet;

/**
 * Manages the collection of {@link Flat} objects.
 * <p>
 * This class is implemented as a singleton and provides methods to load and save
 * a collection of persons from and to a file, as well as access the current list
 * of persons in memory.
 * </p>
 *
 * @see Flat
 * @see ru.ifmo.se.io.formatters.Serializator
 */
public class CollectionManager {
    private static CollectionManager instance;

    /**
     * Flat HashSet collection.
     */
    private HashSet<Flat> collection = new HashSet<>();
    /**
     * Collection creation date.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    protected LocalDateTime creationDate;

    /**
     * Private constructor for serializer.
     */
    @JsonCreator
    private CollectionManager(@JsonProperty("creationDate") LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Private constructor for getInstance
     */
    private CollectionManager(){
        load();
    }

    /**
     * Collection getter.
     * @return collection of flats
     */
    public HashSet<Flat> getCollection(){
        return collection;
    }

    /**
     * Returns the singleton instance of {@code CollectionManager}.
     * <p>
     * If the instance is {@code null}, a new instance is created and returned.
     * </p>
     *
     * @return the {@code CollectionManager} instance
     */
    public static CollectionManager getInstance(){
        return instance == null ? instance = new CollectionManager() : instance;
    }

    /**
     * Loads the collection from a file specified by the {@code COLLECTION_FILE} environment variable.
     * <p>
     * If the collection file is not found or the file is empty, a new collection is created with the current date.
     * </p>
     *
     * @throws RuntimeException if the environment variable {@code COLLECTION_FILE} is not set
     */
    public void load(){

        String collectionFilePath = System.getenv("COLLECTION_FILE") == null ?
                "collection.xml" : System.getenv("COLLECTION_FILE");

        try(Serializator<CollectionManager> xmlSerializator = new XMLSerializator(collectionFilePath)){
            CollectionManager xmlInstance = xmlSerializator.safeRead();

            if(xmlInstance == null){
                creationDate = LocalDateTime.now();
            }else{
                creationDate = xmlInstance.creationDate;
                collection = xmlInstance.collection;
            }
        }catch (Exception e){
            System.out.println("loading collection error: " + e.getMessage());
        }

    }

    /**
     * Saves the current {@code CollectionManager} instance to a file specified by the {@code COLLECTION_FILE} environment variable.
     * <p>
     * If an error occurs during the saving process, it is silently ignored.
     * </p>
     *
     * @throws RuntimeException if the environment variable {@code COLLECTION_FILE} is not set
     */
    public void save(){

        String collectionFilePath = System.getenv("COLLECTION_FILE");
        if(collectionFilePath == null) throw new RuntimeException("collection file path is not set to environment variable COLLECTION_FILE");

        try(Serializator<CollectionManager> xmlSerializator = new XMLSerializator(collectionFilePath)){
            xmlSerializator.safeWrite(this);
        }catch (Exception ignored){

        }
    }

    /**
     * Returns a string representation of the collection with CollectionManager creation date and size of collection.
     *
     * @return a formatted string with the creation date and element count
     */
    public String toString(){
        return String.format("""
                Collection HashSet<Flat>
                CreationDate: %s
                Amount of elements: %d
                """, creationDate, collection.size());
    }
}
