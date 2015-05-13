package hhowar2.washington.edu.quizdroid;

import java.util.List;

/**
 * Created by Howard on 4/27/2015.
 */
public class Topic {
    private String name;
    private List<Question> questions;
    private String shortDescription;
    private String longDescription;

    public Topic(String name, List<Question> questions, String shortDescription, String longDescription) {
        this.name = name;
        this.questions = questions;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }
    public void addQuestions(Question q) {
        questions.add(q);
    }

    public List<Question> getQuestions() {
        return this.questions;
    }

    public String getName() {
        return this.name;
    }
    public String getLongDescription() {
        return this.longDescription;
    }

}
