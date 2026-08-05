public enum CustomerTiers {

    STANDARD(10, 1.0),
    SILVER(5, 2.5),
    GOLD(0, 5.0);

    private double fee ;
    private double interest ;

    CustomerTiers(double fee , double interest){
        this.fee = fee;
        this.interest = interest;
    }

    public double getFee() {
        return fee;
    }

    public double getInterest() {
        return interest;
    }
}
