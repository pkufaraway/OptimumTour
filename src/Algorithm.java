import java.util.Set;

/**
 * Created by yaoyuanliu on 9/26/16.
 */
public interface Algorithm {
    public Site findBestStartingPoint(Set<Site> unvisitedSites);
    public Site findNextSite(int currentTime, Set<Site> unvisitedSites, Site currentSite, int currentDay);
}
