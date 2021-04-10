package supermarket;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import supermarket.checkout.CheckoutBill;
import supermarket.checkout.shoppingitem.Item;

public class CheckoutBillTest {

	CheckoutBill bill;
	String currencySymbol = Currency.getInstance("AUD").getSymbol();

	@Before
	public void setUp() throws Exception {
		bill = new CheckoutBill();
	}

	@Test
	public void Given_OneItem_Then_ReturnABillString() {
		//Item item = mockNewItem("A", 60.00);
		List<Item> items = Stream.of(Item.A).collect(Collectors.toList());
		String expectedBill = CheckoutBill.BILL_TITLE + "A: $60\n\nTotal: $60.00";
		Assert.assertThat(bill.generateBill(items, currencySymbol), is(expectedBill));
	}

	@Test
	public void Given_TwoSameItems_Then_ReturnABillString() {
		//Item item = mockNewItem("A", 60.00);
		//Item item2 = mockNewItem("A", 60.00);
		List<Item> items = Stream.of(Item.A, Item.A).collect(Collectors.toList());
		String expectedBill = CheckoutBill.BILL_TITLE + "A: $60\nA: $60\n\nTotal: $120.00";
		Assert.assertThat(bill.generateBill(items, currencySymbol), is(expectedBill));
	}

	

	/*private Item mockNewItem(String name, double price) {
		Item item = mock(Item.class);
		when(item.getName()).thenReturn(name);
		when(item.getPrice()).thenReturn(new BigDecimal(price));
		return item;
	}*/

}