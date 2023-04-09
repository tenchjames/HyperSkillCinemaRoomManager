package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);
        int rows = 0;
        int seats = 0;
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();

        char[][] cinema = buildCinema(rows, seats);

        int choice;
        while ((choice = showMenu()) != 0) {
            switch (choice) {
                case 1:
                    printHeader(seats);
                    printSeats(cinema);
                    break;
                case 2:
                    buyTicket(cinema);
                    break;
                case 3:
                    statistics(cinema);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void statistics(char[][] cinema) {
        int totalSeats = cinema.length * cinema[0].length;
        int purchasedSeats = 0;
        int currentIncome = 0;
        int totalIncome = 0;
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[0].length; j++) {
                if (cinema[i][j] == 'B') {
                    purchasedSeats++;
                    int price = 0;
                    if (totalSeats <= 60) {
                        price = 10;
                    } else {
                        int frontRows = cinema.length / 2;
                        if (i < frontRows) {
                            price = 10;
                        } else {
                            price = 8;
                        }
                    }
                    currentIncome += price;
                }
            }
        }
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else {
            int frontRows = cinema.length / 2;
            int backRows = cinema.length - frontRows;
            totalIncome = frontRows * cinema[0].length * 10 + backRows * cinema[0].length * 8;
        }
        System.out.println("Number of purchased tickets: " + purchasedSeats);
        System.out.printf("Percentage: %.2f%%%n", (double) purchasedSeats / totalSeats * 100);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    private static int getRow() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        return scanner.nextInt();
    }

    private static int getSeat() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a seat number in that row:");
        return scanner.nextInt();
    }

    private static void buyTicket(char[][] cinema) {
        int row = getRow();
        int seat = getSeat();
        boolean seatIsValid = row > 0 && row <= cinema.length && seat > 0 && seat <= cinema[0].length;
        while (!seatIsValid) {
            System.out.println("Wrong input!");
            row = getRow();
            seat = getSeat();
            seatIsValid = row > 0 && row <= cinema.length && seat > 0 && seat <= cinema[0].length;
        }
        boolean seatIsAvailable = cinema[row - 1][seat - 1] == 'S';
        while (!seatIsAvailable) {
            System.out.println("That ticket has already been purchased!");
            row = getRow();
            seat = getSeat();
            seatIsAvailable = cinema[row - 1][seat - 1] == 'S';
        }
        cinema[row - 1][seat - 1] = 'B';
        int totalSeats = cinema.length * cinema[0].length;
        int price = 0;
        if (totalSeats <= 60) {
            price = 10;
        } else {
            int frontRows = cinema.length / 2;
            if (row <= frontRows) {
                price = 10;
            } else {
                price = 8;
            }
        }
        System.out.println("Ticket price: $" + price);
    }

    private static int showMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static void printSeats(char[][] cinema) {
        for (int i = 1; i <= cinema.length; i++) {
            System.out.print(i + " ");
            for (int j = 1; j <= cinema[0].length; j++) {
                System.out.print(cinema[i - 1][j - 1] + " ");
            }
            System.out.println();
        }
    }

    private static void printHeader(int seats) {
        System.out.println();
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= seats; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
    }

    private static char[][] buildCinema(int rows, int seats) {
        char[][] cinema = new char[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = 'S';
            }
        }
        return cinema;
    }

}