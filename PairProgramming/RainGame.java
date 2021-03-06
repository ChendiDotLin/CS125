//UIUC CS125 FALL 2013 MP. File: RainGame.java, CS125 Project: PairProgramming, Version: 2013-10-01T10:58:42-0500.686626489
/**
 * @author bfu3,bzhang45 
 */
public class RainGame {

	public static void main(String[] args) {
		// To get points type your netids above (CORRECTLY-Please double check your partner correctly spells your netid correctly!!)
		// Your netid is the unique part of your @illinois email address
		// Do not put your name or your UIN. 
		// REMEMBER TO COMMIT this file...
	
		int x=0, y=0, dx=0, dy=0;
		int level = 1, score = 0, levelSkiped = 0;
		String text = "";
		String distraction = "Distraction";
		int disX=0, disY=0;
		long startTime =System.currentTimeMillis();
		boolean firstGame = true;
		int miss = 0;
		
		while (Zen.isRunning()) {

			if (disY == 0) {
				disY = Zen.getZenHeight()/3;
			}
			if (text.length() == 0) {
				//Reset the score and level
				long elapsed = System.currentTimeMillis() - startTime;
				startTime = System.currentTimeMillis();
				score = score + ((int)(3000/elapsed));
				level = levelSkiped + score/10 +1;
				//Reset the position of Text
				x = 20;
				y = Zen.getZenHeight() / 3;
				//Reset the acceleration of Text
				dx = 0;
				dy = level;
				//Reset the numbers of text
				text = "" + (int) (Math.random() * 999);

				if (firstGame) {
					score = 0;
					firstGame = !firstGame;
				}
			}
			
			Zen.setColor(39, 61, 81);
			Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());

			//Reset the number back to the top if they fall past the bottom of the screen
			if (y>Zen.getZenHeight()) {
				y = Zen.getZenHeight() / 3;
				text = "" + (int) (Math.random() * 999);
				miss++;
			}
			if (disY>Zen.getZenHeight()) {
				disY = Zen.getZenHeight()/3;
			}

			Zen.setFont("Helvetica-60");
			Zen.setColor(250, 228, 83);
			Zen.drawText(text, x, y);
			Zen.drawText(distraction, disX, disY);
			
			Zen.setFont("Helvetica-40");
			Zen.drawText("Level: " + level ,20,50);
			Zen.drawText("Score: " + score , 200,50);
			Zen.setFont("Helvetica-20");
			Zen.drawText("Press S to Skip this level", 400, Zen.getZenHeight()-9);

			x += dx;
			y += dy;
			disX = disX + 2*dx;
			disY = disY + 2*dy;
			
			// Find out what keys the user has been pressing.
			String user = Zen.getEditText();
			// Reset the keyboard input to an empty string
			// So next iteration we will only get the most recently pressed keys.
			Zen.setEditText("");
			
			for(int i=0;i < user.length();i++) {
				char c = user.charAt(i);
				//When user pressed key s we skip the current level
				if (c == 's') {
					levelSkiped++;
					level++;
					dy = level;					
				}
				//Now It's impossible to make the game die with an exception
				if (text.length()>0) {
					if(c == text.charAt(0))
						text = text.substring(1,text.length()); // all except first character
					}
				}
			
			if (miss > 5) {
				level = 0;
				score = 0;
				dx = 0;
				dy = 0;
				Zen.setFont("Helvetica-60");
				Zen.drawText("GAME OVER",200,300);
			}

			//They don't like the flicker so we use flipBuffer
			Zen.flipBuffer();
			Zen.sleep(10);// sleep for 90 milliseconds


		}
	}

}
