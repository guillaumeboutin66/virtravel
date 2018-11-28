package fr.guillaumeboutin.virtravel;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import org.qap.ctimelineview.TimelineRow;
import org.qap.ctimelineview.TimelineViewAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import fr.guillaumeboutin.virtravel.Classes.VrTimeLine;

public class StepTravelActivity extends AppCompatActivity {

    //Create Timeline Rows List
    private ArrayList<TimelineRow> TimelineRowsList = new ArrayList<>();
    ArrayAdapter<TimelineRow> myAdapter;

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

        final int color = getRandomColor();

        // Add Random Rows to the List
        for (int i=0; i<15; i++) {
            TimelineRowsList.add(
                    new VrTimeLine(
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
                if (totalItem - currentFirstVisibleItem == currentVisibleItemCount
                        && this.currentScrollState == SCROLL_STATE_IDLE) {
                    /** To do code here*/

                    for (int i=0; i<15; i++) {
                        myAdapter.add(
                                new VrTimeLine(
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

                }
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


    }









    //Random Methods
    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
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
}
