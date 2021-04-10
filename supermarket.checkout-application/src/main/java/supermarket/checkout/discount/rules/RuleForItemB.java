package supermarket.checkout.discount.rules;

import java.util.List;

import supermarket.checkout.shoppingitem.Item;

public class RuleForItemB extends SpecialDiscountRule {

	private static final int NUM_OF_ITEMS_REQUIRED_FOR_DISCOUNT = 2;
	private static final int NUM_OF_ITEMS_TO_DISCOUNT = 1;

	public RuleForItemB(int priority) {
		super(NUM_OF_ITEMS_REQUIRED_FOR_DISCOUNT, NUM_OF_ITEMS_TO_DISCOUNT, priority);
	}

	@Override
	protected Boolean filterCriteria(Item item) {
		return "B".equals(item.getName()) && !item.hasDiscountApplied();
	}

	@Override
	protected void applyDiscountOnSet(List<Item> items) {
		items.get(items.size() - 1).setPrice(Item.B.getPrice().doubleValue()/2);
	}
}
