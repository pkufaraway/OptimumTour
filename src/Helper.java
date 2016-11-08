/**
 * Created by yaoyuanliu on 9/26/16.
 */
public class Helper {
    public static boolean couldVisit(Site possibleSite, int currentTime, Site currentSite, int currentDay) {
        //Add walking time to currentTime;
        if(currentSite == null){
            return (possibleSite.endHour[currentDay] -possibleSite.openingHour[currentDay]) * 60
                    > possibleSite.desiredTime;
        }
        int myCurrentTime = currentTime +Math.abs(currentSite.avenue - possibleSite.avenue)
                + Math.abs(currentSite.street - possibleSite.street);

        // Wait if necessary
        if (myCurrentTime < possibleSite.openingHour[currentDay] * 60) {
            myCurrentTime = possibleSite.openingHour[currentDay] * 60;
        }

        // If the place is already closed, or we won't have enough time, we can't visit
        return !((myCurrentTime + possibleSite.desiredTime) >= possibleSite.endHour[currentDay] * 60);
    }

    public static int visit(Site possibleSite, int currentTime, Site currentSite, int currentDay) {
        if (!couldVisit(possibleSite, currentTime, currentSite, currentDay)){
            return -1;
        }
        if(currentSite == null){
            return possibleSite.openingHour[currentDay] * 60 + possibleSite.desiredTime;
        }
        int myCurrentTime = currentTime + Math.abs(currentSite.avenue - possibleSite.avenue)
                + Math.abs(currentSite.street - possibleSite.street);
        if (myCurrentTime < possibleSite.openingHour[currentDay] * 60) {
            myCurrentTime = possibleSite.openingHour[currentDay] * 60;
        }
        if (myCurrentTime + possibleSite.desiredTime <= possibleSite.endHour[currentDay] * 60){
            return myCurrentTime + possibleSite.desiredTime;
        }
        else{
            return -1;
        }

    }

    public static int distance(Site siteA, Site siteB){
        return Math.abs(siteA.avenue - siteB.avenue) + Math.abs(siteA.street - siteB.street);
    }
}
