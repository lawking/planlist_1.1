package com.example.lawrence.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


/**
 * Created by lawrence on 16/2/22.
 */
public class First extends Activity implements AdapterView.OnItemClickListener
{
    private ListView list;
    private ArrayAdapter<String>arr_adapter;
    private Button bt1;
    public static int  po=-1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        po=-1;
        Arr array=new Arr();


        Sql helper= new Sql(First.this,"stutest1.db");
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor c=db.rawQuery("select * from stutbtest1 order by pri",null);
        if(c!=null)
        {

            while(c.moveToNext())
            {

                //String name = c.getString(c.getColumnIndex("name"));
                array.Add(c.getString(c.getColumnIndex("name")));

            }
            c.close();
        }
        db.close();




        list=(ListView)findViewById(R.id.plan);
        arr_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array.nameToSelect );
        list.setAdapter(arr_adapter);
        list.setOnItemClickListener(this);

        bt1=(Button) findViewById(R.id.newPlan);
        bt1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(First.this,Second.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       //获取所点击的列表位置

        po= position;
        Intent intent=new Intent(First.this,Second.class);
        startActivity(intent);
    }
}
