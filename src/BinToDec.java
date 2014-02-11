import java.util.Scanner;

public class BinToDec {

	private static float inputFP;
	private static String binaryString;
	private static boolean signPolarity = true;
	private static String converted32Binary;
	
	public static void main(String[]args){
		
		MainMenu();
	}
	
	static void ThirtyTwoBitConversion(){
		
		System.out.print("Enter a float: ");
		Scanner kybd = new Scanner(System.in);
		inputFP = kybd.nextFloat();
		System.out.println("User input check: " + inputFP);
		
		if (inputFP < 0){
			setSignPolarity(false);
		}
		
		System.out.println(getSignPolarity());
		
		binaryString = DecToFloatConversion(inputFP);
		System.out.println("Returned Binary String: " + binaryString);
		
		int n = binaryString.indexOf(".");
		binaryString = binaryString.replace(".", "");
		n = n-1;
		n = n+127;
		System.out.println("Converted number to binary: " + ConvertTo32Binary(n));
		converted32Binary = ConvertTo32Binary(n);
		binaryString = binaryString.substring(0,1) + "." + binaryString.substring(1);
		System.out.println("NORMALIZE: " + binaryString);
		finalProduct(converted32Binary,binaryString);

	}
	
	private static String ConvertTo32Binary(int n){
		
		String convertedBinaryString = "";
		
		if (n >= 128){
			convertedBinaryString = convertedBinaryString + "1";
			n = n - 128;
		} else {
			convertedBinaryString = convertedBinaryString + "0";
		}
		
		if (n >= 64){
			convertedBinaryString = convertedBinaryString + "1";
			n = n - 64;
		} else {
			convertedBinaryString = convertedBinaryString + "0";
		}
		
		if (n >= 32){
			convertedBinaryString = convertedBinaryString + "1";
			n = n - 32;
		} else {
			convertedBinaryString = convertedBinaryString + "0";
		}
		
		if (n >= 16){
			convertedBinaryString = convertedBinaryString + "1";
			n = n - 16;
		} else {
			convertedBinaryString = convertedBinaryString + "0";
		}
		
		if (n >= 8){
			convertedBinaryString = convertedBinaryString + "1";
			n = n - 8;
		} else {
			convertedBinaryString = convertedBinaryString + "0";
		}
		
		if (n >= 4){
			convertedBinaryString = convertedBinaryString + "1";
			n = n - 4;
		} else {
			convertedBinaryString = convertedBinaryString + "0";
		}
		
		if (n >= 2){
			convertedBinaryString = convertedBinaryString + "1";
			n = n - 2;
		} else {
			convertedBinaryString = convertedBinaryString + "0";
		}
		
		if (n >= 1){
			convertedBinaryString = convertedBinaryString + "1";
			n = n - 1;
		} else {
			convertedBinaryString = convertedBinaryString + "0";
		}
		
		return convertedBinaryString;
	}
	static void SixtyFourBitConversion(){
		
	}
	
	private static String DecToFloatConversion(float inputFP){
		
		String binaryString = "";
		String tempBinaryString = "";
		String backwardsBinaryString = "";
		boolean polarityCheck = getSignPolarity();;

		if (polarityCheck == false){
		inputFP = inputFP * -1;																	//Removes the negative sign
		}
		
		//Breaks apart the number at the decimal between Int and Fractional parts.
		long intPartOfNumber = (long) inputFP;;													//Take INT from inputFP store in intPartOfNumber
		System.out.println("Int Part of Numbers is: " + intPartOfNumber);						//For use to convert intPartOfNumber to binary
		float fractionPartOfNumber = inputFP - intPartOfNumber;									//Gets FP by subtracting the INT from InputFP
		System.out.println("Fraction part of number is : " + fractionPartOfNumber);
		
		//Convert fractional to binary
		while(fractionPartOfNumber != 0){
			
			inputFP = fractionPartOfNumber * 2;													//Multiply by 2 and store in inputFP
			System.out.println(fractionPartOfNumber + " * 2 = " + inputFP);
			
			long binaryStringBuilder = (long)inputFP;											//Take INT from inputFP
			System.out.println("String Builder variable: " + binaryStringBuilder);
			fractionPartOfNumber = inputFP - binaryStringBuilder;								//Take FP store in fractionPartOfNumber
			binaryString = binaryString + binaryStringBuilder;									//Building binary string
		}
		
		while(intPartOfNumber !=0){
			
			if (intPartOfNumber % 2 == 0){
				tempBinaryString = tempBinaryString + "0";
			} else {
				tempBinaryString = tempBinaryString + "1";
			}
			intPartOfNumber = intPartOfNumber / 2;
			System.out.println(tempBinaryString);
		}
		
		if (tempBinaryString == ""){
			tempBinaryString = "0";
		}
		
		for (int i = tempBinaryString.length() - 1; i >= 0; i--){
			backwardsBinaryString += (tempBinaryString.charAt(i) + "");
		}
		System.out.println("Shit is now backwards: " + backwardsBinaryString);
		
		binaryString = backwardsBinaryString + "." + binaryString;
		return binaryString;
	}
	
	static void MainMenu(){
		
		System.out.println("MAIN MENU");
		System.out.println("---------");
		System.out.println("Please select 1, 2, or 3");
		Scanner kybd = new Scanner(System.in);
		int menuInput = kybd.nextInt();
		
		if (menuInput == 1){
			ThirtyTwoBitConversion();
			}
		else if (menuInput == 2){
			SixtyFourBitConversion();
			}
		else if (menuInput == 3){
			System.exit(0);
			}
		else {
			System.out.println("Please enter a valid selection");
			System.out.println("");
			MainMenu();
		}	
	}

	public static boolean getSignPolarity() {
		return signPolarity;
	}

	public static void setSignPolarity(boolean signPolarity) {
		BinToDec.signPolarity = signPolarity;
	}
	
	public static void finalProduct(String finalExponent, String finalMantissa){
		String finalString = "";
		boolean finalSign = getSignPolarity();
		char posOrNeg = '0';
		int mantissaLength;
		
		if (finalSign == false){
			posOrNeg = '1';
		}
		
		finalMantissa = finalMantissa.substring(2);
		mantissaLength = finalMantissa.length();
		
		if (mantissaLength < 22){
			for (int i=1; i<24-mantissaLength;i++){
				finalMantissa = finalMantissa + "0";
			}
		}
		
		System.out.println("Sign  Exponents     Mantissa");
		System.out.println(posOrNeg + "     " + finalExponent + "      " + finalMantissa);
		
		
	}
}
