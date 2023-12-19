package n2exercici1;

import java.io.*;
import java.sql.*;
import java.util.*;

public class DataBaseManager {
    private static final String currDir = System.getProperty("user.dir");
    private static final String propertiesPath = currDir + "/src/main/resources/database.properties";
    private static String connectionURL;
    private static String user;
    private static String password;
    private static Connection con;
    private static Statement statementProd;
    private static Statement statementSub;
    private static Statement statementTic;
    private static Statement statementTicPro;
    private static final String TYPE_FLOWER = "FLOWER";
    private static final String TYPE_TREE = "TREE";
    private static final String TYPE_DECORATION = "DECORATION";

    static{
        getProperties();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connectionURL, user, password);
            statementProd = con.createStatement();
            statementSub = con.createStatement();
            statementTic = con.createStatement();
            statementTicPro = con.createStatement();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            System.err.println("Problem connecting to database");
        }
    }

    private static void getProperties(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(propertiesPath));
            connectionURL = (String) properties.get("CONNECTION_URL");
            user = (String) properties.get("USER");
            password = (String) properties.get("PASSWORD");
        }
        catch (IOException ex){
            ex.printStackTrace();
            System.err.println("Problem loading properties");
        }
    }
    private static void executeInsert(String query){
        try {
            statementProd.execute(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Problem saving product to database");
        }
    }

    private static void addProduct(Product product, int quantity){
        System.out.println("Saving product...");
        String query = String.format("INSERT INTO products VALUES(%d, '%s', '%s', %.2f, %d);", product.getId(), product.getType(), product.getName(), product.getPrice(), quantity);
        String subquery;
        if(product.getType().toString().equalsIgnoreCase(TYPE_FLOWER)){
            Flower flower = (Flower) product;
            subquery = String.format("INSERT INTO flowers VALUES(%d, '%s');", flower.getId(), flower.getColour());
        }
        else if(product.getType().toString().equalsIgnoreCase(TYPE_TREE)){
            Tree tree = (Tree) product;
            subquery = String.format("INSERT INTO trees VALUES(%d, '%.2f');", tree.getId(), tree.getHeight());
        }
        else {
            Decoration decoration = (Decoration) product;
            subquery = String.format("INSERT INTO decorations VALUES(%d, '%s');", decoration.getId(), decoration.getType());
        }
        executeInsert(query);
        executeInsert(subquery);
        System.out.println("Product successfully saved");
    }
    public static void changeStockQuant(int id, int quantity){
        System.out.println("Updating product stock...");
        String query = String.format("UPDATE products SET quantity = quantity + %d WHERE id = %d", quantity, id);
        executeInsert(query);
        System.out.println("Product stock successfully updated");
    }

    public static void saveProduct(Product product, int quantity) {
        String name = product.getName();
        float price = product.getPrice();
        String type = product.getType().toString();

        String query = String.format("SELECT id FROM products WHERE name = '%s' AND type = '%s';", name, type);
        System.out.println(query);
        try{
            ResultSet resultSet = statementProd.executeQuery(query);
            if(resultSet.next()){
                System.out.println("Product already exists");
                changeStockQuant(resultSet.getInt("id"), quantity);
                Product.decreaseId();
            }
            else{
                addProduct(product, quantity);
            }
            resultSet.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Problem saving product to database");
        }
    }

    public static int findProdQuantity(Product product){
        String query = String.format("SELECT quantity FROM products WHERE id = %d", product.getId());
        try{
            ResultSet resultSet = statementProd.executeQuery(query);
            resultSet.next();
            int quantity = resultSet.getInt("quantity");
            resultSet.close();
            return quantity;
        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.err.println("Problem with database");
        }
        return -1;
    }

    public static void saveTicket(Ticket ticket){
        String query = String.format("INSERT INTO tickets (total) VALUES(%.2f)", ticket.getAmount());
        executeInsert(query);
        try{
            ResultSet resultSet = statementTic.executeQuery("SELECT id FROM tickets WHERE id = LAST_INSERT_ID()");
            resultSet.next();
            ticket.setId(resultSet.getInt("id"));
            resultSet.close();
            Map<Product, Integer> products = ticket.getProductMap();
            products.forEach((pro, quantity) -> executeInsert(String.format("INSERT INTO tickets_has_products (idTicket, idProduct, quantity) VALUES(%d, %d, %d)", ticket.getId(), pro.getId(), quantity)));
        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.err.println("Problem with database");
        }
    }
    private static Flower loadFlower(int id, String name, float price) throws SQLException{
        String query = String.format("SELECT colour FROM flowers WHERE idflower = %d", id);
        ResultSet resultSetFlower = statementSub.executeQuery(query);
        resultSetFlower.next();
        String colour = resultSetFlower.getString("colour");
        resultSetFlower.close();
        return new Flower(id, name, price, colour);
    }

    private static Tree loadTree(int id, String name, float price) throws SQLException{
        String query = String.format("SELECT height FROM trees WHERE idtree = %d", id);
        ResultSet resultSetTree = statementSub.executeQuery(query);
        resultSetTree.next();
        float height = resultSetTree.getFloat("height");
        resultSetTree.close();
        return new Tree(id, name, price, height);
    }

    private static Decoration loadDecoration(int id, String name, float price) throws SQLException{
        String query = String.format("SELECT material FROM decorations WHERE iddecoration = %d", id);
        ResultSet resultSetDecoration = statementSub.executeQuery(query);
        resultSetDecoration.next();
        Decoration.Material material = Decoration.Material.valueOf(resultSetDecoration.getString("material"));
        resultSetDecoration.close();
        return new Decoration(id, name, price, material);
    }

    private static Product loadProduct(int id, String type, String name, float price) throws SQLException{
        if(type.equalsIgnoreCase(TYPE_FLOWER)){
            return loadFlower(id, name, price);
        }
        else if(type.equalsIgnoreCase(TYPE_TREE)){
            return loadTree(id, name, price);
        }
        else{
            return loadDecoration(id, name, price);
        }
    }
    private static Product loadProductWithId(int id) throws SQLException {
        String query = String.format("SELECT * FROM products WHERE id = %d;", id);
        ResultSet resultSet = statementProd.executeQuery(query);
        resultSet.next();
        Product.Type type = Product.Type.valueOf(resultSet.getString("type"));
        String name = resultSet.getString("name");
        float price = resultSet.getFloat("price");
        Product product = loadProduct(id, type.toString(), name, price);
        resultSet.close();
        return product;
    }

    private static ResultSet getProducts() throws SQLException{
        String query = "SELECT * FROM products ORDER BY id;";
        return statementProd.executeQuery(query);
    }

    public static List<Product> loadStock(){
        System.out.println("Loading stock...");
        List<Product> products = new ArrayList<>();
        try{
            ResultSet resultSet = getProducts();
            if (resultSet.next()){
                do {
                    int id = resultSet.getInt("id");
                    Product.Type type = Product.Type.valueOf(resultSet.getString("type"));
                    String name = resultSet.getString("name");
                    float price = resultSet.getFloat("price");
                    Product product = loadProduct(id, type.toString(), name, price);
                    products.add(product);
                } while (resultSet.next());
            } else {
                System.out.println("No initial stock found, starting with empty stock.");
                return products;
            }
            resultSet.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.err.println("Problem loading stock data, starting with empty stock");
        }
        return products;
    }

    public static float calcStockValue(){
        float total = 0.0f;
        String query = "SELECT SUM(quantity * price) FROM products";
        try{
            ResultSet resultSet = statementProd.executeQuery(query);
            resultSet.next();
            total = resultSet.getFloat("SUM(quantity * price)");
            resultSet.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.err.println("Problem with database");
        }
        return total;
    }

    public static float calcSalesValue(){
        float total = 0.0f;
        String query = "SELECT SUM(total) FROM tickets";
        try{
            ResultSet resultSet = statementTic.executeQuery(query);
            resultSet.next();
            total = resultSet.getFloat("SUM(total)");
            resultSet.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.err.println("Problem with database");
        }
        return total;
    }
    private static Map<Product, Integer> loadTicketProducts(int idTicket) throws SQLException{
        Map<Product, Integer> productMap = new HashMap<>();
        String query = String.format("SELECT * FROM tickets_has_products WHERE idTicket = %d", idTicket);
        ResultSet resultSetTicPro = statementTicPro.executeQuery(query);
        while (resultSetTicPro.next()){
            int id = resultSetTicPro.getInt("idProduct");
            int quantity = resultSetTicPro.getInt("quantity");
            Product product = loadProductWithId(id);
            productMap.put(product, quantity);
        }
        resultSetTicPro.close();
        return productMap;
    }

    public static List<Ticket> loadTickets(){
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM tickets ORDER BY id";
        try{
            ResultSet resultSet = statementTic.executeQuery(query);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                float total = resultSet.getFloat("total");
                Map<Product, Integer> productMap = loadTicketProducts(id);
                Ticket ticket = new Ticket(id, productMap, total);
                tickets.add(ticket);
            }
            resultSet.close();
            return tickets;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Problem with database");
            return tickets;
        }
    }
}