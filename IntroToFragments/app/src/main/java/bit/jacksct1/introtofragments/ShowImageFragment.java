package bit.jacksct1.introtofragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jacksct1 on 14/03/2016.
 */
public class ShowImageFragment extends Fragment
{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View fragmentView = inflater.inflate(R.layout.show_image_fragment, container, false);
        return fragmentView;
    }
}
