/**
 * Created by yaoyuanliu on 9/26/16.
 */
public class Helper {
    public static boolean couldVisit(Site possibleSite, int currentTime, Site currentSite, int currentDay) {
        //Add walking time to currentTime;
        currentTime += Math.abs(currentSite.avenue - possibleSite.avenue)
                + Math.abs(currentSite.street - possibleSite.street);

        // Wait if necessary
        if (currentTime < possibleSite.openingHour[currentDay] * 60) {
            currentTime = possibleSite.openingHour[currentDay] * 60;
        }

        // If the place is already closed, or we won't have enough time, we can't visit
        return !((currentTime + possibleSite.desiredTime) >= possibleSite.endHour[currentDay] * 60);
    }
}
