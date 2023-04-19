package utility;

import data.Location;
import data.Person;

import java.time.LocalDateTime;
import java.util.*;


/**
 * Operates the collection itself.
 */
public class CollectionManager {
    private HashMap<Integer, Person> personCollection = new HashMap<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private FileManager fileManager;

    public CollectionManager(FileManager fileManager){
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileManager = fileManager;

        loadCollection();
    }

    /**
     * @return The collection itself.
     */
    public HashMap<Integer, Person> getPersonCollection(){
        return personCollection;
    }

    /**
     * @return Last initialization time or null if there wasn't initialization.
     */
    public LocalDateTime getLastInitTime(){
        return lastInitTime;
    }

    /**
     * @return Last save time or null if there wasn't saving.
     */
    public  LocalDateTime getLastSaveTime(){
        return lastSaveTime;
    }

    /**
     * @return Name of the collection's type.
     */
    public String collectionType(){
        return personCollection.getClass().getName();
    }

    /**
     * @return Size of the collection.
     */
    public int collectionSize(){
        return personCollection.size();
    }

    /**
     * @return The first element of the collection or null if collection is empty.
     */
    public Person getFirst(){
        if(personCollection.isEmpty()) return null;
        Integer firstkey = Collections.min(personCollection.keySet());
        return personCollection.get(firstkey);
    }

    /**
     * @return The last element of the collection or null if collection is empty.
     */
    public Person getLast(){
        if(personCollection.isEmpty()) return null;
        int lastKey = Collections.max(personCollection.keySet());
        return personCollection.get(lastKey);
    }

    /**
     * @param id ID of the person.
     * @return A person by ID or null if person isn't found.
     */
    public Person getByKey(int id){
        for (Person person : personCollection.values()){
            if(person.getId() == id) return person;
        }
        return null;
    }

    /**
     * Adds a new person to collection.
     * @param person A person to add.
     */
    public void addToCollection(Person person){
        personCollection.put(person.getId(), person);
    }

    /**
     * Removes a new person to collection.
     * @param person A person to remove.
     */
    public void removeFromCollection(Person person){
        personCollection.remove(person.getId());
    }

    /**
     * Clears the collection.
     */
    public void clearCollection() {
        personCollection.clear();
    }

    /**
     * Generates next ID. IT'll be the bigger one +1.
     * @retrun Next ID.
     */
    public int generateNextId(){
        if (personCollection.isEmpty()) return 1;
        else {
            int maxId = Collections.max(personCollection.values(), Comparator.comparing(Person :: getId)).getId();
            return maxId + 1;
        }
    }

    /**
     * Saves the collection to file.
     */

    public void saveCollection() {
        fileManager.writeCollection(personCollection);
        lastSaveTime = LocalDateTime.now();
    }

    /**
     * Loads the collection from file.
     */
    private void loadCollection(){
        personCollection = fileManager.readCollection();
        lastInitTime = LocalDateTime.now();
    }

    /**
     * @param nameToFilter Name to filter by.
     * @return Information about valid person or empty string, if there's no such person.
     */
    public String personFilterInfo(String nameToFilter){
        String info = "";
        for (Person person : personCollection.values()){
            if(person.getName().equals(nameToFilter)){
                info += person + "\n\n";
            }
        }
        return info.trim();
    }

    /**
     * @return Return value unique location.
     */
    public List<Location> uniqueLocation(){
        Map<Location, Integer> locationCounts = new HashMap<>();
        for (Person person : personCollection.values()){
            Location location = person.getLocation();
            locationCounts.put(location, locationCounts.getOrDefault(location, 0) +1);
        }
        List<Location> uniqueLocation = new ArrayList<>();
        for (Map.Entry<Location, Integer> entry : locationCounts.entrySet()){
            if (entry.getValue() == 1){
                uniqueLocation.add(entry.getKey());
            }
        }
        return uniqueLocation;
    }

    /**
     * @return Return value descending weight.
     */
    public List<Float> getDescendingWeights(){
        List<Float> weights = new ArrayList<>();
        for (Person person : new ArrayList<>(personCollection.values())) {
            Integer weight = person.getWeight();
            weights.add(Float.valueOf(weight));
        }
        weights.sort(Comparator.reverseOrder());
        return weights;
    }

    /**
     * @return A person by his value or null if person isn't found.
     */
    public Person getByValue(Person personToFind){
        if (personCollection.containsValue(personToFind)){
            for (Person person : personCollection.values()){
                if (person.equals(personToFind)){
                    return person;
                }
            }
        }
        return null;
    }

    /**
     * @param personFromCollection A person to remove with.
     * Remove person lower than the selected one.
     */
    public void removeLower(Person personFromCollection){
        personCollection.entrySet().removeIf(entry -> entry.getValue().compareTo(personFromCollection) < 0);
    }

    /**
     * Clears the collection.
     */
    public void clearCollecton(){
        personCollection.clear();
    }

    @Override
    public String toString(){
        if (personCollection.isEmpty()) return "Collection is empty!";

        String info = "";
        for(Person person : personCollection.values()){
            info += person;
            if(person != personCollection.values().stream().reduce((a,b) -> b).orElse(null)) info += "\n\n";
        }
        return info;
    }
}
