class Site{
  int id;
  int avenue;
  int street;
  int desiredTime;
  float value;
  int[] beginHour;
  int[] endHour;

  public Site(int id, int avenue, int street, int desiredTime, float value){
    this.id = id;
    this.avenue = avenue;
    this.street = street;
    this.desiredTime = desiredTime;
    this.value = value;
    beginHour = new int[11];
    endHour = new int[11];
  }
}
