package com.alejandroquindimil.proyectorecpm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.alejandroquindimil.proyectorecpm.main.HomeFragment;
import com.alejandroquindimil.proyectorecpm.main.NotificationsFragment;
import com.alejandroquindimil.proyectorecpm.main.SearchFragment;
import com.alejandroquindimil.proyectorecpm.main.UserFragment;

public class MainActivity extends AppCompatActivity {

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
        testLogs();
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


    private void testLogs() {

        Log.v("VERBOSE", "Mensaje de Verbose");
        Log.d("DEBUG", "Mensjae de Debug");
        Log.i("INFO", "Mensaje de Info");
        Log.w("WARNING", "Mensaje de Warning");
        Log.e("ERROR", "Mensaje de Error");
    }
}