package fr.guillaumeboutin.virtravel.Classes;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by guillaumeboutin on 27/03/2017.
 */

public class Travel extends RealmObject {
    private int id;
    private String name;
    private RealmList<StepTravel> stepTravels;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<StepTravel> getStepTravels() {
        return stepTravels;
    }

    public void setStepTravels(RealmList<StepTravel> stepTravels) {
        this.stepTravels = stepTravels;
    }

    public void addImages(StepTravel stepTravel) {
        this.stepTravels.add(stepTravel);
    }
}
