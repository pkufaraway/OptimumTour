import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Force {
    List<Site> map;
    int days;
    List<List<Site>> answer;
    HashSet<Site> visitedSite;
    List<Site> daySites;
    int maxDepth = 0;
    LocalDateTime currentSecond;
    double bestScore;
    public Force(List<Site> siteInfo, int days, int maxDepth){
        map = siteInfo;
        this.days = days;
        visitedSite = new HashSet<>();
        answer = new ArrayList<>();
        this.maxDepth = maxDepth;
    }

    public void findBest(List<Site> currentState, int depth, int day, int currentTime, double score) {
        if (depth == maxDepth) {
            return;
        }
        //System.out.println(score);
        if(score > bestScore){
            bestScore = score;
            daySites = new ArrayList<>(currentState);
        }
        Duration duration = Duration.between(currentSecond, LocalDateTime.now());
        if (duration.getSeconds() > 110.0 / days){
            return;
        }

        for(Site site: map){
            Site lastSite = null;
            if(currentState.size() != 0){
                lastSite = currentState.get(currentState.size() - 1);
            }
            if(!visitedSite.contains(site) && Helper.couldVisit(site, currentTime, lastSite, day)){
                int newTime = Helper.visit(site, currentTime, lastSite, day);
                currentState.add(site);
                visitedSite.add(site);
                findBest(currentState, depth + 1, day, newTime, score + site.value);
                currentState.remove(site);
                visitedSite.remove(site);
            }
        }
    }

    public List<List<Site>> calcBest(){
        for(int i = 1; i <= days; i++){
            currentSecond = LocalDateTime.now();
            bestScore = 0;
            List<Site> Sites= new ArrayList<Site>();
            findBest(Sites, 0, i, 0, 0);
            System.out.printf("Day %d score %f\n", i, bestScore);
            answer.add(daySites);
            visitedSite = new HashSet<>();
            for(List<Site> daySites: answer){
                visitedSite.addAll(daySites);
            }
        }
        return answer;
    }


}
