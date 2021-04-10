package supermarket.discount.rules;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import supermarket.checkout.discount.rules.RuleForItemA;
import supermarket.checkout.shoppingitem.Item;

public class RuleForItemATest {

	private final BigDecimal priceOfA = Item.A.getPrice();
	private RuleForItemA rule;

	@Before
	public void setup() {
		this.rule = new RuleForItemA(1);
	}

	@Test
	public void Given_3As_Then_ThirdOneIsHalfPrice() {
		Item a1 = Item.A;
		Item a2 = Item.A;
		Item a3 = Item.A;
		List<Item> items = Stream.of(a1, a2, a3).collect(Collectors.toList());
		rule.apply(items);

		assertPriceAndDiscount(a1, priceOfA, true);
		assertPriceAndDiscount(a2, priceOfA, true);
		assertPriceAndDiscount(a3, new BigDecimal(Item.A.getPrice().doubleValue()/2), true);
	}

	
	private void assertPriceAndDiscount(Item item, BigDecimal expectedPrice, Boolean hasDiscountApplied) {
		assertThat(item.getPrice(), is(expectedPrice));
		assertThat(item.hasDiscountApplied(), is(hasDiscountApplied));
	}

}