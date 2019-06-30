package com.assign.test.adapter;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assign.test.Model.ArticleModel;
import com.assign.test.R;
import com.assign.test.databinding.ArticleItemBinding;

import java.util.List;

public class ArticleAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<ArticleModel> articleList;
    public ArticleAdapter(List<ArticleModel> articleList){
        this.articleList=articleList;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
      
         ArticleItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.article_item, parent, false);
            return new MyHolder(binding);
        
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder)
            ((MyHolder) holder).bindData();

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    private class MyHolder extends RecyclerView.ViewHolder {
        public MyHolder(ArticleItemBinding binding) {
            super(binding.getRoot());
        }

        public void bindData() {
        }
    }
}
