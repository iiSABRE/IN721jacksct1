package bit.jacksct1.introtofragments;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by jacksct1 on 14/03/2016.
 */

//Class that extends Fragment to handle a fragment containing a List View
public class ShowListFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //creating a View that holds the layout for an image
        View fragmentView = inflater.inflate(R.layout.show_list_fragment, container, false);

        //Get reference to the List View
        ListView lvCapitals = (ListView) fragmentView.findViewById(R.id.lvCapitals);

        //Create resource handler and use it to get String Array
        Resources resource = getResources();
        String[] capitalArray = resource.getStringArray(R.array.CapitalsArray);

        //Create adapter and pass it to the setAdapter method of the ListView
        ArrayAdapter<String> capitalAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, capitalArray);
        lvCapitals.setAdapter(capitalAdapter);

        //Return the view configured View
        return fragmentView;
    }
}
