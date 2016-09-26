import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScoreCalculator {
  
  /***
   * Takes the route provided by the algorithm and scores is based on the 
   * sites the algorithm chooses. Will return a negative number if the route is
   * invalid (i.e. visits the same site twice, or visits a site that isn't 
   * open).
   * @param dailyRoutes List of List of sites visited per day
   * @return Value of the route passed by the user
   */

  public static double routeScore(List<List<Site>> dailyRoutes) {
    double totalScore = 0;
    if (!routeIsValid(dailyRoutes)) {
      return -Double.MAX_VALUE;
    }
    for (List<Site> dailyRoute : dailyRoutes) {
      for (Site location : dailyRoute) {
        totalScore += location.value;
      }
    }
    return totalScore;
  }
  
  private static boolean routeIsValid(List<List<Site>> dailyRoutes) {
    Set<Site> visitedSites = new HashSet<Site>();
    int currentTime; //Will be -1 if no sites have been visited yet today
    int day = 1;
    int currentStreet = 0;
    int currentAvenue = 0;
    
    for (List<Site> dailyRoute : dailyRoutes) {
      currentTime = -1;
      for (Site location : dailyRoute) {

        if (currentTime == -1) {
          currentTime = (location.openingHour[day]) * 60 + location.desiredTime;
          currentStreet = location.street;
          currentAvenue = location.avenue;
        } else {
          // Travel to new site
          currentTime += Math.abs(currentAvenue - location.avenue) 
              + Math.abs(currentStreet - location.street);
          // Adjust location variables
          currentStreet = location.street;
          currentAvenue = location.avenue;
          // Wait if place hasn't opened
          if (currentTime <= location.openingHour[day] * 60) {
            currentTime = location.openingHour[day] * 60;
          } 
          // If there won't be enough time, or the place is closed, this will
          // not be a valid route
          if (currentTime >= location.endHour[day] * 60
              || (currentTime + location.desiredTime) >= location.endHour[day] * 60) {
            if (currentTime >= location.endHour[day] * 60) {
              System.out.println("Place already closed: it closes at " 
                  + location.endHour[day] + " and it's " + currentTime);
            } else {
              System.out.println("Not enough time to visit this location");
            }
            return false;
          }
          currentTime += location.desiredTime;
        }
        
        //If the Site has already been visited, the route is invalid
        if (visitedSites.contains(location)) {
          System.out.println("Duplicate site error");
          return false;
        } else {
          visitedSites.add(location);
        }
      }
      day++;
    }
    return true;
  }
}
