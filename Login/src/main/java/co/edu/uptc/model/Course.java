package co.edu.uptc.model;

import co.edu.uptc.model.persontypes.Student;
import com.google.gson.annotations.Expose;
import java.util.ArrayList;


public class Course {
    private String name = "";
    private String id = "";
    private ArrayList<String> idStudents;
    private String idProfessor = "";
    public Course(){
        idStudents = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getIdStudents() {
        return idStudents;
    }

    public void setIdStudents(ArrayList<String> idStudents) {
        this.idStudents = idStudents;
    }

    public String getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(String idProfessor) {
        this.idProfessor = idProfessor;
    }
}
