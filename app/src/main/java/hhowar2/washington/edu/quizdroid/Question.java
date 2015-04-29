package hhowar2.washington.edu.quizdroid;

/**
 * Created by Howard on 4/27/2015.
 */
public class Question {
    private String question;
    private String answer;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion(){
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }
}
