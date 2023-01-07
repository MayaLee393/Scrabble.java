
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Scrabble
{

    public static void main(String[] args) throws IOException
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter number of players: ");
        int numOfPlayers = input.nextInt();
        input.nextLine();
        Game scrabble = new Game(numOfPlayers, 3);

        //Number of Players
       System.out.println();
        for(int k=1; k<=numOfPlayers; k++)
        {
            System.out.print("Enter Player "+k+": ");
            String playerName = input.nextLine();
            Player p = new Player(playerName, 0);
            scrabble.addPlayer(p);
            System.out.println();
        }


        //Play
       scrabble.play(input);



    }





}