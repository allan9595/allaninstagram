package com.example.allanchang.allan_instagram;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.allanchang.allan_instagram.fragments.ComposeFragment;
import com.example.allanchang.allan_instagram.fragments.PostsFragment;
import com.example.allanchang.allan_instagram.fragments.ProfileFragment;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnLogout;



    private BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final FragmentManager fragmentManager = getSupportFragmentManager();
        bottomNavigationView = findViewById(R.id.bottom_navigation);




        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;

                switch (item.getItemId()) {
                    case R.id.action_home:
                        fragment = new PostsFragment();
                        Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_compose:
                        fragment = new ComposeFragment();
                        Toast.makeText(MainActivity.this, "compose", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_profile:

                        //Toast.makeText(MainActivity.this, "profile", Toast.LENGTH_SHORT).show();
                    default:
                        fragment = new ProfileFragment();
                        break;
                }

                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }

        });

        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);

    }
}


