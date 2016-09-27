import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class GreedyDistanceTouring implements Algorithm{
    /***
     * The naive way to find the best start point today is to find today's best value;
     * @param unvisitedSites
     * @param currentDay
     * @return
     */
    public Site findBestStartingPoint(Set<Site> unvisitedSites, int currentDay) {
        double maxValue = 0;
        Site bestSite = unvisitedSites.iterator().next();
        for (Site site : unvisitedSites) {
            double convertedValue = 0;
            if(Helper.couldVisit(site, 0, site, currentDay)){
                for (Site nearSite : unvisitedSites){
                    if(Helper.couldVisit(nearSite, Helper.visit(site,0,site,currentDay), site, currentDay)){
                        convertedValue += nearSite.value/(Helper.distance(site, nearSite) + 1);
                    }
                }
                if (convertedValue > maxValue && Helper.couldVisit(site,0,site,currentDay)) {
                    maxValue = convertedValue;
                    bestSite = site;
                }
            }
        }

        return bestSite;
    }

    public Site findNextSite(int currentTime, Set<Site> unvisitedSites, Site currentSite, int currentDay) {
        int closestDistance = Integer.MAX_VALUE;
        Site closestSite = null;

        for (Site possibleSite : unvisitedSites) {
            int totalDistance = Helper.distance(currentSite, possibleSite);

            if (totalDistance < closestDistance &&
                    Helper.couldVisit(possibleSite, currentTime, currentSite, currentDay)) {
                closestDistance = totalDistance;
                closestSite = possibleSite;

            }
        }
        return closestSite;
    }
}
