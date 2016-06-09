package slide.apptech.com.rpiconnect;

/**
 * Created by MOHIT on 09-06-2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//custom adapter class extends a arrayadapter
public class customadapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList values;

    public customadapter(Context context, ArrayList values) {

        //for super constructor pass
        // context files
        //layout file required for list
        //arraylist that has strings to be displayed
        super(context, R.layout.roomtile, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.roomtile, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.tvroomname);

        //get(position method is used to access the elements of arraylist)
        String val = (String) values.get(position);
        textView.setText(val);
        // Change the icon for Windows and iPhone
        //String s = values[position];
        return rowView;
    }
}
