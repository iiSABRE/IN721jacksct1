package bit.jacksct1.languagetrainer;

import java.util.ArrayList;

/**
 * Created by jacksct1 on 28/03/2016.
 */
public class UtilityManager
{


    int arrayPositon = 0;
    ArrayList<QuestionSetup> questionArray = new ArrayList<QuestionSetup>();

    public void initalArraySetup()
    {

        questionArray.add(new QuestionSetup("Masculine", "Der", "Apfel", "Apple", (R.drawable.apple)));

    }

    public int getArrayPositon() {
        return arrayPositon;
    }

    public void setArrayPositon(int arrayPositon) {
        this.arrayPositon = arrayPositon;
        arrayPositon++;
    }


}
