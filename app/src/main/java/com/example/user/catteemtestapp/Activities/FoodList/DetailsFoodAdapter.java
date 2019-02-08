package com.example.user.catteemtestapp.Activities.FoodList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.catteemtestapp.Model.Foodpogo;
import com.example.user.catteemtestapp.R;

import java.util.List;

public class DetailsFoodAdapter extends RecyclerView.Adapter<DetailsFoodAdapter.Myviewholder> {
    private List<Foodpogo> foodpogo;
    private Context context;

    public DetailsFoodAdapter(List<Foodpogo> foodpogo, Context context) {
        this.foodpogo = foodpogo;
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
        String imagerl = foodpogo.get( i ).getCm_item_image();
        Glide.with( context ).load( imagerl ).into( imageview );
        textView.setText( foodpogo.get( i ).getCm_item_name());

    }

    @Override
    public int getItemCount() {
        return foodpogo.size();
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


