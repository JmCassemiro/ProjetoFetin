package com.example.crud_operations.BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.crud_operations.model.Produtos;

import java.util.ArrayList;

public class ProdutosBD extends SQLiteOpenHelper {

    private static final String DATABASE = "bdprodutos";
    private static final int VERSION = 2;

    public ProdutosBD(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE produtos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "nome TEXT NOT NULL," +
                "marca TEXT NOT NULL," +
                "preco DOUBLE NOT NULL," +
                "localizacao TEXT NOT NULL," +
                "tipo TEXT NOT NULL DEFAULT '');";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            String alterTable = "ALTER TABLE produtos ADD COLUMN tipo TEXT NOT NULL DEFAULT '';";
            db.execSQL(alterTable);
        }
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
            values.put("tipo", produto.getTipo());

            id = db.insert("produtos", null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return id != -1;
    }

    public boolean alterarProduto(Produtos produto) {
        SQLiteDatabase db = null;
        int rowsAffected = 0;
        try {
            db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nome", produto.getNome());
            values.put("marca", produto.getMarca());
            values.put("preco", produto.getPreco());
            values.put("localizacao", produto.getLocalizacao());
            values.put("tipo", produto.getTipo());

            String[] args = {produto.getId().toString()};
            rowsAffected = db.update("produtos", values, "id=?", args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return rowsAffected > 0;
    }

    public void deletarProduto(Produtos produto) {
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            String[] args = {produto.getId().toString()};
            db.delete("produtos", "id=?", args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public ArrayList<Produtos> getLista() {
        ArrayList<Produtos> produtos = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = getReadableDatabase();
            String[] columns = {"id", "nome", "marca", "preco", "localizacao", "tipo"};

            cursor = db.query(
                    "produtos",
                    columns,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            while (cursor.moveToNext()) {
                Produtos produto = new Produtos();
                produto.setId(cursor.getLong(0));
                produto.setNome(cursor.getString(1));
                produto.setMarca(cursor.getString(2));
                produto.setPreco(cursor.getDouble(3));
                produto.setLocalizacao(cursor.getString(4));
                produto.setTipo(cursor.getString(5));

                produtos.add(produto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return produtos;
    }

    public ArrayList<Produtos> getProdutosPorTipo(String tipo) {
        ArrayList<Produtos> produtos = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = getReadableDatabase();
            String[] columns = {"id", "nome", "marca", "preco", "localizacao", "tipo"};
            String selection = "tipo = ?";
            String[] selectionArgs = {tipo};

            cursor = db.query(
                    "produtos",
                    columns,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            while (cursor.moveToNext()) {
                Produtos produto = new Produtos();
                produto.setId(cursor.getLong(0));
                produto.setNome(cursor.getString(1));
                produto.setMarca(cursor.getString(2));
                produto.setPreco(cursor.getDouble(3));
                produto.setLocalizacao(cursor.getString(4));
                produto.setTipo(cursor.getString(5));

                produtos.add(produto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return produtos;
    }
}
