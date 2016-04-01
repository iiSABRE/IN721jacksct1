package bit.jacksct1.welcometodunedinlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bit.jacksct1.welcometodunedinlist.ActivityActivity;
import bit.jacksct1.welcometodunedinlist.FunThings;
import bit.jacksct1.welcometodunedinlist.MainActivity;

/**
 * Created by jacksct1 on 1/04/2016.
 */
public class FunArrayAdaptor extends ArrayAdapter<FunThings>
{

    public FunArrayAdaptor(Context context, int resource, FunThings[] objects)
    {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View customView = inflater.inflate(R.layout.funtodo_custom_layout, parent, false);

        ImageView itemImageView = (ImageView) customView.findViewById(R.id.ivListImage);
        TextView itemTextView = (TextView) customView.findViewById(R.id.tvListWords);

        FunThings currentItem = getItem(position);

        itemImageView.setImageDrawable(currentItem.funImage);
        itemTextView.setText(currentItem.funName);

        return  customView;

    }


}
