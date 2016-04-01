package bit.jacksct1.welcometodunedinlist;

import android.graphics.drawable.Drawable;

/**
 * Created by jacksct1 on 1/04/2016.
 */
public class FunThings
{
    Drawable funImage;
    String funName;
    String url;

    public FunThings(Drawable funImage, String funName, String url)
    {
        this.funImage = funImage;
        this.funName = funName;
        this.url = url;
    }

    @Override
    public String toString() {
        return "bit.jacksct1.welcometodunedinlist.FunThings{" +
                "funImage=" + funImage +
                ", funName='" + funName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
