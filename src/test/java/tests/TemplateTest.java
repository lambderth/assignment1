package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;

public class TemplateTest extends BaseTest {

    @Test(enabled = true)
    public void searchOnGoogle() {
        templatePage.searchSomething("Busqueda de prueba");
        
        // Mock assertions
        Assert.assertEquals(true, true);
    }
}
