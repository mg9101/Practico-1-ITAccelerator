import java.util.Collection;

public interface CurrencyService {
    public void initialize(Currency[] itemCurrency) throws ItemException;
    public Currency getCurrency(String id) throws ItemException;
    public boolean currencyExists(String id) throws ItemException;
    public void addCurrency(Currency item) throws ItemException;
}
