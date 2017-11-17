public class ReportModel {
    private String itemName;
    private int unitsSold;
    private double revenue;

    public ReportModel(){

    }

    public ReportModel(String itemNameIn, int unitsSoldIn, double revenueIn) {
        this.itemName = itemNameIn;
        this.unitsSold = unitsSoldIn;
        this.revenue = revenueIn;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setUnitsSold(int unitsSold) {
        this.unitsSold = unitsSold;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public String getItemName() {
        return itemName;
    }

    public int getUnitsSold() {
        return unitsSold;
    }

    public double getRevenue() {
        return revenue;
    }

}
