package com.example.crud_operations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.crud_operations.model.TipoProduto;

import java.util.List;

public class TipoProdutoAdapter extends ArrayAdapter<TipoProduto> {

    public TipoProdutoAdapter(Context context, List<TipoProduto> tipos) {
        super(context, 0, tipos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_tipo, parent, false);
        }

        TipoProduto tipoProduto = getItem(position);

        ImageView imageViewIcon = convertView.findViewById(R.id.imageView_icon); // ID ajustado
        TextView textViewTipoProduto = convertView.findViewById(R.id.textView_tipo_produto); // ID ajustado

        if (tipoProduto != null) {
            textViewTipoProduto.setText(tipoProduto.getNome());
            imageViewIcon.setImageResource(tipoProduto.getIcone());
        }

        return convertView;
    }
}
