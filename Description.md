## Eight Puzzle Game

*a simple 8-puzzle game written in javafx*

#### Main.java

Functions:

- `main` : launches the javafx app
- `start`: loads the `sample.fxml` file in the window
- `endWithCongrats`: displays an alert box with congratulations message

#### AlertBox.java

*`display`* function show a window a the given title and message

#### sample.fxml

contains layout xml for Puzzle Pieces and reset button

#### Controller.java

- `elements` : array of 1-8 puzzle pieces

- `points` : a bunch of row and column pairs as  Points

  we shuffle them to place puzzle pieces at first

- `board` : integer 3*3 table that contains piece numbers (0 is empty)

- `emptyCell` : row and column of empty cell

- `winningBoard` : the winning state of the board

- `Point Class` : it is used to contain column and row for every piece and check if they are neighbor

- `initialize` : adds puzzle pieces to elements array & initializes coordinates array

- `reset` : shuffles coordinates array and randomly places pieces based on that

- `isCompleted` : checks if the current state of the board equals the winning state

- `move` : moves the clicked piece based on its place comparing to empty cell

  and changes empty cell coordinates