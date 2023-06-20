package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText code;
    private EditText name;

    private UsuariosSQLiteHelper usdbh;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usdbh =
                new UsuariosSQLiteHelper(this, "DBUsuarios", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        code = findViewById(R.id.txtReg);
        name = findViewById(R.id.txtVal);
    }

    public void insert (View view){
        Integer code1 = Integer.valueOf(code.getText().toString());
        String name1 = name.getText().toString();
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("codigo", code1);
        nuevoRegistro.put("nombre",name1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        db.insert("Usuarios",null,nuevoRegistro);
        db.close();
        Toast.makeText(MainActivity.this,"Se inserto correctamente",Toast.LENGTH_SHORT).show();
        recreate();
    }
    public void update (View view){
        Integer code1 = Integer.valueOf(code.getText().toString());
        String name1 = name.getText().toString();
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("nombre",name1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        db.update("Usuarios",nuevoRegistro,"codigo="+code1,null);
        db.close();
        Toast.makeText(MainActivity.this,"Se modifico correctamente",Toast.LENGTH_SHORT).show();
        recreate();
    }

    public void delete (View view){
        Integer code1 = Integer.valueOf(code.getText().toString());
        String name1 = name.getText().toString();
        SQLiteDatabase db = usdbh.getWritableDatabase();
        db.delete("Usuarios","codigo="+code1,null);
        db.close();
        Toast.makeText(MainActivity.this,"Se modifico correctamente",Toast.LENGTH_SHORT).show();
        recreate();
    }

    public void list (View view){

        SQLiteDatabase db = usdbh.getWritableDatabase();
        LinearLayout layout = (LinearLayout)findViewById(R.id.LinearLayout1);
        String[] args = new String[] {"usu1"};
        Cursor c = db.rawQuery("SELECT codigo ,nombre FROM  Usuarios ",null);
        if (c.moveToFirst()) {
            do {
                String codigo = c.getString(0);
                String nombre = c.getString(1);
                TextView text = new TextView(this);
                text.setId(Integer.valueOf(codigo));
                text.setText("Codigo :" + codigo + "   Nombre: " +nombre);
                layout.addView(text);
            } while(c.moveToNext());
        }
        db.close();

    }


}