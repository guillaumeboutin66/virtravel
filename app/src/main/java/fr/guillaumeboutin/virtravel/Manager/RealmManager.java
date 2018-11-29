package fr.guillaumeboutin.virtravel.Manager;

import android.content.Context;

import fr.guillaumeboutin.virtravel.Classes.StepTravel;
import fr.guillaumeboutin.virtravel.Classes.Travel;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by guillaumeboutin on 27/03/2017.
 */

public class RealmManager {
    Realm realm;


    public RealmManager(Context ctx){
        Realm.init(ctx);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        // Use the config
        realm = Realm.getInstance(config);
    }

    public Realm getRealm(){
        return realm;
    }

    public int getNextKey()
    {
        try {
            return realm.where(Travel.class).max("id").intValue() + 1;
        } catch (Exception e)
        {
            return 0;
        }
    }

    public int getNextKeyPicture()
    {
        try {
            return realm.where(StepTravel.class).max("id").intValue() + 1;
        } catch (Exception e)
        {
            return 0;
        }
    }
    public RealmResults<Travel> getTravels(){
        return realm.where(Travel.class).findAll();
    }

    public RealmResults<StepTravel> getPicturesByTravel(int idTravel){
        return realm.where(StepTravel.class).equalTo("idTravel", idTravel).findAll();
    }

}
