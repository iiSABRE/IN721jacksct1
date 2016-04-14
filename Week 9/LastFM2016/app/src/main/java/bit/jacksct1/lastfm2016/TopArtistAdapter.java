package bit.jacksct1.lastfm2016;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import bit.jacksct1.lastfm2016.TopArtist;

/**
 * Created by jacksct1 on 14/04/2016.
 */
public class TopArtistAdapter extends ArrayAdapter<TopArtist>
{

    public TopArtistAdapter(Context context, int resource, ArrayList<TopArtist> objects)
    {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View customView = inflater.inflate(R.layout.top20_custom_layout, parent, false);

        TextView artistTextView = (TextView) customView.findViewById(R.id.tvArtistName);
        TextView listenerTextView = (TextView) customView.findViewById(R.id.tvListenerCount);

        TopArtist currentItem = getItem(position);

        artistTextView.setText(currentItem.artistName);
        listenerTextView.setText(currentItem.listenerCount);

        return  customView;

    }
}
