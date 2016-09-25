import java.util.List;


public class OutputToServer {
  public static void printSitesByDay(List<List<Site>> dailyRoutes) {
    StringBuilder dailyRouteString;
    for (List<Site> dailyRoute : dailyRoutes) {
      dailyRouteString = new StringBuilder();
      for (int i = 0; i < dailyRoute.size(); i++) {
        dailyRouteString.append(dailyRoute.get(i).id);
        if (i != dailyRoute.size() - 1) {
          dailyRouteString.append(" ");
        }
      }
      System.out.println(dailyRouteString.toString());
    }
  }
}
