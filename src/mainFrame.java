import java.util.ArrayList;
import java.util.List;
import java.time.*;

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



        int maxDepth = 0;
        //Check greedy algorithms
        for (Algorithm algorithm : myAlgorithms){
            FindRoute tempRouter = new FindRoute(refactoredSites, myReader.maxDays);
            List<List<Site>> tempResult = tempRouter.findRoute(algorithm);
            for(List<Site> daySites: tempResult){
                if(daySites.size() > maxDepth){
                    maxDepth = daySites.size();
                }
            }
            if(ScoreCalculator.routeScore(tempResult) > maxResult){
                answer = tempResult;
                maxResult = ScoreCalculator.routeScore(tempResult);
            }
        }

        System.out.println(maxDepth * 2);
        //Check brute force

        Force myForce = new Force(myReader.mySite, myReader.maxDays, maxDepth * 2);
        List<List<Site>> tempResultForce = myForce.calcBest();
        OutputToServer.printSitesByDay(tempResultForce);
        if(ScoreCalculator.routeScore(tempResultForce) > maxResult){
            answer = tempResultForce;
        }
        System.out.println(ScoreCalculator.routeScore(tempResultForce));


        //Check A* algorithm
        Astar myAstar = new Astar(refactoredSites, myReader.maxDays);
        List<List<Site>> tempResult = myAstar.findRoute();
        if(ScoreCalculator.routeScore(tempResult) > maxResult){
            answer = tempResult;
        }

        //Output the best result
        System.out.println(maxResult);
        OutputToServer.printSitesByDay(answer);
    }
}
