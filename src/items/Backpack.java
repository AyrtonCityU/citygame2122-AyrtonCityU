package items;

import java.util.ArrayList;

//Create the players backpack
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

    public void toggle(){ //Toggle the current item

        getCurrentItem().takeOff();
        currentItem++;
        if (currentItem == items.size()) {
            currentItem = 0;
        }
        if (j == "Boots"){
            j = "Gun";
        }
        else if (j== "Gun"){
            j = "Shotgun";
        }
        else {
            j = "Boots";
        }


        System.out.println("Current item:" + getCurrentItem().getType());
    }
}
