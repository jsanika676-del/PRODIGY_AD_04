package com.example.tictactoe;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button[] buttons = new Button[9];
    boolean playerX = true;
    int moveCount = 0;
    TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tvStatus);

        int[] ids = {R.id.b1,R.id.b2,R.id.b3,R.id.b4,R.id.b5,
                R.id.b6,R.id.b7,R.id.b8,R.id.b9};

        for (int i = 0; i < 9; i++) {
            buttons[i] = findViewById(ids[i]);
            buttons[i].setOnClickListener(this::buttonClick);
        }

        findViewById(R.id.btnReset).setOnClickListener(v -> resetGame());
    }

    public void buttonClick(View view) {
        Button btn = (Button) view;

        if (!btn.getText().toString().equals("")) return;

        btn.setText(playerX ? "X" : "O");
        moveCount++;

        if (checkWinner()) {
            Toast.makeText(this,
                    "Player " + (playerX ? "X" : "O") + " Wins!",
                    Toast.LENGTH_LONG).show();
            disableButtons();
            return;
        }

        if (moveCount == 9) {
            Toast.makeText(this, "Match Draw", Toast.LENGTH_LONG).show();
            return;
        }

        playerX = !playerX;
        tvStatus.setText("Player " + (playerX ? "X" : "O") + " Turn");
    }

    private boolean checkWinner() {
        int[][] winPos = {
                {0,1,2},{3,4,5},{6,7,8},
                {0,3,6},{1,4,7},{2,5,8},
                {0,4,8},{2,4,6}
        };

        for (int[] pos : winPos) {
            String a = buttons[pos[0]].getText().toString();
            String b = buttons[pos[1]].getText().toString();
            String c = buttons[pos[2]].getText().toString();

            if (!a.equals("") && a.equals(b) && b.equals(c)) {
                return true;
            }
        }
        return false;
    }

    private void disableButtons() {
        for (Button b : buttons) {
            b.setEnabled(false);
        }
    }

    private void resetGame() {
        for (Button b : buttons) {
            b.setText("");
            b.setEnabled(true);
        }
        playerX = true;
        moveCount = 0;
        tvStatus.setText("Player X Turn");
    }
}