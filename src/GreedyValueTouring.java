import java.util.Set;

public class GreedyValueTouring implements Algorithm{

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
                int distance = Math.abs(currentSite.avenue - possibleSite.avenue) + Math.abs(currentSite.street - possibleSite.street);
                if(possibleSite.value / distance> largestValue) {
                    largestValue = possibleSite.value / distance;
                    largestValueSite = possibleSite;
                }
            }
        }
        return largestValueSite;
    }
}
