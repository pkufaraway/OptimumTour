import java.util.List;

public class mainFrame {
    public static void main(String[] args){
        String inputFile = "input.txt";
        Reader myReader = new Reader();
        myReader.readInput(inputFile);
        List<Site> refactoredSites = ValueRefactor.valueRefactor(myReader.mySite);
        //Initialize all your algorithms here.
        Algorithm[] myAlgorithms = {new GreedyDistanceTouring(), new GreedyValueTouring()};

        for (Algorithm algorithm : myAlgorithms){
            FindRoute tempRouter = new FindRoute(refactoredSites, myReader.maxDays);
            List<List<Site>> tempResult = tempRouter.findRoute(algorithm);
            System.out.println(algorithm.getClass().getName());
            System.out.println(ScoreCalculator.routeScore(tempResult));
        }
    }
}
