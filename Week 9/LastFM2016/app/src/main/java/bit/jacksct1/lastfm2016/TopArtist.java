package bit.jacksct1.lastfm2016;

/**
 * Created by jacksct1 on 14/04/2016.
 */
public class TopArtist
{
    String artistName;
    String listenerCount;



    public TopArtist(String artistName, String listenerCount)
    {
        this.artistName = artistName;
        this.listenerCount = listenerCount;
    }

    @Override
    public String toString() {
        return "TopArtist{" +
                "artistName='" + artistName + '\'' +
                ", listenerCount='" + listenerCount + '\'' +
                '}';
    }


}
