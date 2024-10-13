package com.example.crud_operations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.crud_operations.BDHelper.ProdutosBD;
import com.example.crud_operations.model.Produtos;

import java.util.ArrayList;
import java.util.Arrays;

public class FormActivity extends AppCompatActivity {

    EditText editText_nome, editText_marca, editText_preco, editText_localizacao;
    Spinner spinner_tipo;
    Button btn_polimorfismo;
    Produtos editarProduto, produto;
    ProdutosBD bdHelper;
    String tipoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        View clickableArea = findViewById(R.id.icon_voltar);
        clickableArea.setOnClickListener(view -> {
            Intent intent = new Intent(FormActivity.this, ListActivity.class);
            startActivity(intent);
            finish();
        });

        produto = new Produtos();
        bdHelper = new ProdutosBD(FormActivity.this);

        Intent intent = getIntent();
        editarProduto = (Produtos) intent.getSerializableExtra("produto-escolhido");

        editText_nome = findViewById(R.id.editText_nome);
        editText_marca = findViewById(R.id.editText_marca);
        editText_preco = findViewById(R.id.editText_preco);
        editText_localizacao = findViewById(R.id.editText_localizacao);
        spinner_tipo = findViewById(R.id.spinner_tipo);

        btn_polimorfismo = findViewById(R.id.btn_polimorfismo);

        final ArrayList<String> tipos = new ArrayList<>(Arrays.asList(
                "Tipo",
                "Carne e frutos do mar",
                "Bebidas alcoólicas",
                "Casa",
                "Frios",
                "Frutas",
                "Higiene pessoal",
                "Laticínios",
                "Horti Fruti",
                "Não perecíveis",
                "Origem animal",
                "Padaria",
                "Produtos de limpeza",
                "Produtos para pets",
                "Refrigerantes",
                "Outros"
        ));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_tipo.setAdapter(adapter);

        spinner_tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipoSelecionado = tipos.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tipoSelecionado = null;
            }
        });

        if (editarProduto != null) {
            btn_polimorfismo.setText("Modificar");
            editText_nome.setText(editarProduto.getNome());
            editText_marca.setText(editarProduto.getMarca());
            editText_preco.setText(String.valueOf(editarProduto.getPreco()));
            editText_localizacao.setText(editarProduto.getLocalizacao());

            int spinnerPosition = adapter.getPosition(editarProduto.getTipo());
            spinner_tipo.setSelection(spinnerPosition);
        } else {
            btn_polimorfismo.setText("Cadastrar");
        }

        btn_polimorfismo.setOnClickListener(view -> {
            produto.setNome(editText_nome.getText().toString());
            produto.setMarca(editText_marca.getText().toString());

            double preco = 0;
            try {
                preco = Double.parseDouble(editText_preco.getText().toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            produto.setPreco(preco);
            produto.setLocalizacao(editText_localizacao.getText().toString());
            produto.setTipo(tipoSelecionado);

            if (btn_polimorfismo.getText().toString().equals("Cadastrar")) {
                boolean success = bdHelper.salvarProduto(produto);
                if (success) {
                    Toast.makeText(FormActivity.this, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    clearFields();
                } else {
                    Toast.makeText(FormActivity.this, "Falha ao cadastrar o produto.", Toast.LENGTH_SHORT).show();
                }
            } else {
                produto.setId(editarProduto.getId());
                boolean success = bdHelper.alterarProduto(produto);
                if (success) {
                    Toast.makeText(FormActivity.this, "Produto modificado com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FormActivity.this, "Falha ao modificar o produto.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clearFields() {
        editText_nome.setText("");
        editText_marca.setText("");
        editText_preco.setText("");
        editText_localizacao.setText("");
        spinner_tipo.setSelection(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bdHelper != null) {
            bdHelper.close();
        }
    }
}
