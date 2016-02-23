package com.example.lawrence.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.sql.Date;

/**
 * Created by lawrence on 16/2/22.
 */
public class Second extends Activity
{
    private String content="呵呵";

    private CheckBox ch;
    private Button bt;
    private Button bt2;
    private EditText ed1;
    private EditText ed2;
    private EditText ed3;
    private RadioGroup rg;
    private RadioButton rg1;
    private RadioButton rg2;
    private RadioButton rg3;

    public static String id=null;
    public static String name = null;
    public static String des= null;
    public static String date= null;
    public static int pri= 3;

    public static int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan);

        i=0;
        pri= 3;

        ch=(CheckBox)findViewById(R.id.checkBox);
        bt=(Button)findViewById(R.id.finish);
        bt2=(Button)findViewById(R.id.re);
        ed1=(EditText)findViewById(R.id.planName);
        ed2=(EditText)findViewById(R.id.planDescribe);
        ed3=(EditText)findViewById(R.id.date);
        rg=(RadioGroup)findViewById(R.id.pre);
        rg1=(RadioButton)findViewById(R.id.pre1);
        rg2=(RadioButton)findViewById(R.id.pre2);
        rg3=(RadioButton)findViewById(R.id.pre3);


        ed1.setText(null);
        ed2.setText(null);
        ed3.setText(null);
        rg3.setChecked(true);


        First fir=new First();
        Sql helper= new Sql(Second.this,"stutest1.db");
        SQLiteDatabase db=helper.getWritableDatabase();

        Cursor c=db.rawQuery("select * from stutbtest1 order by pri",null);
        if(c!=null)
        {

            while(c.moveToNext())
            {;

                if (i==fir.po)
                {//怎么获取id？"_id"
                    id= c.getString(c.getColumnIndex("_id"));
                 name =(String) c.getString(c.getColumnIndex("name"));
                 des = (String) c.getString(c.getColumnIndex("des"));
                 date= (String) c.getString(c.getColumnIndex("date"));
                 pri = c.getInt(c.getColumnIndex("pri"));
                    ed1.setText(name);
                    ed2.setText(des);
                    ed3.setText(date);
                    switch (pri)
                    {
                        case 1:
                            rg1.setChecked(true);
                            break;
                        case 2:
                            rg2.setChecked(true);
                            break;
                        case 3:
                            rg3.setChecked(true);
                            break;
                        default:
                            break;
                    }
                        break;
                }
                i++;
            }
            c.close();
        }
        db.close();



        ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Sql helper= new Sql(Second.this,"stutest1.db");
                SQLiteDatabase db=helper.getWritableDatabase();
                db.delete("stutbtest1","_id=?",new String[]{id});
                db.close();

                Intent intent=new Intent(Second.this,First.class);
                startActivity(intent);

            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.pre1:
                        pri= 1;
                        break;
                    case R.id.pre2:
                        pri= 2;
                        break;
                    case R.id.pre3:
                        pri= 3;
                        break;
                    default:
                        break;
                }
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sql helper= new Sql(Second.this,"stutest1.db");
                SQLiteDatabase db=helper.getWritableDatabase();
                ContentValues values=new ContentValues();
                //此处有bug。即使无内容也一定会加上一行
                if ((String)ed1.getText().toString()!="")
                {values.put("name",ed1.getText().toString());
                values.put("des",ed2.getText().toString());
                values.put("date",ed3.getText().toString());
                values.put("pri",pri);
                //存储一组数据
                long rowId=db.insert("stutbtest1",null,values);
                }//将该组数据插入表格
                db.close();


                Intent intent=new Intent(Second.this,First.class);
                startActivity(intent);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Second.this,First.class);
                startActivity(intent);
            }
        });
    }
}
