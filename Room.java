import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes, updated by Michael Biondi
 * @version 2024.11.10
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private HashSet<Item> items;                    // stores items in this room

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new HashSet<Item>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    /**
     * Add an item to this room.
     * 
     * @param description The description of the item
     * @param weight The weight of the item in pounds
     */
    public void addItem(String name, String description, double weight)
    {
        items.add(new Item(name, description, weight));
    }
    
    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + "\n" + getItemString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    
    /**
     * Return the HashSet of items in the room
     */
    public HashSet<Item> getItems()
    {
        return items;
    }
    
    /**
     * Add an item to the room
     * 
     * @param itemToAdd The Item to be added to the room
     */
    public void addItem(Item itemToAdd)
    {
        items.add(itemToAdd);
    }
    
    /**
     * Remove the specified item from the room
     * 
     * @param itemToRemove The Item to be removed from the room
     */
    public void removeItem(Item itemToRemove)
    {
        items.remove(itemToRemove);
    }
    
    /**
     * Returns a string describing the items in the room, for example:
     * "Items in room:
     * the Blade of Zeltron (zeltron), 2000.0 lbs
     * cardboard box (box), 0.1 lbs"
     * 
     * @return Info about the items in the room
     */
    private String getItemString()
    {
        String returnString = "Items in room:";
        for(Item item : items) {
            String weightString = String.format("%.1f", item.getWeight());
            returnString +=
            "\n" + item.getDescription() + " (" + item.getName() + "), "
            + weightString + " lbs";
        }
        return returnString;
    }
    
    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

