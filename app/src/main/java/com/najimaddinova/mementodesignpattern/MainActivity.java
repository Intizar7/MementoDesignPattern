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

                currentArticle++;
                originator.setArticle(writeText);
                caretaker.addMemento(originator.createMemento(), true);

                undoButton.setEnabled(true);
                theArticle.getText().clear();

                saveText.setText(saveText.getText() + writeText);

            break;
            }
            case R.id.undo:{
                if(currentArticle > 0){
                    currentArticle--;
                    originator.restore( caretaker.getMemento(currentArticle) );

                    theArticle.setText(originator.getArticle());
                    redoButton.setEnabled(true);

                    String tempSaveText = saveText.getText().toString();
                    int len = originator.getArticle().length();
                    saveText.setText(tempSaveText.substring(0, tempSaveText.length() - len));
                } else {
                    undoButton.setEnabled(false);
                }
                break;
            }
            case R.id.redo:{
                //if((saveFiles - 1) > currentArticle){
                    currentArticle++;

                    Memento memento = caretaker.getMemento(currentArticle);

                    if(memento == null){
                        currentArticle--;
                        return;
                    }

                    originator.restore( memento );
                    theArticle.setText(originator.getArticle());
                    saveText.setText(saveText.getText() + originator.getArticle());

                    undoButton.setEnabled(true);
                /*} else {
                    redoButton.setEnabled(false);
                }*/
                break;
                }
            }
        }
    }