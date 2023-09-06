package co.edu.uptc.utilities;

import co.edu.uptc.model.Account;
import co.edu.uptc.model.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonStorageUtilities {
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private FileWriter fileWriter;
    private FileReader fileReader;
    private Gson gson;
    // Creo una lista global para almacenar los objetos leídos del archivo, esto para que
    //cuando agreguemos algo, no borre el resto
    private List<Person> existingContentsPersons = new ArrayList<>();
    private List<Account> existingContentsAccounts = new ArrayList<>();
    private static final String FILEPATH = "./src/main/java/co/edu/uptc/persistence/";
    private static final String EXTENSION = ".json";
    private static final String FILEPATHPEOPLE = "./src/main/java/co/edu/uptc/persistence/people.json";

    public JsonStorageUtilities(){
        //El gson esta inicializado asi para que se escriba en cascada y no en una misma linea
        gson = new GsonBuilder().setPrettyPrinting().create();
        //Esto es para que reconozca siempre el contenido del archivo, ya que si no lo hacemos asi
        //cuando se agregue algo nuevo se va a sobreescribir
        //readContentFromFile();
        readPersons("people");
        readAccounts("accounts");
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

//    /**
//     * Reads the content from a JSON file located at the specified file path.
//     * @return {@code true} if the file is successfully read and the content is loaded into the internal array list,
//     *         {@code false} if the file doesn't exist or an error occurs during reading.
//     */
//    public boolean readContentFromFile() {
//        File file = new File(FILEPATHPEOPLE);
//        if (!file.exists()) {
//            //Verifica si el archivo existe
//            return false;
//        }//Intenta leer el archivo
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
//            //Llena el array list con el contenido del Json, como el Json esta escrito como una lista
//            //por eso esta especificado como lista, ya que al leer va a obtener una lista
//            //El type es para que se conserve la informacion del tipo de objeto
//            existingContentsPersons = gson.fromJson(bufferedReader, new TypeToken<List<Person>>(){}.getType());
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }

    //Se utiliza métodos genericos para la creación de dos archivos diferentes ( cuentas y personas )
    public <T> boolean saveDataToFile(List<T> dataList, String fileName, Type type) {
        File file = new File(FILEPATH + fileName + EXTENSION);

        if (dataList == null) {
            dataList = new ArrayList<>();
        }

        try (FileWriter fileWriter = new FileWriter(file)) {
            gson.toJson(dataList, type, fileWriter);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean saveDataToFilePerson(List<Person> listPerson, String fileName) {
        Type personListType = new TypeToken<List<Person>>() {}.getType();
        return saveDataToFile(listPerson, fileName, personListType);
    }

    public boolean saveDataToFileAccount(List<Account> listAccount, String fileName) {
        Type accountListType = new TypeToken<List<Account>>() {}.getType();
        return saveDataToFile(listAccount, fileName, accountListType);
    }

    public <T> List<T> readContentFromFile(String fileName, Type type) {
        List<T> dataList = new ArrayList<>();

        File file = new File( FILEPATH + fileName + EXTENSION);
        if (!file.exists()) {
            return null;
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            dataList.clear();
            dataList.addAll(gson.fromJson(bufferedReader, type));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return dataList;
    }

    public void readPersons(String fileName) {
         existingContentsPersons.addAll(readContentFromFile(fileName, new TypeToken<List<Person>>() {}.getType()));
    }

    public void readAccounts(String fileName) {
        existingContentsAccounts.addAll(readContentFromFile(fileName, new TypeToken<List<Account>>() {}.getType()));
    }



//    /**
//     * This method receives an array of people already linked to their respective accounts. It can contain multiple people at once,
//     * but in real execution, they are added one by one. This method is based on the array that contains the existing content in
//     * the file. It adds the new data to the array of the previous content and writes the JSON.
//     *
//     * @param listPerson An array of people already linked to their respective accounts.
//     * @return {@code true} if the data is successfully loaded and written to the JSON file, {@code false} otherwise.
//     */
//    public boolean loadDataToFilePerson(List<Person> listPerson, String fileName){
//        File file = new File(FILEPATH + fileName);
//        if(!file.exists()){
//            return false;
//        }
//        if(existingContentsPersons == null){
//            existingContentsPersons = new ArrayList<>();
//        }
//        //Esto simplemente agrega el nuevo contenido al array, para luego pasarlo al archivo
//        //Lo agrega uno por uno, como un for
//        for (Person object : listPerson) {
//            existingContentsPersons.add(object);
//        }
//
//        try (FileWriter fileWriter = new FileWriter(FILEPATHPEOPLE)){
//            //Escribo lo que esta en la colleccion en el Json
//            gson.toJson(existingContentsPersons, fileWriter);
//            return true;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public boolean loadDataToFileAccount(List<Account> listPerson, String fileName){
//        File file = new File(FILEPATH + fileName + EXTENSION);
//
//        if(existingContentsAccounts == null){
//            existingContentsAccounts = new ArrayList<>();
//        }
//        //Esto simplemente agrega el nuevo contenido al array, para luego pasarlo al archivo
//        //Lo agrega uno por uno, como un for
//        for (Account object : listPerson) {
//            existingContentsAccounts.add(object);
//        }
//
//        try (FileWriter fileWriter = new FileWriter(FILEPATH + fileName + EXTENSION)){
//            //Escribo lo que esta en la colleccion en el Json
//            gson.toJson(existingContentsAccounts, fileWriter);
//            return true;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    
    public Person findPosition(String id) {
        for (int i = 0; i < existingContentsPersons.size(); i++) {
            if (existingContentsPersons.get(i).getId().equals(id)) {
                return existingContentsPersons.get(i);
            }
        }
        return null;
    }

    public boolean deletePerson(String id) {
        Person personToRemove = findPosition(id);

        if (personToRemove != null) {
            existingContentsPersons.remove(personToRemove);
            saveObject(existingContentsPersons);
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

    public List<Account> getExistingContentsAccounts() {
        return existingContentsAccounts;
    }

    public void setExistingContentsAccounts(List<Account> existingContentsAccounts) {
        this.existingContentsAccounts = existingContentsAccounts;
    }

    public List<Person> getExistingContentsPersons() {
        return existingContentsPersons;
    }

    public void setExistingContentsPersons(List<Person> existingContents) {
        this.existingContentsPersons = existingContents;
    }
}
