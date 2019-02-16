package com.example.user.catteemtestapp.Fragments;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.catteemtestapp.Activities.FoodList.DetailsFoodAdapter;
import com.example.user.catteemtestapp.Activities.DetailSingleItem.detailsorder;
import com.example.user.catteemtestapp.Model.Foodpogo;
import com.example.user.catteemtestapp.R;
import com.example.user.catteemtestapp.rest.ApiClient;
import com.example.user.catteemtestapp.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class FoodFragment extends Fragment {
    private List<Foodpogo> foodpogo ;
    private RecyclerView recyclerView2;
    private ProgressDialog pDialog;
    private RecyclerView.LayoutManager layoutManager;
    private String cat_id;
    private TextView heading;
    private String cat_nam;
    public static FoodFragment newInstance()
    {
        FoodFragment FoodFragment=new FoodFragment();
        return FoodFragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate( R.layout.food_fragment,null);
//        cat_id =getIntent().getStringExtra( "Cat_id" );
        Bundle bundle =getArguments();
        cat_id = bundle.getString( "cat_id" );
        cat_nam = bundle.getString( "cat_name" );
        heading = rootView.findViewById( R.id.heading_id );
        recyclerView2 = rootView.findViewById( R.id.recycler_food);
        recyclerView2.setHasFixedSize( true );
        heading.setText( cat_nam );
        layoutManager = new GridLayoutManager( getActivity(),2 );
        recyclerView2.setLayoutManager( layoutManager );
        Food();
        return  rootView;
    }
    private void Food(){
        ApiInterface apiService = ApiClient.getClient().create( ApiInterface.class );
        Call<List<Foodpogo>> call = apiService.food( cat_id );
        call.enqueue( new Callback<List<Foodpogo>>() {
            @Override
            public void onResponse(Call<List<Foodpogo>> call, Response<List<Foodpogo>> response) {
//                if (pDialog.isShowing())
//                    pDialog.dismiss();
                foodpogo = response.body();
                DetailsFoodAdapter detailsFoodAdapter = new DetailsFoodAdapter( foodpogo,getActivity() );
                recyclerView2.setAdapter( detailsFoodAdapter );
                recyclerView2.addOnItemTouchListener( new RecyclerTouchListener( getContext(), recyclerView2, new ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent abc = new Intent( getActivity(), detailsorder.class);
                    }
                    @Override
                    public void onLongClick(View view, int position) {

                    }
                } ) );
            }
            @Override
            public void onFailure(Call<List<Foodpogo>> call, Throwable t) {

            }
        } );


    }
    private void showProgDig() {
        pDialog = new ProgressDialog( getActivity() );
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
        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final FoodFragment.ClickListener clickListener) {
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
