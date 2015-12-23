package damroo.spinno.com.damroo.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import butterknife.Bind;
import butterknife.ButterKnife;
import damroo.spinno.com.damroo.R;

/**
 * Created by spinnosolutions on 10/28/15.
 */
public class FragmentInnerCategories   extends Fragment {

   public  static GridView grid ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmentinnercategories, container, false);
        ButterKnife.bind(this, v);
        grid = (GridView)v.findViewById(R.id.grid);
        //grid.setAdapter(new AdapterCategoryGrid(getActivity(), FRAGMENT_HOME.cat_name));

        return v;
    }

    public static FragmentInnerCategories newInstance(String text) {

        FragmentInnerCategories f = new FragmentInnerCategories();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    @Override
    public void onResume() {
        grid.setAdapter(new AdapterCategoryGrid(getActivity(),FRAGMENT_HOME.cat_image));
        super.onResume();
    }
}
