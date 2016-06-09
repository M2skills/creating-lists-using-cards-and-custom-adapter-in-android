package slide.apptech.com.rpiconnect;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MOHIT on 13-03-2016.
 */
public class database {

    public static final String KEY_ROWID = "id";
    public static final String KEY_NAME = "Sensor_Name";
    public static final String KEY_MAX = "Max";
    public static final String KEY_MIN = "Min";

    private static final String DATABASE_NAME2 = "Rangesdb";
    private static final String DATABASE_TABLE1 = "MinMaxTable";
    private static final int DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;



    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DATABASE_NAME2, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //create table is a command creates table
            //the second line has column name + its data type
            db.execSQL("CREATE TABLE " + DATABASE_TABLE1 + " (" +
                            KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            KEY_NAME + " TEXT DEFAULT ' ' , " +
                            KEY_MAX + " TEXT DEFAULT '0.0' ," +
                            KEY_MIN + " TEXT DEFAULT '0.0' );"
            );

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //checks if table already exists if yes then calls create function and updates the database
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE1);
            onCreate(db);
        }
    }

    //CONSTRUCTOR FOR database CLASS
    public database(Context c){
        ourContext = c;
    }

    //open is a method
    //it sets up table and version
    public database open() throws SQLException {
        ourHelper = new DbHelper(ourContext);

        //ourDatabase opens database using ourHelper in writable mode
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    //this method will close our database
    public void close(){
        ourHelper.close();
    }

    //below method creates a entry it recieves input from Ranges.java
    public long createEntry(String name, String Max , String Min) {
        // TODO Auto-generated method stub

        //content values is similar to a bundle used to pass data between classes
        ContentValues cv = new ContentValues();

        //.put method takes 2 values  1st database variable & 2nd string variable
        cv.put(KEY_NAME, name);
        cv.put(KEY_MAX, Max);
        cv.put(KEY_MIN, Min);

        //below line insert values in database
        //.insert takes 3 values 1st table name & 3rd context variable
        return ourDatabase.insert(DATABASE_TABLE1 , null , cv);
    }

    //this method gets data from database
    //check working of this method
    public String getData() {
        // TODO Auto-generated method stub
        //string array
        String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_MAX, KEY_MIN};
        //cursor is used to read information
        Cursor c = ourDatabase.query(DATABASE_TABLE1, columns, null, null, null, null, null);
        String result = "";

        int iRow = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iMax = c.getColumnIndex(KEY_MAX);
        int iMin = c.getColumnIndex(KEY_MIN);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            result = result + c.getString(iRow) + " " + c.getString(iName) + " " + c.getString(iMax) + " " + c.getString(iMin) +  "\n";
        }

        return result;
    }

    public String getMax(long l) throws SQLException{
        // TODO Auto-generated method stub
        String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_MAX, KEY_MIN};
        //cursor will be used to read the data
        //3rd parameter is selection parameter
        //here we are setting it to l that is passed from a different method
        Cursor c = ourDatabase.query(DATABASE_TABLE1, columns, KEY_ROWID + "=" + l, null, null, null, null);

        //we check if c points to null and we move it to the lth row and get the string
        if (c != null){
            c.moveToFirst();
            //next statement gets string data from column 2 which has max values
            String Max = c.getString(2);
            return Max;
        }
        return null;
    }

    public String getMin(long l) throws SQLException{
        // TODO Auto-generated method stub
        String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_MAX, KEY_MIN};
        Cursor c = ourDatabase.query(DATABASE_TABLE1, columns, KEY_ROWID + "=" + l, null, null, null, null);
        if (c != null){
            c.moveToFirst();
            //next statement gets string data from column 3 which has min values
            String Min = c.getString(3);
            return Min;
        }
        return null;
    }

    public void updateEntry(int lRow, String mName, String mMax , String mMin) throws SQLException{
        // TODO Auto-generated method stub
        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put(KEY_NAME, mName);
        cvUpdate.put(KEY_MAX, mMax);
        cvUpdate.put(KEY_MIN, mMin);
        ourDatabase.update(DATABASE_TABLE1, cvUpdate, KEY_ROWID + "=" + lRow, null);
    }

    /*public void ResetallEntries(long Rows) throws SQLException{
        // TODO Auto-generated method stub
        ourDatabase.delete(DATABASE_TABLE1, KEY_ROWID + "=" + Rows, null);
    }*/
}
