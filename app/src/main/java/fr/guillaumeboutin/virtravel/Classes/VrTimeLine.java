package fr.guillaumeboutin.virtravel.Classes;

import android.graphics.Bitmap;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import org.qap.ctimelineview.TimelineRow;

import java.util.Date;

public class VrTimeLine extends TimelineRow {
    private VrPanoramaView vrPanoramaView = null;

    public VrTimeLine(int id) {
        super(id);
    }

    public VrTimeLine(int id, Date date) {
        super(id, date);
    }

    public VrTimeLine(int id, Date date, String title, String description) {
        super(id, date, title, description);
    }

    public VrTimeLine(int id, Date date, String title, String description, Bitmap image, int bellowLineColor, int bellowLineSize, int imageSize) {
        super(id, date, title, description, image, bellowLineColor, bellowLineSize, imageSize);
    }

    public VrTimeLine(int id, Date date, String title, String description, Bitmap image, int bellowLineColor, int bellowLineSize, int imageSize, int backgroundColor, int backgroundSize) {
        super(id, date, title, description, image, bellowLineColor, bellowLineSize, imageSize, backgroundColor, backgroundSize);
    }

    public VrTimeLine(int id, Date date, String title, String description, Bitmap image, int bellowLineColor, int bellowLineSize, int imageSize, int backgroundColor, int backgroundSize, int dateColor, int titleColor, int descriptionColor) {
        super(id, date, title, description, image, bellowLineColor, bellowLineSize, imageSize, backgroundColor, backgroundSize, dateColor, titleColor, descriptionColor);
    }

    public VrPanoramaView getVrPanoramaView() {
        return vrPanoramaView;
    }

    public void setVrPanoramaView(VrPanoramaView vrPanoramaView) {
        this.vrPanoramaView = vrPanoramaView;
    }
}
