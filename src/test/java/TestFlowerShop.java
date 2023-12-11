import n1exercici1.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class TestFlowerShop {

    static FlowerShop myShop;

    @BeforeAll
    static void createShop(){
        myShop = FlowerShop.createFlowerShop();
    }
    @DisplayName("Calc Value test")
    @Test
    void testCalcValue(){

    }

    @Test
    void testCalcValueStore(){
        float value = myShop.calcValueStore();
        assertEquals(1719.17F, value);
    }

    @Test
    void testAddProduct(){

    }

    @Test
    void testRemoveProduct(){

    }

    @Test
    void testFindProduct(){

    }

    @Test
    void testFindProductById(){

    }


}
