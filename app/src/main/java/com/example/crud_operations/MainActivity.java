package com.example.crud_operations;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crud_operations.BDHelper.ListaProdutosBD;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "AdminPrefs";
    private static final String KEY_IS_AUTHENTICATED = "is_authenticated";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View clickableArea = findViewById(R.id.background_layout);
        clickableArea.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListActivity.class);
            startActivity(intent);
        });

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        ListaProdutosBD listaProdutosBD = new ListaProdutosBD(this);
        listaProdutosBD.resetDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Remove a autenticação do admin quando voltar para MainActivity
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(KEY_IS_AUTHENTICATED);
        editor.apply();
    }
}
