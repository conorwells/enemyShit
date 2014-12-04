package com.example.enemypractice;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

	Timer myTimer;				// create timer
	RelativeLayout rl;          //create layout to add images to
	ImageView img;  			//declare imageView
	ImageView frank;
	Button b;					//declare button
	int xPos = 1150;            //initialize x position for enemies
	int yPos = 0;				//declare variable that will be the lane
	int flag = 0;				//flag will decide what lane the enemies end up in
	int randInt;				//rand integer to set lanes
	int enemyIndex = 0;			//set up index of arrays 
	int killedEnemy = -1;
	int[] translation;			//array of the xvalues for enemies so that they can get translated left
	ImageView[] cats;			//array of images of enemies, this makes sure that the images are moving on screen
	Enemy dummyEnemy;			//object
	Enemy[] enemyArray;			//array of all the enemies
	Bullet[] bulletArray;
	ImageView[] bullets;
	int[] bulletPosition;
	int bulletIndex = 0;
	int pos = 0;
	int x = 100;
	int y = 300;
	int xBull;
	int yBull;
	Hero hero;
	


	public void message(String message){
		Context context = getApplicationContext();
		CharSequence text = message;
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//create a timer
		myTimer = new Timer(true);
		rl = (RelativeLayout) findViewById(R.id.rl);
		//myTimer.scheduleAtFixedRate(new MyTimerTask(), 5000, 1000);
		//declare all the arrays with 100 elements
		enemyArray = new Enemy[100];
		translation = new int[100];
		cats = new ImageView[100];
		
		bulletArray = new Bullet[100];
		bulletPosition = new int[100];
		bullets = new ImageView[100];
		
		frank = new ImageView (MainActivity.this);
		System.out.println("Created frank");
		
		//create hero
		x = 50;
		y = 300;
		hero = new Hero(x,y,frank,rl);
		System.out.println("Created Hero");
		
		//initializes our translation array with 0's
		for(int i = 0; i < 100; i++){
			translation[i]=0;
		}
		//set up our layout and buttons
		Button start = (Button)findViewById(R.id.button2);
		Button stop = (Button)findViewById(R.id.button3);
		Button Up = (Button)findViewById(R.id.button4);
		Button Down = (Button)findViewById(R.id.button5);
		Button shoot = (Button)findViewById(R.id.button6);
		//final Enemy enemy1 = new Enemy(300,img, rl);
		//enemyArray[0] = enemy1;
		//message("buttons made");

		//create a new enemy
		


		start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) { 		
				myTimer.scheduleAtFixedRate(new MyTimerTask(), 500, 750);
			}
		});
		
		
		start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) { 		
				myTimer.scheduleAtFixedRate(new MyTimerTask(), 500, 750);
			}
		});
		
		
		
		stop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) { 		
				myTimer.cancel();
			}
		});
		
		
		Up.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) { 	
				pos += 100;
				hero.moveVertical(pos,frank);
			}
		});
		
		
		Down.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) { 
				System.out.println("Created Hero");
				pos -=100;
				hero.moveVertical(pos,frank);
			}
		});
		
		shoot.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) { 	
				message("bullet shot");
				//creates bullets
				//set the image for bullet
				img = new ImageView (MainActivity.this);
				//Set set x, y for bullet as the same as that of the hero
				xBull = 400;
				yBull = 400;		
							
				//create bullet, update all the arrays and index
				Bullet bullet = new Bullet(xBull,yBull,img,rl);
				bullets[bulletIndex] = img;						//sets image for the enemy
				//message("enemy created" );
				bulletArray[bulletIndex] = bullet;
				bulletIndex+=1;
			
				    			
			}
		});
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private class MyTimerTask extends TimerTask {

		int count =0;
		@Override
		public void run() {
			// This calls the timer on special "timer" thread
			//goes through all the enemies, advances them left across the screen
			runOnUiThread(new Runnable() {         //This tells the computer that when a timer event happens, update the user interface thread
				int speed = 75;

				@Override
				public void run() {
					
					// TODO Auto-generated method stub
					for(int i = 0; i < enemyIndex; i++){
						if(hero.getY() == enemyArray[i].getY()){
							System.out.print("hit!");
							translation[i] += 10000;
							enemyArray[i].moveLeft(translation[i], cats[i]);
						}
						
						translation[i] += speed;
						enemyArray[i].moveLeft(translation[i], cats[i]);
					}
					
					for(int i = 0; i < bulletIndex; i++){
					//	if(bulletArray[i].getY() == enemyArray[i].getY()){
					//		System.out.print("hit!");
					//		translation[i] += 10000;
					//		enemyArray[i].moveLeft(translation[i], cats[i]);
					//	}
						
						bulletPosition[i] += speed;
						bulletArray[i].moveRight(bulletPosition[i], bullets[i]);
					}
					
					
					//set the image for enemy
					img = new ImageView (MainActivity.this);
					//get the random number for the lane, initialize our xPos for the enemies.
					Random rand = new Random();
					randInt = rand.nextInt();
					flag = Math.abs(randInt%4);
					//message("flag is set: " + flag);
					xPos = 1150;

					//depending on the random number generated, assigns the correct lane
					if (flag==0)
					{
						yPos=200;					
					}
					else
						if(flag==1)
						{
							yPos=300;
						}
						else
							if(flag==2)
							{
								yPos=400;
							}
							
								
					//create enemy, update all the arrays and index
					Enemy enemy = new Enemy(xPos,yPos,img,rl);
					cats[enemyIndex] = img;						//sets image for the enemy
					//message("enemy created" );
					enemyArray[enemyIndex] = enemy;
					enemyIndex+=1;
					killedEnemy++;
					
					System.out.print("Frank:" + hero.getY());
				}
				
			// TODO Auto-generated method stub

		});

		}
	
	}
	
}





