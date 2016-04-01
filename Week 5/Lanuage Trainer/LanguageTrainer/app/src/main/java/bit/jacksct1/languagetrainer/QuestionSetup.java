package bit.jacksct1.languagetrainer;

import android.graphics.Bitmap;
import android.media.Image;

/**
 * Created by jacksct1 on 28/03/2016.
 */

//Question class that hold all the information about a question
public class QuestionSetup
{

    private final String gender;
    private final String article;
    private final String noun;
    private final String engTrans;
    private final int picture;



    public QuestionSetup(String Gender, String Article, String Noun, String EngTrans , int Picture)
    {
        gender = Gender;
        article = Article;
        noun = Noun;
        engTrans = EngTrans;
        picture = Picture;

    }

    public String getGender() {
        return gender;
    }

    public String getArticle() {
        return article;
    }

    public String getNoun() {
        return noun;
    }

    public String getEngTrans() {
        return engTrans;
    }

    public int getPicture() {
        return picture;
    }

}
