package supermarket.checkout.discount.rules;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import supermarket.checkout.shoppingitem.Item;

public class RuleForItemA extends SpecialDiscountRule {

	private static final int NUM_OF_ITEMS_REQUIRED_FOR_DISCOUNT = 3;
	private static final int NUM_OF_ITEMS_TO_DISCOUNT = 1;

	public RuleForItemA(int priority) {
		super(NUM_OF_ITEMS_REQUIRED_FOR_DISCOUNT, NUM_OF_ITEMS_TO_DISCOUNT, priority);
	}

	@Override
	protected Boolean filterCriteria(Item item) {
		return "A".equals(item.getName()) && !item.hasDiscountApplied();
	}

	@Override
	protected void applyDiscountOnSet(List<Item> items) {
		items.get(items.size() - 1).setPrice(Item.A.getPrice().doubleValue()/2);
	}
}
