package studio.crunchyiee.degreeprogrammemapper;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import studio.crunchyiee.degreeprogrammemapper.DBHandler;

public class ModulesPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    GridView gv1;
    GridView gv2;
    GridView gv3;
    GridView gv4;
    GridView gv5;
    GridView gv6;

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        dbHandler = new DBHandler(this, null, null, 1);

        Course module = new Course("software", "COMP501", "IT Operations", 5, 15, "--", "Hardware component information.", 1);
        dbHandler.insertModule(module);
        module = new Course("software", "COMP502", "Fundamentals of Programming and Problem Solving", 5, 15, "--", "Programming fundamentals.", 1);
        dbHandler.insertModule(module);
        module = new Course("software", "INFO501", "Professional Practice", 5, 15, "--", "Business, Ethics and Morals.", 1);
        dbHandler.insertModule(module);
        module = new Course("software", "INFO502", "Business Systems Analysis & Design", 5, 15, "--", "Analysing businesses and designing solution.", 1);
        dbHandler.insertModule(module);
        module = new Course("software", "COMP503", "Introduction to Networks (Cisco 1)", 5, 15, "--", "Introduction to networking.", 2);
        dbHandler.insertModule(module);
        module = new Course("software", "COMP504", "Operating Systems & systems Support", 5, 15, "--", "Support for OS.", 2);
        dbHandler.insertModule(module);
        module = new Course("software", "INFO503", "Database Principles", 5, 15, "--", "Principles for Databases.", 2);
        dbHandler.insertModule(module);
        module = new Course("software", "INFO504", "Technical Support", 5, 15, "--", "Support and Help desk.", 2);
        dbHandler.insertModule(module);
        module = new Course("software", "COMP601", "Object Oriented Programming", 6, 15, "COMP502", "OBJ Best Practices.", 3);
        dbHandler.insertModule(module);
        module = new Course("software", "INFO601", "Data-modelling and SQL", 6, 15, "INFO503", "Data modelling theory and implementation.", 3);
        dbHandler.insertModule(module);
        module = new Course("software", "MATH601", "Mathematics for IT", 6, 15, "--", "Math for IT.", 3);
        dbHandler.insertModule(module);
        module = new Course("software", "COMP602", "Web Development", 6, 15, "COMP502, INFO502", "Website Development.", 3);
        dbHandler.insertModule(module);
        module = new Course("software", "INFO602", "Business, Interpersonal Communications & Technical Writing", 6, 15, "--", "Business Comms and Practices.", 4);
        dbHandler.insertModule(module);
        module = new Course("software", "COMP603", "The Web Environment", 6, 15, "COMP602", "The Website Environment.", 4);
        dbHandler.insertModule(module);
        module = new Course("software", "COMP605", "Data Structures and Algorithms", 6, 15, "COMP601, MATH601", "Big O Notation.", 4);
        dbHandler.insertModule(module);
        module = new Course("software", "MATH602", "Mathematics for programming", 6, 15, "MATH601", "Math for Programming.", 4);
        dbHandler.insertModule(module);
        module = new Course("software", "INFO701", "Project Management (Prince 2)", 7, 15, "INFO502", "Project management.", 5);
        dbHandler.insertModule(module);
        module = new Course("software", "BIZM701", "Business Essentials for IT Professionals", 7, 15, "INFO602", "Essentials for IT pros.", 5);
        dbHandler.insertModule(module);
        module = new Course("software", "INFO703", "Big Data and Analytics", 7, 15, "COMP605, MATH602", "Big data analytics.", 5);
        dbHandler.insertModule(module);
        module = new Course("software", "COMP706", "Games Development", 7, 15, "--", "Developing games for multi platform.", 5);
        dbHandler.insertModule(module);
        module = new Course("software", "INFO704", "Data-Warehousing and Business Intelligence", 7, 15, "--", "Data-warehousing and BI.", 6);
        dbHandler.insertModule(module);
        module = new Course("software", "COMP707", "Principles of Software Testing", 7, 15, "COMP605", "Software testing principles.", 6);
        dbHandler.insertModule(module);
        module = new Course("software", "COMP709", "Mobile Apps Development", 7, 15, "COMP601, COMP605, MATH602", "Mobile applications dev.", 6);
        dbHandler.insertModule(module);
        module = new Course("software", "COMP708", "Software Engineering Project", 7, 15, "COMP605, MATH602", "Project/Internship.", 6);
        dbHandler.insertModule(module);

        // Action toolbar for the Navigation Drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        dbHandler = new DBHandler(this, null, null, 1);

        gv1 = (GridView) findViewById(R.id.semester1Gv);
        gv2 = (GridView) findViewById(R.id.semester2Gv);
        gv3 = (GridView) findViewById(R.id.semester3Gv);
        gv4 = (GridView) findViewById(R.id.semester4Gv);
        gv5 = (GridView) findViewById(R.id.semester5Gv);
        gv6 = (GridView) findViewById(R.id.semester6Gv);

        Log.i("DPM", "Modules: Getting data");
        String[] semester1Codes = dbHandler.getModuleSemester("software", 1);
        ArrayAdapter<String> gridViewArrayAdapter = new ModulesAdapter(this, semester1Codes);
        gv1.setAdapter(gridViewArrayAdapter);
        String[] semester2Codes = dbHandler.getModuleSemester("software", 2);
        gridViewArrayAdapter = new ModulesAdapter(this, semester2Codes);
        gv2.setAdapter(gridViewArrayAdapter);
        String[] semester3Codes = dbHandler.getModuleSemester("software", 3);
        gridViewArrayAdapter = new ModulesAdapter(this, semester3Codes);
        gv3.setAdapter(gridViewArrayAdapter);
        String[] semester4Codes = dbHandler.getModuleSemester("software", 4);
        gridViewArrayAdapter = new ModulesAdapter(this, semester4Codes);
        gv4.setAdapter(gridViewArrayAdapter);
        String[] semester5Codes = dbHandler.getModuleSemester("software", 5);
        gridViewArrayAdapter = new ModulesAdapter(this, semester5Codes);
        gv5.setAdapter(gridViewArrayAdapter);
        String[] semester6Codes = dbHandler.getModuleSemester("software", 6);
        gridViewArrayAdapter = new ModulesAdapter(this, semester6Codes);
        gv6.setAdapter(gridViewArrayAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // Creating 3 Dot menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_About:
                //startSettings();
                Intent intent = new Intent(this, AboutPage.class);
                startActivity(intent);
                return true;

            case R.id.action_visitWebsite:
                //startSettings();
                intent = new Intent(this, WebPageView.class);
                startActivity(intent);
                return true;

            case R.id.action_disclaimer:
                //startSettings();
                final Dialog disclaimerDialog;
                TextView disclaimerTitle, disclaimerMsg;
                Button dismissDis;

                disclaimerDialog = new Dialog(this);

                disclaimerDialog.setContentView(R.layout.disclaimer_message);
                dismissDis = disclaimerDialog.findViewById(R.id.btn_i_understand);

                disclaimerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                disclaimerDialog.show();

                dismissDis.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        disclaimerDialog.dismiss();
                    }
                });


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Navigation Drawer, Defining the Options that the menu has and the uses of them.
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main_menu) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            // Skeleton of the Manager Mode card that requires the password "WinITDMP01"
        } else if (id == R.id.nav_managers) {
            final Dialog loginDialogue;
            TextView loginTitle, loginMsg;
            EditText ManagerModePasswordInput;
            Button dismissDis;

            final Intent intent = new Intent(getApplicationContext(), ManagerMode.class);
            loginDialogue = new Dialog(this);

            loginDialogue.setContentView(R.layout.manager_mode_login);
            dismissDis = loginDialogue.findViewById(R.id.btn_i_understand);

            loginDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            loginDialogue.show();

            dismissDis.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    loginDialogue.dismiss();
                    startActivity(intent);
                }
            });

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
