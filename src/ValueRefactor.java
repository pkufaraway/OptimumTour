import java.util.ArrayList;
import java.util.List;

public class ValueRefactor {

    public static List<Site> valueRefactor(List<Site> mySites){
        ArrayList<Site> newSites = new ArrayList<Site>(mySites);
        for (Site site: newSites){
            float hour = 0;
            float day = 0;
            for (int i = 0; i < site.openingHour.length; i++){
                if(site.endHour[i] - site.openingHour[i] > 0){
                    hour += (site.endHour[i] - site.openingHour[i]);
                    day += 1;
                }
            }
            site.convertedValue = site.value / (site.desiredTime * hour);
        }
        return newSites;
    }
}
