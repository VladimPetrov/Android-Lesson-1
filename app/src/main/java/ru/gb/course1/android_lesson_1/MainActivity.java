package ru.gb.course1.android_lesson_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    static String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
            "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango",
            "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple",
            "pumpkin", "potato"};
    static String searchWord;
    static int attemptNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initParametrs();
    }

    public static String findChar(String searchWord, String modelWord) {
        String shadowStr = new String();
        int minLength;

        if (searchWord.length() <= modelWord.length()) {
            minLength = searchWord.length();
        } else {
            minLength = modelWord.length();
        }
        for (int i = 0; i < minLength; i++) {
            if (searchWord.charAt(i) == modelWord.charAt(i)) {
                shadowStr += searchWord.charAt(i);
            } else {
                shadowStr += '#';
            }
        }
        for (int i = minLength; i < 15; i++) {
            shadowStr += '#';
        }
        return shadowStr;
    }

    public String arrayToStr(String[] strArray) {
        String str = "";
        for (String elem : strArray) {
            str += elem + "  ";
        }
        return str;
    }

    public void initParametrs() {
        Random rand = new Random();
        searchWord = words[rand.nextInt(words.length)];
        final Button buttonStart = findViewById(R.id.buttonStart);
        final EditText editTextPlayerWord = findViewById(R.id.editTextPlayerWord);
        final EditText editTextFindChar = findViewById(R.id.editTextFindChar);
        final TextView textHead = findViewById(R.id.textHead);
        final TextView textListWords = findViewById(R.id.textListWords);
        final TextView textHint = findViewById(R.id.textHint);
        final CheckBox checkBox = findViewById(R.id.checkBox);

        textHead.setText("Попытка №" + String.valueOf(attemptNumber));
        textListWords.setText(arrayToStr(words));

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptNumber++;
                textHead.setText("Попытка №" + String.valueOf(attemptNumber));
                if (searchWord.equals(editTextPlayerWord.getText().toString())) {
                    textHead.setText("Вы выйграли!!!!!");
                    textHead.setTextColor(Color.GREEN);
                    editTextFindChar.setText(editTextPlayerWord.getText());
                    return;
                } else {
                    editTextFindChar.setText(findChar(editTextPlayerWord.getText().toString(), searchWord));
                    editTextPlayerWord.setText("");
                }

            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    textHint.setText(searchWord);
                else {
                    textHint.setText("");
                }
            }
        });
    }
}