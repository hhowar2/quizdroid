package hhowar2.washington.edu.quizdroid;

/**
 * Created by Howard on 4/27/2015.
 */
public class Question {
    private String question;
    private String[] answer;
    private int index;

    public Question(String question, String[] answer, int index) {
        this.question = question;
        this.answer = answer;
        this.index = index;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String[] answer) {
        this.answer = answer;
    }

    public String getQuestion(){
        return this.question;
    }

    public String[] getAnswers() {
        return this.answer;
    }
    public String getCorrectAnswer() {
        return this.answer[index];
    }
}
