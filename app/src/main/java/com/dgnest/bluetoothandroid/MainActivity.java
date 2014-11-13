package com.dgnest.bluetoothandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.dgnest.bluetoothandroid.util.BluetoothDroid;

public class MainActivity extends Activity {

    ListView lvBt;
    Button btnGetBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        lvBt = (ListView) findViewById(R.id.lvBt);
        btnGetBt = (Button) findViewById(R.id.btnGetBt);

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

    }
}
