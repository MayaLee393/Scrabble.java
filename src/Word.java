import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Word {

    String word;
    int totalPoints = 0;
    int beforeMult = 0;
    ArrayList<Integer> tilePoints= new ArrayList<>();


    public Word(String word)
    {
        this.word = word;
        calculateBeforeMult();

    }

    public int getBeforeMult() {
        return beforeMult;
    }

    public void calculateBeforeMult() {
        int total = 0;
        for(int k=0; k<word.length(); k++)
        {
            char c = word.charAt(k);
            Tile t = new Tile(c);
            tilePoints.add(t.getValue());
            total+=t.getValue();
        }
        beforeMult = total;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void calculateSpecialTiles(String m) {
            String multipliers[] = m.split(", ");
            for(int k=0; k<multipliers.length; k+=2)
            {
                int index = Integer.parseInt(multipliers[k]);
                String type = multipliers[k+1].trim();
                if(type.equals("DL"))
                {
                    int point = tilePoints.get(index);
                    point= point*2;
                    tilePoints.set(index, point);
                }
                if(type.equals("TL"))
                {
                    int point = tilePoints.get(index);
                    point=point*3;
                    tilePoints.set(index, point);
                }
                if(type.equals("DW"))
                {
                    for(int j=0; j<tilePoints.size(); j++)
                    {
                        int point = tilePoints.get(j);
                        point=point*2;
                        tilePoints.set(j, point);
                    }
                }
                if(type.equals("TW"))
                {
                    for(int j=0; j<tilePoints.size(); j++)
                    {int point = tilePoints.get(j);
                        point=point*3;
                        tilePoints.set(j, point);
                    }
                }
            }

    }

    public void calculateTotal()
    {
        int total = 0;
        for(int p: tilePoints)
        {
            total= total+p;
        }
        totalPoints = total;

    }

    public boolean isWord() throws IOException
    {
        File file = new File("dictionary.txt");
        Scanner scan = new Scanner(file);

        String w = "";
        while(scan.hasNextLine())
        {
            w = scan.nextLine().toUpperCase().trim();
            if(w.equals(word.trim()))
            {
                return true;
            }
        }

        System.out.println("\n"+word+" is not a word");
        return false;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
