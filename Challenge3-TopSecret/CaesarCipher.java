//UIUC CS125 FALL 2013 MP. File: CaesarCipher.java, CS125 Project: Challenge3-TopSecret, Version: 2013-09-21T10:50:52-0500.946838668
/**
 * A program to search for to encrypt and decrypt lines of text. See
 * CaesarCipher.txt
 * Hints: line.charAt( int ) is useful.
 * You'll need loops, and conditions and variables
 * You'll need to read the Test cases to understand how your program should work.
 * Good Programming Hints: "DRY: Don't Repeat Yourself"
 * Try to make your program as short as possible.
 * TODO: add your netid to the line below
 * @author bfu3
 */
public class CaesarCipher {

	public static void main(String[] strings) {
		System.out.println("Please enter the shift value (between -25..-1 and 1..25)");
		int shiftValue = TextIO.getlnInt();
		while (shiftValue>25 || shiftValue<-25 || shiftValue==0)
		{
			if (shiftValue == 999 || shiftValue == -999)
				break;
			System.out.printf("%d is not a valid shift value.\n", shiftValue);
			System.out.println("Please enter the shift value (between -25..-1 and 1..25)");
		    shiftValue = TextIO.getlnInt();
		}

		if (shiftValue == 999 || shiftValue == -999) 
			System.out.printf("Using position shift\n");
		else
			System.out.printf("Using shift value of %d\n", shiftValue);
		

		System.out.println("Please enter the source text (empty line to quit)");
		String sourceText = TextIO.getln();
		while (sourceText.length()>0) {
			System.out.printf("Source   :%s\n", sourceText);
			
			String processedString = "";
			sourceText = sourceText.toUpperCase();


			for (int i=0; i<sourceText.length(); i++) {
				char a = sourceText.charAt(i);


				if (a<91 && a>64) {
					if (shiftValue == 999) {
						int positionShift = i;
						while (positionShift>26)
							positionShift = positionShift -26;
						a = (char)(a+positionShift);
					}
					else if (shiftValue == -999) {
						int positionShift = i;
						while (positionShift>26)
							positionShift = positionShift-26;
						a = (char)(a-positionShift);
					}
					else
						a = (char)(a+shiftValue);

					
					while (a>90)
						a = (char)(a-26);
					while (a<65)
						a = (char)(a+26);
				}
				processedString = processedString + String.valueOf(a);
			}

			System.out.printf("Processed:%s\n", processedString);
			System.out.printf("Please enter the source text (empty line to quit)\n");
			sourceText = TextIO.getln();
			//System.out.printf("Source   :%s\n", sourceText);
		}
		System.out.println("Bye.");
	}
}
