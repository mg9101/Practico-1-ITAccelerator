import java.util.Collection;

public interface ItemService {
    public void initialize(Item[] items) throws ItemException;
    public void addItem(Item item) throws ItemException;
    public Collection<Item> getItems() throws ItemException;
    public Item getItem(String id) throws ItemException;
    public Item editItem(Item item)
            throws ItemException;
    public void deleteItem(String id) throws ItemException;
    public boolean itemExists(String id) throws ItemException;
    public Collection<String> getTitulos() throws ItemException;
    public Collection<Item> getItemsOrdenados(String attr, String order) throws ItemException;
    public Collection<Item> getItemsPorPrecio(String priceFrom, String priceTo) throws ItemException;
    public Collection<Item> getItemsConTag() throws ItemException;
}
