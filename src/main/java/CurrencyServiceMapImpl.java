
import java.util.Collection;
import java.util.HashMap;

public class CurrencyServiceMapImpl implements CurrencyService {
    private HashMap<String, Currency> itemMap;

    public CurrencyServiceMapImpl() {
        itemMap = new HashMap<String, Currency>();
    }


    public void addCurrency(Currency item) throws ItemException {
        this.itemMap.put(item.getId(), item);
    }
    public Currency getCurrency(String id) throws ItemException {
        if (!this.currencyExists(id)){
            throw new ItemException("No se encontró el objeto");
        }
        return this.itemMap.get(id);
    }

    public boolean currencyExists(String id) throws ItemException {
        return this.itemMap.containsKey(id);
    }

    public void initialize(Currency[] items)  throws ItemException{
        this.itemMap.clear();
        for (Currency i:items) {
            this.addCurrency(i);
        }
    }
}
