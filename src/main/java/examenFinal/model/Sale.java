package examenFinal.model;

public class Sale {

    private String gameTitle;
    private int quantity;
    private double total;

    public Sale(String gameTitle,
                int quantity,
                double total) {

        this.gameTitle = gameTitle;
        this.quantity = quantity;
        this.total = total;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "gameTitle='" + gameTitle + '\'' +
                ", quantity=" + quantity +
                ", total=" + total +
                '}';
    }
}
