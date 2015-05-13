package hhowar2.washington.edu.quizdroid;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Howard on 5/10/2015.
 */
//Hardcoded data to be loaded in case JSON read fails
public class Data {
    public Topic Math;
    public Topic Physics;
    public Topic MarvelSuperHeroes;
    public Topic ScienceFiction;
    public List<Topic> TopicsList = new ArrayList<Topic>();



    public Data() {
        List<Question> questions;
        String topic;
        String description;
        String longDescription;
        questions = new ArrayList<Question>();
        //if(topic.equals("Math")) {
        String[] answer = new String[4];
        int index;
        for(int i = 0; i < 4; i++) {
            answer[i] = "" + (i + 2);
        }
        questions.add(new Question("1 + 1", answer, 0));
        questions.add(new Question("2 + 1", answer, 1));
        questions.add(new Question("3 + 1", answer, 2));
        questions.add(new Question("4 + 1", answer, 3));
        description = "The study of relationships and numbers";
        longDescription = "The study of quantity, structure, space and change. The usage of mathematic proofs to verify patterns and new conjectures";
        Math = new Topic("Math", questions, description, longDescription);
        TopicsList.add(Math);
        //} else if(topic.equals("Physics")) {
        questions = new ArrayList<Question>();
        String[] answer1 = new String[4];

        answer1[0] = "-9.8m/s^2";
        answer1[1] = "ma";
        answer1[2] = "mc^2";
        answer1[3] = "MV";
        questions.add(new Question("Gravity", answer, 0));
        questions.add(new Question("F=", answer, 1));
        questions.add(new Question("e=", answer, 2));
        questions.add(new Question("p=", answer, 3));
        description = "Explaining real-world laws through math";
        longDescription = "";
        Physics = new Topic("Physics", questions, description, longDescription);
        TopicsList.add(Physics);
        //} else if(topic .equals("Marvel Super Heroes")) {
        questions = new ArrayList<Question>();
        String[] answer2 = new String[4];

        answer2[0] = "Peter Parker";
        answer2[1] = "Clark Kent";
        answer2[2] = "Bruce Wayne";
        answer2[3] = "Barry Gordon";
        questions.add(new Question("Spider Man's identity?", answer, 0));
        questions.add(new Question("Super Man's Identity", answer, 1));
        questions.add(new Question("Batman's Identity", answer, 2));
        questions.add(new Question("Flash's Identity ", answer, 3));
        description = "The coolest kids you'll ever meet";
        MarvelSuperHeroes = new Topic("Marvel Super Heroes", questions, description, longDescription);
        TopicsList.add(MarvelSuperHeroes);
        //} else if(topic.equals("Science Fiction")) {
        questions = new ArrayList<Question>();
        String[] answer3 = new String[4];

        answer3[0] = "IRobot";
        answer3[1] = "Dune";
        answer3[2] = "Starship Troopers";
        answer3[3] = "Discworld";
        questions.add(new Question("Isaac Asimov wrote?", answer, 0));
        questions.add(new Question("Frank Herbert wrote?", answer, 1));
        questions.add(new Question("Robert Heinlein wrote?", answer, 2));
        questions.add(new Question("Terry Practchett wrote? ", answer, 3));
        description = "The genre that defines the future";
        ScienceFiction = new Topic("Science Fiction", questions, description, longDescription);
        TopicsList.add(ScienceFiction);
    }



}
