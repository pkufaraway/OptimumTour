import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class GreedyDistanceTouring implements Algorithm{
    public Site findBestStartingPoint(Set<Site> unvisitedSites) {
        double maxValue = 0;
        Site bestSite = unvisitedSites.iterator().next();

        for (Site site : unvisitedSites) {
            if (site.value > maxValue) {
                maxValue = site.value;
                bestSite = site;
            }
        }

        return bestSite;
    }

    public Site findNextSite(int currentTime, Set<Site> unvisitedSites, Site currentSite, int currentDay) {
        int closestDistance = Integer.MAX_VALUE;
        Site closestSite = null;

        for (Site possibleSite : unvisitedSites) {
            int totalDistance = Math.abs(currentSite.avenue - possibleSite.avenue)
                    + Math.abs(currentSite.street - possibleSite.street);

            if (totalDistance < closestDistance &&
                    Helper.couldVisit(possibleSite, currentTime, currentSite, currentDay)) {
                closestDistance = totalDistance;
                closestSite = possibleSite;

            }
        }
        return closestSite;
    }
}
