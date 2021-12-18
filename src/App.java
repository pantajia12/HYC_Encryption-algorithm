package Java.HYC_EncryptionAlgorithm.src;

import java.util.Scanner;
import java.util.Arrays;

public class App {
	public static void main(String[] args) {

		
		boolean is = false;
		String scanBT;
		int connectionType = 0;
		String scanString;
		int bcn = 0;
		int tcn = 0;
		
		String B = new String();
		String T = new String();
		String[] alphabet_Value = new String[26];
		try (Scanner scan = new Scanner(System.in)) {
			System.out.println("암호키 입력");
			scanBT = scan.next();
			System.out.println("타입 입력 (0: 문자열 출력, 1: 암호화, 2: 복호화, 99: 종료)");
			
			
			scanBT = scanBT.replaceAll("bc","").replaceAll("tc",",");
			bcn = Integer.parseInt(scanBT.substring(0,scanBT.indexOf(",")));
			tcn = Integer.parseInt(scanBT.substring(scanBT.indexOf(",")+1));

			
			for(int i=0;i<bcn*tcn*10;i++) {
				B = B + Integer.toBinaryString(i);
			}

			for(int i=0;i<B.length()-bcn;i+=bcn) {
				T = T + Integer.toString(Integer.parseInt(B.substring(i, i+bcn), 2));
			}

			int value=0;
			for(int i=0;i<26;i++) {
				alphabet_Value[i] = T.substring(value, value+tcn);
				value+=tcn;
			}
			
			for(int i=0;i<26;i++) {
				for(int j=0;j<26;j++) {
					if(i != j) {
						if(Integer.parseInt(alphabet_Value[i]) == Integer.parseInt(alphabet_Value[j])) {
							is = true;
						}
					}
				}
			}
			if(is) {
				System.out.println("사용불가 암호키");
			} else {
				while(true) {
					connectionType = scan.nextInt();
					if(connectionType == 0) {
						value=0;
						System.out.println("bc" + bcn + "tc" + tcn);
						for(int i=0;i<26;i++) {
							alphabet_Value[i] = T.substring(value, value+tcn);
							System.out.print((char)(i+65) + ": ");
							System.out.println(alphabet_Value[i]);
							value+=tcn;
						}
						
					} else if(connectionType == 1) {
						String result = "";
						System.out.println("암호화 시킬 문자열 입력");
						scanString = scan.next();
						
						for(int i=0;i<scanString.length();i++) {
							result += alphabet_Value[scanString.charAt(i)-'A'];
						}
						System.out.println("암호화 결과: " + scanString + " -> " + result);
						
					} else if(connectionType == 2) {
						String spiltString;
						System.out.println("복호화 시킬 문자열 입력");
						scanString = scan.next();
						
						System.out.print("복호화 결과" + scanString + "-> ");
						for(int i=0;i<scanString.length();i+=tcn) {
							spiltString = scanString.substring(i, i+tcn);
							System.out.print((char)(Arrays.asList(alphabet_Value).indexOf(spiltString)+65));
						}
						System.out.println("");
						
					} else if(connectionType == 99) {
						System.out.println("종료");
						break;
					}
				}

			}
		}
	}
}