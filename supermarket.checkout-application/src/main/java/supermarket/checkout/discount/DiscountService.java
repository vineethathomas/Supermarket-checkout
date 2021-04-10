package supermarket.checkout.discount;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import supermarket.checkout.discount.rules.DiscountRule;
import supermarket.checkout.shoppingitem.Item;

public class DiscountService {

	DiscountFactory factory;

	public DiscountService(DiscountFactory factory) {
		this.factory = factory;
	}

    public void applyDiscount(List<Item> items) {
		List<DiscountRule> discounts = factory.discounts();
		Collections.sort(discounts, Comparator.comparing(DiscountRule::getPriority));
		discounts.stream().forEachOrdered( discount -> discount.apply(items));
    }
}


