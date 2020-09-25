package com.ma7moud3ly.a180degree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.ma7moud3ly.a180degree.fragments.LoginFragment;
import com.ma7moud3ly.a180degree.fragments.SplashFragment;

public class MainActivity extends AppCompatActivity implements NavigationHost {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //navigate to splash activity.
        navigateTo(new SplashFragment(), null, false, false);
    }


    //navigate between activities with slide animation.
    @Override
    public void navigateTo(Fragment fragment, Bundle bundle, boolean addToBackStack, boolean animate) {
        if (bundle != null) fragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (animate)
            transaction.setCustomAnimations(R.anim.slide_right, R.anim.slide_left);
        transaction.replace(R.id.container, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        try {
            int c = getSupportFragmentManager().getBackStackEntryCount();
            if (c == 0) finish();
            getSupportFragmentManager().popBackStack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}