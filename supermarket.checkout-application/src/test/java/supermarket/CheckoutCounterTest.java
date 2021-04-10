package supermarket;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import supermarket.checkout.CheckoutBill;
import supermarket.checkout.CheckoutCounter;
import supermarket.checkout.discount.DiscountService;
import supermarket.checkout.exception.NoItemsException;
import supermarket.checkout.shoppingitem.Item;

public class CheckoutCounterTest {

    CheckoutCounter checkoutCounter;
    DiscountService mockedDiscountService;
    CheckoutBill mockedBill;
    Currency currency = Currency.getInstance("GBP");

    @Before
    public void setUp() throws Exception {
        mockedDiscountService = mock(DiscountService.class);
        mockedBill = mock(CheckoutBill.class);
        checkoutCounter = new CheckoutCounter(mockedDiscountService, mockedBill, currency);
    }

    @Test
    public void Given_Items_Then_ApplyDiscounts() throws Exception {
        List<Item> items = mockItems();
        checkoutCounter.doCheckout(items);
        verify(mockedDiscountService, times(1)).applyDiscount(items);
    }

    @Test
    public void Given_Items_Then_GenerateABill() throws Exception {
        List<Item> items = mockItems();
        String expectedBillOutput = "BillOutput";
        when(mockedBill.generateBill(items, currency.getSymbol())).thenReturn(expectedBillOutput);
        Assert.assertThat(checkoutCounter.doCheckout(items), is(expectedBillOutput));
    }

    @Test(expected = NoItemsException.class)
    public void Given_NullItems_Then_ThrowAnException() throws Exception {
        checkoutCounter.doCheckout(null);
    }

    @Test(expected = NoItemsException.class)
    public void Given_EmptyItems_Then_ThrowAnException() throws Exception {
        checkoutCounter.doCheckout(new ArrayList<>());
    }

    private List<Item> mockItems() {
        return Stream.of(mock(Item.class)).collect(Collectors.toList());
    }

}