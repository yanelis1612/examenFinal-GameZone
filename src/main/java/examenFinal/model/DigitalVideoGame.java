package examenFinal.model;

import examenFinal.interfaces.PriceCalculable;
import examenFinal.interfaces.Sellable;

public class DigitalVideoGame extends VideoGame
        implements PriceCalculable, Sellable {

    private double sizeGB;

    public DigitalVideoGame(String title,
                            String platform,
                            double price,
                            int stock,
                            double sizeGB) {

        super(title, platform, price, stock);
        this.sizeGB = sizeGB;
    }

    @Override
    public double calculateFinalPrice() {

        if (sizeGB > 50) {
            return price + 5000;
        }

        return price;
    }

    @Override
    public double sell(int quantity) {

        if (quantity > stock) {
            throw new RuntimeException("Stock insuficiente");
        }

        stock -= quantity;

        return calculateFinalPrice() * quantity;
    }

    public double getSizeGB() {
        return sizeGB;
    }

    public void setSizeGB(double sizeGB) {
        this.sizeGB = sizeGB;
    }
    @Override
    public String getType() {
        return "Digital";
    }

    @Override
    public String toString() {
        return "DigitalVideoGame{" +
                "title='" + title + '\'' +
                ", platform='" + platform + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", sizeGB=" + sizeGB +
                '}';
    }
}
