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

import com.example.user.catteemtestapp.Activities.FoodList.DetailsFoodList;
import com.example.user.catteemtestapp.Activities.MainActivity.MainActivity;
import com.example.user.catteemtestapp.Activities.MainActivity.MenuAdapter;
import com.example.user.catteemtestapp.Model.CategoriesPogo;
import com.example.user.catteemtestapp.R;
import com.example.user.catteemtestapp.rest.ApiClient;
import com.example.user.catteemtestapp.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuFragment extends Fragment {



    private List<CategoriesPogo> categoriesPogo;
    private RecyclerView recyclerView1;
    private ProgressDialog pDialog;
    private RecyclerView.LayoutManager layoutManager;
    public static MenuFragment newInstance()
    {
        MenuFragment menuFragment=new MenuFragment();

        return menuFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate( R.layout.menu_fragment,null);
        recyclerView1 = (RecyclerView) rootView.findViewById(R.id.recycler_menu_fragment);
        recyclerView1.setHasFixedSize( true );
        layoutManager = new GridLayoutManager( getActivity(),1 );
        recyclerView1.setLayoutManager( layoutManager );
        LoadMenu();





        return rootView;
    }


    private void LoadMenu(){
        ApiInterface apiService = ApiClient.getClient().create( ApiInterface.class );
        Call<List<CategoriesPogo>> call = apiService.categoriesid();
        call.enqueue( new Callback<List<CategoriesPogo>>() {
            @Override
            public void onResponse(Call<List<CategoriesPogo>> call, Response<List<CategoriesPogo>> response) {
//                if(pDialog.isShowing())
//                    pDialog.dismiss();
                categoriesPogo = response.body();
                MenuAdapter menuAdapter = new MenuAdapter( categoriesPogo,getActivity() );
                recyclerView1.setAdapter( menuAdapter );
//                recyclerView.addOnItemTouchListener( new RecyclerTouchListener( getApplicationContext(), recyclerView, new ClickListener() {
//                    @Override
//                    public void onClick(View view, int position) {
//                        Intent Food = new Intent( MainActivity.this, DetailsFoodList.class );
//                        Food.putExtra( "Cat_id",categoriesPogo.get( position ).getCm_cat_id() );
//                        startActivity( Food );
//                    }
//
//                    @Override
//                    public void onLongClick(View view, int position) {
//
//                    }
//                } ) );


            }

            @Override
            public void onFailure(Call<List<CategoriesPogo>> call, Throwable t) {

            }
        } );


    }
    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }
    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
        private GestureDetector gestureDetector;
        private ClickListener clickListener;
        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MenuFragment.ClickListener clickListener) {
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
