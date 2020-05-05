package com.najimaddinova.mementodesignpattern;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button saveButton, undoButton, redoButton;
    private EditText theArticle;
    private TextView saveText;

    Caretaker caretaker = new Caretaker();
    Originator originator = new Originator();
    int saveFiles = 0, currentArticle = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theArticle= findViewById(R.id.et_write_area);
        saveText = findViewById(R.id.txt_save_undo_redo);

        saveButton = findViewById(R.id.save);
        saveButton.setOnClickListener(this);
        undoButton = findViewById(R.id.undo);
        undoButton.setOnClickListener(this);
        redoButton = findViewById(R.id.redo);
        redoButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:{
                String writeText = theArticle.getText().toString();

                originator.set(writeText);
                caretaker.addMemento(originator.storeInMemento());
                currentArticle++;

                saveFiles++;
                undoButton.setEnabled(true);
                theArticle.getText().clear();

                saveText.setText(saveText.getText() + writeText);

            break;
            }
            case R.id.undo:{
                if(currentArticle > -1){
                    String textBoxString = originator.restoreFromMemento( caretaker.getMemento(currentArticle) );
                    currentArticle--;
                    theArticle.setText(textBoxString);
                    redoButton.setEnabled(true);
                } else {
                    undoButton.setEnabled(false);
                }
                break;
            }
            case R.id.redo:{
                if((saveFiles - 1) > currentArticle){
                    currentArticle++;
                    String textBoxString = originator.restoreFromMemento( caretaker.getMemento(currentArticle) );
                    theArticle.setText(textBoxString);
                    undoButton.setEnabled(true);
                } else {
                    redoButton.setEnabled(false);
                }
                break;
                }
            }
        }
    }