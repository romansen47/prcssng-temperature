package temperature;
import java.util.Random;

public interface IBall {
	
	 default public Ball[] Collision(Ball[] Balls,int LevelOfCorrectness) {
		  if (Balls.length<2){
			  return Balls;
		  }
		  if (Balls.length==2){
		     Balls = Interaction(Balls[0],Balls[1]);
		  }
		  else {	     
		     Ball tmp1 = Balls[Balls.length-1];
		     Ball[] TmpBalls1 = new Ball[Balls.length-1];
		     for (int i=0; i<Balls.length-1;i++){
		        TmpBalls1[i]=Balls[i]; 
		     }
		     for (int i=0;i<TmpBalls1.length;i++){
		        Ball[] Interaction = Interaction(TmpBalls1[i],tmp1);
		        TmpBalls1[i]=Interaction[0];
		        tmp1 = Interaction[1];
		     }
		     TmpBalls1=Collision(TmpBalls1,LevelOfCorrectness-1);
		     if (LevelOfCorrectness>0) {
		       Ball tmp2 = Balls[0];
		       Ball[] TmpBalls2 = new Ball[Balls.length-1];
		       for (int i=0; i<Balls.length-1;i++){
		          TmpBalls2[i]=Balls[i+1]; 
		       }
		       for (int i=0;i<TmpBalls2.length;i++){
		          Ball[] Interaction = Interaction(TmpBalls2[i],tmp2);
		          TmpBalls2[i]=Interaction[0];
		          tmp2 = Interaction[1];
		       }
		       TmpBalls2=Collision(TmpBalls2,LevelOfCorrectness-1);
		     
		       Balls[0].setPosition(ScalarMultiplication(0.5,AdditionOfVectors(TmpBalls1[0].getPosition(),tmp2.getPosition())));
		       Balls[Balls.length-1].setPosition(ScalarMultiplication(0.5,AdditionOfVectors(TmpBalls2[TmpBalls2.length-1].getPosition(),tmp1.getPosition())));
		       for (int i=1;i<Balls.length-1;i++){
		       Balls[i].setPosition(ScalarMultiplication(0.5,AdditionOfVectors(TmpBalls1[i].getPosition(),TmpBalls2[i-1].getPosition())));  
		       }
		     }
		     else {
		       for (int i=0; i<Balls.length-2;i++){
		         Balls[i]=TmpBalls1[i];
		         Balls[Balls.length-1]=tmp1;
		       }
		     }
		  }
		  return Balls;
	 }
	 default public Ball[] Interaction(Ball A,Ball B){
		   Ball ATmp = A.Prediction();
		   Ball BTmp = B.Prediction();
		   double[] Tmp=AdditionOfVectors(ATmp.getPosition(),ReversalOfVector(BTmp.getPosition()));
		   if (Abs(Tmp[0])<=A.getRadius()+B.getRadius() && Abs(Tmp[1])<=A.getRadius()+B.getRadius()){
		     double Condition = MagnitudeOfVector(AdditionOfVectors(AdditionOfVectors(ATmp.getPosition(),ATmp.getVelocity()),AdditionOfVectors(ReversalOfVector(BTmp.getPosition()),ReversalOfVector(BTmp.getVelocity()))));  
		     if ( Condition <= A.getRadius()+B.getRadius() ){
		      double[] connection,PointOfCollision,projectedVelocityA,projectedVelocityB = new double[2];
		      connection = AdditionOfVectors(B.getPosition(),ReversalOfVector(A.getPosition()));
		      PointOfCollision = AdditionOfVectors(A.getPosition(),ScalarMultiplication(A.getRadius(),UnitVector(connection)));
		      A.setPosition(ScalarMultiplication(0.5,AdditionOfVectors(A.getPosition(),AdditionOfVectors(PointOfCollision,ScalarMultiplication(-A.getRadius()-1, UnitVector(connection))))));
		      B.setPosition(ScalarMultiplication(0.5,AdditionOfVectors(B.getPosition(),AdditionOfVectors(PointOfCollision,ScalarMultiplication( B.getRadius()+1, UnitVector(connection))))));
		      projectedVelocityA = Projection(A.getVelocity(),UnitVector(connection));
		      projectedVelocityB = Projection(B.getVelocity(),UnitVector(connection));
		      //double[] TmpVelA= new double[2];
		      A.setVelocity(AdditionOfVectors(A.getVelocity(),ScalarMultiplication(2*B.getMass()/(A.getMass()+B.getMass()),AdditionOfVectors(projectedVelocityB,ReversalOfVector(projectedVelocityA)))));
		      B.setVelocity(AdditionOfVectors(B.getVelocity(),ScalarMultiplication(2*A.getMass()/(A.getMass()+B.getMass()),AdditionOfVectors(projectedVelocityA,ReversalOfVector(projectedVelocityB)))));
		     }
		    }
		    Ball[] BALLS = new Ball[2];
		    BALLS[0]=A;
		    BALLS[1]=B;
		    return  BALLS;
	 }
	 default public double Abs (double Scalar) {if (Scalar>=0) return Scalar; else return -Scalar;}
	 default public double SignumFunction (double Int) { if (Int == 0) return 0; else { if (Int < 0) return -1; else return 1;}}
	 default public double ScalarProduct (double[] VEC1, double[] VEC2){ double skalprod = VEC1[0]*VEC2[0]+ VEC1[1]*VEC2[1]; return skalprod;}
	 default public double MagnitudeOfVector(double[] VEC){return SQRT(ScalarProduct(VEC,VEC));} /* Usage of native Math.sqrt-function weirdly lacks precision */
	 default public double[] ScalarMultiplication (double skalar, double[] VECTOR)
	 {
		 double[] skalarmult = new double[2]; 
		 skalarmult[0] = skalar*VECTOR[0];
		 skalarmult[1] = skalar*VECTOR[1];
		 return skalarmult;
	 }
	 default public double[] UnitVector (double[] VEC){return ScalarMultiplication(1/MagnitudeOfVector(VEC),VEC);}
	 default public double[] AdditionOfVectors (double[] VEC1, double[] VEC2)
	 {
		 double[] addarrays = new double[2]; 
		 addarrays[0] = VEC1[0]+VEC2[0];
		 addarrays[1] = VEC1[1]+VEC2[1];
		 return addarrays;
	 }
	 default public double SQRT(double Square)
	 {
		  return(Math.sqrt(Square));
		  /*double tmp=Square/2;
		  while (Abs(tmp*tmp-Square)>Config.SquareRootCorrectness){
		   tmp=0.5*(tmp+Square/tmp); 
		  }
		  return tmp;*/
	 }
	 default public double[] ReversalOfVector (double[] VEC){return ScalarMultiplication(-1,VEC);}
	 default public double[] Projection(double[] X, double [] Y){return ScalarMultiplication(ScalarProduct(X,Y),ScalarMultiplication(1/MagnitudeOfVector(Y),Y));}
	 default public double[] ProjectionComplement(double[] X, double[] Y){return AdditionOfVectors(X,ReversalOfVector(Projection(X,Y)));}
	 default public void MoveBalls(Ball[] Balls){
		  for (int i=0;i<Balls.length;i++) {
		    Balls[i].move();
		  }
	 }
	 default public Ball[] createNewBalls(int Amount) {
		 Random random = new Random();
		 Ball[] Balls = new Ball[Amount]; { 
		 for (int i=0; i<Amount; i++){
			 double[] Velocity = new double[2];
			 { 
		      Velocity[0] = random.nextInt()%2;
		      Velocity[1] = 0;//random.nextInt()%0;
			 }
			 int[] Color = new int[3];
			 int Radius = Config.MinRad+Math.abs(random.nextInt()%(Config.MaxRad-Config.MinRad));//9/4*displayWidth/(config.AmountOfBalls)+i/10;// (int)(random(5,10));
			 Color[0]  = 50+Math.abs(random.nextInt()%150);//(int) random(200)%256;
			 Color[1]  = 50+Math.abs(random.nextInt()%150);//(int) (random(200)+i)%256;
			 Color[2]  = 50+Math.abs(random.nextInt()%150);//(int) (random(200)+2*i)%256;
			 double[] Position= new double[2];
			 {
		      Position[0] = Radius+Math.abs(random.nextInt()%(Config.getRightWall()-Config.MaxRad));
		      Position[1] = Radius+(random.nextInt()%(Config.getBottom()-2*Radius));
			 };
			 Balls[i]=new Ball(Position,Velocity,Radius,Color);
		 	 }
		 return(Balls);
		}
	 }
	 default public int[] Color(double spd, double Mass) {
		 double energy = Mass*(spd*spd)/Config.Energy;
		 int[] tmp = new int[3];
		 if (energy < 1) {
			 tmp[0] = (int)(255*(SQRT(1-Math.pow(1-energy, 2))));
			 tmp[1] = (int)(255*SQRT(energy)*SQRT(1-energy));
			 tmp[2] = (int)(255*(1-SQRT(1-Math.pow(1-energy, 2))));
		 }
		 else{
			 tmp[2]=0;
			 tmp[1]=0;
			 tmp[0]=255;
		 }
		 return(tmp);
	 }
}