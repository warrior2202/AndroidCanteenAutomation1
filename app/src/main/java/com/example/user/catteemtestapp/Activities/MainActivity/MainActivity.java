package com.example.user.catteemtestapp.Activities.MainActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.user.catteemtestapp.Activities.Addtocart.CartActivity;
import com.example.user.catteemtestapp.Activities.Addtocart.ItemDetailsActivity;
import com.example.user.catteemtestapp.Activities.FoodList.DetailsFoodList;
import com.example.user.catteemtestapp.Activities.Login.Login;
import com.example.user.catteemtestapp.Fragments.MenuFragment;
import com.example.user.catteemtestapp.Model.CategoriesPogo;
import com.example.user.catteemtestapp.R;
import com.example.user.catteemtestapp.rest.ApiClient;
import com.example.user.catteemtestapp.rest.ApiInterface;
import com.example.user.catteemtestapp.wallet;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private List<CategoriesPogo> categoriesPogo;
    private RecyclerView recyclerView;
    private ProgressDialog pDialog;
    private RecyclerView.LayoutManager layoutManager;
    private TextView textView;
    public static String wallet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        wallet = getIntent().getStringExtra( "wallet" );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.Fraagment_frame_Layout, MenuFragment.newInstance()).commit();

        String name = getIntent().getStringExtra( "nameuser" );

//        textView.setText( "MANAN SHAH" );
//        recyclerView = findViewById( R.id.recycler_menu );
//        recyclerView.setHasFixedSize( true );
//        layoutManager = new GridLayoutManager( MainActivity.this,1 );
//        recyclerView.setLayoutManager( layoutManager );
//        showProgDig();
//        LoadMenu();



//        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
//        fab.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG )
//                        .setAction( "Action", null ).show();
//            }
//        } );

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.Students_name_id);
        navUsername.setText( getIntent().getStringExtra( "nameuser" ) );
//        textView = findViewById( R.id.Students_name_id );
//        String namegh =
//        textView.setText( "MANAN SHAH" );

        navigationView.setNavigationItemSelectedListener( this );
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected( item );
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.nav_menu) {
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.Fraagment_frame_Layout, MenuFragment.newInstance()).commit();

        } else if (id == R.id.nav_cart) {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);

        } else if (id == R.id.nav_orders) {


        } else if (id == R.id.nav_wallet) {
            Intent intent1 = new Intent(MainActivity.this, wallet.class);
            intent1.putExtra( "wallet1",wallet );
            startActivity(intent1);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }

    private void showProgDig() {
        pDialog = new ProgressDialog( MainActivity.this );
        pDialog.setMessage( "Please wait..." );
        pDialog.setCancelable( true );
        pDialog.show();
    }

    private void LoadMenu(){
        ApiInterface apiService = ApiClient.getClient().create( ApiInterface.class );
        Call<List<CategoriesPogo>> call = apiService.categoriesid();
        call.enqueue( new Callback<List<CategoriesPogo>>() {
            @Override
            public void onResponse(Call<List<CategoriesPogo>> call, Response<List<CategoriesPogo>> response) {
                if(pDialog.isShowing())
                    pDialog.dismiss();
                categoriesPogo = response.body();
                MenuAdapter menuAdapter = new MenuAdapter( categoriesPogo,MainActivity.this );
                recyclerView.setAdapter( menuAdapter );
                recyclerView.addOnItemTouchListener( new RecyclerTouchListener( getApplicationContext(), recyclerView, new ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent Food = new Intent( MainActivity.this, DetailsFoodList.class );
                        Food.putExtra( "Cat_id",categoriesPogo.get( position ).getCm_cat_id() );
                        startActivity( Food );
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                } ) );


            }

            @Override
            public void onFailure(Call<List<CategoriesPogo>> call, Throwable t) {

            }
        } );


    }

    public void CartIntent(View view) {
        Intent intent = new Intent(MainActivity.this,CartActivity.class);
        startActivity(intent);
    }

    public void opnDrawer(View view)
    {
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.openDrawer(Gravity.START);


    }

    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }
    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
        private GestureDetector gestureDetector;
        private ClickListener clickListener;
        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MainActivity.ClickListener clickListener) {
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
