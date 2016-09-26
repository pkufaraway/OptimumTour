import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FindRoute {
    private final List<Site> sites;
    private int currentDay;
    private int totalDays;
    private final Set<Site> unvisitedSites;
    private Site currentSite;
    private int currentTime;

    public FindRoute(List<Site> sites, int days) {
        this.sites = new ArrayList<Site>(sites);
        this.totalDays = days;
        unvisitedSites = new HashSet<Site>(sites);
    }

    public List<List<Site>> findRoute(Algorithm myAlgorithm) {
        final List<List<Site>> finalRoute = new ArrayList<List<Site>>();

        for (currentDay = 1; currentDay <= totalDays; currentDay++) {
            List<Site> todaysRoute = new ArrayList<Site>();

            currentSite = myAlgorithm.findBestStartingPoint(unvisitedSites);
            currentTime = currentSite.openingHour[currentDay] * 60;
            currentTime += currentSite.desiredTime;

            todaysRoute.add(currentSite);
            unvisitedSites.remove(currentSite);

            while (!endOfTheDay(currentTime)) {
                Site newSite = myAlgorithm.findNextSite(currentTime, unvisitedSites, currentSite, currentDay);
                currentTime += Math.abs(newSite.avenue - currentSite.avenue)
                        + Math.abs(newSite.street - currentSite.street);

                if (currentTime < currentSite.openingHour[currentDay] * 60) {
                    currentTime = currentSite.openingHour[currentDay] * 60;
                }

                currentTime += currentSite.desiredTime;
                todaysRoute.add(newSite);
                unvisitedSites.remove(newSite);
                currentSite = newSite;
            }
            finalRoute.add(todaysRoute);
        }
        return finalRoute;
    }

    /***
     * Return true if there are no more sites you can visit in a given day. This
     * will happen if all the sites are close or you can't travel to a site and
     * complete it before it closes
     * @param currentTime current time
     * @return Whether another site can be added to the route.
     */
    private boolean endOfTheDay(int currentTime) {
        for (Site possibleSite : unvisitedSites) {
            if (Helper.couldVisit(possibleSite, currentTime, currentSite, currentDay)) {
                return false;
            }
        }
        return true;
    }
}
