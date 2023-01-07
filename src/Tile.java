public class Tile {

    char letter;
    int value = 0;
    int []points =   { 1 ,  3 , 3 , 2 , 1 , 4 , 2 , 4 , 1 , 8 , 5 , 1 , 3 , 1 , 1 , 3 , 10, 1 , 1 , 1 , 1 , 4 , 4 , 8 , 4 , 10};
    char []letters = {'A', 'B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    public Tile(char letter)
    {
        this.letter = letter;
        for(int k=0; k<letters.length; k++)
        {
            if(letters[k]==letter)
            {
                value = points[k];
            }
        }

    }


    public int getValue()
    {
        return value;
    }

    public char getLetter() {
        return letter;
    }
}
