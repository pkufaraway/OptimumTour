class Site{
  int id;
  int avenue;
  int street;
  int desiredTime;
  float value;
  int[] beginhour;
  int[] endhour;
  public Site(int id, int avenue, int street, int desiredTime, float value, int daysCount){
    this.id = id;
    this.avenue = avenue;
    this.street = street;
    this.desiredTime = desiredTime;
    this.value = value;
    beginhour = new int[dayCount + 1];
    endhour = new int[dayCount + 1];
  
  }
}
