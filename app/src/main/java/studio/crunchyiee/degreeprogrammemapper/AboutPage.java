package studio.crunchyiee.degreeprogrammemapper;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AboutPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Action toolbar for the Navigation Drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
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
