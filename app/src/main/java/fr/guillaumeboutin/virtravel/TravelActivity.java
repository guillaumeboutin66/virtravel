package fr.guillaumeboutin.virtravel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import java.util.List;

import fr.guillaumeboutin.virtravel.Adapter.TravelAdapter;
import fr.guillaumeboutin.virtravel.Classes.Travel;
import fr.guillaumeboutin.virtravel.Manager.RealmManager;
import io.realm.Realm;

public class TravelActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener{
    private String m_Text = "";
    private Context ctx;
    private Realm realm;
    private RealmManager rm;
    private GridView mGridView;
    private TravelAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ctx = this;

        rm = new RealmManager(ctx);
        realm = rm.getRealm();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle("Créer un voyage virtuel");

                // Set up the input
                final EditText input = new EditText(ctx);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("Créer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();
                        realm.beginTransaction();
                        Travel trvl = realm.createObject(Travel.class);
                        trvl.setId(rm.getNextKey());
                        trvl.setName(m_Text);
                        realm.commitTransaction();
                        mAdapter.setData(rm.getTravels());

                        Log.e("Number", "Count travels : "+rm.getTravels().size());
                        mAdapter.notifyDataSetChanged();
                        mGridView.setAdapter(mAdapter);
                    }
                });
                builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                //Intent i = new Intent(TravelActivity.this, StepTravelActivity.class);
                //startActivity(i);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();

        // Load from file "cities.json" first time
        if(mAdapter == null) {
            List<Travel> travels = rm.getTravels();

            Log.e("Number", "Count travels : "+travels.size());


            //This is the GridView adapter
            mAdapter = new TravelAdapter(this);
            mAdapter.setData(travels);

            //This is the GridView which will display the list of cities
            mGridView = (GridView) findViewById(R.id.travels_list);
            mGridView.setAdapter(mAdapter);
            mGridView.setOnItemClickListener(TravelActivity.this);
            mAdapter.notifyDataSetChanged();
            mGridView.invalidate();

            mGridView.setOnItemClickListener(

                    new AdapterView.OnItemClickListener(){
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent(TravelActivity.this, StepTravelActivity.class);
                            i.putExtra("position", position);
                            startActivity(i);
                        }
                    }
            );

        }
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
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }


}
