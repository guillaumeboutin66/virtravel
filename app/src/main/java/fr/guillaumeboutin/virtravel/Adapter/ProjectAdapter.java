package fr.guillaumeboutin.virtravel.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import fr.guillaumeboutin.virtravel.Classes.Travel;
import fr.guillaumeboutin.virtravel.Manager.RealmManager;
import fr.guillaumeboutin.virtravel.R;
import fr.guillaumeboutin.virtravel.StepTravelActivity;

/** Created by guillaumeboutin on 27/03/2017.
 */

public class ProjectAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Travel> projects = null;
    private Context context;
    private RealmManager rm;

    public ProjectAdapter(Context context) {
        this.context = context;
        this.rm = new RealmManager(context);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<Travel> projects) {
        this.projects = projects;
    }

    @Override
    public int getCount() {
        if (projects == null) {
            return 0;
        }
        return projects.size();
    }

    @Override
    public Travel getItem(int position) {
        if (projects == null || projects.get(position) == null) {
            return null;
        }
        return projects.get(position);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).getId();
    }

    @Override
    public View getView(final int position, View currentView, ViewGroup parent) {
        if (currentView == null) {
            currentView = inflater.inflate(R.layout.content_steptravel, parent, false);
        }

        Travel project = projects.get(position);

        if (project != null) {
            ((TextView) currentView.findViewById(R.id.nameTravel)).setText(project.getName());
            ((TextView) currentView.findViewById(R.id.idTravel)).setText(project.getId() >= 0 ? ""+project.getId()  : (""+0));
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
                        .setMessage("Etes-vous sûr de vouloir supprimer ce projet ?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                rm.getRealm().beginTransaction();
                                Travel travel = rm.getRealm().where(Travel.class).equalTo("id",getItemId(position)).findFirst();
                                travel.deleteFromRealm();
                                //projects.remove(position);
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
        ProjectAdapter.this.notifyDataSetChanged();
    }

    private void startAct(int position){
        Intent intent = new Intent(context, StepTravelActivity.class);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }
}
