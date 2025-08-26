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

<img width="1232" height="642" alt="image" src="https://github.com/user-attachments/assets/8680b14c-c385-45cb-8bec-d7eba6b2f157" />
