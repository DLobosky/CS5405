// Programmer: Dalton Lobosky
// Date: 9/6/2016
// File: HW_02

package hw_02;

import java.util.Scanner;

public class PalindromesDemo
{
	public static void main(String[] args)
	{
		System.out.println("\n\nDalton Lobosky\nHW_02\n\n\n");
		
		System.out.println("Please enter your desired word.");
		
		Scanner input = new Scanner(System.in);
		String word = input.next();
		
        char[] word_array = word.toCharArray(); 
        System.out.println(pal_find(word_array));       
    }

    public static boolean pal_find(char[] word)
	{
        boolean pal = false;
        if(word.length%2 == 0){
            for(int i = 0; i < word.length/2-1; i++)
			{
                if(word[i] != word[word.length-i-1])
				{
                    return false;
                }else
				{
                    pal = true;
                }
            }
        }
		
		else
		{
            for(int i = 0; i < (word.length-1)/2-1; i++)
			{
                if(word[i] != word[word.length-i-1])
				{
                    return false;
                }
				else
				{
                    pal = true;
                }
            }
        }
        return pal;
    }
}
