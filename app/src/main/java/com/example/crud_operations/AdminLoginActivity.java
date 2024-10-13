package com.example.crud_operations;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonAuthenticate;
    private ImageView buttonBack;

    private static final String PREFS_NAME = "AdminPrefs";
    private static final String KEY_IS_AUTHENTICATED = "is_authenticated";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        editTextUsername = findViewById(R.id.editText_username);
        editTextPassword = findViewById(R.id.editText_password);
        buttonAuthenticate = findViewById(R.id.button_authenticate);
        buttonBack = findViewById(R.id.icon_voltar);

        buttonAuthenticate.setOnClickListener(v -> authenticate());
        buttonBack.setOnClickListener(v -> {
            Intent intent = new Intent(AdminLoginActivity.this, ListActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void authenticate() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if ("ADMIN".equals(username) && "1234".equals(password)) {
            SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(KEY_IS_AUTHENTICATED, true);
            editor.apply();

            Toast.makeText(this, "Administrador conectado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AdminLoginActivity.this, ListActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Credenciais incorretas", Toast.LENGTH_SHORT).show();
        }
    }
}
