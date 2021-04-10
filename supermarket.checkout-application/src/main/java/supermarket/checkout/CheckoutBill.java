package supermarket.checkout;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import supermarket.checkout.discount.rules.DiscountRule;
import supermarket.checkout.shoppingitem.Item;

public class CheckoutBill {

	public static final String BILL_TITLE = "### BILL ###\n";

    public String generateBill(List<Item> items, String currencySymbol) {
		Collections.sort(items, Comparator.comparing(Item::getName));
		String formattedItems = formatLineItems(items, currencySymbol);
		BigDecimal totalPrice = calcTotalPrice(items);
        return BILL_TITLE + "" + formattedItems + "\nTotal: " + currencySymbol + totalPrice.setScale(2, BigDecimal.ROUND_DOWN);
    }

	private String formatLineItems(List<Item> items, String currencySymbol) {
		return items.stream().map(item -> genLineItem(item, currencySymbol)).collect(Collectors.joining());
	}

	private String genLineItem(Item item, String currency) {
		return item.getName() + ": " + currency + item.getPrice()+ "\n";
	}

	private BigDecimal calcTotalPrice(List<Item> items) {
    	return items.stream().map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}


