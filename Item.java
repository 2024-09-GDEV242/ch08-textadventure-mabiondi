/**
 * An item, multiple of which can be stored in a room.
 *
 * @author Michael Biondi
 * @version 2024.11.10
 */
public class Item
{
    private String description;
    private double weight;

    /**
     * Constructor for items
     * @param description The description of the item
     * @param weight The weight of the item in pounds
     */
    public Item(String description, double weight)
    {
        this.description = description;
        this.weight = weight;
    }
    
    /**
     * Return the description of the item
     * 
     * @return The description of the item
     */
    public String getItemDescription()
    {
        return description;
    }
    
    /**
     * Set a new description for the item
     *
     * @param newDescription The new description of the item
     */
    public void setItemDescription(String newDescription)
    {
        description = newDescription;
    }
    
    /**
     * Return the weight of the item
     * 
     * @return The weight of the item in pounds
     */
    public double getItemWeight()
    {
        return weight;
    }
    
    /**
     * Set a new weight for the item
     *
     * @param newWeight The new weight of the item in pounds
     */
    public void setItemWeight(double newWeight)
    {
        weight = newWeight;
    }
}
