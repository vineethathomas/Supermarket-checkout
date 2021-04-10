package supermarket;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import supermarket.checkout.CheckoutBill;
import supermarket.checkout.CheckoutCounter;
import supermarket.checkout.discount.DiscountFactory;
import supermarket.checkout.discount.DiscountService;
import supermarket.checkout.shoppingitem.Item;

public class IntegrationTest {

	@Test
	public void IntegrationTest() throws Exception {
		DiscountFactory discountFactory = new DiscountFactory();
		DiscountService discountService = new DiscountService(discountFactory);
		CheckoutBill bill = new CheckoutBill();
		Currency currency = Currency.getInstance("AUD");
		CheckoutCounter checkoutCounter = new CheckoutCounter(discountService, bill, currency);

		List<Item> items = Stream.of(
				Item.B,
				Item.A,
				Item.B
		).collect(Collectors.toList());
		String billOutput = checkoutCounter.doCheckout(items);
		System.out.print(billOutput);

		assertThat(billOutput, is(
				"### BILL ###\n" +
						"B: $45.00\n" +
						"A: $60.00\n" +
						"B: $45.00\n" +
						"\nTotal: $105.00"
		));
	}

}
