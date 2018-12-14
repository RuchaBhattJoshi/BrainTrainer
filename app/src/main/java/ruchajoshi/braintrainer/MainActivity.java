package ruchajoshi.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    RelativeLayout gameRelativeLayout;
    TextView sumTextView,pointsTextView,resultTextView,timerTextView;
    Button startButton,button0,button1,button2,button3,playAgainButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuestions=0;
    GridLayout gameGridLayout;

    public void generateQuestion() {

        Random rand =new Random();

        int a= rand.nextInt(51);
        int b= rand.nextInt(51);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer= rand.nextInt(4);
        answers.clear();

        int incorrectAnswer;

        for(int i=0;i<4;i++){

            if(i==locationOfCorrectAnswer){

                answers.add(a+b);
            }
            else{

                incorrectAnswer=rand.nextInt(71);

                while(incorrectAnswer==a+b){

                    incorrectAnswer=rand.nextInt(71);
                }
                answers.add(incorrectAnswer);
            }
        }


        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playagainbutton));

    }

    public void playAgain(View view) {

        score=0;
        numberOfQuestions=0;
        gameGridLayout.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30100,1000 ){

            @Override
            public void onTick(long l) {

                timerTextView.setText(String.valueOf(l/1000)+ "s");

            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);
                gameGridLayout.setVisibility(View.INVISIBLE);
                sumTextView.setVisibility(View.INVISIBLE);
                pointsTextView.setText("0/0");
                timerTextView.setText("0s");
                resultTextView.setText("Your Score " + Integer.toString(score) +"/"+ Integer.toString(numberOfQuestions));


            }
        }.start();


    }

    public void chooseAnswer(View view) {

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
        {
            score++;
            resultTextView.setText("Correct!");
        }
        else{

            resultTextView.setText("Wrong!");
        }

        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) +"/"+ Integer.toString(numberOfQuestions));
        generateQuestion();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         startButton= findViewById(R.id.startButton);
         sumTextView=findViewById(R.id.sumtextview);
         pointsTextView=findViewById(R.id.pointstextView);
         button0=findViewById(R.id.button0);
         button1=findViewById(R.id.button1);
         button2=findViewById(R.id.button2);
         button3=findViewById(R.id.button3);
         resultTextView=findViewById(R.id.resultTextView);
         timerTextView=findViewById(R.id.timertextView);
         playAgainButton=findViewById(R.id.playagainbutton);
         gameRelativeLayout=findViewById(R.id.gamerelativelayout);
         gameGridLayout=findViewById(R.id.gridLayout);

    }


}
