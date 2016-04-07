package bit.jacksct1.westeroshousesdatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by jacksct1 on 7/04/2016.
 */
public class DatabaseHandler extends AppCompatActivity
{
    SQLiteDatabase westerosPeopleAndHousesDB;

    public void createDatabase()
    {
        westerosPeopleAndHousesDB = openOrCreateDatabase("westerosPeopleAndHousesDB",MODE_PRIVATE, null);
    }

    public void createTables()
    {
        dropTable();
        String createQuery = "CREATE TABLE IF NOT EXISTS tblPerson(" +
                             "personID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                             "personFirstName TEXT NOT NULL, " +
                             "personlastName TEXT NOT NULL, " +
                             "houseName TEXT NOT NULL);";
        westerosPeopleAndHousesDB.execSQL(createQuery);
    }


    public void populateDatabase()
    {
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Eddard', 'Stark', 'Stark')");

    }

    public String[] getSpinnerList()
    {
        String selectQuery = "SELECT DISTINCT houseName FROM tblPerson ORDER BY houseName DESC";
        Cursor recordSet = westerosPeopleAndHousesDB.rawQuery(selectQuery, null);

        int recordCount = recordSet.getCount();
        String[] spinnerArrayOfHouses = new String[recordCount];

        int houseNameIndex = recordSet.getColumnIndex("houseName");

        recordSet.moveToFirst();

        for (int i =0; i<recordCount;i++)
        {
            spinnerArrayOfHouses[i] = "House " + recordSet.getString(houseNameIndex);
            recordSet.moveToNext();
        }


        return spinnerArrayOfHouses;
    }

    public ArrayList<String> getListviewList(String houseName)
    {
        ArrayList<String> listviewrArrayList = new ArrayList<String>();

        return listviewrArrayList;
    }

    private void dropTable()
    {
        String dropQuery = "DROP TABLE tblPerson";
        westerosPeopleAndHousesDB.execSQL(dropQuery);
    }


}
