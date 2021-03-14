package com.alejandroquindimil.proyectorecpm.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alejandroquindimil.proyectorecpm.R;

public class NotificationsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotificationsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationsFragment newInstance(String param1, String param2) {
        NotificationsFragment fragment = new NotificationsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    public static class MainActivity extends AppCompatActivity {

        HomeFragment frg01= new HomeFragment();
        SearchFragment frg02= new SearchFragment();
        NotificationsFragment frg03= new NotificationsFragment();
        UserFragment frg04= new UserFragment();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            initButtons();
            initFragment();
        }

        private void initButtons(){
            ImageButton btn01= findViewById(R.id.act_main_imgbtn_home);
            ImageButton btn02= findViewById(R.id.act_main_imgbtn_search);
            ImageButton btn03= findViewById(R.id.act_main_imgbtn_notificaciones);
            ImageButton btn04= findViewById(R.id.act_main_imgbtn_user);

            btn01.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cambiaFragment(frg01);
                }
            });
            btn02.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cambiaFragment(frg02);
                }
            });
            btn03.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cambiaFragment(frg03);
                }
            });
            btn04.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cambiaFragment(frg04);
                }
            });

        }

        private void initFragment(){
            cambiaFragment(this.frg01);
        }


        private void cambiaFragment(Fragment frg){
            FragmentManager manager =getSupportFragmentManager();
            FragmentTransaction trans =manager.beginTransaction();

            trans.replace(R.id.act_main_container,frg,"fragment_01");
            trans.commit();
        }

    }
}
