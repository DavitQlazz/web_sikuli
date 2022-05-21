package tests;

import base.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomePageTest extends TestBase {

    HomePage homePage;

    @Test
    public void AddingSaleItemsToShoppingCart() {
        Integer saleItemsCount = homePage
                .open()
                .getSaleItemsCount();
        if (saleItemsCount == 0) {
            System.out.println("No Sale Item");
            return;
        }
        homePage
                .addAllSaleItemsToCard()
                .checkItemsCount(saleItemsCount);
    }
}

