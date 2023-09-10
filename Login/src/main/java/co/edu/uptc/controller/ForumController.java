package co.edu.uptc.controller;

import co.edu.uptc.model.Answer;
import co.edu.uptc.model.Forum;
import co.edu.uptc.model.Person;

import java.util.ArrayList;
/**
 *this class is for forum management.
 * saved forums in ArrayList
 * @Author Edwin Martinez
 */
public class ForumController {
    private ArrayList<Forum> forums;
    private Forum loggedForum;

    /**
     * The void constructor initializes the ArrayList
     */
    public ForumController(){
        forums=new ArrayList<>();
    }

    /**
     * This method allows you to be logged in by means of the forum number
     * @param i forum index to log in
     * @return if it was possible to log in
     */
    public boolean selectForum(int i){
        try{
            this.loggedForum=forums.get(i);
            return true;
        }catch (IndexOutOfBoundsException e){

        }
        return false;
    }

    /**
     * this method creates the forum
     * @param titleForum,description requirements to create the forum
     * @return if it was possible to create the forum
     */
    public boolean createdForum(String titleForum,String description){
        Forum f=new Forum(titleForum,description);
        forums.add(f);
        return true;
    }

    /**
     * This method shows the forum with the answers
     * @return forum content
     */
    public String seeForum(){
        return this.loggedForum.toString();
    }

    /**
     * this method deletes a forum
     * @param c index of forum
     * @return if it was possible to delete the forum
     */
    public boolean deleteForum(int c){
        try{
            forums.remove(forums.get(c));
            return true;
        }catch (IndexOutOfBoundsException e){

        }
        return false;
    }

    /**
     * This method shows all the titles of the forums that exist
     * @return names forums with index
     */
    public String seeTitles(){
        String aux="";
        for (int i=0;i<forums.size();i++) {
            aux+=(i+1)+" "+forums.get(i).getTitulo()+"\n";
        }
        return aux;
    }

    /**
     * this method adds a response to forum
     * @param comment comment to add
     * @return if it was possible to add a comment
     */
    public boolean addComment(String comment, Person person){
        loggedForum.addAnswer(comment, person);
        return true;
    }

    /**
     * This method deletes an existing forum comment.
     * @param comment,person person with comment to delete
     * @return if it was possible to delete a comment
     */
    public boolean deleteComment(String comment, Person person){
        ArrayList<Answer> aux = loggedForum.getAnswerForum();
        Answer auxAnswer = new Answer(comment, person);
        for (int i=0;i<aux.size();i++) {
            if (aux.get(i).equals(auxAnswer)){

                aux.remove(auxAnswer);

                loggedForum.setAnswerForum(aux);
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getOwnAnswers(Person p){
        ArrayList<String> answers = new ArrayList<>();
        ArrayList<Answer> aux = loggedForum.getAnswerForum();
        for (Answer ans: aux){
            try {
                if (ans.getPerson().equals(p)){
                    answers.add(ans.getAnwers());
                }
            }catch (NullPointerException e){}
        }

        return answers;
    }


}
