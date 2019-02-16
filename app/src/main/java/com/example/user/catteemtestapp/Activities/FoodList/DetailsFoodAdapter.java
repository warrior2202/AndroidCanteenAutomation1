package com.example.user.catteemtestapp.Activities.FoodList;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.user.catteemtestapp.Activities.Addtocart.DBHelper;
import com.example.user.catteemtestapp.Activities.Addtocart.DatabaseHelper;
import com.example.user.catteemtestapp.Activities.Addtocart.ItemDetailsActivity;
import com.example.user.catteemtestapp.Activities.DetailSingleItem.detailsorder;
import com.example.user.catteemtestapp.Model.Foodpogo;
import com.example.user.catteemtestapp.R;

import java.util.List;

public class DetailsFoodAdapter extends RecyclerView.Adapter<DetailsFoodAdapter.Myviewholder> {
    private List<Foodpogo> foodpogo;
    private Context context;
    public static int iqw;
    private SQLiteDatabase mDatabase;

    public DetailsFoodAdapter(List<Foodpogo> foodpogo, Context context) {
        this.foodpogo = foodpogo;
        this.context = context;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.activity_detail_single_item ,viewGroup,false);
        Myviewholder myviewholder = new Myviewholder( view );
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Myviewholder myviewholder, final int i) {
        ImageView imageview = myviewholder.imageviewlol;
        TextView textView = myviewholder.textView;
        TextView textView1 = myviewholder.textprice;




        ImageButton imageButton1 = myviewholder.imageButton;
        String imagerl = foodpogo.get( i ).getCm_item_image();
        Glide.with( context ).load( imagerl ).into( imageview );

        textView.setText( foodpogo.get( i ).getCm_item_name());
        textView1.setText( foodpogo.get( i ).getCm_item_price() );
        imageview.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent details  = new Intent( v.getContext(), ItemDetailsActivity.class );
                details.putExtra( "image_url",foodpogo.get( i ).getCm_item_image() );
                details.putExtra( "item_id",foodpogo.get( i ).getCm_item_id());
                details.putExtra( "cat_id",foodpogo.get( i ).getCm_item_cat_id() );
                details.putExtra( "price",foodpogo.get( i ).getCm_item_price() );
                details.putExtra( "name",foodpogo.get(i).getCm_item_name() );
                details.putExtra( "des",foodpogo.get( i ).getCm_item_desc() );
                context.startActivity(details);




            }
        } );
        imageButton1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( v.getContext(),foodpogo.get( i ).getCm_item_name(),Toast.LENGTH_LONG ).show();
                DBHelper dbHelper = new DBHelper(v.getContext());
                mDatabase = dbHelper.getWritableDatabase();
                String SelectedQuantity = "1";
                String QuantityPrice=foodpogo.get( i ).getCm_item_price();
                String itemInstruction = "none";

                ContentValues cv = new ContentValues();
                cv.put( DatabaseHelper.CartEntry.COL_1,"1" );
                cv.put(DatabaseHelper.CartEntry.COL_2, foodpogo.get( i ).getCm_item_cat_id());
                cv.put(DatabaseHelper.CartEntry.COL_3, foodpogo.get( i ).getCm_item_id());
                cv.put(DatabaseHelper.CartEntry.COL_4, foodpogo.get( i ).getCm_item_name());
                cv.put(DatabaseHelper.CartEntry.COL_5, foodpogo.get( i ).getCm_item_image());
                cv.put(DatabaseHelper.CartEntry.COL_6, foodpogo.get( i ).getCm_item_price());
                cv.put(DatabaseHelper.CartEntry.COL_7, SelectedQuantity);
                cv.put(DatabaseHelper.CartEntry.COL_8, itemInstruction);
                cv.put(DatabaseHelper.CartEntry.COL_9, QuantityPrice);

                mDatabase.insert(DatabaseHelper.CartEntry.TABLE_NAME, null, cv);
                Snackbar.make( v, "Your Item is added to the cart", Snackbar.LENGTH_LONG )
                        .setAction( "Action", null ).show();



            }
        } );

    }

    @Override
    public int getItemCount() {
        return foodpogo.size();
    }

    public static  class Myviewholder extends RecyclerView.ViewHolder {
        ImageView imageviewlol ;
        TextView textView,textprice;
        ImageButton imageButton;

        public Myviewholder(@NonNull View itemView) {
            super( itemView );
            this.imageviewlol  = itemView.findViewById( R.id.image_list);
            this.textView = itemView.findViewById( R.id.title_text);
            this.textprice = itemView.findViewById( R.id.price_text );
            this.imageButton = itemView.findViewById( R.id.addbutton );

        }
    }


}


