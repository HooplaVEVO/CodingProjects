/*
Name: MYLES KAUFMAN
Description: This program is a rudimentary game of Tic-Tac-Toe against a computer.
The computer is very basic, and will select randomly until it is in danger of losing.
The computer will also detect when it can win, and will make the selection to win.
*/
int playerScore=0;
int PCScore=0;
int playerPick=-1;
int PCPick=0;
int[] board = new int[9];

void setup(){
  background(255);
  size(600,400);
  rectMode(CENTER);
  resetArray();
}//end of setup

void draw(){
  drawScoreTiles();
  drawGameBoard();
}//end of draw

void mouseClicked(){
  clickDetection();
}//end of mouseClicked

void tieDetection(){
  if (board[0]!=0&&board[1]!=0&&board[2]!=0&&board[3]!=0&&board[4]!=0&&board[5]!=0&&board[6]!=0&&board[7]!=0&&board[8]!=0){
    resetArray();
    println("Tie");
  }//end of tieDetection
}
void winDetection(){
  //Player win possibility
  if(board[0]==1&&board[1]==1&&board[2]==1){
    playerWin();
  }//end of if
  if(board[3]==1&&board[4]==1&&board[5]==1){
    playerWin();
  }//end of if
  if(board[6]==1&&board[7]==1&&board[8]==1){
    playerWin();
  }//end of if
  if(board[0]==1&&board[3]==1&&board[6]==1){
    playerWin();
  }//end of if
  if(board[1]==1&&board[4]==1&&board[7]==1){
    playerWin();
  }//end
  if(board[2]==1&&board[5]==1&&board[8]==1){
    playerWin();
  }//end of if
  if(board[0]==1&&board[4]==1&&board[8]==1){
    playerWin();
  }//end of if
  if(board[2]==1&&board[4]==1&&board[6]==1){
    playerWin();
  }//end of if
  //PC win possibilities
  if(board[0]==2&&board[1]==2&&board[2]==2){
    pcWin();
  }//end of if
  if(board[3]==2&&board[4]==2&&board[5]==2){
    pcWin();
  }//end of if
  if(board[6]==2&&board[7]==2&&board[8]==2){
    pcWin();
  }//end of if
  if(board[0]==2&&board[3]==2&&board[6]==2){
    pcWin();
  }//end of if
  if(board[1]==2&&board[4]==2&&board[7]==2){
    pcWin();
  }//end
  if(board[2]==2&&board[5]==2&&board[8]==2){
    pcWin();
  }//end of if
  if(board[0]==2&&board[4]==2&&board[8]==2){
    pcWin();
  }//end of if
  if(board[2]==2&&board[4]==2&&board[6]==2){
    pcWin();
  }//end of if
}//end of winDetection

void playerWin(){
  playerScore+=1;
  resetArray();
  println("Player Wins");
}//end of playerwin

void pcWin(){
  PCScore+=1;
  resetArray();
  println("PC Wins");
}//end of pcwin

void resetArray(){
    for (int n=0;n<9;n++){
  board[n]=0;
  }//end of for
  background(255);
}//end of array initialize

void updatePlayer(){
  if (playerPick!=-1){
    board[playerPick]=1;
    drawPlayerSquare(playerPick);
    playerPick=-1;
    winDetection();
    tieDetection();
    PCSelect();
  }//end of if
}//end of updatePlayer

void updatePCScore(){
    board[PCPick]=2;
    drawPCSquare(PCPick);
    winDetection();
    tieDetection();
}//end of updatePC


void drawScoreTiles(){
  fill(200);
  textSize(25);
  rect(100,100,190,190);
  rect(100,300,190,190);
  fill(25);
  text(playerScore,100,130);
  text("PLAYER SCORE",10,75);
  textSize(35);
  text("PC SCORE",10,275);
  text(PCScore,100,330);
}//end of scoreTiles

void drawGameBoard(){
  line(333,0,333,400);
  line(466,0,466,400);
  line(200,133,600,133);
  line(200,266,600,266);
}//end of drawGameBoard

void drawX(float x,float y){
  fill(180,0,0);
  quad(x-30,y-25,x-25,y-30,x+30,y+25,x+25,y+30);
  quad(x+30,y-25,x+25,y-30,x-30,y+25,x-25,y+30);
}//end of drawX

void drawPlayerSquare(int pp){
  if(pp==0){
    drawX(266.5,66.5);
  }//end of if
  if(pp==1){
    drawX(400,66.5);
  }//end of if
  if(pp==2){
    drawX(533,66.5);
  }//end of if
  if(pp==3){
    drawX(266.5,200);
  }//end of if
  if(pp==4){
    drawX(400,200);
  }//end of if
  if(pp==5){
    drawX(533,200);
  }//end of if
  if(pp==6){
    drawX(266.5,333);
  }//end of if
  if(pp==7){
    drawX(400,333);
  }//end of if
  if(pp==8){
    drawX(533,333);
  }//end of if
}//end of drawX

void drawO(float x,float y){
  fill(0,0,175);
  ellipse(x,y,60,60);
  fill(255);
  ellipse(x,y,40,40);
}//end of drawO

void drawPCSquare(int pcp){
  fill(0,0,150);
  if(pcp==0){
    drawO(266.5,66.5);
  }//end of if
  if(pcp==1){
    drawO(400,66.5);
  }//end of if
  if(pcp==2){
    drawO(533,66.5);
  }//end of if
  if(pcp==3){
    drawO(266.5,200);
  }//end of if
  if(pcp==4){
    drawO(400,200);
  }//end of if
  if(pcp==5){
    drawO(533,200);
  }//end of if
  if(pcp==6){
    drawO(266.5,333);
  }//end of if
  if(pcp==7){
    drawO(400,333);
  }//end of if
  if(pcp==8){
    drawO(533,333);
  }//end of if
}//end of PCSquare

void clickDetection(){
  if (mouseX>200){
    if ((mouseX<333&&mouseY<133)&&board[0]==0){
      playerPick=0;
    }//end of if
    if ((mouseX>333&&mouseX<466&&mouseY<133)&&board[1]==0){
      playerPick=1;
    }//end of if
    if ((mouseX>466&&mouseY<133)&&board[2]==0){
      playerPick=2;
    }//end of if
    if ((mouseX<333&&mouseY<266&&mouseY>133)&&board[3]==0){
      playerPick=3;
    }//end of if
    if ((mouseX>333&&mouseX<466&&mouseY<266&&mouseY>133)&&board[4]==0){
      playerPick=4;
    }//end of if
    if ((mouseX>466&&mouseY<266&&mouseY>133)&&board[5]==0){
      playerPick=5;
    }//end of if
    if ((mouseX<333&&mouseY>266)&&board[6]==0){
      playerPick=6;
    }//end of if
    if ((mouseX>333&&mouseX<466&&mouseY>266)&&board[7]==0){
      playerPick=7;
    }//end of if
    if ((mouseX>466&&mouseY>266)&&board[8]==0){
      playerPick=8;
    }//end of if
    updatePlayer();
  }//end of if
}//end of clickDetection

void PCSelect(){
  //Offensive Plays
  if (((board[0]==2&&board[8]==2)||(board[2]==2&&board[6]==2))&&board[4]==0){
    PCPick=4;
  }//end of if
  else if (((board[3]==2&&board[5]==2)||(board[1]==2&&board[7]==2))&&board[4]==0){
    PCPick=4;
  }//end of if
  else if (((board[3]==2&&board[6]==2)||(board[1]==2&&board[2]==2)||(board[4]==2&&board[8]==2))&&board[0]==0){
    PCPick=0;
  }//end of if
  else if (((board[0]==2&&board[2]==2)||(board[4]==2&&board[7]==2))&&board[1]==0){
    PCPick=1;
  }//end of if
  else if (((board[0]==2&&board[1]==2)||(board[8]==2&&board[5]==2)||(board[4]==2&&board[6]==2))&&board[2]==0){
    PCPick=2;
  }//end of if
  else if (((board[0]==2&&board[6]==2)||(board[4]==2&&board[5]==2))&&board[3]==0){
    PCPick=3;
  }//end of if
  else if (((board[2]==2&&board[8]==2)||(board[3]==2&&board[4]==2))&&board[5]==0){
    PCPick=5;
  }//end of if
  else if (((board[0]==2&&board[3]==2)||(board[7]==2&&board[8]==2)||(board[4]==2&&board[2]==2))&&board[6]==0){
    PCPick=6;
  }//end of if
  else if (((board[1]==2&&board[4]==2)||(board[6]==2&&board[8]==2))&&board[7]==0){
    PCPick=7;
  }//end of if
  else if (((board[2]==2&&board[5]==2)||(board[6]==2&&board[7]==2)||(board[0]==2&&board[4]==2))&&board[8]==0){
    PCPick=8;
  }//end of if
  
  //Defensive Plays
  if (((board[0]==1&&board[8]==1)||(board[2]==1&&board[6]==1))&&board[4]==0){
    PCPick=4;
  }//end of if
  else if (((board[3]==1&&board[5]==1)||(board[1]==1&&board[7]==1))&&board[4]==0){
    PCPick=4;
  }//end of if
  else if (((board[3]==1&&board[6]==1)||(board[1]==1&&board[2]==1)||(board[4]==1&&board[8]==1))&&board[0]==0){
    PCPick=0;
  }//end of if
  else if (((board[0]==1&&board[2]==1)||(board[4]==1&&board[7]==1))&&board[1]==0){
    PCPick=1;
  }//end of if
  else if (((board[0]==1&&board[1]==1)||(board[8]==1&&board[5]==1)||(board[6]==1&&board[4]==1))&&board[2]==0){
    PCPick=2;
  }//end of if
  else if (((board[0]==1&&board[6]==1)||(board[4]==1&&board[5]==1))&&board[3]==0){
    PCPick=3;
  }//end of if
  else if (((board[2]==1&&board[8]==1)||(board[3]==1&&board[4]==1))&&board[5]==0){
    PCPick=5;
  }//end of if
  else if (((board[0]==1&&board[3]==1)||(board[7]==1&&board[8]==1)||(board[4]==1&&board[6]==1))&&board[6]==0){
    PCPick=6;
  }//end of if
  else if (((board[1]==1&&board[4]==1)||(board[6]==1&&board[8]==1))&&board[7]==0){
    PCPick=7;
  }//end of if
  else if (((board[2]==1&&board[5]==1)||(board[6]==1&&board[7]==1)||(board[0]==1&&board[4]==1))&&board[8]==0){
    PCPick=8;
  }//end of if
  
  else{//random pick if no imminent win/loss detected
    randomPick();
  }//end of else
  updatePCScore();
}//end of PCSelect

void randomPick(){
  PCPick=int(random(0,8));
  if (board[PCPick]!=0){
    randomPick();
  }//end of if
}//end of randomPick
