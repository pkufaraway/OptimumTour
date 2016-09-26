import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class GreedyValueTouring {
    private final List<Site> sites;
    private int currentDay;
    private int totalDays;
    private final Set<Site> unvisitedSites;
    private int currentAve;
    private int currentStreet;
    private int currentTime;

    public GreedyValueTouring(List<Site> sites, int days) {
        this.sites = new ArrayList<Site>(sites);
        this.totalDays = days;
        unvisitedSites = new HashSet<Site>(sites);
    }

    public List<List<Site>> findRoute() {
        final List<List<Site>> finalRoute = new ArrayList<List<Site>>();

        for (currentDay = 1; currentDay <= totalDays; currentDay++) {
            List<Site> todaysRoute = new ArrayList<Site>();

            Site currentSite = findBestStartingPoint();
            currentAve = currentSite.avenue;
            currentStreet = currentSite.street;
            currentTime = currentSite.openingHour[currentDay];
            currentTime += currentSite.desiredTime;

            todaysRoute.add(currentSite);
            unvisitedSites.remove(currentSite);

            while (!endOfTheDay(currentTime)) {
                currentSite = findMostValuableOpenSite(currentTime);

                currentTime += Math.abs(currentAve - currentSite.avenue)
                        + Math.abs(currentStreet - currentSite.street);
                currentStreet = currentSite.street;
                currentAve = currentSite.avenue;

                if (currentTime < currentSite.openingHour[currentDay] * 60) {
                    currentTime = currentSite.openingHour[currentDay] * 60;
                }

                currentTime += currentSite.desiredTime;
                todaysRoute.add(currentSite);
                unvisitedSites.remove(currentSite);
            }
            finalRoute.add(todaysRoute);
        }
        return finalRoute;
    }

    private Site findBestStartingPoint() {
        double maxValue = 0;
        Site bestSite = sites.get(0);

        for (Site site : unvisitedSites) {
            if (site.value > maxValue) {
                maxValue = site.value;
                bestSite = site;
            }
        }

        return bestSite;
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
            if (couldVisit(possibleSite, currentTime)) {
                return false;
            }
        }
        return true;
    }

    /***
     * Finds the nearest open site that hasn't been previously visited.
     * @param currentTime current time
     * @return The nearest open, unvisited site
     */
    private Site findMostValuableOpenSite(int currentTime) {
        float largestValue = 0;
        Site largestValueSite = null;

        for (Site possibleSite : unvisitedSites) {
            if (possibleSite.value > largestValue && couldVisit(possibleSite, currentTime)) {
                largestValue = possibleSite.value;
                largestValueSite = possibleSite;
            }
        }
        return largestValueSite;
    }

    /**
     * Check if a site is possible to go today, given currentTime
     *
     * @param possibleSite the site that we want to visit
     * @param currentTime  the time of the day
     * @return true if we could get the value going there, false if we could not
     */
    private boolean couldVisit(Site possibleSite, int currentTime) {
        //Add walking time to currentTime;
        currentTime += Math.abs(currentAve - possibleSite.avenue)
                + Math.abs(currentStreet - possibleSite.street);

        // Wait if necessary
        if (currentTime < possibleSite.openingHour[currentDay] * 60) {
            currentTime = possibleSite.openingHour[currentDay] * 60;
        }

        // If the place is already closed, or we won't have enough time, we can't visit
        return !((currentTime + possibleSite.desiredTime) >= possibleSite.endHour[currentDay] * 60);
    }
}
