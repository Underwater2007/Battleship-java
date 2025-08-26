# Battleship (Java, Console)

A simple **console-based Battleship game** written in Java. Built to practice arrays, loops, user input, and basic game logic. The player places ships and takes turns against the computer. Hits and misses are tracked on separate boards.

## Features
- 10×10 boards for player and computer
- Ship lengths: **{2, 3, 3, 4, 5}**
- Manual player ship placement (row/column + rotation)
- Turn-based play vs. computer
- **Hit = shoot again** rule
- Separate **hit/miss** tracking boards
- Simple colored terminal output (if your terminal supports ANSI colors)

## How to Play
1. You will place your 5 ships by entering:
   - **Rotation** (horizontal/vertical)
   - **Starting coordinate** (row and column)
2. Game alternates turns between **you** and the **computer**.
3. If a shot **hits**, that side gets another shot.
4. Sink all opponent ships first to **win**.

## Run Locally

### Prerequisites
- Java 8+ (JDK)
- A terminal that supports ANSI colors (Windows Terminal, VS Code terminal, macOS Terminal, most Linux terminals)

### Compile & Run
From the project root:
```bash
javac -d out src/*.java
java -cp out Battleship

run:
Welcome To Battleship
Both the player and computer have 5 ships of different length
ShipLengths {2,3,3,4,5}
The player will have to place ships manually entering rotation and location
If player or comptuer hit the opponents ship they go again
If player hits the computer there will be a 7 displayed on the hit miss board and it miss there will be a 6
same goes for the comptuer but on the computer hit miss board
If player or computer distroys one of the opponents ships they will get a message of Ship Snak
If either the player or the comptuer sink all of the opponets ships they win

Computer Board
 A B C D E F G H I J
0 0 0 0 0 0 0 0 0 0 2 
1 0 0 0 0 0 0 0 0 0 2 
2 0 0 0 0 0 0 0 0 0 2 
3 3 0 0 0 0 0 0 0 0 0 
4 3 0 0 0 0 0 0 0 0 0 
5 3 0 0 5 5 5 5 5 0 0 
6 0 0 0 0 0 0 0 0 0 0 
7 0 0 0 1 1 0 0 0 0 0 
8 0 0 0 0 0 0 0 0 0 0 
9 0 0 0 4 4 4 4 0 0 0 

Where do you want to place ship 1 with length: 2
Rotaion:(0=Horizontal 1=Vertical)1
Enter Coordinates "row col" between(0-9): 1 1
You entered (1,1)
Where do you want to place ship 2 with length: 3
Rotaion:(0=Horizontal 1=Vertical)1 
Enter Coordinates "row col" between(0-9): 2 2
You entered (2,2)
Where do you want to place ship 3 with length: 3
Rotaion:(0=Horizontal 1=Vertical)1
Enter Coordinates "row col" between(0-9): 3 3
You entered (3,3)
Where do you want to place ship 4 with length: 4
Rotaion:(0=Horizontal 1=Vertical)1
Enter Coordinates "row col" between(0-9): 4 4
You entered (4,4)
Where do you want to place ship 5 with length: 5
Rotaion:(0=Horizontal 1=Vertical)1
Enter Coordinates "row col" between(0-9): 5 5
You entered (5,5)
Player Board
 A B C D E F G H I J
0 0 0 0 0 0 0 0 0 0 0 
1 0 1 0 0 0 0 0 0 0 0 
2 0 1 2 0 0 0 0 0 0 0 
3 0 0 2 3 0 0 0 0 0 0 
4 0 0 2 3 4 0 0 0 0 0 
5 0 0 0 3 4 5 0 0 0 0 
6 0 0 0 0 4 5 0 0 0 0 
7 0 0 0 0 4 5 0 0 0 0 
8 0 0 0 0 0 5 0 0 0 0 
9 0 0 0 0 0 5 0 0 0 0 
Enter Shot Coordinates(0-9) "row col": 0 1
Player Missed
Player Hit Miss Board
 A B C D E F G H I J
0 0 6 0 0 0 0 0 0 0 0 
1 0 0 0 0 0 0 0 0 0 0 
2 0 0 0 0 0 0 0 0 0 0 
3 0 0 0 0 0 0 0 0 0 0 
4 0 0 0 0 0 0 0 0 0 0 
5 0 0 0 0 0 0 0 0 0 0 
6 0 0 0 0 0 0 0 0 0 0 
7 0 0 0 0 0 0 0 0 0 0 
8 0 0 0 0 0 0 0 0 0 0 
9 0 0 0 0 0 0 0 0 0 0 
Computer shot location: (1,2)
Computer Missed
Compcount 0
Computer Hit Miss Board
 A B C D E F G H I J
0 0 0 0 0 0 0 0 0 0 0 
1 0 0 6 0 0 0 0 0 0 0 
2 0 0 0 0 0 0 0 0 0 0 
3 0 0 0 0 0 0 0 0 0 0 
4 0 0 0 0 0 0 0 0 0 0 
5 0 0 0 0 0 0 0 0 0 0 
6 0 0 0 0 0 0 0 0 0 0 
7 0 0 0 0 0 0 0 0 0 0 
8 0 0 0 0 0 0 0 0 0 0 
9 0 0 0 0 0 0 0 0 0 0 
Enter Shot Coordinates(0-9) "row col":

battleship-java/
  ├─ src/
  │   ├─ Battleship.java
  │   ├─ Board.java
  │   └─ Ship.java
  ├─ README.md
  └─ .gitignore


