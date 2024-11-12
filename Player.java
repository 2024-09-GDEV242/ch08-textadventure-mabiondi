import java.util.Stack;
import java.util.ArrayList;

/**
 * The player of the game, including what room
 * they are currently in, what rooms they have visited,
 * and what items they are currently holding.
 *
 * @author Michael Biondi
 * @version 2024.11.11
 */
public class Player
{
    private Room currentRoom;
    private Stack<Room> roomsSequence;
    private ArrayList<Item> inventory;

    /**
     * Constructor to init the player in a specified room
     * 
     * @param startingRoom The room to start the player in.
     */
    public Player(Room startingRoom)
    {
        currentRoom = startingRoom;
        roomsSequence = new Stack<Room>();
        roomsSequence.push(startingRoom);
        inventory = new ArrayList<Item>();
    }
    
    /**
     * Returns the current room the player is located in.
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    /**
     * Sets the current room of the player to a specified room.
     * 
     * @param nextRoom The room to become the player's current room.
     */
    public void updateCurrentRoom(Room nextRoom)
    {
        currentRoom = nextRoom;
        roomsSequence.push(currentRoom);
    }
    
    /**
     * Returns a string describing the items in the player's inventory, for example:
     * "Items in Inventory:
     * the Blade of Zeltron (zeltron), 2000.0 lbs
     * cardboard box (box), 0.1 lbs"
     * 
     * @return Info about the items in the player's inventory
     */
    public String items()
    {
        String returnString = "Items in Inventory:";
        for(Item item : inventory) {
            String weightString = String.format("%.1f", item.getWeight());
            returnString +=
            "\n" + item.getDescription() + " (" + item.getName() + "), "
            + weightString + " lbs";
        }
        return returnString;
    }
    
    /**
     * Removes the named item from the player's
     * current room and adds it to their inventory.
     * 
     * @param itemName The short name of the item to be picked up.
     */
    public void pickUpItem(String itemName)
    {
        for(Item item : currentRoom.getItems())
        {
            if(itemName.equals(item.getName()))
            {
                inventory.add(item);
                currentRoom.removeItem(item);
                System.out.println("You picked up " + item.getDescription() + "!");
                return;
            }
        }
        System.out.println("You struggle to pick anything up.");
    }
    
    /**
     * Removes the named item from the player's
     * inventory and adds it to their current room.
     * 
     * @param itemName The short name of the item to be picked up.
     */
    public void dropItem(String itemName)
    {
        for(Item item : inventory)
        {
            if(itemName.equals(item.getName()))
            {
                currentRoom.addItem(item);
                inventory.remove(item);
                System.out.println("You dropped " + item.getDescription() + ".");
                return;
            }
        }
        System.out.println("You can't let go.");
    }
    
    /**
     * Takes the player back one room. Returns
     * whether back was successful or not.
     * 
     * @return Whether the player was able to be taken back or not.
     */
    public boolean back()
    {
        if (roomsSequence.size() > 1) {
            roomsSequence.pop();
            currentRoom = roomsSequence.peek();
            return true;
        }
        else {
            System.out.println("You are already at the starting location and cannot go back further.");
            return false;
        }
    }
}
