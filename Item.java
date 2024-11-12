/**
 * An item, multiple of which can be stored in a room.
 *
 * @author Michael Biondi
 * @version 2024.11.12
 */
public class Item
{
    private String name;
    private String description;
    private double weight;

    /**
     * Constructor for items
     * @param name The short name of the item
     * @param description The description of the item
     * @param weight The weight of the item in pounds
     */
    public Item(String name, String description, double weight)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }
    
    /**
     * Return the short name of the item
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Set a new name for the item
     * 
     * @param newName The new name for the item
     */
    public void setName(String newName)
    {
        name = newName;
    }
    
    /**
     * Return the description of the item
     * 
     * @return The description of the item
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Set a new description for the item
     *
     * @param newDescription The new description of the item
     */
    public void setDescription(String newDescription)
    {
        description = newDescription;
    }
    
    /**
     * Return the weight of the item
     * 
     * @return The weight of the item in pounds
     */
    public double getWeight()
    {
        return weight;
    }
    
    /**
     * Set a new weight for the item
     *
     * @param newWeight The new weight of the item in pounds
     */
    public void setWeight(double newWeight)
    {
        weight = newWeight;
    }
}
