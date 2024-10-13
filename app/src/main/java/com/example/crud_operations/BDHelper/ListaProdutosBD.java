package com.example.crud_operations.BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.crud_operations.model.Produtos;

import java.util.ArrayList;

public class ListaProdutosBD extends SQLiteOpenHelper {

    private static final String DATABASE = "listaProdutos";
    private static final int VERSION = 1;

    public ListaProdutosBD(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String listaProdutos = "CREATE TABLE listaProdutos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "nome TEXT NOT NULL," +
                "marca TEXT NOT NULL," +
                "preco DOUBLE NOT NULL," +
                "localizacao TEXT NOT NULL);";
        db.execSQL(listaProdutos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String listaProdutos = "DROP TABLE IF EXISTS listaProdutos";
        db.execSQL(listaProdutos);
        onCreate(db);
    }

    public boolean salvarProduto(Produtos produto) {
        SQLiteDatabase db = null;
        long id = -1;
        try {
            db = getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("nome", produto.getNome());
            values.put("marca", produto.getMarca());
            values.put("preco", produto.getPreco());
            values.put("localizacao", produto.getLocalizacao());

            id = db.insert("listaProdutos", null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return id != -1;
    }

    public void deletarProduto(Produtos produto) {
        String[] args = {produto.getId().toString()};
        getWritableDatabase().delete("listaProdutos", "id=?", args);
    }

    public ArrayList<Produtos> getLista() {
        String[] columns = {"id", "nome", "marca", "preco", "localizacao"};

        Cursor cursor = getReadableDatabase().query(
                "listaProdutos",
                columns,
                null,
                null,
                null,
                null,
                null,
                null
        );

        ArrayList<Produtos> produtos = new ArrayList<>();

        while (cursor.moveToNext()) {
            Produtos produto = new Produtos();
            produto.setId(cursor.getLong(0));
            produto.setNome(cursor.getString(1));
            produto.setMarca(cursor.getString(2));
            produto.setPreco(cursor.getDouble(3));
            produto.setLocalizacao(cursor.getString(4));

            produtos.add(produto);
        }
        cursor.close();

        return produtos;
    }

    public void resetDatabase() {
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS listaProdutos");
            onCreate(db);
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
}
