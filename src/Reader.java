import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Reader {
    ArrayList<Site> mySite;
    int maxAvenue;
    int maxStreet;
    int maxDays;
    public Reader(){
        mySite = new ArrayList<Site>();
        maxStreet = 0;
        maxAvenue = 0;
    }

    /**
     * To read the inputFile into a ArrayList, contains all the information in a linear architecture.
     * @param inputName the name of input file;
     */
    public void readInput(String inputName) {
        Site currentLine;
        String line;
        HashMap<Integer, Site> myHashMap = new HashMap<Integer, Site>();
        try {
            FileReader fileReader = new FileReader(inputName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //Read basic information of each site.
            while ((line = bufferedReader.readLine()) != null) {
                if(line.trim().equals("")){
                    break;
                }
                else if(line.charAt(0) != 's'){
                    String[] splitString = line.split(" ");
                    int siteId = Integer.valueOf(splitString[0]);
                    int avenue = Integer.valueOf(splitString[1]);
                    int street = Integer.valueOf(splitString[2]);
                    int desiredtime = Integer.valueOf(splitString[3]);
                    float value = Float.valueOf(splitString[4]);
                    currentLine = new Site(siteId,avenue,street,desiredtime,value);
                    mySite.add(currentLine);
                    myHashMap.put(siteId, currentLine);
                    if (avenue > maxAvenue){
                        maxAvenue = avenue;
                    }
                    if (street > maxStreet){
                        maxStreet = street;
                    }
                }
            }

            while ((line = bufferedReader.readLine()) != null) {
                if(line.charAt(0) != 's'){
                    String[] splitString = line.split(" ");
                    int siteId = Integer.valueOf(splitString[0]);
                    int day = Integer.valueOf(splitString[1]);
                    int beginHour = Integer.valueOf(splitString[2]);
                    int endHour = Integer.valueOf(splitString[3]);
                    currentLine = myHashMap.get(siteId);
                    currentLine.openingHour[day] = beginHour;
                    currentLine.endHour[day] = endHour;
                    if(day > maxDays){
                        maxDays = day;
                    }
                }
            }
            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" + inputName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '" + inputName + "'");
        }

    }
    /*
    public static void main(String[] args){
        String inputFile = args[0];
        Reader myReader = new Reader();
        myReader.readInput(inputFile);
        for (Site site: myReader.mySite){
            System.out.printf("%d %d %d %d %f %n", site.id, site.avenue, site.street, site.desiredTime, site.value);
            for (int i = 0; i < 11; i++){
                System.out.printf("%d %d %n", site.beginHour[i], site.endHour[i]);
            }
        }
    }
    */
}
