package supermarket.shoppingitem;

import org.junit.Test;

import supermarket.checkout.shoppingitem.Item;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ItemTest {

	 @Test
	    public void should() {
		 //name field
	        assertThat(Item.A.getName(), is("A"));
	        assertThat(Item.B.getName(), is("B"));
	        
	      //price
	        assertThat(Item.A.getPrice(), is(60.00));
	        assertThat(Item.B.getPrice(), is(45.00));
	      
	       //discount boolean
	        assertThat(Item.A.hasDiscountApplied(), is(false));
	        Item.B.markAsDiscounted();
	        assertThat(Item.A.hasDiscountApplied(), is(true));
	    }


	

}