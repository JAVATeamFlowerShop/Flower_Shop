package n2exercici1;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseManager {
    private static Connection con;
    private static Statement statementProd;
    private static Statement statementSub;
    private static Statement statementTic;
    private static Statement statementTicPro;
    private static final String TYPE_FLOWER = "FLOWER";
    private static final String TYPE_TREE = "TREE";
    private static final String TYPE_DECORATION = "DECORATION";

    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://192.168.56.102:3306/flowershop", "sandy", "1234");
            statementProd = con.createStatement();
            statementSub = con.createStatement();
            statementTic = con.createStatement();
            statementTicPro = con.createStatement();
        } catch (Exception ex) {
            System.err.println("Problem connecting to database");
        }
    }

    private static void execute(String query){
        try {
            statementProd.execute(query);
            System.out.println("Product successfully saved");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Problem saving product to database");
        }
    }
    public static void saveProduct(Product product, int quantity){
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
        execute(query);
        execute(subquery);
    }
    public static void updateStock(Product product, int quantity){
        System.out.println("Updating product stock...");
        String query = String.format("UPDATE products SET quantity = %d WHERE idproduct = %d", quantity, product.getId());
        execute(query);
    }
    public static void saveTicket(Ticket ticket){
        String query = String.format("INSERT INTO tickets VALUES(%d, %.2f)", ticket.getId(), ticket.getAmount());
        execute(query);
        Map<Product, Integer> products = ticket.getProductMap();
        products.forEach((pro, quantity) -> execute(String.format("INSERT INTO ticket_has_products VALUES(%d, %d, %d)", ticket.getId(), pro.getId(), quantity)));
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
    private static Product loadProductWithId(int id) throws SQLException{
        String query = String.format("SELECT * FROM products WHERE id = %d;", id);
        ResultSet resultSet = statementProd.executeQuery(query);
        Product.Type type = Product.Type.valueOf(resultSet.getString("type"));
        String name = resultSet.getString("name");
        float price = resultSet.getFloat("price");
        Product product = loadProduct(id, type.toString(), name, price);
        resultSet.close();
        return product;
    }


    public static Map<Product, Integer> loadStock(){
        System.out.println("Loading stock...");
        Map<Product, Integer> stock = new HashMap<>();
        String query = "SELECT * FROM products;";
        try {
            ResultSet resultSet = statementProd.executeQuery(query);
            while(resultSet.next()){
                int id = resultSet.getInt("idproduct");
                Product.Type type = Product.Type.valueOf(resultSet.getString("type"));
                String name = resultSet.getString("name");
                float price = resultSet.getFloat("price");
                int quantity = resultSet.getInt("quantity");
                Product product = loadProduct(id, type.toString(), name, price);
                stock.put(product, quantity);
            }
            resultSet.close();
            System.out.println("Stock successfully loaded");
            return stock;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Problem loading stock data, starting with empty stock");
            return new HashMap<Product, Integer>();
        }
    }

    private static Map<Product, Integer> loadTicketProducts(int idTicket) throws SQLException{
        Map<Product, Integer> productMap = new HashMap<>();
        String query = String.format("SELECT * FROM tickets_has_products WHERE idTicket = %d", idTicket);
        ResultSet resultSetTicPro = statementTicPro.executeQuery(query);
        while (resultSetTicPro.next()){
            int idProduct = resultSetTicPro.getInt("idproduct");
            int quantity = resultSetTicPro.getInt("quantity");
            Product product = loadProductWithId(idProduct);
            productMap.put(product, quantity);
        }
        resultSetTicPro.close();
        return productMap;
    }

    public static List<Ticket> loadTickets(){
        System.out.println("Loading tickets...");
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM tickets";
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
            System.out.println("Tickets successfully loaded");
            return tickets;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Problem loading ticket data, starting with no previous sales");
            return new ArrayList<Ticket>();
        }
    }
}