package com.assign.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.assign.test.baseview.BaseFragment;
import com.assign.test.fragment.DashBoardFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment(DashBoardFragment.TAG, null, true);
    }
    @SuppressWarnings("deprecated")
    public void addFragment(String fragmentName, Bundle bundle, boolean addToBackStack) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        BaseFragment top = (BaseFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        if (top != null) {

        }
        fragmentTransaction.add(R.id.container, Fragment.instantiate(this, fragmentName, bundle), fragmentName);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragmentName);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }
}

