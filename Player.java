import java.util.Stack;

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

    /**
     * Constructor to init the player in a specified room
     * 
     * @param startingRoom The room to start the player in.
     */
    public Player(Room startingRoom)
    {
        currentRoom = startingRoom;
        roomsSequence = new Stack<Room>();
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
     * Takes the player back one room. Returns
     * whether back was successful or not.
     * 
     * @return Whether the player wa sable to be taken back or not.
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
