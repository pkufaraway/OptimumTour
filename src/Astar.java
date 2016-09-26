import java.util.Set;

public class Astar implements Algorithm{

    public Site findBestStartingPoint(Set<Site> unvisitedSites) {
        double maxValue = 0;
        Site bestSite = unvisitedSites.iterator().next();

        for (Site site : unvisitedSites) {
            if (site.convertedValue > maxValue) {
                maxValue = site.convertedValue;
                bestSite = site;
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
                if(possibleSite.convertedValue> largestValue) {
                    largestValue = possibleSite.convertedValue;
                    largestValueSite = possibleSite;
                }
            }
        }
        return largestValueSite;
    }
}
