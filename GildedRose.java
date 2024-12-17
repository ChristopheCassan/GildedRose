package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item: items) {
            // Sulfuras does not degrade or change
            if (!item.name.contains("Sulfuras")) {
                item.sellIn--;
                // Aged Brie increase quality
                if (item.name.startsWith("Aged Brie")) {
                    updateAgedBrie(item);
                }
                // Backstage passes increase quality specifically
                else if (item.name.startsWith("Backstage passes")) {
                    updateBackstagePasses(item);
                }
                // Conjured decrease quality specifically
                else if (item.name.startsWith("Conjured")) {
                    updateConjuredItem(item);
                }
                // Normal item decreases quality
                else {
                    updateNormalItem(item);
                }
            }
        }
    }

    private void updateAgedBrie(Item item) {
        increaseQuality(item);
        if (item.sellIn < 0) {
            increaseQuality(item);
        }
    }

    private void updateBackstagePasses(Item item) {
        increaseQuality(item);
        if (item.sellIn < 0) {
            item.quality = 0;
        } else if (item.sellIn <= 5) {
            increaseQuality(item);
            increaseQuality(item);
        } else if (item.sellIn <= 10) {
            increaseQuality(item);
        }
    }

    private void updateConjuredItem(Item item) {
        decreaseQuality(item);
        decreaseQuality(item);
        if (item.sellIn < 0) {
            decreaseQuality(item);
            decreaseQuality(item);
        }
    }

    private void updateNormalItem(Item item) {
        decreaseQuality(item);
        if (item.sellIn < 0) {
            decreaseQuality(item);
        }
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality--;
        }
    }
}
