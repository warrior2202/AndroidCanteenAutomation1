package com.example.user.catteemtestapp.Activities.FoodList;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.user.catteemtestapp.Model.Foodpogo;
import com.example.user.catteemtestapp.R;
import com.example.user.catteemtestapp.rest.ApiClient;
import com.example.user.catteemtestapp.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsFoodList extends AppCompatActivity {
    private List<Foodpogo> foodpogo ;
    private RecyclerView recyclerView;
    private ProgressDialog pDialog;
    private RecyclerView.LayoutManager layoutManager;
    private String cat_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_details_food_list );
        cat_id =getIntent().getStringExtra( "Cat_id" );
        recyclerView = findViewById( R.id.recycler_food);
        recyclerView.setHasFixedSize( true );
        layoutManager = new GridLayoutManager( DetailsFoodList.this,1 );
        recyclerView.setLayoutManager( layoutManager );
        Food();
        showProgDig();


    }
    private void Food(){
        ApiInterface apiService = ApiClient.getClient().create( ApiInterface.class );
        Call<List<Foodpogo>> call = apiService.food( cat_id );
        call.enqueue( new Callback<List<Foodpogo>>() {
            @Override
            public void onResponse(Call<List<Foodpogo>> call, Response<List<Foodpogo>> response) {
                if (pDialog.isShowing())
                    pDialog.dismiss();
                foodpogo = response.body();
                DetailsFoodAdapter detailsFoodAdapter = new DetailsFoodAdapter( foodpogo,DetailsFoodList.this );
                recyclerView.setAdapter( detailsFoodAdapter );


            }

            @Override
            public void onFailure(Call<List<Foodpogo>> call, Throwable t) {

            }
        } );


    }
    private void showProgDig() {
        pDialog = new ProgressDialog( DetailsFoodList.this );
        pDialog.setMessage( "Please wait..." );
        pDialog.setCancelable( true );
        pDialog.show();
    }
    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }
    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
        private GestureDetector gestureDetector;
        private ClickListener clickListener;
        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final DetailsFoodList.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }
        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }
        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }
        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }
}
