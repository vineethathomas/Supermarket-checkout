package supermarket.discount;

import org.junit.Test;

import supermarket.checkout.discount.DiscountFactory;
import supermarket.checkout.discount.rules.DiscountRule;
import supermarket.checkout.discount.rules.RuleForItemA;
import supermarket.checkout.discount.rules.RuleForItemB;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class DiscountFactoryTest {

	@Test
	public void ReturnsDiscountRuleSet() throws Exception {
		DiscountFactory discountFactory = new DiscountFactory();
		List<DiscountRule> expectedRuleSet = Stream.of(
				new RuleForItemA(1),
				new RuleForItemB(2)
				
		).collect(Collectors.toList());
		List<DiscountRule> actualRuleSet = discountFactory.discounts();

		assertThat(actualRuleSet.size(), is(expectedRuleSet.size()));
	}

}