package com.allan.kostku;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.zip.Inflater;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private KostAdapter adapter;
    private ArrayList<Kost> kostArrayList;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_logout:
                keluar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void keluar() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Logout Apps");
        alertDialog.setMessage("Are You Sure?");

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You clicked on Ok", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You clicked on cancel", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ... Code inside onCreate() method
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Recycle View
        addData();

        recyclerView = (RecyclerView) findViewById(R.id.list_kost);

        adapter = new KostAdapter(kostArrayList, MainActivity.this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);




        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home_menu:
                        break;
                    case R.id.history_menu:
                        Intent a = new Intent(MainActivity.this, ActivityOne.class);
                        a.setFlags(FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(a);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.payment_menu:
                        Intent b = new Intent(MainActivity.this, ActivityTwo.class);
                        b.setFlags(FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(b);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.report_menu:
                        break;
                    case R.id.account_menu:
                        break;
                }
                return false;
            }
        });
    }
    public void addData(){
        kostArrayList = new ArrayList<>();
        kostArrayList.add(new Kost("Dimas Maulana", "20", "lengkap"));
        kostArrayList.add(new Kost("Fadly Yonk", "21", "kamar mandi dalam"));
        kostArrayList.add(new Kost("Ariyandi Nugraha", "22", "lengkap"));
        kostArrayList.add(new Kost("Aham Siswana", "23", "lengkap"));
    }

}