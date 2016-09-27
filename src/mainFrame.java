import java.util.ArrayList;
import java.util.List;

public class mainFrame {
    public static void main(String[] args){
        Reader myReader = new Reader();
        myReader.readInput("given_info.txt");
        List<Site> refactoredSites = ValueRefactor.valueRefactor(myReader.mySite);
        //Initialize all your algorithms here.
        Algorithm[] myAlgorithms = {new GreedyDistanceTouring(),
                new GreedyValueTouring(),
        };

        double maxResult = 0;
        List<List<Site>> answer = null;

        //Check greedy algorithms
        for (Algorithm algorithm : myAlgorithms){
            FindRoute tempRouter = new FindRoute(refactoredSites, myReader.maxDays);
            List<List<Site>> tempResult = tempRouter.findRoute(algorithm);
            if(ScoreCalculator.routeScore(tempResult) > maxResult){
                answer = tempResult;
                maxResult = ScoreCalculator.routeScore(tempResult);
            }
        }

        //Check A* algorithm
        Astar myAstar = new Astar(refactoredSites, myReader.maxDays);
        List<List<Site>> tempResult = myAstar.findRoute();
        if(ScoreCalculator.routeScore(tempResult) > maxResult){
            answer = tempResult;
        }

        //Output the best result
        OutputToServer.printSitesByDay(answer);
    }
}
