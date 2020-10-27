import java.util.Scanner;

public class TicTacToe {

    //This method takes in the grid, row and col position they want to add the value and the value (x or y)
    //Returns the grid
    public static char[][] move(char[][] grid, int x, int y, char value){
        grid[x][y] = value;
        return grid;
    }

    //Calculates 8 different ways to win
    //returns a int 1->win 0->nothing
    public static int findWinner(char[][] grid){
        if(grid[0][0] == grid[0][1] && grid[0][1] == grid[0][2]){ //First row win
            return 1;
        }else if(grid[1][0] == grid[1][1] && grid[1][1] == grid[1][2]){ //Second row win
            return 1;
        }else if(grid[2][0] == grid[2][1] && grid[2][1] == grid[2][2]){ //thrid row win
            return 1;
        }else if(grid[0][0] == grid[1][0] && grid[1][0] == grid[2][0]){ //first column win
            return 1;
        }else if(grid[0][1] == grid[1][1] && grid[1][1] == grid[2][1]){ //second column win
            return 1;
        }else if(grid[0][2] == grid[1][2] && grid[1][2] == grid[2][2]){ //third column win
            return 1;
        }else if(grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]){ //diagonal (left to right) win
            return 1;
        }else if(grid[2][0] == grid[1][1] && grid[1][1] == grid[0][2]){ //diagonal (right to left) win
            return 1;
        }else{
            return 0; //No win
        }
    }

    //Used to print all the values in the grid for the users to see the updated grid
    //Also used for debugging use
    public static void printGrid(char[][] grid){
        int j = 0;
        for(int i = 0; i < 3; i++){
            System.out.println(grid[i][0] + " | " + grid[i][1] + " | " + grid[i][2]);

            //Used for formatting
            if(i == 0 || i == 1){
                System.out.println("- - - - -");
            }
        }
        System.out.println();
    }

    public static void main(String[] args){
        //TicTacToe uses a 3 by 3 grid
        char[][] grid = {{'1', '2', '3'},{'4', '5', '6'},{'7', '8', '9'}};
        int [] valid = {1,2,3,4,5,6,7,8,9}; //valid moves, once used they are replaced with 0
        char playerValue = 'X';
        int player = 0;
        Scanner scan = new Scanner(System.in); //Gets user input
        int spot;
        int flag;
        int win = 0;

        //Print out grid
        printGrid(grid);

        //The max number of turns in this game is 9.
        //To save on the number of operations, we will only to start looking for a winner at turn 5
        //because thats the earliest a person can win
        for(int moves = 0; moves < 9; moves++){
            flag = 0;
            player = moves%2;
            //Determines whose turn it is
            if(player==0){//Player 1
                playerValue = 'X';
            }else{//Player 2
                playerValue = 'O';
            }

            //Asks player for input
            //(player + 1) gives us player 1 or 2 instead of having 0 and 1.
            System.out.println("Player " + (player+1) + ", please pick a number on the grid to place a move there.");
            spot = scan.nextInt();

            //Loops until the player gives a validate
            while(flag == 0){
                if((spot > 0 && spot < 10)) { //Looks for numbers between 1-9
                    if (spot == valid[spot - 1]) { //Checks to see if the move was already made
                        valid[spot - 1] = 0; //replaces valid move
                        flag = 1; //sets flag
                        break;
                    }
                }
                System.out.println("Please pick a validate number on the board!");
                spot = scan.nextInt();
            }

            //Determines the placement
            switch (spot) {
                case 1:
                    grid = move(grid, 0, 0, playerValue);
                    break;
                case 2:
                    grid = move(grid, 0, 1, playerValue);
                    break;
                case 3:
                    grid = move(grid, 0, 2, playerValue);
                    break;
                case 4:
                    grid = move(grid, 1, 0, playerValue);
                    break;
                case 5:
                    grid = move(grid, 1, 1, playerValue);
                    break;
                case 6:
                    grid = move(grid, 1, 2, playerValue);
                    break;
                case 7:
                    grid = move(grid, 2, 0, playerValue);
                    break;
                case 8:
                    grid = move(grid, 2, 1, playerValue);
                    break;
                case 9:
                    grid = move(grid, 2, 2, playerValue);
                    break;
            }
            printGrid(grid);

            //Checks if there was a win
            if(moves >= 4){
                if(findWinner(grid) == 1){
                    win = 1;
                    break;
                }
            }
        }
        if(win == 1){
            System.out.println("Player " + (player+1) + " Wins!");
        }else{
            System.out.println("Draw!");
        }
        return;
    }
}