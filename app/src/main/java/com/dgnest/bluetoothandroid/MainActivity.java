package com.dgnest.bluetoothandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.dgnest.bluetoothandroid.util.BluetoothDroid;

public class MainActivity extends Activity {

    ListView lvBt;
    Button btnGetBt, btnSend;
    EditText etMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        lvBt = (ListView) findViewById(R.id.lvBt);
        btnGetBt = (Button) findViewById(R.id.btnGetBt);
        btnSend = (Button) findViewById(R.id.btnSend);
        etMsg = (EditText) findViewById(R.id.etMsg);

        BluetoothDroid.getInstance(this);

        btnGetBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        BluetoothDroid.getInstance(MainActivity.this).getNameBtDevices()
                );

                lvBt.setAdapter(adapter);

            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = etMsg.getEditableText().toString();
                BluetoothDroid.getInstance(MainActivity.this).enviarPaqBT(msg);
            }
        });

        lvBt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BluetoothDroid.getInstance(MainActivity.this).connectDevice(position);
                    }
                }).start();
            }
        });

    }
}
