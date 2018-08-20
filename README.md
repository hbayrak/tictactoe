# TicTacToe

A tic-tac-toe game with a little twist.
	
* Play field is configurable between 3x3 and 10x10.
* The player symbols are configurable.
* Game starts with two human players and one AI player.
* The player order is generated randomly at the beginning of each game.
* Input and output is on the console.

## Getting Started

Game configuration is made by the resource files. Default resource file name is "game-configuration". 
You can modify it or provide your own resource file name. 

Resource file format	
	
	playfield-size= 3
	players= X,Y,Z

### Installing

You should copy the project on your local machine. After that `cd` into the project location.

Run the below command to create the executable

```
mvn clean package
```

And to run the executable with default configuration

```
java -jar target/tictactoe-0.0.1.jar
```

If you want to use your configuration file "your-config-file-name" put it into resources folder and run command

```
java -jar target/tictactoe-0.0.1.jar your-config-file-name
```


## Running the tests

Run the below command to run tests

	mvn test


## Built With

* [Maven](https://maven.apache.org/)


## Acknowledgments

* General Rules: https://en.wikipedia.org/wiki/Tic-tac-toe
