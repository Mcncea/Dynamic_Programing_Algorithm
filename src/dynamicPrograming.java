import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class dynamicPrograming {

    public static void main(String[] args){

        System.out.println(
                "1. Please enter the total amount of paths that zombies would appear on the map.\n" +
                "2. Followed with the exact amount of zombies would appear at specific path\n" +
                "3. First matrix need to be left to right direction." +
                "4. Second matrix need to be top to bottom direction."
        );


        String tempInput =
                        "4\n" +

                        "4\n" +

                        "6 9 6 10 1\n" +
                        "8 1 2 8 1\n" +
                        "3 1 2 8 3\n" +
                        "8 7 9 3 10\n" +

                        "-\n" +

                        "1 1 10 6\n" +
                        "4 4 7 6\n" +
                        "3 7 5 1\n" +
                        "10 9 5 5\n" +
                        "2 6 3 5";


        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream( tempInput.getBytes(StandardCharsets.UTF_8));


        Scanner inputInts = new Scanner(byteArrayInputStream);
//        Scanner inputInts = new Scanner(System.in);


        int Row = inputInts.nextInt(); // the row of the map;
        int Col = inputInts.nextInt(); // the col of the map;
        int[][] MatrixDown = new int[Row][Col+1];   // the matrix of going down direction;
        int[][] MatrixRight = new int[Row+1][Col];  // the matrix of going right direction;

        for(int i=0; i<Row; i++){

            for(int j=0; j<=Col; j++){

                MatrixDown[i][j] = inputInts.nextInt();

            }
        }

        String Sepe = inputInts.next(); // the '-' separates right and down matrices;

        for(int i=0; i<=Row; i++){

            for(int j=0; j<Col; j++){

                MatrixRight[i][j] = inputInts.nextInt();

            }
        }

        inputInts.close();

        //------------------------------------------------------------------------------------//

        //Showing the user input
        System.out.println("The Row path and Column path of the map are: " + Row +" " + Col);

        for(int i=0; i<Row; i++){
            for(int j=0; j<=Col; j++){

                System.out.print(" " + MatrixDown[i][j]);

            }

            System.out.println();

        }

        System.out.println(" " + Sepe); // printout the '-' separate two matrices;

        for(int i=0; i<=Row; i++){

            for(int j=0; j<Col; j++){

                System.out.print(" " + MatrixRight[i][j]);

            }

            System.out.println();
        }
        System.out.println("Finish input section.");



        //Math Approaching
        System.out.println("Computing the inner path martix:");
        int[][] Score = new int[Row+1][Col+1]; // Score is the longest distance from V[i][j] to V[0][0];
        // step 1: set up the starting point
        Score[0][0] = 0;

        // step 2: calculate the first row
        for(int i=1; i<=Row; i++){
            Score[i][0] = Score[i-1][0] + MatrixDown[i-1][0];
        }

        // step 3: calculate the first col
        for(int j=1; j<=Col; j++){
            Score[0][j] = Score[0][j-1] + MatrixRight[0][j-1];
        }

        //step 4: calculate all other points:
        for(int i=1; i<=Row; i++){
            for(int j=1; j<=Col; j++){

                Score[i][j] = Score[i-1][j] + MatrixDown[i-1][j];
                if(Score[i][j-1] + MatrixRight[i][j-1] > Score[i][j]){
                    Score[i][j] = Score[i][j-1] + MatrixRight[i][j-1];
                    //step 5:
                    System.out.print( " " + Score[i][j] + "<"); // identify the number from left
                }else {
                    System.out.print(" " + Score[i][j] + "^");// identify the number from top
                }
            } // end for inner matrix calculate
            System.out.println();
        }


        System.out.println("Output the final block corner matrix:");
        for(int i=0; i<=Row; i++){
            for(int j=0; j<=Col; j++){
                System.out.print(" " + Score[i][j] + " ");
            }
            System.out.println();
        }

    }
}
