import java.util.Set;

public class GreedyValueTouring implements Algorithm{

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
                if (convertedValue > maxValue) {
                    maxValue = convertedValue;
                    bestSite = site;
                }
            }
        }

        return bestSite;
    }

    /***
     * Finds the nearest open site that hasn't been previously visited.
     * @param currentTime current time
     * @return The nearest open, unvisited site
     */
    public Site findNextSite(int currentTime, Set<Site> unvisitedSites, Site currentSite, int currentDay) {
        float largestValue = 0;
        Site largestValueSite = null;

        for (Site possibleSite : unvisitedSites) {
            if (Helper.couldVisit(possibleSite, currentTime, currentSite, currentDay)) {
                if(possibleSite.value > largestValue) {
                    largestValue = possibleSite.value;
                    largestValueSite = possibleSite;
                }
            }
        }
        return largestValueSite;
    }
}
