package fr.guillaumeboutin.virtravel;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import fr.guillaumeboutin.virtravel.Adapter.TimelineRow;
import fr.guillaumeboutin.virtravel.Adapter.TimelineViewAdapter;
import fr.guillaumeboutin.virtravel.Classes.StepTravel;
import fr.guillaumeboutin.virtravel.Classes.Travel;
import fr.guillaumeboutin.virtravel.Manager.RealmManager;

public class StepTravelActivity extends AppCompatActivity implements OnMapReadyCallback {

    //Create Timeline Rows List
    private ArrayList<TimelineRow> TimelineRowsList = new ArrayList<>();
    ArrayAdapter<TimelineRow> myAdapter;
    Travel travel;
    private RealmManager rm;
    private Context ctx;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steptravel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        ctx = this;
        rm = new RealmManager(ctx);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if(b!=null)
        {
            int position =(int) b.get("position");
            travel = rm.getTravels().get(position);
            getSupportActionBar().setTitle(travel.getName());
        }

        final int color = getRandomColor();

        // Add Random Rows to the List
        for (int i=0; i<3; i++) {
            TimelineRowsList.add(
                    new TimelineRow(
                            //Row Id
                            i
                            //Row Date
                            ,getRandomDate()
                            //Row Title or null
                            ,"Title "+i
                            //Row Description or null
                            ,"Description " +i
                            //Row bitmap Image or null
                            , null
                            //Row Bellow Line Color
                            , color
                            //Row Bellow Line Size in dp
                            , 2
                            //Row Image Size in dp
                            , 25
                            //Row image Background color or -1
                            , color
                            //Row Background Size in dp or -1
                            , 25
                            , color
                            , color
                            , color
                            , BitmapFactory.decodeResource(this.getResources(), R.drawable.panorama_preview_6)
                    )
            );
        }

        //Create the Timeline Adapter
        myAdapter = new TimelineViewAdapter(this, 0, TimelineRowsList,
                //if true, list will be arranged by date
                true);



        //Get the ListView and Bind it with the Timeline Adapter
        ListView myListView = (ListView) findViewById(R.id.timelineListView);
        ViewCompat.setNestedScrollingEnabled(myListView, true);
        myListView.setAdapter(myAdapter);



        myListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int currentVisibleItemCount;
            private int currentScrollState;
            private int currentFirstVisibleItem;
            private int totalItem;
            private LinearLayout lBelow;


            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub
                this.currentScrollState = scrollState;
                this.isScrollCompleted();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                // TODO Auto-generated method stub
                this.currentFirstVisibleItem = firstVisibleItem;
                this.currentVisibleItemCount = visibleItemCount;
                this.totalItem = totalItemCount;


            }

            private void isScrollCompleted() {
                /*if (totalItem - currentFirstVisibleItem == currentVisibleItemCount
                        && this.currentScrollState == SCROLL_STATE_IDLE) {
                    *//** To do code here*//*

                    for (int i=0; i<15; i++) {
                        myAdapter.add(
                                new TimelineRow(
                                        //Row Id
                                        i
                                        //Row Date
                                        ,getRandomDate()
                                        //Row Title or null
                                        ,"Title "+i
                                        //Row Description or null
                                        ,"Description " +i
                                        //Row bitmap Image or null
                                        , null
                                        //Row Bellow Line Color
                                        , color
                                        //Row Bellow Line Size in dp
                                        , 2
                                        //Row Image Size in dp
                                        , 25
                                        //Row image Background color or -1
                                        , color
                                        //Row Background Size in dp or -1
                                        , 25
                                )
                        );
                    }

                }*/
            }


        });

        //if you wish to handle the clicks on the rows
        AdapterView.OnItemClickListener adapterListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TimelineRow row = TimelineRowsList.get(position);
                Toast.makeText(StepTravelActivity.this, row.getTitle(), Toast.LENGTH_SHORT).show();
                myAdapter.insert(new TimelineRow(
                                //Row Id
                                TimelineRowsList.size()
                                //Row Date
                                ,new Date()
                                //Row Title or null
                                ,"Title "+TimelineRowsList.size()
                                //Row Description or null
                                ,"Description " +TimelineRowsList.size()
                                //Row Image
                                ,null
                                //Row Bellow Line Color
                                , getRandomColor()
                                //Row Bellow Line Size in dp
                                , getRandomNumber(2,25)
                                //Row Image Size in dp
                                , getRandomNumber(25,40)
                                //Row Background color or -1
                                , getRandomColor()
                                //Row Background Size in dp or -1
                                , getRandomNumber(25,40)
                        )
                        //insert position
                        ,0)
                ;
            }
        };
        myListView.setOnItemClickListener(adapterListener);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    //Random Methods
    public int getRandomColor(){
        Random rnd = new Random();
        //return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

        return Color.argb(255, 13, 71, 161);
    }

    public int getRandomNumber(int min, int max){
        return  min + (int)(Math.random() * max);
    }



    public Date getRandomDate () {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = null;
        Date endDate = new Date();
        try {
            startDate = sdf.parse("02/09/2015");
            long random = ThreadLocalRandom.current().nextLong(startDate.getTime(), endDate.getTime());
            endDate = new Date(random);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return endDate;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_travel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {


        //List<LatLng> etapes = new ArrayList();
        List<StepTravel> etapes = new ArrayList();

        // Add a marker in Sydney and move the camera
        etapes.add(new StepTravel(0,travel.getId(), travel.getName(), "", -34.00, 151.00));
        etapes.add(new StepTravel(0,travel.getId(), travel.getName(), "", -32.00, 147.00));
        etapes.add(new StepTravel(0,travel.getId(), travel.getName(), "", -28.00, 144.00));

        this.mMap = googleMap;

        setEtapesOnMap(etapes);
    }

    public void setEtapesOnMap(List<StepTravel> etapes) {
        //Instanciation
        PolylineOptions rectOptions = new PolylineOptions();

        for (StepTravel etape: etapes) {
            //Ajout du marqueur
            this.mMap.addMarker(new MarkerOptions().position(new LatLng(etape.getCoordLong(), etape.getCoordLatt())).title(etape.getName()));

            //Ajout du Polyline
            rectOptions.add(new LatLng(etape.getCoordLong(), etape.getCoordLatt()));
        }

        this.mMap.addPolyline(rectOptions);
        this.mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(etapes.get(0).getCoordLong(), etapes.get(0).getCoordLatt())));
    }
}
