import java.util.List;

public class mainFrame {
    public static void main(String[] args){
        String inputFile = "input.txt";
        Reader myReader = new Reader();
        myReader.readInput(inputFile);
        FindRoute greedyTouring = new FindRoute(ValueRefactor.valueRefactor(myReader.mySite), myReader.maxDays);
        FindRoute greedyValueTouring = new FindRoute(ValueRefactor.valueRefactor(myReader.mySite), myReader.maxDays);
        List<List<Site>> greedyTouringResult = greedyTouring.findRoute(new GreedyDistanceTouring());
        /*
        for (List<Site> dayRoutes : greedyTouringResult){
            for (Site eachSite : dayRoutes){
                System.out.println(eachSite.id);
            }
        }*/
        List<List<Site>> greedyValueTouringResult = greedyValueTouring.findRoute(new GreedyValueTouring());
        /*
        for (List<Site> dayRoutes : greedyValueTouringResult){
            for (Site eachSite : dayRoutes){
                System.out.println(eachSite.id);
            }
        }*/
        System.out.println(ScoreCalculator.routeScore(greedyTouringResult));
        System.out.println(ScoreCalculator.routeScore(greedyValueTouringResult));
    }
}
