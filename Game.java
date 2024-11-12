/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes, updated by Michael Biondi
 * @version 2024.11.12
 */

public class Game 
{
    private Parser parser;
    private Player player;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room bedroom, livingRoom, kitchen, outside, station, train, city, dome;
      
        // create the rooms
        bedroom = new Room("in your childhood bedroom, where you always hoped that one day you would amount to something greater");
        livingRoom = new Room("in the living room");
        kitchen = new Room("in the family kitchen. Usually, there's some snacks left on the table");
        outside = new Room("outside");
        station = new Room("at your local subway station");
        train = new Room("on a crowded, noisy subway train");
        city = new Room("in the middle of a sprawling uptopia, with dizzyingly tall skyscrapers and flying cars whizzing by");
        dome = new Room("in a giant, imposing glass DOME, full of other penguins pummeling each other with the most comical of weapons");
        
        // initialise room exits
        bedroom.setExit("downstairs", livingRoom);
        
        livingRoom.setExit("upstairs", bedroom);
        livingRoom.setExit("ahead", kitchen);
        
        kitchen.setExit("lounge", livingRoom);
        kitchen.setExit("outside", outside);
        
        outside.setExit("away", station);
        outside.setExit("inside", kitchen);
        
        station.setExit("choochoo", train);
        station.setExit("outside", outside);
        
        train.setExit("off", city);
        train.setExit("station", station);
        
        city.setExit("north", dome);
        city.setExit("underground", train);
        
        dome.setExit("south", city);
        
        // add items
        bedroom.addItem("coffee", "a coffee mug", 1.0);
        kitchen.addItem("chips", "a bag of chips", 0.2);
        station.addItem("guitar", "an acoustic guitar, probably left behind by your friend Dave", 3.5);
        train.addItem("sword", "the Master Sword? THE Master Sword? Left behind on a train? Well this is getting ridiculous", 10.0);
        
        player = new Player(bedroom);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to PENGUIN DOME");
        System.out.println("PENGUIN DOME is an absolutely ludicrous text-based adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        look();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;
                
            case LOOK:
                look();
                break;
            
            case TAKE:
                take(command);
                break;
            
            case DROP:
                drop(command);
                break;
                
            case BACK:
                back();
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are a penguin. You've always aspired to become");
        System.out.println("one of the great gladiators of the PENGUIN DOME");
        System.out.println("who fight each other honorably, with some of the");
        System.out.println("wackiest weapons known to penguinkind.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            player.updateCurrentRoom(nextRoom);
            look();
        }
    }
    
    /**
     * Print out the long description of the current room.
     */
    private void look()
    {
        System.out.println(player.getCurrentRoom().getLongDescription());
        System.out.println(player.items());
    }
    
    /**
     * Print out information about all of the items in the player's inventory.
     */
    private void items()
    {
        System.out.println(player.items());
    }
    
    /**
     * Take the item mentioned in the command if it is an item in the room.
     */
    private void take(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Pick up what?");
            return;
        }
        
        String itemToTake = command.getSecondWord();
        
        player.pickUpItem(itemToTake);
    }
    
    /**
     * Drop the item named in the command in the current room,
     * if the item named is the name of an item in the player's inventory
     */
    private void drop(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Drop what?");
            return;
        }
        
        String itemToDrop = command.getSecondWord();
        
        player.dropItem(itemToDrop);
    }
    
    /**
     * Go back to the previous room.
     */
    private void back()
    {
        if(player.back())
        {
            look();
        }
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
