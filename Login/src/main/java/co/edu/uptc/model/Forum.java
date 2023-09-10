package co.edu.uptc.model;

import java.util.ArrayList;

public class Forum {

    private String titulo;
    private String DescripcionDelForo;
    private ArrayList<Answer> answerForum;
    public Forum(String titulo, String descripcionDelForo) {
        this.titulo = titulo;
        DescripcionDelForo = descripcionDelForo;
        this.answerForum = new ArrayList<>();
    }
   
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcionDelForo() {
        return DescripcionDelForo;
    }
    public void setDescripcionDelForo(String descripcionDelForo) {
        DescripcionDelForo = descripcionDelForo;
    }

    public ArrayList<Answer> getAnswerForum() {
        return answerForum;
    }
    //esta son las respuestas del foro
    public void setAnswerForum(ArrayList<Answer> answerForum) {

        this.answerForum = answerForum;
    }

    public void addAnswer(String answer, Person person){

        Answer answerForum = new Answer(answer , person);
        this.answerForum.add(answerForum);
    }

    public String getDivider(){
        String divider = "";
        int maxLen = 0;
        for (Answer ans : this.answerForum){
            try {
                if (ans.getAnwers().length() > maxLen) maxLen = ans.getAnwers().length();
            }catch (NullPointerException e){}
        }
        try {
            if (this.DescripcionDelForo.length() > maxLen) maxLen = this.DescripcionDelForo.length();
            if (this.titulo.length() > maxLen) maxLen = this.titulo.length();
        }catch (NullPointerException e){}

        divider = "+";
        for (int i = 2 ; i < maxLen; i++){
            divider +="-";
        }
        divider += "+";
        return divider;
    }

    @Override
    public String toString() {
        String div = this.getDivider();
        String forum = div + "\n" + this.titulo.toUpperCase() + "\n" + "Description: \n" + this.DescripcionDelForo+ "\n";
        for (Answer ans : this.answerForum){
            forum += div + "\n"+ ans.toString() + "\n";
        }
        return forum;
    }
}
