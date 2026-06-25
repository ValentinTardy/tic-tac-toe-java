# Simple Tic-Tac-Toe Game with Java Swing, Login, and Statistics

## Student Information
- **Name:** Valentin Gabriel Paul Tardy
- **Student ID:** 5999252044
- **Class:** Q

## Project Description
This project is a simple Tic-Tac-Toe game built using Java Swing GUI.
The player (X) plays against the computer (O) on a 3x3 board.
The application includes a login system connected to a PostgreSQL database,
game statistics tracking, and a Top 5 scorers leaderboard.

## Features
- Login using PostgreSQL database
- Play Tic-Tac-Toe using Java Swing GUI
- Computer AI that blocks the player and tries to win
- Record wins, losses, draws, and score after each game
- Display personal statistics
- Display Top 5 scorers using JTable
- Window size is preserved when navigating between screens

## Score System
| Result | Points |
|--------|--------|
| Win    | +10    |
| Draw   | +3     |
| Lose   | +0     |

## Database
- **Database system:** PostgreSQL
- **Table name:** players
- **Columns:** id, username, password, wins, losses, draws, score

## How to Run

### 1. Create the database
Open DBeaver or any PostgreSQL client and run:
```sql
CREATE DATABASE game_project;
```

### 2. Import the schema
Connect to `game_project` and run the contents of `database/schema.sql`.

### 3. Configure the database connection
Open `src/DatabaseManager.java` and update:
```java
private static final String URL = "jdbc:postgresql://YOUR_IP:5432/game_project";
private static final String USER = "postgres";
private static final String PASSWORD = "your_password";
```

### 4. Add the JDBC driver
Download `postgresql-42.7.4.jar` and place it in the project root folder.

### 5. Compile the project
```bash
javac -cp src:postgresql-42.7.4.jar -d out src/*.java
```

### 6. Run the project
```bash
java -cp out:postgresql-42.7.4.jar Main
```

## Class Explanation

| Class | Responsibility |
|-------|---------------|
| `Main` | Starts the program and opens the Login Window |
| `DatabaseManager` | Handles JDBC connection to PostgreSQL |
| `Player` | Stores player data: id, username, wins, losses, draws, score |
| `PlayerService` | Handles login, statistics update, and Top 5 query |
| `GameLogic` | Handles move validation, winner checking, draw checking, and computer AI |
| `LoginFrame` | Swing window for username and password input |
| `MainMenuFrame` | Swing window for the main menu after login |
| `GameFrame` | Swing window for playing the Tic-Tac-Toe game |
| `StatisticsFrame` | Swing window for displaying personal statistics |
| `TopScorersFrame` | Swing window for displaying Top 5 scorers using JTable |

## Screenshots
_(Add screenshots of the running application here)_

## Default Test Accounts
| Username | Password |
|----------|----------|
| student1 | 12345    |
| student2 | 12345    |
| student3 | 12345    |
| student4 | 12345    |
| student5 | 12345    |

## Video Link
YouTube: https://youtu.be/EoZcLZHc_Jo

## GitHub Link
GitHub: https://github.com/ValentinTardy/tic-tac-toe-java
