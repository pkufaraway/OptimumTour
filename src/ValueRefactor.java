import java.util.ArrayList;
import java.util.List;

public class ValueRefactor {

    public static List<Site> valueRefactor(List<Site> mySites){
        ArrayList<Site> newSites = new ArrayList<Site>(mySites);
        for (Site site: newSites){
            float hour = 0;
            for (int i = 0; i < site.openingHour.length; i++){
                if(site.endHour[i] - site.openingHour[i] > 0){
                    hour += (site.endHour[i] - site.openingHour[i]);
                }
            }
            site.convertedValue = site.value / (site.desiredTime * hour);
        }

        for (Site site: newSites){
            float density = 0;
            for (int i = 0; i < site.openingHour.length; i++){
                for (Site nearSite: newSites){
                    int distance = Helper.distance(site,nearSite);
                    if (distance <= 60 && Helper.couldVisit(nearSite, 0, nearSite, i)){
                        density += site.value / (1 + distance);
                    }
                }
                site.densityValue[i] = density;
            }

        }
        return newSites;
    }
}
