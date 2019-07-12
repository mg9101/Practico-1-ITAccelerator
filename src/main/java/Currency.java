public class Currency {
    String symbol;
    String description;
    int decimal_places;

    public Currency(String symbol, String description, int decimal_places) {
        this.symbol = symbol;
        this.description = description;
        this.decimal_places = decimal_places;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDecimal_places() {
        return decimal_places;
    }

    public void setDecimal_places(int decimal_places) {
        this.decimal_places = decimal_places;
    }
}
