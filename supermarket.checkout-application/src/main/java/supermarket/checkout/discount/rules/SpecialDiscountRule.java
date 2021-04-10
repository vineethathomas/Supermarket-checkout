package supermarket.checkout.discount.rules;

import java.util.List;

import supermarket.checkout.shoppingitem.Item;

public abstract class SpecialDiscountRule extends DiscountRule {

	protected int numItemsRequiredForDiscount;
	protected int numItemsToDiscount;

	public SpecialDiscountRule(int numItemsRequiredForDiscount, int numItemsToDiscount, int priority) {
		super(priority);
		this.numItemsRequiredForDiscount = numItemsRequiredForDiscount;
		this.numItemsToDiscount = numItemsToDiscount;
	}

	@Override
	void applyDiscountOn(List<Item> items) {
		for (int i = 0; i < items.size(); i++) {
			if (shouldApply(i)) {
				List<Item> itemSetToApplyDiscount = items.subList(i + 1 - numItemsRequiredForDiscount, i + 1);
				applyDiscountOnSet(itemSetToApplyDiscount);
				markItemSetAsDiscounted(itemSetToApplyDiscount);
			}
		}
	}

	private Boolean shouldApply(int index) {
		return (index + 1) % numItemsRequiredForDiscount == 0;
	}

	abstract void applyDiscountOnSet(List<Item> itemSet);

	private void markItemSetAsDiscounted(List<Item> items) {
		items.forEach(Item::markAsDiscounted);
	}

}
