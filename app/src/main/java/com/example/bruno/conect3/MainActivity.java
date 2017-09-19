package com.example.bruno.conect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int player = 0;
    //boolean gameIsActive = true;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPosition = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    //player: 0 to red, 1 to yellow

    public void playAgain(View view){
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        player = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }


    public void dropIn(View view){
        ImageView piece = (ImageView) view;
        int tappedCounter = Integer.parseInt(piece.getTag().toString());
        if (gameState[tappedCounter] == 2) {
            piece.setTranslationY(-1000f);
            gameState[tappedCounter] = player;
            if (player == 0) {
                piece.setImageResource(R.drawable.red);
                player = 1;
            } else {
                piece.setImageResource(R.drawable.yellow);
                player = 0;

            }
        }
        piece.animate().translationYBy(1000f).setDuration(2000);
        for (int[] winningPositions : winningPosition) {
            if  (gameState[winningPositions[0]] == gameState[winningPositions[1]] &&
                    gameState[winningPositions[1]] == gameState[winningPositions[2]] &&
                    gameState[winningPositions[0]] != 2) {
                String winner = "Yellow";
                if (gameState[winningPositions[0]] == 0) {
                    winner = "Red";
                }
                //Someone won!
                TextView winnerMessage = (TextView) findViewById(R.id.playAgainText);
                winnerMessage.setText(winner + " has won!");
                LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                layout.setVisibility(View.VISIBLE);

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
