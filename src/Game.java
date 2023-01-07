import javax.imageio.IIOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.random;

public class Game {

    int whosTurn = 0;
    ArrayList<Player> players = new ArrayList<>();

    int randomVariable;
    int numOfPlayers;

    public Game(int num, int random)
    {
        numOfPlayers = num;
        randomVariable = random;
    }


    public void addPlayer(Player p)
    {
        players.add(p);
    }

    public static int getWordPoints(Scanner input, String word)
    {
        Word w = new Word(word);
        System.out.print("Enter any DW, DL, TW, TL: "); // index, string, index, string
        String m = input.nextLine();

        if (!m.equals(""))
        {
            w.calculateSpecialTiles(m);
        }

        w.calculateTotal();

        return w.getTotalPoints();
    }


    public Player playerTurn()
    {
        int turn = whosTurn;
        return players.get(turn);
    }

    public void skipTurn()
    {
        nextPlayer();
        System.out.println("turn skipped");
    }

    public void nextPlayer()
    {
        if(whosTurn==numOfPlayers-1)
            whosTurn=0;
        else
            whosTurn++;
    }

    public void winner()
    {
        boolean tie = false;
        int max = players.get(0).getScore();
        Player winner = players.get(0);
        for(int k=1; k<numOfPlayers; k++)
        {
            if(players.get(k).getScore()>max)
            {
                max = players.get(k).getScore();
                winner = players.get(k);
            }
            else if(players.get(k).getScore() == max)
            {
                tie = true;
            }
        }
        if(!tie)
            System.out.println(winner.getName()+" won!");
        else
            System.out.println("It's a tie!");

    }



    public void display()
    {
        for(int k=0; k<numOfPlayers; k++)
        {
            System.out.println(players.get(k).getName()+": "+players.get(k).getScore());

        }
        winner();
    }

    public void play(Scanner input) throws IOException
    {
        String word = "temp";
        int count = 1;
        int round = 1;
        while(!word.equals("."))
        {
            System.out.println();
            System.out.println("(Enter . to exit game\t" +
                    "Press enter to skip turn)");
            System.out.println();


            if(count==1)
            {
                System.out.println("Round: "+round);
                System.out.println("==================================================");
            }

            Player p = playerTurn();
            System.out.println(p.getName()+"'s turn...");
            System.out.print("Enter word: ");
            word = input.nextLine();
            Word w = new Word(word);
            if(word.equals("."))
                break;
            else if(word.equals(""))
            {
                p.addWordPoints(0);
                p.addScore(0);
                skipTurn();
            }
            else
            {
                while(!w.isWord())
                {
                    System.out.println("Press Enter to skip or ");
                    System.out.print("Enter another word: ");
                    word = input.nextLine();
                    w.setWord(word);
                    if(word.equals(""))
                    {
                        p.addWordPoints(0);
                        p.addScore(0);
                        skipTurn();
                        break;
                    }
                }
                int point = getWordPoints(input, word);
                p.addWordPoints(point);
                System.out.println(word+": "+point);
                p.addScore(point);

                if(randomExecute())
                {
                    randomPhrase();
                }
                nextPlayer();
            }
            System.out.println(p.getName()+" has "+p.getScore()+" points so far");
            if(count==numOfPlayers)
            {
                count = 1;
                round++;
            }

            else
                count++;
        }

        display();
    }

    public void seeScoreLog()
    {
        for(Player p: players)
        {
            System.out.println(p.getName()+" scores: ");
            p.displayWordPoints();
        }
    }

    public boolean randomExecute()
    {
        int random = (int) (Math.random()*5+1);

        if(random == randomVariable)
            return true;
        else
            return false;
    }

    public void randomPhrase()
    {
        int random = (int) ((Math.random()*12)+1);

        switch(random)
        {
            case 1:
                System.out.println("\t\t\tLame answer");
                playSound(new File("Lame.wav"));
                break;
            case 2:
                System.out.println("\t\t\tGreat word!");
                playSound(new File("great.wav"));
                break;
            case 3:
                System.out.println("\t\t\tTerrible word");
                playSound(new File("terrible.wav"));
                break;
            case 4:
                System.out.println("\t\t\tWow!");
                playSound(new File("wow.wav"));
                break;
            case 5:
                System.out.println("\t\t\tNice");
                playSound(new File("nice.wav"));
                break;
            case 6:
                System.out.println("\t\t\tWhat..?");
                playSound(new File("what.wav"));
                break;
            case 7:
                System.out.println("\t\t\tInteresting word");
                playSound(new File("interesting.wav"));
                break;
            case 8:
                System.out.println("\t\t\tReally...?");
                playSound(new File("really.wav"));
                break;
            case 9:
                System.out.println("\t\t\tNooooo!");
                playSound(new File("nooo.wav"));
                break;
            case 10:
                System.out.println("\t\t\tI guess it's okay..?");
                playSound(new File("okay.wav"));
                break;
        }


    }


    public void playSound(File Sound)
    {
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();

            Thread.sleep(clip.getMicrosecondLength()/1000);
        }catch(Exception e)
        {

        }
    }

}