package hhowar2.washington.edu.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuizFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizFragment extends Fragment {
    String topic;
    Button submit;
    RadioGroup answersRadio;
    Question question;
    int number;
    int correct;
    int incorrect;
    List<Question> questions;
    Topic topicChosen;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuizFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizFragment newInstance(String param1, String param2) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public QuizFragment() {
        // Required empty public constructor
    }

    public void onRadioButtonClicked(View v) {
        submit.setEnabled(true);
    }

    /*public void addListenerOnButton() {
        answers = (RadioGroup) findViewById(R.id.radioGroup);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = answers.getCheckedRadioButtonId();
                RadioButton selected = (RadioButton) findViewById(selectedId);
                //Intent next = new Intent(Quizz.this, AnswerActivity.class);
                next.putExtra("Topic", topic);
                next.putExtra("Number", number);
                next.putExtra("UserAnswer", selected.getText());
                next.putExtra("Answer", question.getAnswer());
                if(selected.getText().equals(question.getAnswer())) {
                    //increment correct or incorrect
                    correct++;
                } else {
                    incorrect++;

                }
                next.putExtra("Correct", correct);
                next.putExtra("Incorrect", incorrect);
                startActivity(next);

            }
        });
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_quiz, container, false);;
        //view = inflater.inflate(R.layout.fragment_quiz, container, false);
        Bundle extras = this.getArguments();
        //if(extras != null) {
        topic = extras.getString("Topic");
        number = extras.getInt("Number");
        QuizApp data = (QuizApp)getActivity().getApplication();
        for(int i = 0; i <data.TopicList.size(); i++) {
            String title = data.TopicList.get(i).getName();
            if(topic.equals(title)) {
                topicChosen = data.TopicList.get(i);
            }
        }
        if(number == 0) {
            correct = 0;
            incorrect = 0;
        } else {
            correct = extras.getInt("Correct");
            incorrect = extras.getInt("Incorrect");
        }
        submit = (Button)view.findViewById(R.id.button5);
        submit.setEnabled(false);
        questions = new ArrayList<Question>();
        questions = topicChosen.getQuestions();
        question = questions.get(number);
        /*if(topic.equals("Math")) {
            questions = data.Math.getQuestions();
            question = questions.get(number);

        } else if(topic.equals("Physics")) {
            questions = data.Physics.getQuestions();
            question = questions.get(number);
        } else if(topic.equals("Marvel Super Heroes")) {
            questions = data.MarvelSuperHeroes.getQuestions();
            question = questions.get(number);
        } else {
            questions = data.ScienceFiction.getQuestions();
            question = questions.get(number);
        }*/

        TextView newTextView = (TextView) view.findViewById(R.id.textView10);
        newTextView.setText("" + question.getQuestion());
        //for(int i = 0; i < mathQuestions.size(); i++) {
        RadioButton radioView = (RadioButton) view.findViewById(R.id.radioButton5);
        radioView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit.setEnabled(true);
            }
        });
        String[] answers = question.getAnswers();
        radioView.setText(answers[0]);
        radioView = (RadioButton)view.findViewById(R.id.radioButton6);
        radioView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit.setEnabled(true);
            }
        });
        radioView.setText(answers[1]);
        radioView = (RadioButton)view.findViewById(R.id.radioButton7);
        radioView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit.setEnabled(true);
            }
        });
        radioView.setText(answers[2]);
        radioView = (RadioButton)view.findViewById(R.id.radioButton8);
        radioView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit.setEnabled(true);
            }
        });
        radioView.setText(answers[3]);
        answersRadio = (RadioGroup) view.findViewById(R.id.radioGroup2);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = answersRadio.getCheckedRadioButtonId();
                RadioButton selected = (RadioButton) view.findViewById(selectedId);
                Log.i("Test", "" + selected.getText());
                //Intent next = new Intent(Quizz.this, AnswerActivity.class);
                /*next.putExtra("Topic", topic);
                next.putExtra("Number", number);
                next.putExtra("UserAnswer", selected.getText());
                next.putExtra("Answer", question.getAnswer());*/
                if(selected.getText().equals(question.getCorrectAnswer())) {
                    //increment correct or incorrect
                    correct++;
                } else {
                    incorrect++;

                }
                mListener.answer(topic, correct, incorrect, number + 1, question.getCorrectAnswer(), "" + selected.getText(), questions.size());
                /*next.putExtra("Correct", correct);
                next.putExtra("Incorrect", incorrect);
                startActivity(next);*/

            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            //mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        //public void onFragmentInteraction(Uri uri);
        public void answer(String topic, int correct, int incorrect, int number, String answer, String userAnswer, int questionSize);
    }

}
