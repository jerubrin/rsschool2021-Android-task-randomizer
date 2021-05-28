package com.rsschool.android2021;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FragmentsSwitcher {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(0, "", "");
        getSupportActionBar().hide();
    }

    private void openFirstFragment(int previousNumber, String min, String max) {
        getFragmentManager().popBackStack();
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber, min, max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment);
        transaction.commit();
    }

    private void openSecondFragment(String min, String max) {
        final Fragment secondFragment = SecondFragment.newInstance(min, max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, secondFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void switchToFirstFragment(int previousNumber, String min, String max) {
        openFirstFragment(previousNumber, min, max);
    }

    @Override
    public void switchToSecondFragment(String min, String max) {
        openSecondFragment(min, max);
    }
}
