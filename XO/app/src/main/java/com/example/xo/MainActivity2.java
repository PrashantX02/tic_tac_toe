package com.example.xo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    TextView btn1;
    TextView btn2;
    TextView btn3;
    TextView btn4;
    TextView btn5;
    TextView btn6;
    TextView btn7;
    TextView btn8;
    TextView btn9;
    TextView player;

    TextView markX;
    TextView markY;

    CardView cardView_1;
    CardView cardView_2;
    CardView cardView_3;
    CardView cardView_4;
    CardView cardView_5;
    CardView cardView_6;
    CardView cardView_7;
    CardView cardView_8;
    CardView cardView_9;
    CardView topdashX;
    CardView topdashY;

    int count = 0 ;

    int move = 0;

    int marksX;
    int marksY;

    Animation animation ;

    boolean winnerDeclared;

    TextView x;
    TextView o;

    TextView player_x;
    TextView player_o;

    Handler handler;

    int red = R.color.red;
    int white = R.color.white;
    int black = R.color.black;
    int green = R.color.green;
    int blue = R.color.blue;
    int backgroundColorRED ;
    int backgroundColorWHITE ;

    ImageView exit;
    int black_back ;
    int green_back ;
    int blue_back ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


         backgroundColorRED = ContextCompat.getColor(MainActivity2.this,red);
         backgroundColorWHITE = ContextCompat.getColor(MainActivity2.this,white);
         black_back = ContextCompat.getColor(MainActivity2.this,black);
         green_back = ContextCompat.getColor(MainActivity2.this,green);
         blue_back = ContextCompat.getColor(MainActivity2.this,blue);

         player_x = findViewById(R.id.player_x);
         player_o = findViewById(R.id.player_o);

         Intent intent = getIntent();
         String s1 = intent.getStringExtra("keyo").toString();
         String s2 = intent.getStringExtra("keyx").toString();
         if(s1.length()>10) s1 = s1.substring(0,10);
         if(s2.length()>10) s2 = s2.substring(0,10);
         player_x.setText(s1);
         player_o.setText(s2);

        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);

        exit  = findViewById(R.id.exit_top_button);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
              builder.setTitle("Exit ?");
              builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      finish();
                  }
              });
              builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      dialog.dismiss();
                  }
              });
              AlertDialog dialog = builder.create();
              dialog.show();
            }
        });




        // declaring a cardView
         cardView_1 = findViewById(R.id.cardView_1);
         cardView_2 = findViewById(R.id.cardView_2);
         cardView_3  = findViewById(R.id.cardView_3);
         cardView_4 = findViewById(R.id.cardView_4);
         cardView_5 = findViewById(R.id.cardView_5);
         cardView_6 = findViewById(R.id.cardView_6);
         cardView_7 = findViewById(R.id.cardView_7);
         cardView_8 = findViewById(R.id.cardView_8);
         cardView_9 = findViewById(R.id.cardView_9);

         markX = findViewById(R.id.marks_X);
         markY = findViewById(R.id.marks_Y);

         // dash bording;

        topdashX = findViewById(R.id.cardViewTopLeft);
        topdashY = findViewById(R.id.cardViewTopRight);

     //   player = findViewById(R.id.player);
        animation = AnimationUtils.loadAnimation(MainActivity2.this, R.anim.fade_out);


        x = findViewById(R.id.x);
        o = findViewById(R.id.big_o);


        ImageView restart = findViewById(R.id.reset);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartDialog();
                setBubbleBounceEffect(v);
            }
        });

    }

    public void check(View view){

        TextView button = (TextView) view;

        setBubbleBounceEffect(view);

        if(button.getText().toString().equals("") && !winnerDeclared){
            count++;
            if(move == 0){
                button.setText("X");

                topdashX.setCardBackgroundColor(backgroundColorWHITE);
                x.setTextColor(black_back);

                topdashY.setCardBackgroundColor(backgroundColorRED);
                o.setTextColor(backgroundColorWHITE);
               // setBubbleBounceEffect(o);
                move = 1;
            }else{
                button.setText("O");
                topdashX.setCardBackgroundColor(backgroundColorRED);
                x.setTextColor(backgroundColorWHITE);
                //setBubbleBounceEffect(x);
                topdashY.setCardBackgroundColor(backgroundColorWHITE);
                o.setTextColor(black_back);
                move = 0;
            }


            if(count>4) isWinnerOrNot();
            if(count>=9 && !winnerDeclared){
                instantRestart();
            }
        }


    }

    public void isWinnerOrNot(){
        String one = btn1.getText().toString();
        String two = btn2.getText().toString();
        String three = btn3.getText().toString();
        String four = btn4.getText().toString();
        String five = btn5.getText().toString();
        String six = btn6.getText().toString();
        String seven = btn7.getText().toString();
        String eight = btn8.getText().toString();
        String nine = btn9.getText().toString();

        if(!one.equals("") && one.equals(two) && two.equals(three)){  // 1,2,3
            cardView_1.setCardBackgroundColor(green_back);
            cardView_2.setCardBackgroundColor(green_back);
            cardView_3.setCardBackgroundColor(green_back);

            setBubbleBounceEffectLong(btn1);
            setBubbleBounceEffectLong(btn2);
            setBubbleBounceEffectLong(btn3);

            setBubbleBounceEffectLong(btn1);
            setBubbleBounceEffectLong(btn2);
            setBubbleBounceEffectLong(btn3);

            if(one.equals("X")){
                marksX++;
                setBubbleBounceEffect(markX);
                markX.setText(String.valueOf(marksX));
            }
            if(one.equals("O")){
                marksY++;
                setBubbleBounceEffect(markY);
                markY.setText(String.valueOf(marksY));
            }

            cardView_4.startAnimation(animation);
            cardView_4.setVisibility(View.INVISIBLE);

            cardView_5.startAnimation(animation);
            cardView_5.setVisibility(View.INVISIBLE);

            cardView_6.startAnimation(animation);
            cardView_6.setVisibility(View.INVISIBLE);

            cardView_7.startAnimation(animation);
            cardView_7.setVisibility(View.INVISIBLE);

            cardView_8.startAnimation(animation);
            cardView_8.setVisibility(View.INVISIBLE);

            cardView_9.startAnimation(animation);
            cardView_9.setVisibility(View.INVISIBLE);

            winnerDeclared = true;

            reset();


        }else if(!four.equals("") && five.equals(four) && five.equals(six)){ // 4,5,6

            cardView_4.setCardBackgroundColor(green_back);
            cardView_5.setCardBackgroundColor(green_back);
            cardView_6.setCardBackgroundColor(green_back);

            setBubbleBounceEffectLong(btn4);
            setBubbleBounceEffectLong(btn5);
            setBubbleBounceEffectLong(btn6);

            if(four.equals("X")) {
                marksX++;
                markX.setText(String.valueOf(marksX));
            }
            if(four.equals("O")){
                marksY++;
                markY.setText(String.valueOf(marksY));
            }

            cardView_1.startAnimation(animation);
            cardView_1.setVisibility(View.INVISIBLE);

            cardView_2.startAnimation(animation);
            cardView_2.setVisibility(View.INVISIBLE);

            cardView_3.startAnimation(animation);
            cardView_3.setVisibility(View.INVISIBLE);

            cardView_7.startAnimation(animation);
            cardView_7.setVisibility(View.INVISIBLE);

            cardView_8.startAnimation(animation);
            cardView_8.setVisibility(View.INVISIBLE);

            cardView_9.startAnimation(animation);
            cardView_9.setVisibility(View.INVISIBLE);

            winnerDeclared = true;

            reset();
        }
        else if(!seven.equals("") && seven.equals(eight) && eight.equals(nine)){ //7,8,9
            cardView_7.setCardBackgroundColor(green_back);
            cardView_8.setCardBackgroundColor(green_back);
            cardView_9.setCardBackgroundColor(green_back);


            setBubbleBounceEffectLong(btn7);
            setBubbleBounceEffectLong(btn8);
            setBubbleBounceEffectLong(btn9);

           if(seven.equals("X")){
               marksX++;
               markX.setText(String.valueOf(marksX));
           }
           if(seven.equals("O")){
               marksY++;
               markY.setText(String.valueOf(marksY));
           }

            cardView_1.startAnimation(animation);
            cardView_1.setVisibility(View.INVISIBLE);

            cardView_2.startAnimation(animation);
            cardView_2.setVisibility(View.INVISIBLE);

            cardView_3.startAnimation(animation);
            cardView_3.setVisibility(View.INVISIBLE);

            cardView_4.startAnimation(animation);
            cardView_4.setVisibility(View.INVISIBLE);

            cardView_5.startAnimation(animation);
            cardView_5.setVisibility(View.INVISIBLE);

            cardView_6.startAnimation(animation);
            cardView_6.setVisibility(View.INVISIBLE);
            winnerDeclared = true;

            reset();
        }

        else if(!one.equals("") && one.equals(four) && four.equals(seven)){  //1,4,7

            cardView_1.setCardBackgroundColor(green_back);
            cardView_4.setCardBackgroundColor(green_back);
            cardView_7.setCardBackgroundColor(green_back);

            setBubbleBounceEffectLong(btn1);
            setBubbleBounceEffectLong(btn4);
            setBubbleBounceEffectLong(btn7);

            if(one.equals("X")){
                marksX++;
                markX.setText(String.valueOf(marksX));
            }
            if(one.equals("O")){
                marksY++;
                markY.setText(String.valueOf(marksY));
            }

            cardView_2.startAnimation(animation);
            cardView_2.setVisibility(View.INVISIBLE);

            cardView_3.startAnimation(animation);
            cardView_3.setVisibility(View.INVISIBLE);

            cardView_5.startAnimation(animation);
            cardView_5.setVisibility(View.INVISIBLE);

            cardView_6.startAnimation(animation);
            cardView_6.setVisibility(View.INVISIBLE);

            cardView_8.startAnimation(animation);
            cardView_8.setVisibility(View.INVISIBLE);

            cardView_9.startAnimation(animation);
            cardView_9.setVisibility(View.INVISIBLE);
            winnerDeclared = true;

            reset();


        }
        else if(!two.equals("") && two.equals(five) && five.equals(eight)){  //2/5/8

            cardView_2.setCardBackgroundColor(green_back);
            cardView_5.setCardBackgroundColor(green_back);
            cardView_8.setCardBackgroundColor(green_back);

            setBubbleBounceEffectLong(btn2);
            setBubbleBounceEffectLong(btn5);
            setBubbleBounceEffectLong(btn8);

            if(two.equals("X")){
                marksX++;
                markX.setText(String.valueOf(marksX));
            }
            if(two.equals("O")){
                marksY++;
                markY.setText(String.valueOf(marksY));
            }
            cardView_1.startAnimation(animation);
            cardView_1.setVisibility(View.INVISIBLE);

            cardView_4.startAnimation(animation);
            cardView_4.setVisibility(View.INVISIBLE);

            cardView_7.startAnimation(animation);
            cardView_7.setVisibility(View.INVISIBLE);

            cardView_3.startAnimation(animation);
            cardView_3.setVisibility(View.INVISIBLE);

            cardView_6.startAnimation(animation);
            cardView_6.setVisibility(View.INVISIBLE);

            cardView_9.startAnimation(animation);
            cardView_9.setVisibility(View.INVISIBLE);

            winnerDeclared = true;
            reset();
        }
        else if(!three.equals("") && three.equals(six) && six.equals(nine)){ //3,6,9

            cardView_3.setCardBackgroundColor(green_back);
            cardView_6.setCardBackgroundColor(green_back);
            cardView_9.setCardBackgroundColor(green_back);

            setBubbleBounceEffectLong(btn3);
            setBubbleBounceEffectLong(btn6);
            setBubbleBounceEffectLong(btn9);

            if(three.equals("X")){
                marksX++;
                markX.setText(String.valueOf(marksX));
            }
            if(three.equals("O")){
                marksY++;
                markY.setText(String.valueOf(marksY));
            }

            cardView_1.startAnimation(animation);
            cardView_1.setVisibility(View.INVISIBLE);

            cardView_2.startAnimation(animation);
            cardView_2.setVisibility(View.INVISIBLE);

            cardView_4.startAnimation(animation);
            cardView_4.setVisibility(View.INVISIBLE);

            cardView_5.startAnimation(animation);
            cardView_5.setVisibility(View.INVISIBLE);

            cardView_7.startAnimation(animation);
            cardView_7.setVisibility(View.INVISIBLE);

            cardView_8.startAnimation(animation);
            cardView_8.setVisibility(View.INVISIBLE);

            winnerDeclared = true;
            reset();
        }

        else if(!one.equals("") && one.equals(five) && five.equals(nine)){   //1,5,9

            cardView_1.setCardBackgroundColor(green_back);
            cardView_5.setCardBackgroundColor(green_back);
            cardView_9.setCardBackgroundColor(green_back);

            setBubbleBounceEffectLong(btn1);
            setBubbleBounceEffectLong(btn5);
            setBubbleBounceEffectLong(btn9);

            if(one.equals("X")){
                marksX++;
               // setBubbleBounceEffectC(markX);
                markX.setText(String.valueOf(marksX));
            }
            if(one.equals("O")){
                marksY++;

               // setBubbleBounceEffectC(markY);
                markY.setText(String.valueOf(marksY));
            }

            cardView_2.startAnimation(animation);
            cardView_2.setVisibility(View.INVISIBLE);

            cardView_3.startAnimation(animation);
            cardView_3.setVisibility(View.INVISIBLE);

            cardView_6.startAnimation(animation);
            cardView_6.setVisibility(View.INVISIBLE);

            cardView_4.startAnimation(animation);
            cardView_4.setVisibility(View.INVISIBLE);

            cardView_7.startAnimation(animation);
            cardView_7.setVisibility(View.INVISIBLE);

            cardView_8.startAnimation(animation);
            cardView_8.setVisibility(View.INVISIBLE);
            winnerDeclared = true;

            reset();
        }
        else if(!three.equals("") && three.equals(five) && three.equals(seven)){ //3,5,7

            cardView_3.setCardBackgroundColor(green_back);
            cardView_5.setCardBackgroundColor(green_back);
            cardView_7.setCardBackgroundColor(green_back);

            setBubbleBounceEffectLong(btn3);
            setBubbleBounceEffectLong(btn5);
            setBubbleBounceEffectLong(btn7);

            if(three.equals("X")){
                marksX++;
                setBubbleBounceEffectC(markX);
                markX.setText(String.valueOf(marksX));
            }
            if(three.equals("O")){
                marksY++;
                setBubbleBounceEffectC(markY);
                markY.setText(String.valueOf(marksY));
            }

            cardView_2.startAnimation(animation);
            cardView_2.setVisibility(View.INVISIBLE);

            cardView_1.startAnimation(animation);
            cardView_1.setVisibility(View.INVISIBLE);

            cardView_4.startAnimation(animation);
            cardView_4.setVisibility(View.INVISIBLE);

            cardView_6.startAnimation(animation);
            cardView_6.setVisibility(View.INVISIBLE);

            cardView_8.startAnimation(animation);
            cardView_8.setVisibility(View.INVISIBLE);

            cardView_9.startAnimation(animation);
            cardView_9.setVisibility(View.INVISIBLE);
            winnerDeclared = true;
            reset();
        }
    }

    public void reset(){


        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                btn1.setText("");
                btn2.setText("");
                btn3.setText("");
                btn4.setText("");
                btn5.setText("");
                btn6.setText("");
                btn7.setText("");
                btn8.setText("");
                btn9.setText("");

                count = 0;
                move = 0;

                topdashX.setCardBackgroundColor(backgroundColorRED);
                topdashY.setCardBackgroundColor(backgroundColorWHITE);

                x.setTextColor(backgroundColorWHITE);
                o.setTextColor(black_back);


                cardView_1.setVisibility(View.VISIBLE);
                cardView_2.setVisibility(View.VISIBLE);
                cardView_3.setVisibility(View.VISIBLE);
                cardView_4.setVisibility(View.VISIBLE);
                cardView_5.setVisibility(View.VISIBLE);
                cardView_6.setVisibility(View.VISIBLE);
                cardView_7.setVisibility(View.VISIBLE);
                cardView_8.setVisibility(View.VISIBLE);
                cardView_9.setVisibility(View.VISIBLE);

                cardView_1.setCardBackgroundColor(blue_back);
                cardView_2.setCardBackgroundColor(blue_back);
                cardView_3.setCardBackgroundColor(blue_back);
                cardView_4.setCardBackgroundColor(blue_back);
                cardView_5.setCardBackgroundColor(blue_back);
                cardView_6.setCardBackgroundColor(blue_back);
                cardView_7.setCardBackgroundColor(blue_back);
                cardView_8.setCardBackgroundColor(blue_back);
                cardView_9.setCardBackgroundColor(blue_back);

                winnerDeclared = false;
            }
        },1400);
    }

    public void instantRestart(){
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");

        winnerDeclared = false;
        count = 0;
        move = 0;
       // player.setText("Player : X");

        cardView_1.setVisibility(View.VISIBLE);
        cardView_2.setVisibility(View.VISIBLE);
        cardView_3.setVisibility(View.VISIBLE);
        cardView_4.setVisibility(View.VISIBLE);
        cardView_5.setVisibility(View.VISIBLE);
        cardView_6.setVisibility(View.VISIBLE);
        cardView_7.setVisibility(View.VISIBLE);
        cardView_8.setVisibility(View.VISIBLE);
        cardView_9.setVisibility(View.VISIBLE);


        cardView_1.setCardBackgroundColor(blue_back);
        cardView_2.setCardBackgroundColor(blue_back);
        cardView_3.setCardBackgroundColor(blue_back);
        cardView_4.setCardBackgroundColor(blue_back);
        cardView_5.setCardBackgroundColor(blue_back);
        cardView_6.setCardBackgroundColor(blue_back);
        cardView_7.setCardBackgroundColor(blue_back);
        cardView_8.setCardBackgroundColor(blue_back);
        cardView_9.setCardBackgroundColor(blue_back);

        topdashX.setCardBackgroundColor(backgroundColorRED);
        topdashY.setCardBackgroundColor(backgroundColorWHITE);

        x.setTextColor(backgroundColorWHITE);
        o.setTextColor(black_back);
    }

    public void restartDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Restart ?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                instantRestart();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
               dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void New_game(View view){
        setBubbleBounceEffect(view);
        TextView newGame = (TextView) view;

       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setTitle("New Game ?");
       builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               new_game_restart();
           }
       });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
       AlertDialog dialog = builder.create();
       dialog.show();
    }

    public void new_game_restart(){
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");



        winnerDeclared = false;
        count = 0;
        move = 0;
        // player.setText("Player : X");

        marksY = 0;
        marksX = 0;

        markX.setText(String.valueOf(marksX));
        markY.setText(String.valueOf(marksY));

        cardView_1.setVisibility(View.VISIBLE);
        cardView_2.setVisibility(View.VISIBLE);
        cardView_3.setVisibility(View.VISIBLE);
        cardView_4.setVisibility(View.VISIBLE);
        cardView_5.setVisibility(View.VISIBLE);
        cardView_6.setVisibility(View.VISIBLE);
        cardView_7.setVisibility(View.VISIBLE);
        cardView_8.setVisibility(View.VISIBLE);
        cardView_9.setVisibility(View.VISIBLE);


        cardView_1.setCardBackgroundColor(blue_back);
        cardView_2.setCardBackgroundColor(blue_back);
        cardView_3.setCardBackgroundColor(blue_back);
        cardView_4.setCardBackgroundColor(blue_back);
        cardView_5.setCardBackgroundColor(blue_back);
        cardView_6.setCardBackgroundColor(blue_back);
        cardView_7.setCardBackgroundColor(blue_back);
        cardView_8.setCardBackgroundColor(blue_back);
        cardView_9.setCardBackgroundColor(blue_back);

        topdashX.setCardBackgroundColor(backgroundColorRED);
        topdashY.setCardBackgroundColor(backgroundColorWHITE);

        x.setTextColor(backgroundColorWHITE);
        o.setTextColor(black_back);
    }




    public void setBubbleBounceEffect(View view) {
        // Create a ValueAnimator for "tap in" animation

        //TextView cardView = (TextView) view;
        ValueAnimator tapInAnimator = ValueAnimator.ofFloat(1.0f, 1.2f);
        tapInAnimator.setDuration(230);
        tapInAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = (float) animation.getAnimatedValue();
                view.setScaleX(scale);
                view.setScaleY(scale);
            }
        });

// Create a ValueAnimator for "tap out" animation
        ValueAnimator tapOutAnimator = ValueAnimator.ofFloat(1.2f, 1.0f);
        tapOutAnimator.setDuration(330);
        tapOutAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = (float) animation.getAnimatedValue();
                view.setScaleX(scale);
                view.setScaleY(scale);
            }

        });

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(tapInAnimator, tapOutAnimator);
        animatorSet.start();
    }

    public void setBubbleBounceEffectLong(View view) {
        // Create a ValueAnimator for "tap in" animation

        TextView txt = (TextView) view;
        ValueAnimator tapInAnimator = ValueAnimator.ofFloat(1.0f, 1.2f);
        tapInAnimator.setDuration(230);
        tapInAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = (float) animation.getAnimatedValue();
                txt.setScaleX(scale);
                txt.setScaleY(scale);
            }
        });

// Create a ValueAnimator for "tap out" animation
        ValueAnimator tapOutAnimator = ValueAnimator.ofFloat(1.2f, 1.0f);
        tapOutAnimator.setDuration(730);
        tapOutAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = (float) animation.getAnimatedValue();
                txt.setScaleX(scale);
                txt.setScaleY(scale);
            }

        });

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(tapInAnimator, tapOutAnimator);
        animatorSet.start();
    }

    public void setBubbleBounceEffectC(View view) {
        // Create a ValueAnimator for "tap in" animation

        TextView txt = (TextView) view;
        ValueAnimator tapInAnimator = ValueAnimator.ofFloat(1.0f, 1.2f);
        tapInAnimator.setDuration(230);
        tapInAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = (float) animation.getAnimatedValue();
                txt.setScaleX(scale);
                txt.setScaleY(scale);
            }
        });

// Create a ValueAnimator for "tap out" animation
        ValueAnimator tapOutAnimator = ValueAnimator.ofFloat(1.2f, 1.0f);
        tapOutAnimator.setDuration(730);
        tapOutAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = (float) animation.getAnimatedValue();
                txt.setScaleX(scale);
                txt.setScaleY(scale);
            }

        });

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(tapInAnimator, tapOutAnimator);
        animatorSet.start();
    }
}