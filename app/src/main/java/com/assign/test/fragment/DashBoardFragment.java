package com.assign.test.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.assign.test.Model.ArticleModel;
import com.assign.test.R;
import com.assign.test.adapter.ArticleAdapter;
import com.assign.test.baseview.BaseFragment;
import com.assign.test.databinding.FragmentDasboardBinding;
import com.assign.test.utils.SharedPrefUtils;
import com.assign.test.viewmodel.CacheViewModel;
import com.assign.test.viewmodel.CacheViewModelFactory;


import java.util.List;

public class DashBoardFragment extends BaseFragment {
    public static final String TAG=DashBoardFragment.class.getName();
    private FragmentDasboardBinding binding;
    private CacheViewModel cacheViewModel;
    private CacheViewModelFactory cacheViewModelFactory;
    private LinearLayoutManager linearLayoutManager;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dasboard, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setLinearLayoutManager(new LinearLayoutManager(getActivity()));


        cacheViewModelFactory = new CacheViewModelFactory(SharedPrefUtils.getInstance(getActivity()),/* AppDatabaseClient.getInstance().getAppDatabase(getActivity()*/ null);

        cacheViewModel = ViewModelProviders.of(this).get(CacheViewModel.class);
        observeLiveData();

    }
    public void observeLiveData(){
        binding.setIsLoading(true);

       cacheViewModel.getAllArticles().observe(this, new Observer<List<ArticleModel>>() {
           @Override
           public void onChanged(List<ArticleModel> articleModels) {
               if(articleModels.size()>0){

                   binding.setIsLoading(false);
                   setAdapter(articleModels);
               }

           }

       });
        binding.setIsLoading(false);
    }

    private void setAdapter(List<ArticleModel> articleModels) {
        ArticleAdapter adapter=new ArticleAdapter(articleModels);
        binding.setAdapter(adapter);


    }
}
