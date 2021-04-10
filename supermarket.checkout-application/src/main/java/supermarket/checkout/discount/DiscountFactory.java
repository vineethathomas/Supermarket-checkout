package supermarket.checkout.discount;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import supermarket.checkout.discount.rules.DiscountRule;
import supermarket.checkout.discount.rules.RuleForItemA;
import supermarket.checkout.discount.rules.RuleForItemB;

/**
 * Made to encapsulate the creation of discount rules. The initial idea was to
 * have these discount rules and their priority configurable from a settings file (e.g. XML)
 * to provide inversion of control and the ability to easily add/remove rules.
 */
public class DiscountFactory {

	public List<DiscountRule> discounts() {
		return Stream.of(
				new RuleForItemA(1),
				new RuleForItemB(2)
						)
		.collect(Collectors.toList());
	}

}
