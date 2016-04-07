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

        //Call methods to create database
        createDatabase();
        createTables();
        populateDatabase();

        //Set up the spinner
        Spinner houseSpinner = (Spinner) findViewById(R.id.spnHouseName);
        int layoutID = android.R.layout.simple_spinner_item;
        ArrayAdapter<String> houseAdapter = new ArrayAdapter<String>(this, layoutID, getSpinnerList() );
        houseSpinner.setAdapter(houseAdapter);

        //Set the buttons onClickListener
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new searchHandler());


    }


    //createDatabase method creates database or opens if exists
    public void createDatabase()
    {
        westerosPeopleAndHousesDB = openOrCreateDatabase("westerosPeopleAndHousesDB",MODE_PRIVATE, null);
    }

    //createTables method create the person table
    public void createTables()
    {
        dropTable();
        String createQuery = "CREATE TABLE IF NOT EXISTS tblPerson(" +
                "personID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "personFirstName TEXT NOT NULL, " +
                "houseName TEXT NOT NULL);";
        westerosPeopleAndHousesDB.execSQL(createQuery);
    }


    //populateDatabase method populate the person table with data
    public void populateDatabase()
    {
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Eddard', 'Stark')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Rob', 'Stark')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Sansa', 'Stark')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Bran', 'Stark')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Ayra', 'Stark')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Catelyn', 'Stark')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Jon', 'Arryn')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Lysa', 'Arryn')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Robin', 'Arryn')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Robert', 'Baratheon')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Stannis', 'Baratheon')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Renly', 'Baratheon')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Joffrey', 'Baratheon')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Myrcella', 'Baratheon')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Tommen', 'Baratheon')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Roose', 'Bolton')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Walda', 'Bolton')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Ramsay', 'Bolton')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Walder', 'Frey')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Joyeuse', 'Frey')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Stenron', 'Frey')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Lothar', 'Frey')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Roslin', 'Frey')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Walder', 'Frey')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Ryger', 'Frey')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Balon', 'Greyjoy')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Euron', 'Greyjoy')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Rodrik', 'Greyjoy')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Yara', 'Greyjoy')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Theon', 'Greyjoy')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Tywin', 'Lannister')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Cersei', 'Lannister')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Jamie', 'Lannister')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Tyrion', 'Lannister')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Lancel', 'Lannister')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Doran', 'Martell')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Elia', 'Martell')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Oberyn', 'Martell')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Trystane', 'Martell')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Aemon', 'Targaryen')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Aegon', 'Targaryen')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Aerys', 'Targaryen')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Rhaegar', 'Targaryen')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Viserys', 'Targaryen')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Daenerys', 'Targaryen')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Olenna', 'Tyrell')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Mace', 'Tyrell')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Margaery', 'Tyrell')");
        westerosPeopleAndHousesDB.execSQL("INSERT INTO tblPerson VALUES(null, 'Loras', 'Tyrell')");

    }

    //getSpinnerList method sets up the spinner
    public String[] getSpinnerList()
    {
        //Select all distinct houses from the person table and orders them alphbetically
        String selectQuery = "SELECT DISTINCT houseName FROM tblPerson ORDER BY houseName ASC";
        Cursor recordSet = westerosPeopleAndHousesDB.rawQuery(selectQuery, null);

        //Get the number of records from query
        int recordCount = recordSet.getCount();
        //Create array to hold house names
        String[] spinnerArrayOfHouses = new String[recordCount];

        //Get the index value of the houseName column
        int houseNameIndex = recordSet.getColumnIndex("houseName");

        //Move pointer to the first positon
        recordSet.moveToFirst();

        //Run through all retrieved records
        for (int i =0; i<recordCount;i++)
        {
            //Add records string to array and move to next index
            spinnerArrayOfHouses[i] = recordSet.getString(houseNameIndex);
            recordSet.moveToNext();
        }

        //Return the string array of houses
        return spinnerArrayOfHouses;
    }

    //getListviewList method and pass it the house name to query on
    public String[] getListviewList(String houseName)
    {
        //Select everything from person table when houseName is select house. Order alphbetically
        String selectQuery = "SELECT * FROM tblPerson WHERE houseName = '" + houseName + "' ORDER BY personFirstName ASC";
        Cursor recordSets = westerosPeopleAndHousesDB.rawQuery(selectQuery, null);

        //Get the number of records from query
        int recordCount = recordSets.getCount();
        //Create array to hold first names
        String[] arrayOfPeople = new String[recordCount];

        //Get the index value of the houseName column
        int personFirstNameIndex = recordSets.getColumnIndex("personFirstName");

        //Move pointer to the first positon
        recordSets.moveToFirst();

        //Run through all retrieved records
        for (int i =0; i<recordCount;i++)
        {
            //Add records string to array and move to next index
            arrayOfPeople[i] = recordSets.getString(personFirstNameIndex);
            recordSets.moveToNext();
        }

        //Return the string array of first names
        return arrayOfPeople;
    }

    //dropTable method drops person table if it exists
    private void dropTable()
    {
        String dropQuery = "DROP TABLE IF EXISTS tblPerson";
        westerosPeopleAndHousesDB.execSQL(dropQuery);
    }

    //searchHandler method implementing the onClickListener
    private class searchHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //Calls the setListView method on click of search button
            setListView();
        }
    }

    //setListView method populates the list view
    private void setListView()
    {
        ListView personList = (ListView) findViewById(R.id.lvPeople);
        Spinner houseSpinner = (Spinner) findViewById(R.id.spnHouseName);
        String house = houseSpinner.getSelectedItem().toString();
        ArrayAdapter<String> personAdaptor = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getListviewList(house));
        personList.setAdapter(personAdaptor);

    }
}
