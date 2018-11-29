package fr.guillaumeboutin.virtravel.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.util.List;

import fr.guillaumeboutin.virtravel.Classes.StepTravel;
import fr.guillaumeboutin.virtravel.R;

/**
 * Created by guillaumeboutin on 01/04/2017.
 */

public class ImageProjectAdapter  extends BaseAdapter {
    private LayoutInflater inflater;
    private List<StepTravel> stepTravels = null;

    public ImageProjectAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<StepTravel> stepTravels) {
        this.stepTravels = stepTravels;
    }

    @Override
    public int getCount() {
        if (stepTravels == null) {
            return 0;
        }
        return stepTravels.size();
    }

    @Override
    public StepTravel getItem(int position) {
        if (stepTravels == null || stepTravels.get(position) == null) {
            return null;
        }
        return stepTravels.get(position);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).getId();
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent) {
        if (currentView == null) {
            currentView = inflater.inflate(R.layout.image_listitem, parent, false);
        }

        StepTravel stepTravel = stepTravels.get(position);

        if (stepTravel != null) {
            //int id = currentView.getResources().getIdentifier("fr.guillaumeboutin.vrapplication1:drawable/" + stepTravel.getName(), null, null);
            VrPanoramaView vr = ((VrPanoramaView) currentView.findViewById(R.id.imageButton));
            vr.loadImageFromBitmap(BitmapFactory.decodeFile(stepTravel.getUrl()), null);

            vr.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return false;
                }
            });
        }

        return currentView;
    }
}
