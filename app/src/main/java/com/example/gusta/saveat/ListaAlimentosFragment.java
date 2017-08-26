package com.example.gusta.saveat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ListViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by gusta on 23/08/2017.
 */

public class ListaAlimentosFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_alimentos, container, false);
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.procura_produto_button);
        Button button = (Button) view.findViewById(R.id.procura_mercado_button);
        final EditText editText = (EditText) view.findViewById(R.id.produto_text_edit);
        final ProductAdapter productAdapter = new ProductAdapter(getContext(), null);
        ((ListView) view.findViewById(R.id.produto_list_view)).setAdapter(productAdapter);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerCall serverCall = new ServerCall(getActivity());
                serverCall.getProductDetails(editText.getText().toString(), productAdapter);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListaMercadosFragment listaMercadosFragment = new ListaMercadosFragment(productAdapter.getList());
                getFragmentManager().beginTransaction().replace(R.id.middle_frame, listaMercadosFragment, "2").commit();
            }
        });
        return view;
    }
}
