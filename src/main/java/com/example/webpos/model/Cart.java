package com.example.webpos.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {

    private List<Item> items = new ArrayList<>();

    public boolean addItem(Item item) {
        Iterator<Item> iter = items.iterator();
        while(iter.hasNext()) {
            Item tmp = iter.next();
            if(tmp.getProduct().getId() == item.getProduct().getId()) {
                int newAmount = tmp.getQuantity() + item.getQuantity();
                if(newAmount <= 0) {
                    iter.remove();
                }
                else {
                    tmp.setQuantity(newAmount);
                }
                return true;
            }
        }
        return items.add(item);
    }

    public boolean deleteItem(String productId) {
        Iterator<Item> iter = items.iterator();
        while(iter.hasNext()) {
            Item tmp = iter.next();
            if(tmp.getProduct().getId().equals(productId)) {
                iter.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (items.size() ==0){
            return "Empty Cart";
        }
        double total = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cart -----------------\n"  );

        for (int i = 0; i < items.size(); i++) {
            stringBuilder.append(items.get(i).toString()).append("\n");
            total += items.get(i).getQuantity() * items.get(i).getProduct().getPrice();
        }
        stringBuilder.append("----------------------\n"  );

        stringBuilder.append("Total...\t\t\t" + total );

        return stringBuilder.toString();
    }

    public double calculateTotal() {
        double total = 0;
        for(Item item : items) {
            total += item.getQuantity() * item.getProduct().getPrice();
        }
        return total;
    }

    public void clear() {
        items.clear();
    }
}
