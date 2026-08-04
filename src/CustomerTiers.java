public enum CustomerTiers {

    STANDARD(12,1),
    SILVER(12,12) ,
    GOLD(23,12) ;

    private double fee ;
    private double interest ;

    CustomerTiers(double fee , double interest){
        this.fee = fee;
        this.interest = interest;
    }

}
