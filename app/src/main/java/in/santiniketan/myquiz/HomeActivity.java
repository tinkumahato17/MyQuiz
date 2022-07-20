package in.santiniketan.myquiz;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import androidx.annotation.NonNull;
import androidx.annotation.StyleableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomeActivity extends AppCompatActivity {

    private Button startbtn1;
    private Button startbtn2;
    private Button fullform;
    private  Button shortcutkeys;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        startbtn1 = findViewById(R.id.startbtn1);

        startbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent categoryintent = new Intent(HomeActivity.this,CategoryActivity.class);
                startActivity(categoryintent);
                finish();

            }
        });
        startbtn2 = findViewById(R.id.startbtn2);

        startbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent categoryintent = new Intent(HomeActivity.this,QuizActivity.class);
                startActivity(categoryintent);
                finish();

            }
        });

        fullform = findViewById(R.id.fullform);
        fullform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent categoryintent = new Intent(HomeActivity.this,fullformActivity.class);
                startActivity(categoryintent);
                finish();
            }
        });

        shortcutkeys = findViewById(R.id.shortcutkeys);
        shortcutkeys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shortcutkeysintent = new Intent(HomeActivity.this,shortcutkeysActivity.class);
                startActivity(shortcutkeysintent);
                finish();
            }
        });




        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_study,R.id.nav_quiz,R.id.nav_update,
                R.id.nav_share,R.id.nav_rate,R.id.nav_other)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_home:
                        break;
                    case R.id.nav_study:
                        Intent i = new Intent(HomeActivity.this,CategoryActivity.class);
                        startActivity(i);
                        break;
                    case R.id.nav_quiz:
                        Intent j = new Intent(HomeActivity.this,QuizActivity.class);
                        startActivity(j);
                        break;

                    case R.id.nav_update:
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("market://details?id=" +"in.santiniketan.myquiz")));
                        }
                        catch (ActivityNotFoundException e)
                        {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http//play.google.com/store/apps/details?id" + getPackageName())));
                        }
                        break;
                    case R.id.nav_share:
                        Intent shareingIntent  = new Intent(Intent.ACTION_SEND);
                        shareingIntent.setType("text//plane");
                        String shareBody = "###########"; //give play store path of your path
                        String shareSubject = "MYQuiz APP";
                        shareingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                        shareingIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);
                        startActivity(Intent.createChooser(shareingIntent,"Share Using"));
                        break;

                    case R.id.nav_rate:
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("market://details?id=" +"in.santiniketan.myquiz")));
                        }
                        catch (ActivityNotFoundException e)
                        {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http//play.google.com/store/apps/details?id" + getPackageName())));
                        }
                        break;
                    case R.id.nav_other:
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://search?q=pub:Education+life+line")));
                        }
                        catch (android.content.ActivityNotFoundException anfe)
                        {
                            startActivity(new  Intent(Intent.ACTION_VIEW,Uri.parse("http://play.google.com/store/apps/developer?id=Developer+Name+Here")));
                        }
                        break;

                }

                drawer.closeDrawers();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id ==R.id.action_privacy) {
            Intent intent = new Intent(HomeActivity.this,PolicyActivity.class);
            startActivity(intent);
            finish();


            return true;

        }
        else
            if (id == R.id.action_about) {
                StyleableToast.makeText(getApplicationContext(),"Course On Computer Science",R.style.rightToast).show();
                return true;
            }

            else
            if (id == R.id.action_contact) {
                StyleableToast.makeText(getApplicationContext(),"mahatotinku83@gmail.com",R.style.rightToast).show();
                return true;
            }
            else
            if (id == R.id.action_exit) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Do you want to exit ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                HomeActivity.super.onBackPressed();



                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();


                return true;
            }
            else
        return super.onOptionsItemSelected(item);
    }
}
