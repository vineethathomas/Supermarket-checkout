package supermarket.checkout;

import java.util.Currency;
import java.util.List;

import supermarket.checkout.discount.DiscountService;
import supermarket.checkout.exception.NoItemsException;
import supermarket.checkout.shoppingitem.Item;

public class CheckoutCounter {

    private DiscountService discountService;
	private CheckoutBill receipt;
	private Currency currency;

    public CheckoutCounter(DiscountService discountService, CheckoutBill receipt, Currency currency) {
        this.discountService = discountService;
        this.receipt = receipt;
        this.currency = currency;
    }

    public String doCheckout(List<Item> items) throws NoItemsException {
        if (items == null || items.isEmpty()) throw new NoItemsException();
        discountService.applyDiscount(items);
        return receipt.generateBill(items, currency.getSymbol());
    }

}

