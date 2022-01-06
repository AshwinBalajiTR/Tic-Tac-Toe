package com.example.ttt;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView pa,pb,playerStatus;
    private Button reset;
    private Button[] buttons= new Button[9];
    private int paCount,pbCount,rCount;
    boolean activePlayer;
    int [] gameState={2,2,2,2,2,2,2,2,2};
    int[][] win={
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pa=findViewById(R.id.sa);
        pb=findViewById(R.id.sb);
        playerStatus=findViewById(R.id.t1);
        reset=findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rCount=0;
                activePlayer=true;
                for(int i=0;i<buttons.length;i++) {
                    gameState[i] = 2;
                    buttons[i].setText("");
                }
            }
        });
        for(int i=0;i<buttons.length;i++){
            String bId="b"+i;
            int resourceId=getResources().getIdentifier(bId,"id",getPackageName());
            buttons[i]=findViewById(resourceId);
            buttons[i].setOnClickListener(this);
        }
        activePlayer=true;
        rCount=0;
        paCount=0;
        pbCount=0;
    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals("")){
            return;
        }
        String buttonId=v.getResources().getResourceEntryName(v.getId());
        int gsPointer = Integer.parseInt(buttonId.substring(buttonId.length()-1,buttonId.length()));
        if(activePlayer){
            ((Button) v).setText("X");
            ((Button) v).setTextColor(Color.parseColor("#FFC34A"));
            gameState[gsPointer]=0;
        }
        else {
            ((Button) v).setText("O");
            ((Button) v).setTextColor(Color.parseColor("#70FFEA"));
            gameState[gsPointer]=1;
        }
        rCount=rCount+1;
        if(checkWinner()){
            if(activePlayer){
                paCount++;
                updateScore();
                Toast.makeText(this, "Player A Won !!!", Toast.LENGTH_SHORT).show();
                play();
            }
            else{
                pbCount++;
                updateScore();
                Toast.makeText(this, "Player B Won !!!", Toast.LENGTH_SHORT).show();
                play();
            }
        }
        else if(rCount==9){
            play();
            Toast.makeText(this, "No winner !", Toast.LENGTH_SHORT).show();
        }
        else{
            activePlayer = !activePlayer;
        }
    }
    public boolean checkWinner(){
        boolean wins = false;
        for(int[] wps : win){
            if(gameState[wps[0]]==gameState[wps[1]] &&
                    gameState[wps[1]]==gameState[wps[2]] &&
                    gameState[wps[0]]!=2){
                wins=true;
            }
        }
        return wins;
    }
    public void updateScore(){
        pa.setText(Integer.toString(paCount));
        pb.setText(Integer.toString(pbCount));
    }
    public void play(){
        rCount=0;
        activePlayer=true;
        for(int i=0;i<buttons.length;i++){
            gameState[i]=2;
            buttons[i].setText("");
        }

    }
}