package com.its.samuele.constatazioneamichevole.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.its.samuele.constatazioneamichevole.R;
import com.its.samuele.constatazioneamichevole.adapter.SinistroCursorAdapter;
import com.its.samuele.constatazioneamichevole.database.SinistroDB;
import com.its.samuele.constatazioneamichevole.database.SinistroTableHelper;


public class MainActivity extends AppCompatActivity {

    ListView listSinistri;
    Button aggiungiSinistro;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_contestazioni);

        listSinistri = (ListView) findViewById(R.id.listaSinistri);
        aggiungiSinistro = (Button) findViewById(R.id.fab);

        SQLiteDatabase database = new SinistroDB(MainActivity.this).getReadableDatabase();
        Cursor cursor = database.query(SinistroTableHelper.TABLE_NAME,
                new String[]{SinistroTableHelper._ID, SinistroTableHelper.DATA_INSERT,SinistroTableHelper.TIME},null,null,null,null, SinistroTableHelper.TIME);
        SinistroCursorAdapter adapter = new SinistroCursorAdapter(MainActivity.this, cursor);
        listSinistri.setAdapter(adapter);

        listSinistri.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bundle = new Bundle();
                bundle.putLong("SINISTROID",id);
                //listener.listPress(bundle);


            }
        });
        database.close();


    }
}
