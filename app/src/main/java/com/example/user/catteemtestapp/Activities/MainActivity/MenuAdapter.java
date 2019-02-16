package com.example.user.catteemtestapp.Activities.MainActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.catteemtestapp.Model.CategoriesPogo;
import com.example.user.catteemtestapp.R;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.Myviewholder> {
    private List<CategoriesPogo> categoriesPogo;
    private  Context context;

    public MenuAdapter(List<CategoriesPogo> categoriesPogo, Context context) {
        this.categoriesPogo = categoriesPogo;
        this.context = context;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.single_menu_item ,viewGroup,false);
        Myviewholder myviewholder = new Myviewholder( view );
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder myviewholder, int i) {
        ImageView imageview = myviewholder.imageviewlol;
        TextView textView = myviewholder.textView;
        String imagerl = categoriesPogo.get( i ).getCm_cat_image();
        Glide.with( context ).load( imagerl ).into( imageview );
        textView.setText( categoriesPogo.get( i ).getCm_cat_name());
    }

    @Override
    public int getItemCount() {
        return categoriesPogo.size();
    }

    public static  class Myviewholder extends RecyclerView.ViewHolder {
        ImageView imageviewlol ;
        TextView textView;
        public Myviewholder(@NonNull View itemView) {
            super( itemView );
            this.imageviewlol  = itemView.findViewById( R.id.menu_image);
            this.textView = itemView.findViewById( R.id.menu_title);

        }
    }


}
