package com.example.enemypractice;


import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Enemy{

	private int xPos;
	private int yPos;

	public Enemy(){

	}

	//creates an enemy and creates the image of the enemy on the screen
	public Enemy(int xPosi, int lane,ImageView img, RelativeLayout rl){
		xPos = xPosi;
		yPos = lane;
		img.setImageResource(R.drawable.ic_launcher);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams( 
				(int) LayoutParams.WRAP_CONTENT, (int) LayoutParams.WRAP_CONTENT);
		params.setMargins(xPos,yPos,200,200);
		img.setLayoutParams(params);
		rl.addView(img);

		// TODO Auto-generated constructor stub
	}
	//same as a "setY" method
	public void setLane(int lane){
		yPos = lane;
	}

	public int getX(){
		return xPos;
	}

	//Moves our enemy across the screen
	public void moveLeft(int speed, ImageView img){
		xPos -= speed;
		img.setTranslationX(-speed);
	}
	
	public int getY(){
		return yPos;
	}

}
