import java.util.Set;

public interface Algorithm {
    Site findBestStartingPoint(Set<Site> unvisitedSites);
    Site findNextSite(int currentTime, Set<Site> unvisitedSites, Site currentSite, int currentDay);
}
