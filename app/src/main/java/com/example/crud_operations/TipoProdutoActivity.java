package com.example.crud_operations;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crud_operations.model.TipoProduto;

import java.util.ArrayList;
import java.util.List;

public class TipoProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_produto);

        ListView listViewTipoProduto = findViewById(R.id.listView_tipo_produto);

        List<TipoProduto> tipos = new ArrayList<>();
        tipos.add(new TipoProduto("Açougue", R.drawable.icon_acougue));
        tipos.add(new TipoProduto("Bebidas alcoólicas", R.drawable.icon_bebidas_alcoolicas));
        tipos.add(new TipoProduto("Casa", R.drawable.icon_casa));
        tipos.add(new TipoProduto("Frios", R.drawable.icon_frios));
        tipos.add(new TipoProduto("Frutas", R.drawable.icon_frutas));
        tipos.add(new TipoProduto("Higiene pessoal", R.drawable.icon_higiene_pessoal));
        tipos.add(new TipoProduto("Laticínios", R.drawable.icon_laticinios));
        tipos.add(new TipoProduto("Horti-Fruti", R.drawable.icon_horti_fruti));
        tipos.add(new TipoProduto("Não perecíveis", R.drawable.icon_nao_pereciveis));
        tipos.add(new TipoProduto("Origem animal", R.drawable.icon_origem_animal));
        tipos.add(new TipoProduto("Padaria", R.drawable.icon_padaria));
        tipos.add(new TipoProduto("Produtos de limpeza", R.drawable.icon_produtos_limpeza));
        tipos.add(new TipoProduto("Produtos para pets", R.drawable.icon_produtos_pets));
        tipos.add(new TipoProduto("Refrigerantes", R.drawable.icon_refrigerantes));
        tipos.add(new TipoProduto("Outros", R.drawable.icon_outros));

        TipoProdutoAdapter adapter = new TipoProdutoAdapter(this, tipos);
        listViewTipoProduto.setAdapter(adapter);
    }
}
