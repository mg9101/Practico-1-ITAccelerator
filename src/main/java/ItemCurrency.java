import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class ItemCurrency implements Serializable {
    String id;
    String site_id;
    String title;
    double price;
    String currency_id;
    String listing_type_id;
    Date stop_time;
    String thumbnail;
    String[] tags;

    public ItemCurrency() {
    }

    public ItemCurrency(String id, String site_id, String title, double price, String currency_id, String listing_type_id, Date stop_time, String thumbnail, String[] tags) {
        this.id = id;
        this.site_id = site_id;
        this.title = title;
        this.price = price;
        this.currency_id = currency_id;
        this.listing_type_id = listing_type_id;
        this.stop_time = stop_time;
        this.thumbnail = thumbnail;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public String getListing_type_id() {
        return listing_type_id;
    }

    public void setListing_type_id(String listing_type_id) {
        this.listing_type_id = listing_type_id;
    }

    public Date getStop_time() {
        return stop_time;
    }

    public void setStop_time(Date stop_time) {
        this.stop_time = stop_time;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public int compareTo(ItemCurrency o) {
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.id);
        sb.append(this.getSite_id());
        sb.append(this.getListing_type_id());
        sb.append(this.getTags());
        sb.append(this.getCurrency_id());
        sb.append(this.getPrice());
        sb.append(this.getTitle());
        sb.append(this.getStop_time());
        sb.append(this.getThumbnail());
        return sb.toString();
    }

}
