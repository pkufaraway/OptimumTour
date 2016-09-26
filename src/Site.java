class Site{
  int id;
  int avenue;
  int street;
  int desiredTime;
  float value;
  int[] openingHour;
  int[] endHour;

  public Site(int id, int avenue, int street, int desiredTime, float value){
    this.id = id;
    this.avenue = avenue;
    this.street = street;
    this.desiredTime = desiredTime;
    this.value = value;
    this.openingHour = new int[11];
    this.endHour = new int[11];
  }
}
