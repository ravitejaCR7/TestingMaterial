package layout.FragmentsList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.talarir.testingmaterial.R;

/**
 * Created by talarir on 06/12/2016.
 */

public class FragmentThree extends Fragment {
    public FragmentThree()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.three_fragment, container, false);
    }
}
