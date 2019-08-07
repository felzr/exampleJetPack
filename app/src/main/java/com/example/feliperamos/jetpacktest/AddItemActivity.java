package com.example.feliperamos.jetpacktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItemActivity extends AppCompatActivity {

    public static final String EXTRA = "com.example.android.wordlistsql.REPLY";
    EditText inputNome;
    Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        inputNome = findViewById(R.id.input_nome);
        btnSalvar = findViewById(R.id.btn_salvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(inputNome.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = inputNome.getText().toString();
                    replyIntent.putExtra(EXTRA, word);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
