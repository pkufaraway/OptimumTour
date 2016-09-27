class Site {
    int id;
    int avenue;
    int street;
    int desiredTime;
    float value;
    float convertedValue;
    float[] densityValue;
    int[] openingHour;
    int[] endHour;

    public Site(int id, int avenue, int street, int desiredTime, float value) {
        this.id = id;
        this.avenue = avenue;
        this.street = street;
        this.desiredTime = desiredTime;
        this.value = value;
        this.openingHour = new int[11];
        this.endHour = new int[11];
        this.densityValue = new float[11];
    }

    public Site(Site site){
        this.id = site.id;
        this.avenue = site.avenue;
        this.street = site.street;
        this.desiredTime = site.desiredTime;
        this.value = site.value;
        this.openingHour = site.openingHour.clone();
        this.endHour = site.endHour.clone();
        this.densityValue = site.densityValue.clone();
        this.convertedValue = site.convertedValue;
    }
}
