
import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;

import static java.util.stream.Collectors.toList;

public class ItemServiceFileImpl implements ItemService {
    private HashMap<String, Item> itemMap;

    public ItemServiceFileImpl() {
        try{
            Files.lines(new File("c:/myfile.txt").toPath()).forEach(System.out::println);
        }
        catch (IOException ex){
            System.out.println("Ocurrio un error al escribir el archivo");
        }

    }

    public void addItem(Item item) throws ItemException {
        this.itemMap.put(item.getId(), item);
    }

    @Override
    public Collection<Item> getItems() throws ItemException {
        return this.itemMap.values();
    }

    public Item getItem(String id) throws ItemException {
        return this.itemMap.get(id);
    }

    public Item editItem(Item item) throws ItemException {
        return null;
    }

    public void deleteItem(String id) throws ItemException {

    }

    public boolean itemExists(String id) throws ItemException {
        return false;
    }

    public Collection<String> getTitulos() throws ItemException {
        return this.itemMap.values().stream()
                .map(s -> s.getTitle())
                .collect(toList());
    }

    public Collection<Item> getItemsOrdenados(String attr, String order) throws ItemException {
        switch (attr){
            case "price":
                if (order.equals("asc")){
                    return this.itemMap.values().stream()
                            .sorted(Comparator.comparingDouble(Item::getPrice))
                            .collect(toList());
                }else if(order.equals("desc")){
                    return this.itemMap.values().stream()
                            .sorted(Comparator.comparingDouble(Item::getPrice)
                            .reversed())
                            .collect(toList());
                }
            case "listing_type":
                if (order.equals("asc")){
                        return this.itemMap.values().stream()
                            .sorted(Comparator.comparing(Item::getListing_type_id))
                            .collect(toList());
                }else if(order.equals("desc")){
                    return this.itemMap.values().stream()
                            .sorted(Comparator.comparing(Item::getListing_type_id)
                            .reversed())
                            .collect(toList());
                }
            default:
                return this.itemMap.values();
        }
    }

    public Collection<Item> getItemsPorPrecio(String priceFrom, String priceTo) throws ItemException {
        return this.itemMap.values().stream()
                .filter(s -> s.getPrice() <= Double.parseDouble(priceTo) && s.getPrice() >= Double.parseDouble(priceFrom))
                .collect(toList());
    }

    public Collection<Item> getItemsConTag() throws ItemException{
        return this.itemMap.values().stream()
                .filter(s -> Arrays.asList(s.getTags())
                        .contains("good_quality_thumbnail"))
                .collect(toList());
    }

    public void initialize(Item[] items)  throws ItemException{
        this.itemMap.clear();
        for (Item i:items) {
            this.addItem(i);
        }
    }
}
