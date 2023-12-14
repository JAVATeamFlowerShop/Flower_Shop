import n1exercici1.*;
import n1exercici1.exceptions.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestFlowerShop {

    static Tree tree = new Tree(21, "pine", 25.99f, 1.6f);
    static Decoration decoration = new Decoration(28, "vase", 15.99f, Decoration.Material.WOOD);
    static FlowerShop myShop;

    @BeforeAll
    static void createFlowerShop() {
        myShop = FlowerShop.createFlowerShop();
        myShop.addProduct(decoration, 5);
    }

    @DisplayName("add non previously existent product")
    @Test
    @Order(2)
    void testAddProduct() {
        Flower flower = new Flower(17, "rose", 2.5f, "pink");
        myShop.addProduct(flower, 7);
        final int[] quantity = new int[1];
        myShop.getStock().entrySet().stream().filter(e -> e.getKey() instanceof Flower && e.getKey().equals(flower)).forEach(e -> quantity[0] = e.getValue());
        assertEquals(7, quantity[0]);

    }

    @DisplayName("add previously existent product")
    @Test
    @Order(3)
    void testAddProductAlreadyExists() {
        Tree tree = new Tree(17, "prueba tree", 2.5f, 1.75f);
        myShop.addProduct(tree, 2);
        myShop.addProduct(tree, 3);
        final int[] quantity = new int[1];
        myShop.getStock().entrySet().stream().filter(e -> e.getKey() instanceof Tree && e.getKey().equals(tree)).forEach(e -> quantity[0] = e.getValue());
        assertEquals(5, quantity[0]);
    }

    @DisplayName("remove less than total of a product")
    @Test
    @Order(4)
    void testRemoveProduct() throws NotEnoughStockException {
        myShop.removeProduct(decoration, 2);
        final int[] quantity = new int[1];
        myShop.getStock().entrySet().stream().filter(e -> e.getKey() instanceof Decoration && e.getKey().equals(decoration)).forEach(e -> quantity[0] = e.getValue());
        assertEquals(3, quantity[0]);
    }

    @DisplayName("remove more than total of a product")
    @Test
    @Order(5)
    void testRemoveProductException() {
        assertThrows(NotEnoughStockException.class,
                () -> myShop.removeProduct(decoration, 7));
    }

    @DisplayName("total value store test")
    @Test
    @Order(1)
    void testCalcValueStore() {
        float value = myShop.calcValueStore();
        assertEquals(3918, Math.round(value));
    }

    @DisplayName("store stock value gets updated when adding product")
    @Test
    @Order(6)
    void testUpdateStockValueOnAdd() {
        float stockValueOriginal = myShop.calcValueStore();
        myShop.addProduct(tree, 1);
        float stockValue = myShop.calcValueStore();
        assertEquals(Math.round(stockValueOriginal + tree.getPrice()), Math.round(stockValue));
    }
    @DisplayName("store stock value gets updated when removing product")
    @Test
    @Order(7)
    void testUpdateStockValueOnRemove() throws NotEnoughStockException {
        float stockValueOriginal = myShop.calcValueStore();
        myShop.removeProduct(decoration, 2);
        float stockValue = myShop.calcValueStore();
        assertEquals(Math.round(stockValueOriginal - decoration.getPrice()*2), Math.round(stockValue));
    }
}