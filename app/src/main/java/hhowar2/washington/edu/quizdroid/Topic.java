package hhowar2.washington.edu.quizdroid;

import java.util.List;

/**
 * Created by Howard on 4/27/2015.
 */
public class Topic {
    private String name;
    private List<Question> questions;
    private String description;

    public Topic(String name, List<Question> questions, String description) {
        this.name = name;
        this.questions = questions;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
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
}
