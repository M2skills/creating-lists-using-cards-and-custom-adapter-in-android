package slide.apptech.com.rpiconnect;

/**
 * Created by MOHIT on 08-06-2016.
 */
import android.os.Bundle;
import android.app.ListActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.content.Context;

import java.util.ArrayList;

//addroom must extent listactivity
public class addroom extends ListActivity {
    EditText et;
    ArrayList values;
    Context context;
    String device;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrooms);
        et = (EditText) findViewById(R.id.etareaname);

        //define context as we need to pass it to adapter
        context = addroom.this;

        //create a arraylist to store the values of list
        values = new ArrayList();

    }

    //this method sends values list to adapter to generate the list
    public void addtoadapter(){
        customadapter adapter = new customadapter(context, values);
        setListAdapter(adapter);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addItem:
                device = et.getText().toString();
                values.add(device);
                et.setText("");
                addtoadapter();
                break;
        }
    }
}