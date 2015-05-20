package appewtc.masterung.ishiharassru;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    //Explicit
    private TextView questionTextView;
    private ImageView ishiharaImageView;
    private RadioGroup choiceRadioGroup;
    private RadioButton choice1RadioButton, choice2RadioButton,
            choice3RadioButton, choice4RadioButton;
    private Button answerButton;
    private int radioAnInt, indexAnInt, scoreAnInt;
    private SSRUmodel objSsrUmodel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();

        //Button Controller
        buttonController();

        //Radio Controller
        radioController();

        //About SSRUmodel
        aboutSSRUmodel();

    }   // onCreate

    private void aboutSSRUmodel() {

        objSsrUmodel = new SSRUmodel();
        objSsrUmodel.setOnSSRUmodelChangeListener(new SSRUmodel.OnSSRUmodelChangeListener() {
            @Override
            public void onSSRUmodelChangeListener(SSRUmodel ssrUmodel) {

                //Change View
                changeView(ssrUmodel.getButtonAnInt());

            }   // event
        });

    }   // aboutSSRUmodel

    private void changeView(int buttonAnInt) {

        //Change Image
        int drawableAnInt[] = new int[]{R.drawable.ishihara_01, R.drawable.ishihara_02,
                R.drawable.ishihara_03, R.drawable.ishihara_04, R.drawable.ishihara_05,
                R.drawable.ishihara_06, R.drawable.ishihara_07, R.drawable.ishihara_08,
                R.drawable.ishihara_09, R.drawable.ishihara_10};
        ishiharaImageView.setImageResource(drawableAnInt[buttonAnInt]);

        //Change Choice
        int choiceAnInt[] = new int[]{R.array.times1, R.array.times2, R.array.times3,
                R.array.times4, R.array.times5, R.array.times6, R.array.times7, R.array.times8,
                R.array.times9, R.array.times10};
        String strChoice[] = new String[4];
        strChoice = getResources().getStringArray(choiceAnInt[buttonAnInt]);
        choice1RadioButton.setText(strChoice[0]);
        choice2RadioButton.setText(strChoice[1]);
        choice3RadioButton.setText(strChoice[2]);
        choice4RadioButton.setText(strChoice[3]);
    }   // changeView

    private void radioController() {

        choiceRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                //Sound Effect
                MediaPlayer radioPlayer = MediaPlayer.create(getBaseContext(), R.raw.effect_btn_shut);
                radioPlayer.start();

                //Setup radioAnInt
                switch (i) {
                    case R.id.radioButton:
                        radioAnInt = 1;
                        break;
                    case R.id.radioButton2:
                        radioAnInt = 2;
                        break;
                    case R.id.radioButton3:
                        radioAnInt = 3;
                        break;
                    case R.id.radioButton4:
                        radioAnInt = 4;
                        break;
                    default:
                        radioAnInt = 0;
                        break;
                }   // switch


            }   // event
        });

    }   // radioContorller

    private void buttonController() {

        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Sound Effect
                MediaPlayer buttonPlayer = MediaPlayer.create(getBaseContext(), R.raw.effect_btn_long);
                buttonPlayer.start();

                //Check Zero
                checkZero();


            }   //event
        });

    }   // buttonController

    private void checkZero() {

        if (radioAnInt == 0) {

            Toast.makeText(MainActivity.this, "กรุณาตอบคำถาม เว้ยเห้ย", Toast.LENGTH_SHORT).show();

        } else {


            //Check Score
            checkScore();


            //Check Times
            checkTimes();


        }   //if

    }   // checkZero

    private void checkScore() {

        int trueAnswer[] = new int[]{1, 2, 3, 1, 2, 3, 1, 2, 4, 4};
        int intUser[] = new int[10];
        intUser[indexAnInt] = radioAnInt;

        if (intUser[indexAnInt] == trueAnswer[indexAnInt]) {
            scoreAnInt += 1;
        }

        choiceRadioGroup.clearCheck();

    }   // checkScore

    private void checkTimes() {

        if (indexAnInt == 9) {

            //Intent to ShowScore
            intentToShowScore();

        } else {

            //Increase indexAnInt
            indexAnInt += 1;

            //Controller Call View
            questionTextView.setText(Integer.toString(indexAnInt + 1) + ". What is this ?");

            //Controller Call Model
            objSsrUmodel.setButtonAnInt(indexAnInt);


        }

    }   // checkTimes

    private void intentToShowScore() {

        Intent objIntent = new Intent(MainActivity.this, ShowScoreActivity.class);
        objIntent.putExtra("Score", scoreAnInt);
        startActivity(objIntent);
        finish();
    }

    private void bindWidget() {

        questionTextView = (TextView) findViewById(R.id.textView2);
        ishiharaImageView = (ImageView) findViewById(R.id.imageView);
        choiceRadioGroup = (RadioGroup) findViewById(R.id.ragChoice);
        choice1RadioButton = (RadioButton) findViewById(R.id.radioButton);
        choice2RadioButton = (RadioButton) findViewById(R.id.radioButton2);
        choice3RadioButton = (RadioButton) findViewById(R.id.radioButton3);
        choice4RadioButton = (RadioButton) findViewById(R.id.radioButton4);
        answerButton = (Button) findViewById(R.id.button);

    }   //bind Widget

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}   // Main Class นี่คือ คลาสหลัก เว้ยเห้ย
