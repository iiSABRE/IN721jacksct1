package bit.jacksct1.welcometodunedinlist;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityActivity extends AppCompatActivity {

    FunThings[] toDoArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_);


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setUpDataArray()
    {
        Resources resourceManager = getResources();

        Drawable larnachCastle = resourceManager.getDrawable(R.drawable.larnach_castle, null);
        Drawable moanaPool = resourceManager.getDrawable(R.drawable.moana_pool, null);
        Drawable monarch = resourceManager.getDrawable(R.drawable.monarch, null);
        Drawable octagon = resourceManager.getDrawable(R.drawable.octagon, null);
        Drawable olverston = resourceManager.getDrawable(R.drawable.olveston, null);
        Drawable op = resourceManager.getDrawable(R.drawable.op, null);
        Drawable peninsula = resourceManager.getDrawable(R.drawable.peninsula, null);
        Drawable saltWaterPool = resourceManager.getDrawable(R.drawable.salt_water_pool, null);
        Drawable speightsBrewery = resourceManager.getDrawable(R.drawable.speights_brewery, null);
        Drawable stkildaBeach = resourceManager.getDrawable(R.drawable.st_kilda_beach, null);
        Drawable taeriRailway = resourceManager.getDrawable(R.drawable.taeri_gorge_railway, null);

        toDoArray = new FunThings[11];
        toDoArray[0] = new FunThings(larnachCastle, "Larnach Castle", null);
        toDoArray[1] = new FunThings(moanaPool, "Moana Pool", null);
        toDoArray[2] = new FunThings(monarch, "Monarch", null);
        toDoArray[3] = new FunThings(octagon, "Octagon", null);
        toDoArray[4] = new FunThings(olverston, "Ovlerston", null);
        toDoArray[5] = new FunThings(op, "Otago Polytechnic", null);
        toDoArray[6] = new FunThings(peninsula, "Peninsula", null);
        toDoArray[7] = new FunThings(saltWaterPool, "Salt Water Pool", null);
        toDoArray[8] = new FunThings(speightsBrewery, "Speights Brewery", null);
        toDoArray[9] = new FunThings(stkildaBeach, "St Kilda Beach", null);
        toDoArray[10] = new FunThings(taeriRailway, "Taeri Gorge Railway", null);


    }


}
