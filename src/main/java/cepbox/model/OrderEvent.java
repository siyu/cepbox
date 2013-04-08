package cepbox.model;

/**
 * Created with IntelliJ IDEA.
 * User: siyu
 * Date: 4/7/13
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrderEvent {
    private String symbol;
    private int price;

    public OrderEvent(String symbol, int price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
