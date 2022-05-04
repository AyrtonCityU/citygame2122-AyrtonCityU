package items;

import java.util.ArrayList;

public class Backpack {

    ArrayList<BackpackItem> items;

    private int currentItem;

    public String j = "Boots";


    public Backpack(){
        items = new ArrayList<BackpackItem>();
        currentItem = -1;
    }

    public void addItem(BackpackItem item){
        items.add(item);
        currentItem = items.size()-1;
    }

    public BackpackItem getCurrentItem(){
        return items.get(currentItem);
    }

    public void toggle(){

        getCurrentItem().takeOff();
        currentItem++;
        if (currentItem == items.size()) {
            currentItem = 0;
        }
        if (j == "Boots"){
            j = "Gun";
        }
        else{
            j = "Boots";
        }

        getCurrentItem().putOn();

        System.out.println("Current item:" + getCurrentItem().getType());
    }
}
