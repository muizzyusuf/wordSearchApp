package com.example.wordsearch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

public class gameActivity extends AppCompatActivity {
    Instant start;
    public void setStart(Instant start) { this.start = start; }
    public Instant getStart() { return start; }

    int count;
    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }

    String currentWord;
    public String getCurrentWord() { return currentWord; }
    public void setCurrentWord(String currentWord) { this.currentWord = currentWord; }

    ArrayList<String> selected = new ArrayList<String>();
    public ArrayList<String> getSelected() { return selected; }

    String [] words = new String[]{"SWIFT", "KOTLIN", "OBJECTIVEC", "VARIABLE", "JAVA", "MOBILE","SHOPIFY","INTERN", "ANDROID", "APP", "SOFTWARE"};
    public String[] getWords() { return words; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        WordSearchGenerator wordSearch = new WordSearchGenerator(getWords(), 12);
        String[][] table = wordSearch.wordSearchTable;

        setStart(Instant.now());
        setCount(0);

        final TextView currWord = findViewById(R.id.currentWord);
        setCurrentWord(currWord.getText().toString());
        TableLayout grid = findViewById(R.id.table);

        for (int i=0; i < table.length; i++) {
            TableRow row = new TableRow(gameActivity.this);
            for (int j=0; j < table[i].length; j++) {
                final TextView tv = new TextView(gameActivity.this);
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                tv.setTag(Integer.toString(i)+Integer.toString(j));

                tv.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        tv.setBackgroundColor(0xFF89b7e5);
                        tv.getText();
                        setCurrentWord(currWord.getText().toString() + tv.getText().toString());
                        currWord.setText(getCurrentWord());
                        getSelected().add(tv.getTag().toString());
                    }
                });

                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.result_font));
                tv.setText(table[i][j]);
                row.addView(tv);
            }
            grid.addView(row);
        }

        TableLayout ans = findViewById(R.id.answers);
        String [][] answers = new String[][]{{"SWIFT", "KOTLIN", "OBJECTIVEC"},
                                            {"VARIABLE", "JAVA", "MOBILE"},
                                            {"INTERN", "ANDROID", "SHOPIFY"},
                                            {"APP", "SOFTWARE"}};

        for (int i=0; i < answers.length; i++) {
            TableRow row2 = new TableRow(gameActivity.this);
            for (int j=0; j < answers[i].length; j++) {
                TextView tv2 = new TextView(gameActivity.this);
                tv2.setGravity(Gravity.CENTER_HORIZONTAL);
                tv2.setTag(answers[i][j]);
                tv2.setText(answers[i][j]);
                row2.addView(tv2);
            }
            ans.addView(row2);
        }

    }

    public void cancel(View view) {
        setCurrentWord("");
        TextView currWord = findViewById(R.id.currentWord);
        currWord.setText("");

        TableLayout grid = findViewById(R.id.table);

        String tag = "";
        for(int i=0; i< getSelected().size(); i++){
            tag = getSelected().get(i);
            TextView tView = grid.findViewWithTag(tag);
            tView.setBackgroundColor(0x00000000);
        }

        getSelected().clear();
    }

    public void validate(View view) {
        String word = getCurrentWord();
        boolean contains = check(word);

        TableLayout grid = findViewById(R.id.table);
        String tag = "";

            if(contains) {
                int color = randColour();
                setCount(getCount()+1);
                for(int i=0; i< getSelected().size(); i++){
                    tag = getSelected().get(i);
                    TextView tView = grid.findViewWithTag(tag);
                    tView.setBackgroundColor(color);

                    TableLayout ans = findViewById(R.id.answers);
                    TextView tView2 = ans.findViewWithTag(getCurrentWord());
                    tView2.setPaintFlags(tView2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);



                    if(getCount() == getWords().length){
                        Instant finish = Instant.now();
                        long timeElapsed = Duration.between(getStart(), finish).getSeconds();
                        Intent intent= new Intent(this, end.class);
                        intent.putExtra("timeElapsed", String.valueOf(timeElapsed));
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }
            }else{
                for(int i=0; i< getSelected().size(); i++){
                    tag = getSelected().get(i);
                    TextView tView = grid.findViewWithTag(tag);
                    tView.setBackgroundColor(0x00000000);
                }
            }

        setCurrentWord("");
        TextView currWord = findViewById(R.id.currentWord);
        currWord.setText("");
        getSelected().clear();
    }

    public boolean check(String x){
        boolean contains = false;
        for(int i=0; i<getWords().length; i++){
            if(getWords()[i].equals(x)){
                contains = true;
            }
        }
        return contains;
    }

    public int randColour(){
        int [] colour = new int[]{0xFFFF9696, 0xFFFED18C, 0xFFcc7ec0, 0xFFC3EAB2, 0xFFefb569};
        Random rand = new Random();
        int randomNum = rand.nextInt((4 - 0) + 1) + 0;
        return colour[randomNum] ;
    }
}
