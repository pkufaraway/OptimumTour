import java.util.List;

public class mainFrame {
    public static void main(String[] args){
        String inputFile = "input.txt";
        Reader myReader = new Reader();
        myReader.readInput(inputFile);
        GreedyTouring greedyTouring = new GreedyTouring(myReader.mySite, myReader.maxDays);
        List<List<Site>> greedyTouringResult = greedyTouring.findRoute();
        System.out.println(ScoreCalculator.routeScore(greedyTouringResult));
    }
}
