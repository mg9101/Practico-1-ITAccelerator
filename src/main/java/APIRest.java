import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static spark.Spark.*;

public class APIRest {
    public static void main(String[] args) {
        ItemService itemService =
                new ItemServiceFileImpl();

        get("/search", (req, res) -> {
            try{
                res.type("application/json");
                JsonObject jobj = callApiMeli("sites/MLA/search?q="+ req.queryParams("q"));
                Item[] items = new Gson().fromJson(jobj.get("results"), Item[].class);
//                Collection<Currency>  currencies= Arrays.stream(items)
//                        .map(s -> s.getCurrency_id())
//                        .collect(Collectors.toList());

                itemService.initialize(items);
                return  new Gson().toJson( new StandardResponse(
                        StatusResponse.SUCCESS, new Gson().
                        toJsonTree(itemService.getItems())
                ));
            }
            catch (ItemException ex){
                return new Gson().toJson( new StandardResponse(
                        StatusResponse.ERROR, ex.getMessage()));
            }
            catch (Exception ex){
                return new Gson().toJson( new StandardResponse(
                        StatusResponse.ERROR, "Ocurrio un error"));
            }
        });

        post("/items", (req, res) -> {
            try{
                res.type("application/json");
                Item item = new Gson().fromJson(
                        req.body(), Item.class
                );
                itemService.addItem(item);
                return new Gson().toJson(new StandardResponse(
                        (StatusResponse.SUCCESS)
                ));
            }
            catch (ItemException ex){
                return new Gson().toJson( new StandardResponse(
                        StatusResponse.ERROR, ex.getMessage()));
            }
            catch (Exception ex){
                return new Gson().toJson( new StandardResponse(
                        StatusResponse.ERROR, "Ocurrio un error"));
            }
        });

        get("/items", (req, res) -> {
            try{
                res.type("application/json");
                return  new Gson().toJson( new StandardResponse(
                        StatusResponse.SUCCESS, new Gson().
                        toJsonTree(itemService.getItems())
                ));
            }
            catch (ItemException ex){
                return new Gson().toJson( new StandardResponse(
                        StatusResponse.ERROR, ex.getMessage()));
            }
            catch (Exception ex){
                return new Gson().toJson( new StandardResponse(
                        StatusResponse.ERROR, "Ocurrio un error"));
            }
        });

        get("/itemsTitulo", (req, res) -> {
            try{
                res.type("application/json");
                return  new Gson().toJson( new StandardResponse(
                        StatusResponse.SUCCESS, new Gson().
                        toJsonTree(itemService.getTitulos())
                ));
            }
            catch (ItemException ex){
                return new Gson().toJson( new StandardResponse(
                        StatusResponse.ERROR, ex.getMessage()));
            }
            catch (Exception ex){
                return new Gson().toJson( new StandardResponse(
                        StatusResponse.ERROR, "Ocurrio un error"));
            }

        });

        get("/itemsOrdenados", (req, res) -> {
            try{
                res.type("application/json");
                return  new Gson().toJson( new StandardResponse(
                        StatusResponse.SUCCESS, new Gson().
                        toJsonTree(itemService.getItemsOrdenados(req.queryParams("tipo"), req.queryParams("orden")))
                ));
            }
            catch (ItemException ex){
                return new Gson().toJson( new StandardResponse(
                        StatusResponse.ERROR, ex.getMessage()));
            }
            catch (Exception ex){
                return new Gson().toJson( new StandardResponse(
                        StatusResponse.ERROR, "Ocurrio un error"));
            }

        });

        get("/itemsPrecio", (req, res) -> {
            try{
                res.type("application/json");
                return  new Gson().toJson( new StandardResponse(
                        StatusResponse.SUCCESS, new Gson().
                        toJsonTree(itemService.getItemsPorPrecio(req.queryParams("precioMin"), req.queryParams("precioMax")))
                ));
            }
            catch (ItemException ex){
                return new Gson().toJson( new StandardResponse(
                        StatusResponse.ERROR, ex.getMessage()));
            }
            catch (Exception ex){
                return new Gson().toJson( new StandardResponse(
                        StatusResponse.ERROR, "Ocurrio un error"));
            }
        });

        get("/itemsTag", (req, res) -> {
            try{
                res.type("application/json");
                return  new Gson().toJson( new StandardResponse(
                        StatusResponse.SUCCESS, new Gson().
                        toJsonTree(itemService.getItemsConTag())
                ));
            }
            catch (ItemException ex){
                return new Gson().toJson( new StandardResponse(
                        StatusResponse.ERROR, ex.getMessage()));
            }
            catch (Exception ex){
                return new Gson().toJson( new StandardResponse(
                        StatusResponse.ERROR, "Ocurrio un error"));
            }
        });

        get("/items/:id", (req, res) -> {
            try{
                res.type("application/json");
                return  new Gson().toJson( new StandardResponse(
                        StatusResponse.SUCCESS, new Gson().
                        toJsonTree(itemService.getItem(req.params(":id")))
                ));
            }
            catch (ItemException ex){
                return new Gson().toJson( new StandardResponse(
                        StatusResponse.ERROR, ex.getMessage()));
            }
            catch (Exception ex){
                return new Gson().toJson( new StandardResponse(
                        StatusResponse.ERROR, "Ocurrio un error"));
            }
        });

        put("/items/:id", (req, res) -> {
            try{
                res.type("application/json");
                Item item = new Gson().
                        fromJson(req.body(), Item.class);
                Item itemE =
                        itemService.editItem(item);
                if (item != null){
                    return new Gson().toJson(new StandardResponse(
                            StatusResponse.SUCCESS, new Gson().toJsonTree(item)
                    ));
                }else {
                    return new Gson().toJson(new StandardResponse(
                            StatusResponse.ERROR, new Gson().toJson("Error al editar")
                    ));
                }
            }
            catch (ItemException ex){
                return new Gson().toJson( new StandardResponse(
                        StatusResponse.ERROR, ex.getMessage()));
            }
            catch (Exception ex){
                return new Gson().toJson( new StandardResponse(
                        StatusResponse.ERROR, "Ocurrio un error"));
            }
        });

        delete("/items/:id", (req, res) -> {
            try{
                res.type("application/json");
                itemService.deleteItem(req.params(":id"));
                return new Gson().toJson(new StandardResponse(
                        StatusResponse.SUCCESS, "Item borrado"
                ));
            }
            catch (ItemException ex){
                return new Gson().toJson( new StandardResponse(
                        StatusResponse.ERROR, ex.getMessage()));
            }
            catch (Exception ex){
                return new Gson().toJson( new StandardResponse(
                        StatusResponse.ERROR, "Ocurrio un error"));
            }
        });

        options("/items/:id", (req, res) -> {
            try{
                res.type("application/json");
                return new Gson().toJson(new StandardResponse(
                        StatusResponse.SUCCESS, (itemService.itemExists(req.params(":id"))) ?
                        "El item existe" : "El item no existe"));
            }
            catch (ItemException ex){
                return new Gson().toJson( new StandardResponse(
                        StatusResponse.ERROR, ex.getMessage()));
            }
            catch (Exception ex){
                return new Gson().toJson( new StandardResponse(
                        StatusResponse.ERROR, "Ocurrio un error"));
            }
        });

    }
    private static JsonObject callApiMeli(String endp) throws ItemException{
        try {
            URL url = new URL("https://api.mercadolibre.com/" + endp);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection connection = null;
            if (urlConnection instanceof HttpURLConnection){
                connection = (HttpURLConnection) urlConnection;
            }else{
                System.out.println("URL inválida");
            }
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String urlString = "";
            String current = null;
            while ((current = in.readLine()) != null){
                urlString += current;
            }
            JsonObject jobj = new Gson().fromJson(urlString, JsonObject.class);
            return jobj;
        }
        catch (MalformedURLException ex){
            throw new ItemException("La url no tiene el formato correcto");
        }
        catch (IOException ex){
            throw new ItemException("No se pudo realizar la peticion a la API");
        }
    }
}
