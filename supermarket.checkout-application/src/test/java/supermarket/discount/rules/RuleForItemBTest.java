package supermarket.discount.rules;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import supermarket.checkout.discount.rules.RuleForItemB;
import supermarket.checkout.shoppingitem.Item;

public class RuleForItemBTest {

	private final BigDecimal priceOfB = Item.B.getPrice();
	private RuleForItemB rule;

	@Before
	public void setup() {
		this.rule = new RuleForItemB(1);
	}

	@Test
	public void Given_2Bs_Then_SecondOneIsHalfPrice() {
		Item b1 = Item.B;
		Item b2 = Item.B;
		
		List<Item> items = Stream.of(b1,b2).collect(Collectors.toList());
		rule.apply(items);

		assertPriceAndDiscount(b1, priceOfB, true);
		
		assertPriceAndDiscount(b2, new BigDecimal(priceOfB.doubleValue()/2), true);
	}

	
	private void assertPriceAndDiscount(Item item, BigDecimal expectedPrice, Boolean hasDiscountApplied) {
		assertThat(item.getPrice(), is(expectedPrice));
		assertThat(item.hasDiscountApplied(), is(hasDiscountApplied));
	}

}