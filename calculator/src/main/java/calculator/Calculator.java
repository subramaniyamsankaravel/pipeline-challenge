package calculator;

import java.util.Scanner;

public class Calculator {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean cond = true;
		System.out.println("enter first number");
		int a = sc.nextInt();
		System.out.println("enter second number");
		int b = sc.nextInt();
		while (cond) {
			System.out.println("-------------------------");
			System.out.println("1.add");
			System.out.println("2.subtract");
			System.out.println("3.multiply");
			System.out.println("4.divide");
			System.out.println("5.Exit");
			System.out.println("Enter choice");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				add(a, b);
				break;
			case 2:
				sub(a, b);
				break;
			case 3:
				multiply(a, b);
				break;
			case 4:
				divide(a, b);
				break;
			case 5:
				cond = false;
				break;
			default:
				System.out.println("Enter proper choice");

			}

		}
	}

	public static double divide(int a, int b) {
		double c=a/b;		
		System.out.println("Division:" + (a / b));
		return c;

	}

	public static long multiply(int a, int b) {
		long c=a*b;
		System.out.println("Multiplication:" + (a * b));
		return c;
	}

	public static long sub(int a, int b) {
		long c=a-b;
		System.out.println("Subtraction:" + (a - b));
		return c;

	}

	public static int add(int a, int b) {
		int c=a+b;
		//System.out.println("Addition:" +c);
		return c;
	}
}
