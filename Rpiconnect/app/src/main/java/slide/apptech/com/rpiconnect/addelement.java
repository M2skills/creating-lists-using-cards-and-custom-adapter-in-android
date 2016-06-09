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
public class addelement extends ListActivity {
    EditText et;
    ArrayList appliances;
    Context context;
    String device;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addelement);
        et = (EditText) findViewById(R.id.etappname);

        //define context as we need to pass it to adapter
        context = addelement.this;

        //create a arraylist to store the values of list
        appliances = new ArrayList();

    }

    //this method sends values list to adapter to generate the list
    public void addtoadapter(){
        customadapter adapter = new customadapter(context, appliances);
        setListAdapter(adapter);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addItem:
                device = et.getText().toString();
                appliances.add(device);
                et.setText("");
                addtoadapter();
                break;
        }
    }
}