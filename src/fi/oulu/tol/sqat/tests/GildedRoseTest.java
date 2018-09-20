package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

// Example scenarios for testing
//   Item("+5 Dexterity Vest", 10, 20));
//   Item("Aged Brie", 2, 0));
//   Item("Elixir of the Mongoose", 5, 7));
//   Item("Sulfuras, Hand of Ragnaros", 0, 80));
//   Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
//   Item("Conjured Mana Cake", 3, 6));

	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_10_11() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(11, itemBrie.getQuality());
	}
	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_0_1() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 0) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(1, itemBrie.getQuality());
	}
	/*quality never over 50*/
	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_50_50() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 50) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(50, itemBrie.getQuality());
		assertEquals(1, itemBrie.getSellIn());
	}
    
	@Test
	public void testUpdateEndOfDay_DexVest_20_19() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("+5 Dexterity Vest", 10, 20));
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemDexVest = items.get(0);
		assertEquals(19, itemDexVest.getQuality());
		assertEquals(9, itemDexVest.getSellIn());
	}
	/*after SellIn passed, quality degrades twice as fast*/
	@Test
	public void testUpdateEndOfDay_DexVest_2_0() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("+5 Dexterity Vest", 0, 2));
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemDexVest = items.get(0);
		assertEquals(0, itemDexVest.getQuality());
		assertEquals(-1, itemDexVest.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_Elixir_7_6() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Elixir of the Mongoose", 5, 7));
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemElixir = items.get(0);
		assertEquals(6, itemElixir.getQuality());
	}
	/*quality never under 0*/
	@Test
	public void testUpdateEndOfDay_Elixir_0_0() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Elixir of the Mongoose", 5, 0));
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemElixir = items.get(0);
		assertEquals(0, itemElixir.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_Sulfuras() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemSulfuras = items.get(0);
		assertEquals(80, itemSulfuras.getQuality());
		assertEquals(0, itemSulfuras.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_Tickets_20_21() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBSPass = items.get(0);
		assertEquals(21, itemBSPass.getQuality());
		assertEquals(14, itemBSPass.getSellIn());
	}
/*same, but SellIn <= 10; quality + 2*/
	@Test
	public void testUpdateEndOfDay_Tickets_20_22() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20));
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBSPass = items.get(0);
		assertEquals(22, itemBSPass.getQuality());
		assertEquals(9, itemBSPass.getSellIn());
	}
/*same, but SellIn <= 5; q + 3*/
	@Test
	public void testUpdateEndOfDay_Tickets_20_23() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20));
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBSPass = items.get(0);
		assertEquals(23, itemBSPass.getQuality());
		assertEquals(4, itemBSPass.getSellIn());
	}
/*same, but SellIn = 0; q = 0*/
	@Test
	public void testUpdateEndOfDay_Tickets_20_0() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20));
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBSPass = items.get(0);
		assertEquals(0, itemBSPass.getQuality());
		assertEquals(-1, itemBSPass.getSellIn());
	}
	
}
