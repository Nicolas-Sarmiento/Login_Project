package co.edu.uptc.model;

import java.util.Objects;

/**
 * The Answer class is responsible for
 * saver anwers on their String atribute
 * @author Samuel Gonzalez
 * @version  1.0
 *
 */

public class Answer {
    private String answer;
    private Person person;
    /**
     * Constructor of the class needs the Answer ande the person who will answer.
     * @param answer,personCometed both entered by user
     * @return If the credentials entered are valid,
     * save the user and return true.
     *
     */
    public Answer(String answer, Person personCometed) {
        this.answer = answer;
        this.person = personCometed;
    }

    public String getAnwers() {
        return answer;
    }

    public void setAnwers(String answer) {
        this.answer = answer;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return this.person.getName() + " " +this.person.getLastname() + "\n" + this.answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer respons = (Answer) o;
        return this.answer.equals(((Answer) o).getAnwers()) & this.person.getId() == respons.getPerson().getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer, person);
    }
}
