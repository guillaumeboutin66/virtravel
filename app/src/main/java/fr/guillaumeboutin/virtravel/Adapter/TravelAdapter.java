package fr.guillaumeboutin.virtravel.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.guillaumeboutin.virtravel.Classes.Travel;
import fr.guillaumeboutin.virtravel.Manager.RealmManager;
import fr.guillaumeboutin.virtravel.R;
import fr.guillaumeboutin.virtravel.StepTravelActivity;

/** Created by guillaumeboutin on 27/03/2017.
 */

public class TravelAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Travel> travels = null;
    private Context context;
    private RealmManager rm;

    public TravelAdapter(Context context) {
        this.context = context;
        this.rm = new RealmManager(context);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<Travel> travels) {
        this.travels = travels;
    }

    @Override
    public int getCount() {
        if (travels == null) {
            return 0;
        }
        return travels.size();
    }

    @Override
    public Travel getItem(int position) {
        if (travels == null || travels.get(position) == null) {
            return null;
        }
        return travels.get(position);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).getId();
    }

    @Override
    public View getView(final int position, View currentView, ViewGroup parent) {
        if (currentView == null) {
            currentView = inflater.inflate(R.layout.travel_listitem, parent, false);
        }

        Travel travel = travels.get(position);

        if (travel != null) {
            ((ImageView) currentView.findViewById(R.id.travelPic)).setImageBitmap(BitmapFactory.decodeResource(currentView.getResources(), getRandomPicture(position)));
            ((TextView) currentView.findViewById(R.id.nameTravel)).setText(travel.getName() + "( "+ (travel.getId() >= 0 ? ""+travel.getId()  : (""+0)) + ")");
            ((TextView) currentView.findViewById(R.id.idTravel)).setText("by Guillaume Boutin");
        }

        currentView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(context);
                }
                builder.setTitle("Supprimer le projet")
                        .setMessage("Etes-vous sûr de vouloir supprimer ce voyage ?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                rm.getRealm().beginTransaction();
                                Travel travel = rm.getRealm().where(Travel.class).equalTo("id",getItemId(position)).findFirst();
                                travel.deleteFromRealm();
                                //travels.remove(position);
                                rm.getRealm().commitTransaction();
                                refresh();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return true;
            }
        });


        currentView.setOnClickListener(

                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        startAct(position);
                    }
                }
        );

        return currentView;
    }

    private void refresh(){
        TravelAdapter.this.notifyDataSetChanged();
    }

    private void startAct(int position){
        Intent intent = new Intent(context, StepTravelActivity.class);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }


    public int getRandomPicture(Integer position){
        List<Integer> pictures = Arrays.asList(R.drawable.panorama_preview_0
                ,R.drawable.panorama_preview_1
                ,R.drawable.panorama_preview_2
                ,R.drawable.panorama_preview_3
                ,R.drawable.panorama_preview_4
                ,R.drawable.panorama_preview_5
                ,R.drawable.panorama_preview_6
                ,R.drawable.panorama_preview_7);
        return  pictures.get(position % 7);
    }
}
