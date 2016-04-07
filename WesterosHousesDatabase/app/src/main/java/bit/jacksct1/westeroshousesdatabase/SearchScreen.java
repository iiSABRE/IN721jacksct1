package bit.jacksct1.westeroshousesdatabase;

import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class SearchScreen extends AppCompatActivity
{

    SQLiteDatabase westerosPeopleAndHousesDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        createDatabase();
        createTables();
        populateDatabase();

        Spinner houseSpinner = (Spinner) findViewById(R.id.spnHouseName);
        int layoutID = android.R.layout.simple_spinner_item;
        ArrayAdapter<String> colourAdapter = new ArrayAdapter<String>(this, layoutID, getSpinnerList() );
        houseSpinner.setAdapter(colourAdapter);

        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new searchHandler());


    }



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
                "personLastName TEXT NOT NULL, " +
                "houseName TEXT NOT NULL);";
        westerosPeopleAndHousesDB.execSQL(createQuery);
    }


    public void populateDatabase()
    {
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Eddard', 'Stark', 'Stark')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Rob', 'Stark', 'Stark')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Sansa', 'Stark', 'Stark')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Bran', 'Stark', 'Stark')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Ayra', 'Stark', 'Stark')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Catelyn', 'Stark', 'Stark')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Jon', 'Arryn', 'Arryn')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Lysa', 'Arryn', 'Arryn')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Robin', 'Arryn', 'Arryn')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Robert', 'Baratheon', 'Baratheon')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Stannis', 'Baratheon', 'Baratheon')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Renly', 'Baratheon', 'Baratheon')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Joffrey', 'Baratheon', 'Baratheon')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Myrcella', 'Baratheon', 'Baratheon')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Tommen', 'Baratheon', 'Baratheon')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Roose', 'Bolton', 'Bolton')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Walda', 'Bolton', 'Bolton')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Ramsay', 'Bolton', 'Bolton')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Walder', 'Frey', 'Frey')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Joyeuse', 'Frey', 'Frey')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Stenron', 'Frey', 'Frey')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Lothar', 'Frey', 'Frey')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Roslin', 'Frey', 'Frey')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Walder', 'Rivers', 'Frey')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Ryger', 'Rivers', 'Frey')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Balon', 'Greyjoy', 'Greyjoy')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Euron', 'Greyjoy', 'Greyjoy')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Rodrik', 'Greyjoy', 'Greyjoy')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Yara', 'Greyjoy', 'Greyjoy')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Theon', 'Greyjoy', 'Greyjoy')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Tywin', 'Lannister', 'Lannister')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Cersei', 'Lannister', 'Lannister')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Jamie', 'Lannister', 'Lannister')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Tyrion', 'Lannister', 'Lannister')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Lancel', 'Lannister', 'Lannister')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Doran', 'Martell', 'Martell')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Elia', 'Martell', 'Martell')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Oberyn', 'Martell', 'Martell')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Trystane', 'Martell', 'Martell')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Aemon', 'Targaryen', 'Targaryen')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Aegon', 'Targaryen', 'Targaryen')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Aerys', 'Targaryen', 'Targaryen')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Rhaegar', 'Targaryen', 'Targaryen')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Viserys', 'Targaryen', 'Targaryen')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Daenerys', 'Targaryen', 'Targaryen')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Olenna', 'Tyrell', 'Tyrell')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Mace', 'Tyrell', 'Tyrell')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Margaery', 'Tyrell', 'Tyrell')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Loras', 'Tyrell', 'Tyrell')");



    }

    public String[] getSpinnerList()
    {
        String selectQuery = "SELECT DISTINCT houseName FROM tblPerson ORDER BY houseName ASC";
        Cursor recordSet = westerosPeopleAndHousesDB.rawQuery(selectQuery, null);

        int recordCount = recordSet.getCount();
        String[] spinnerArrayOfHouses = new String[recordCount];

        int houseNameIndex = recordSet.getColumnIndex("houseName");

        recordSet.moveToFirst();

        for (int i =0; i<recordCount;i++)
        {
            spinnerArrayOfHouses[i] = recordSet.getString(houseNameIndex);
            recordSet.moveToNext();
        }


        return spinnerArrayOfHouses;
    }

    public String[] getListviewList(String houseName)
    {
        String selectQuery = "SELECT * FROM tblPerson WHERE houseName = '" + houseName + "' ORDER BY personFirstName ASC";
        Cursor recordSets = westerosPeopleAndHousesDB.rawQuery(selectQuery, null);

        int recordCount = recordSets.getCount();
        String[] arrayOfPeople = new String[recordCount];

        int personFirstNameIndex = recordSets.getColumnIndex("personFirstName");
        int personLastNameIndex = recordSets.getColumnIndex("personLastName");

        recordSets.moveToFirst();

        for (int i =0; i<recordCount;i++)
        {
            arrayOfPeople[i] = recordSets.getString(personFirstNameIndex) + " " + recordSets.getString(personLastNameIndex);
            recordSets.moveToNext();
        }


        return arrayOfPeople;
    }

    private void dropTable()
    {
        String dropQuery = "DROP TABLE IF EXISTS tblPerson";
        westerosPeopleAndHousesDB.execSQL(dropQuery);
    }

    private class searchHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            setListView();
        }
    }

    private void setListView()
    {
        ListView personList = (ListView) findViewById(R.id.lvPeople);
        Spinner houseSpinner = (Spinner) findViewById(R.id.spnHouseName);
        String house = houseSpinner.getSelectedItem().toString();
        ArrayAdapter<String> personAdaptor = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getListviewList(house));
        personList.setAdapter(personAdaptor);

    }
}
