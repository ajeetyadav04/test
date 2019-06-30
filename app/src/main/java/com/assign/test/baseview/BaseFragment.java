package com.assign.test.baseview;

import android.content.Context;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.assign.test.MainActivity;

import com.assign.test.utils.BasicUtility;

public abstract class BaseFragment extends Fragment {


    private boolean isSession;


    @Override
    public void onAttach(@NonNull Context context) {
        BasicUtility.hideSoftKeyboard(getActivity());
        try{

        }
        catch (ClassCastException exp){

        }
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        BasicUtility.hideSoftKeyboard(getActivity());
        super.onDetach();
    }

    protected void showNormalMessage(String s) {
        if (!TextUtils.isEmpty(s)) {
            BasicUtility.hideSoftKeyboard(getActivity());
            BasicUtility.showSnackBar(getView(), s);
        }
    }



    protected synchronized void handleApiErrors(BaseModel baseModel) {


        if (baseModel.getBaseException() != null) {
            if (!BasicUtility.isEmptyString(baseModel.getBaseException().getMessage()))
                showNormalMessage(baseModel.getBaseException().getMessage());
            else
                showNormalMessage("Unknown Error");
        } else if (baseModel.getBaseError() != null) {
            if (!BasicUtility.isEmptyString(baseModel.getBaseError().getMessage()))
                showNormalMessage(baseModel.getBaseError().getMessage());
            else if (!BasicUtility.isEmptyString(baseModel.getBaseError().getTitle()))
                showNormalMessage(baseModel.getBaseError().getTitle());
            else
                showNormalMessage("Some error occurred");
        } else if (baseModel.isUnknownFailure()) {
            showNormalMessage("Unknown Error occurred");
        }

    }





    protected void setBaseToolbar(@NonNull Toolbar toolbar) {
        if (getActivity() != null)
            if (getActivity() instanceof MainActivity) {

                ((MainActivity) getActivity()).setSupportActionBar(toolbar);
                ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
            }
    }

    public void clearBackStackChild() {
        FragmentManager fm = getChildFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }




}
