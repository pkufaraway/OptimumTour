import javax.net.ssl.SSLContext;
import java.util.*;

class AstarSite extends Site{
    float fscore,hscore;
    int gscore;
    AstarSite parent;
    public AstarSite(Site site) {
        super(site);
        gscore = 0;
        fscore = 0;
        hscore = 0;
        parent = null;
    }
}

public class Astar{
    private int currentDay;
    private int totalDays;
    private Set<AstarSite> visitedSites;
    private AstarSite currentSite;
    private int currentTime;
    private List<AstarSite> astarSites;
    private PriorityQueue<AstarSite>  openList;
    private List<AstarSite> closeList;

    public Astar(List<Site> sites, int days){
        this.totalDays = days;
        closeList = new ArrayList<AstarSite>();
        openList = new PriorityQueue<AstarSite>(
            new Comparator<AstarSite>() {
                @Override
                public int compare(AstarSite s1, AstarSite s2)
                {
                    if(s1.fscore - s2.fscore <= 0){
                        return -1;
                    }
                    else return 1;
                }
            }
        );
        astarSites = new ArrayList<AstarSite>();
        for (Site site: sites){
            astarSites.add(new AstarSite(site));
        }
        visitedSites = new HashSet<AstarSite>();
        for (AstarSite site: astarSites){
            site.gscore = findNextAvaliableEndTime(site, site, 0, 1);
            site.hscore = computeHscore(site, 1, 0, site);
            site.fscore = site.gscore + site.hscore;
            if(site.gscore != -1){
                openList.add(site);
            }
        }

    }

    public float computeHscore(AstarSite site, int currentDay, int currentTime, AstarSite currentSite){
        float stillPossibleValue = 0;
        float impossibleValue = 0;
        float count = 0;
        AstarSite myCurrentSite = currentSite;
        if(currentSite == null){
            myCurrentSite = site;
        }
        currentTime = findNextAvaliableEndTime(site, currentSite, currentTime, currentDay);
        for (AstarSite nearsite: astarSites){
            if(!closeList.contains(nearsite) && nearsite != site) {
                if(findNextAvaliableEndTime(nearsite, myCurrentSite, currentTime % 1440, currentTime/1440) != -1){
                    stillPossibleValue += nearsite.gscore;
                }
                else{
                    impossibleValue += nearsite.gscore;
                }
            }
        }
        return stillPossibleValue;
    }

    private int findNextAvaliableEndTime(AstarSite site, AstarSite currentSite, int currentTime, int currentDay){
        AstarSite myCurrentSite = currentSite;
        if(currentSite == null){
            myCurrentSite = site;
        }
        int visitTodayEndTime = Helper.visit(site, currentTime, myCurrentSite, currentDay);
        if(visitTodayEndTime == -1){
            for(int i = currentDay + 1; i <= totalDays; i++){
                int endTime = Helper.visit(site, 0, site, i);
                if(endTime != -1){
                    return endTime + (i - 1) * 24 * 60;
                }
            }
        }
        if (visitTodayEndTime != -1) {
            return visitTodayEndTime + (currentDay - 1) * 1440;
        }
        return -1;
    }

    private void updateOpenList(int currentTime, AstarSite currentSite, int currentDay){
        for (AstarSite mySite : astarSites){
            if(!closeList.contains(mySite) && mySite != currentSite){
                int newGscore = findNextAvaliableEndTime(mySite, currentSite, currentTime, currentDay);
                if(openList.contains(mySite)){
                    if(newGscore != -1 && newGscore/1440 == currentDay - 1){
                        openList.remove(mySite);
                        if(newGscore <= mySite.gscore){
                            mySite.gscore = newGscore;
                            mySite.parent = currentSite;
                        }
                        mySite.hscore = computeHscore(mySite, currentDay, currentTime, currentSite);
                        mySite.fscore = mySite.gscore + mySite.hscore;
                        openList.add(mySite);
                    }
                }
                else if (newGscore != -1 && newGscore/1440 == currentDay - 1) {
                    mySite.parent = currentSite;
                    mySite.gscore = newGscore;
                    mySite.hscore = computeHscore(mySite, currentDay, currentTime, currentSite);
                    mySite.fscore = mySite.gscore + mySite.hscore;
                    openList.add(mySite);
                }
            }
        }
    }

    public List<List<Site>> findRoute() {
        final List<List<Site>> finalRoute = new ArrayList<List<Site>>();
        currentDay = 1;
        List<Site> todaysRoute = new ArrayList<Site>();
        while (currentDay <= totalDays){
            if (endOfDay(currentDay, currentTime, currentSite)){
                //Extract answer for today;
                float maxValue = 0;
                AstarSite endSite = currentSite;
                for (AstarSite site: closeList){
                    if(!visitedSites.contains(site)) {
                        float sum = 0;
                        AstarSite mySite = site;
                        while (mySite != null) {
                            sum += mySite.value;
                            mySite = mySite.parent;
                        }
                        if (sum > maxValue) {
                            maxValue = sum;
                            endSite = site;
                        }
                    }
                }

                todaysRoute = new ArrayList<Site>();
                closeList.clear();
                while (endSite !=null) {
                    visitedSites.add(endSite);
                    todaysRoute.add(endSite);
                    endSite = endSite.parent;
                }

                currentSite = null;
                Collections.reverse(todaysRoute);
                finalRoute.add(todaysRoute);

                //Refresh gscore for new day
                openList.clear();
                for (AstarSite site : astarSites){
                    site.parent = null;
                    if(!visitedSites.contains(site)) {
                        int newGscore = findNextAvaliableEndTime(site, site, 0, currentDay);
                        if (newGscore != -1) {
                            site.gscore = newGscore;
                            site.hscore = computeHscore(site, currentDay, site.gscore % 1440, site);
                            site.fscore = site.gscore + site.hscore;
                            openList.add(site);
                        }
                    }
                }
                currentDay++;
            }

            AstarSite newSite = openList.poll();

            if(newSite == null){
                break;
            }
            currentTime = newSite.gscore % 1440;
            currentSite = newSite;
            closeList.add(newSite);
            updateOpenList(currentTime, currentSite, currentDay);
        }

        return finalRoute;
    }

    private boolean endOfDay(int currentDay, int currentTime, AstarSite currentSite){
        if (currentSite == null){
            return false;
        }
        for (AstarSite site: openList){
            if (Helper.couldVisit(site,currentTime,currentSite,currentDay)){
               return false;
            }
        }
        return true;
    }
}
