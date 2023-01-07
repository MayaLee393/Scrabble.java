import java.text.DecimalFormat;
import java.util.ArrayList;

public class Player {

    String name;
    int score;
    ArrayList<Integer> wordPoints = new ArrayList<>();
    DecimalFormat out = new DecimalFormat("00");

    public Player(String name, int score)
    {
        this.name = name;
        this.score = score;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void addScore(int point)
    {
        score=score+point;
    }

    public String getName()
    {
        return name;
    }

    public int getScore()
    {
        return score;
    }
    public void addWordPoints(int p)
    {
        wordPoints.add(p);
    }
    public void displayWordPoints()
    {
        for(int p : wordPoints)
        {
            System.out.print(out.format(p));
        }
        System.out.println();
    }
}
