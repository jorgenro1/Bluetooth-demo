package com.dgnest.bluetoothandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.dgnest.bluetoothandroid.util.BT;

import java.util.List;

public class MainActivity extends Activity {

    ListView lvBt;
    Button btnGetBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        lvBt = (ListView) findViewById(R.id.lvBt);
        btnGetBt = (Button) findViewById(R.id.btnGetBt);

        final BT mBt = new BT(this);

        btnGetBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<String> deviceNames = mBt.getBtNames();

                String[] arrDeviceNames = new String[deviceNames.size()];
                for (int i = 0; i < deviceNames.size(); i++) {
                    arrDeviceNames[i] = deviceNames.get(i);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        arrDeviceNames
                );

                lvBt.setAdapter(adapter);

                Log.d("MainActivity", deviceNames.toString());
            }
        });


    }
}
