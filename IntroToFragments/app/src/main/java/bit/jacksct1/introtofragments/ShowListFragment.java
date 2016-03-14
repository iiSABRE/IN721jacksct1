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
public class ShowListFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View fragmentView = inflater.inflate(R.layout.show_list_fragment, container, false);

        ListView lvCapitals = (ListView) fragmentView.findViewById(R.id.lvCapitals);
        Resources resource = getResources();
        String[] capitalArray = resource.getStringArray(R.array.CapitalsArray);

        ArrayAdapter<String> capitalAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, capitalArray);

        lvCapitals.setAdapter(capitalAdapter);

        return fragmentView;
    }
}
