import processing.core.PApplet;

public class Sketch extends PApplet {
  // declaring variables

  // variables for snow
	float[] circleY = new float[15];
  float[] circleX = new float[15];
  float circleWidth = 50;
  float circleHeight = 50;
  int snowSpeed = 3;
  boolean[] blnSnowHideStatus = new boolean[70];
  boolean clickOnSnow = false;
    
  // blue circle variables
  boolean blueCircleUp;
  boolean blueCircleLeft;
  boolean blueCircleDown;
  boolean blueCircleRight;
  float blueCircleX = 0;
  float blueCircleY = 380;
  float blueCircleWidth = circleWidth - 10;
  float blueCircleHeight = circleHeight - 10;
  int intBlueCircleLives = 3;

  // game variable
  int intGameOver = 0;
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(400, 400);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    for(int i = 0; i < circleY.length; i++){
      circleY[i] = random(height);
      circleX[i] = random(40, 400);
      blnSnowHideStatus[i] = true;
      }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    // if out of lives
    if(intGameOver != 0){
      background(255);
      fill(0);
      text("GAME OVER", 160, 200);
    }
    // when game starts  
    else if(intGameOver == 0){
      background(50);
      blueCircle();
      snowFall();
      }
    
  }
  
  // define other methods down here.
  /**
  * drawing the snowfall and adding level 3 and level 4 requirements
  */
  public void snowFall(){
    // snow fall
    for (int i = 0; i < circleY.length; i++) {
      if(blnSnowHideStatus[i]){
        fill(255);
        ellipse(circleX[i], circleY[i], circleWidth, circleHeight);
        }
      
      circleY[i] += snowSpeed;
      
      if (circleY[i] > height) {
        circleY[i] = 0;
        }
    
    // Collision between blue circle and snowballs
    if(dist(blueCircleX, blueCircleY, circleX[i], circleY[i]) <= 30 && (blnSnowHideStatus[i])){
      intBlueCircleLives -= 1;
      blnSnowHideStatus[i] = false;
      }
    // Mouse interplay with snowballs
    if(clickOnSnow && dist(mouseX, mouseY, circleX[i], circleY[i]) <= 15){
      blnSnowHideStatus[i] = false;
      }
    }
    
  }
  /**
  * drawing lives and allowing blue circle to move
  */
  public void blueCircle(){
    // drawing blue circle and lives
    for (int i = 1; i <= intBlueCircleLives; i++){
      fill(255, 0, 0);
      // lives
      rect ((i + 8) * 30, 15, 30, 30);
      }
    fill(0, 0, 255);
    ellipse(blueCircleX, blueCircleY, blueCircleWidth, blueCircleHeight);
  
    if(intBlueCircleLives == 0){
      intGameOver = 3;
    }
    
    // moving blue circle
    if (blueCircleUp){
      blueCircleY -= 3;
    }
    if (blueCircleDown){
      blueCircleY += 3;
    }
    if (blueCircleLeft){   
      blueCircleX -= 3;
    }
    if (blueCircleRight){
      blueCircleX += 3;
    }
  }
  
  /**
  * allowing snow to be pressed
  */
  public void mousePressed(){
    clickOnSnow = true;
  }
  
  /**
  * allowing snow to be released
  */
  public void mouseReleased(){
    clickOnSnow = false;
  }
   /**
  * moving snow slower when bottom arrow key is pressed and faster when upper arrow key is pressed
  */
  public void keyPressed() {
    // changing speed of snow
    if (keyCode == DOWN) {
      snowSpeed = 1;
    }
    if (keyCode == UP) {
      snowSpeed = 5;
    }
    // moving blue circle
    if(key == 'a'){
      blueCircleLeft = true;
      }
    if(key == 's'){
      blueCircleDown = true;
      }
    if(key == 'd'){
      blueCircleRight = true;
      }
    if(key == 'w'){
      blueCircleUp = true;
      }
  }
  /*
  * snow speed back to normal and stopping movement of blue circle
  */
  public void keyReleased() {
    snowSpeed = 3;
    if (key == 'a'){
      blueCircleLeft = false;
      }
    if (key == 's'){
      blueCircleDown = false;
      }
    if (key == 'd'){
      blueCircleRight = false;
      }
    if (key == 'w'){
      blueCircleUp = false;
    }
  }
  
}
