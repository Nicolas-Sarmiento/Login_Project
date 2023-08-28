package co.edu.uptc.utilities;

import co.edu.uptc.model.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JsonStorageUtilities {
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private FileWriter fileWriter;
    private FileReader fileReader;
    private Gson gson;
    // Creo una lista global para almacenar los objetos leídos del archivo, esto para que
    //cuando agreguemos algo, no borre el resto
    private List<Person> existingContents = new ArrayList<>();
    private static final String FILEPATH = "Login/src/main/java/co/edu/uptc/persistence/";
    private static final String FILEPATHPEOPLE = "Login/src/main/java/co/edu/uptc/persistence/people.json";

    public JsonStorageUtilities(){
        //El gson esta inicializado asi para que se escriba en cascada y no en una misma linea
        gson = new GsonBuilder().setPrettyPrinting().create();
        //Esto es para que reconozca siempre el contenido del archivo, ya que si no lo hacemos asi
        //cuando se agregue algo nuevo se va a sobreescribir
        readContentFromFile();
    }

    //Probablemente nunca se use / Este comentario es provisional, por eso el español
    public boolean createFile(String fileName){
        String ext = ".json";
        String filePath = FILEPATH+fileName+ext;

        try {
            fileWriter = new FileWriter(filePath);
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Reads the content from a JSON file located at the specified file path.
     * @return {@code true} if the file is successfully read and the content is loaded into the internal array list,
     *         {@code false} if the file doesn't exist or an error occurs during reading.
     */
    public boolean readContentFromFile() {
        File file = new File(FILEPATHPEOPLE);
        if (!file.exists()) {
            //Verifica si el archivo existe
            return false;
        }//Intenta leer el archivo
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            //Llena el array list con el contenido del Json, como el Json esta escrito como una lista
            //por eso esta especificado como lista, ya que al leer va a obtener una lista
            //El type es para que se conserve la informacion del tipo de objeto
            existingContents = gson.fromJson(bufferedReader, new TypeToken<List<Person>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * This method receives an array of people already linked to their respective accounts. It can contain multiple people at once,
     * but in real execution, they are added one by one. This method is based on the array that contains the existing content in
     * the file. It adds the new data to the array of the previous content and writes the JSON.
     *
     * @param listPerson An array of people already linked to their respective accounts.
     * @return {@code true} if the data is successfully loaded and written to the JSON file, {@code false} otherwise.
     */
    public boolean loadDataToFile(List<Person> listPerson){
        File file = new File(FILEPATHPEOPLE);
        if(!file.exists()){
            return false;
        }
        if(existingContents == null){
            existingContents = new ArrayList<>();
        }
        //Esto simplemente agrega el nuevo contenido al array, para luego pasarlo al archivo
        //Lo agrega uno por uno, como un for
        for (Person object : listPerson) {
            existingContents.add(object);
        }

        try (FileWriter fileWriter = new FileWriter(FILEPATHPEOPLE)){
            //Escribo lo que esta en la colleccion en el Json
            gson.toJson(existingContents, fileWriter);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Person findPosition(String id) {
        for (int i = 0; i < existingContents.size(); i++) {
            if (existingContents.get(i).getId().equals(id)) {
                return existingContents.get(i);
            }
        }
        return null;
    }

    public boolean deletePerson(String id) {
        Person personToRemove = findPosition(id);

        if (personToRemove != null) {
            existingContents.remove(personToRemove);
            saveObject(existingContents);
            return true;
        }

        return false;
    }

    private void saveObject(List<Person> personList) {
        try{
            fileWriter = new FileWriter(FILEPATHPEOPLE);
            fileWriter.write(gson.toJson(personList));
            fileWriter.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public List<Person> getExistingContents() {
        return existingContents;
    }

    public void setExistingContents(List<Person> existingContents) {
        this.existingContents = existingContents;
    }
}
