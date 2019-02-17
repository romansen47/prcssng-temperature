package temperature;

import processingTemplate.Gui;

public class Temperature extends Gui{
	 
	 public static void main(String[] args) {
		 (new Gui()).run("temperature.Temperature");
	 }
	 // Create a set of balls
	 Ball[] Balls;
	 
	 @Override
	 public void settings(){ 
		 fullScreen(P3D);
	 } 
	 
	 @Override
	 public void setup(){
		 frameRate(Config.framerate);
	 }

	 @Override
	 public void draw(){

			// Setting background to black
			background(0);
			
			// If restart condition satisfied create new set of balls
			if (Config.restart==true){
				Config.restart=false;
				Ball[] BallsTmp=Functions.createNewBalls(Config.AmountOfBalls);
				Balls = new Ball[BallsTmp.length];
				for (int j=0;j<Balls.length-1;j++) {
					Balls[j]=BallsTmp[j];
				}
				double[] Pos = new double[2];
				Pos[1]=-100;
				Pos[0]= (int)(Config.getRightWall()/2);
				double[] Vel = new double[2];
				Vel[0]= 0;
				Vel[1]= 0;
				int[] Col = new int[3];
				Col[0]= 255;
				Col[1]= 0;
				Col[2]= 0;
				Balls[Balls.length-1]=new Ball(Pos,Vel,Config.MaxRad*1,Col);
			}
			
			double MaxMass=0;
			for (int j=0;j<Balls.length;j++) {
				if (MaxMass<Balls[j].getMass()) { MaxMass=Balls[j].getMass();}
			}
			
		    for (int i=0;i<Config.speed;i++){
		    	Functions.MoveBalls(Balls);
		    	for (int j=0;j<Balls.length;j++) {
		    		Balls[j].setColor(Functions.Color(Functions.mathOperator.MagnitudeOfVector(Balls[j].getVelocity()),(Balls[j].getMass())/MaxMass));
		    	}
		    	Balls=Functions.Collision(Balls,Config.LevelOfCorrectness);
		    }
		    
		    // Check, if restart condition is satisfied
		    int VelOfAll=0;
		    for (int i=0;i<Balls.length;i++){
		        VelOfAll+=Functions.mathOperator.MagnitudeOfVector(Balls[i].getVelocity());
		    }
		    if (VelOfAll<Config.RestartCondition){
		    	Config.restart=true; 
		    }
		    
			// Draw the balls
		    DrawBalls(Balls);
		    
		    // Manual restart if 'r' is pressed - exit() if another key or mouse is pressed 
		    if ((mousePressed && (mouseButton == LEFT )) || (keyPressed && key=='r')) {
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		    	Config.restart=true;
		    }
		    if ((mousePressed && (mouseButton == RIGHT)) || (keyPressed && (key=='q'))) exit();
	 }
	 
	 // These drawing functions use fill() and will be exported to Functions.java	 
	 public void draw(Ball A) {
		fill(A.getColor()[0],A.getColor()[1],A.getColor()[2]);
		ellipse((float)A.getPosition()[0],(float)A.getPosition()[1],2*A.getRadius(),2*A.getRadius());
	 }
	 public void DrawBalls(Ball[] Bs){
		for (int i=0;i<Bs.length;i++) {
			draw(Bs[i]);
		}
	 }

}