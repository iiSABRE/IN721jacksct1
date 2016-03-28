package bit.jacksct1.languagetrainer;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jacksct1 on 28/03/2016.
 */
public class UtilityManager implements Serializable
{


    int arrayPositon = 0;
    int questionNumber = 1;
    ArrayList<QuestionSetup> questionArray = new ArrayList<QuestionSetup>();

    public void initalArraySetup()
    {

        questionArray.add(new QuestionSetup("Masculine", "Der", "Apfel", "Apple", (R.drawable.apple)));

    }

    public int getArrayPositon() {
        return arrayPositon;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setArrayPositon(int arrayPositon) {
        this.arrayPositon = arrayPositon;
        arrayPositon++;

    }

    public void setQuestionNumber(int questionNumber)
    {
        this.questionNumber =questionNumber;
        questionNumber++;
    }

    public String getCurrentArticle()
    {
        return  questionArray.get(arrayPositon).getArticle();
    }

    public String getCurrentNoun()
    {
        return  questionArray.get(arrayPositon).getNoun();
    }

    public String getCurrentEngTrans()
    {
        return  questionArray.get(arrayPositon).getEngTrans();
    }


    public int getArrayLength()
    {
        int length = questionArray.size();
        return length;
    }

    public boolean checkEnd()
    {
        if (arrayPositon + 1 >= questionArray.size())
        {
            return true;
        }
        else
        {
            return false;
        }
    }


}
