package layout.FragmentsList;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.example.talarir.testingmaterial.MixedFruitJuice.RecyclerContent.AdapterClass;
import com.example.talarir.testingmaterial.MixedFruitJuice.RecyclerContent.DataProvider;
import com.example.talarir.testingmaterial.R;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * Created by talarir on 06/12/2016.
 */



public class FragmentOne extends Fragment
{
    public String[] name,hostel;
    public int image;

    ArrayList<DataProvider> arrayList=new ArrayList<DataProvider>();

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ActionBar tToolbar;

    public FragmentOne()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name= new String[]{"a", "b", "c","d","e","f","g","h","i","j","k"};
        hostel=new String[]{"a", "b", "c","d","e","f","g","h","i","j","k"};
        image=R.mipmap.ic_launcher_arrow;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.one_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tToolbar=getActivity().getActionBar();
        recyclerView=(RecyclerView)getActivity().findViewById(R.id.recycler_view_fragment_one);
        for (int i=0;i<name.length;i++)
        {
            DataProvider dataProvider= new DataProvider(name[i],hostel[i],image);
            arrayList.add(dataProvider);
        }
        adapter= new AdapterClass(arrayList);
        recyclerView.setHasFixedSize(true);
        //layoutManager= new StaggeredGridLayoutManager(2,1);
        layoutManager= new LinearLayoutManager(getActivity());

        recyclerView.setItemAnimator(new SlideInLeftAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


          }
}
