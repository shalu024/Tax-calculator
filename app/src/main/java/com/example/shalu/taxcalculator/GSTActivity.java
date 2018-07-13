package com.example.shalu.taxcalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import javax.xml.validation.Validator;

public class GSTActivity extends AppCompatActivity {
    ListView listView;
    TextView textView2;
    ArrayList<String> itemList;
    ArrayList<String> taxList;
    EditText tax1;
    AlertDialog.Builder b ;
    DBHelper d;
    private byte position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gst);



        textView2 = (TextView) findViewById(R.id.textview2);
        listView = (ListView) findViewById(R.id.listview);
        b = new AlertDialog.Builder(this);

        fetchall();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int position = i;
                tax1 = new EditText(GSTActivity.this);

                b.setTitle("Enter Amount");
                b.setMessage("Amount of " + itemList.get(i) + "\t");
                b.setView(tax1);
                b.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        calculate();

                    }
                });
                b.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        calculate();

                    }
                });
                AlertDialog a = b.create();
                a.show();
            }

        });
    }

    public void calculate()
    {
        int temp = 0;
        float f;

        String str = listView.getItemAtPosition(position).toString();
        int totaltax=0,tax=0;
        if(!TextUtils.isEmpty(tax1.getText().toString())){

            temp=Integer.parseInt(tax1.getText().toString());
            if (str.equals(itemList.get(position))) {
                f=Float.parseFloat((String) taxList.get(position));
                tax = (int)((temp * f)/100);
                totaltax = temp +tax;
                textView2.setText(" AMOUNT of "+str+" ="+temp+"\n"+
                        "Tax% ="+f+"\n"+ "GST Calculated =" + tax +"\n"+ "Total Amount (Inclusion of GST)=" +
                        totaltax + "\f");
            }
        }else{

            textView2.setText(" Amount of "+ str +" = " +temp + " \n " + " Tax =" + tax + "\n" + "Total amount = " + totaltax);
        }


    }
    public void fetchall()
    {
        d=new DBHelper(this);
        ListView lv;

        SQLiteDatabase s=  d.getReadableDatabase();
        Cursor c=s.rawQuery(" SELECT * FROM gst",null);

        c.moveToFirst();
        int t=c.getColumnIndex("Tax");
//        int l=c.getColumnIndex("items");
        Toast.makeText(getApplicationContext(),c.getCount(),Toast.LENGTH_LONG).show();
        itemList=new ArrayList<>();
        taxList=new ArrayList<>();

        while(c.moveToNext())
        {
            String it=c.getString(t);
           // String tax=c.getString(l);
            itemList.add(it);
           // taxList.add(tax);
        }
        c.close();
    }


        }


