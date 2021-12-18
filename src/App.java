package Java.HYC_EncryptionAlgorithm.src;

public class App {
    public static void main(String[] args) {

		int bcn = 2;
		int tcn = 5;
		String B = new String();
		String T = new String();
		String[] alphabet_Value = new String[26];
		
		for(int i=0;i<bcn*tcn*10;i++) {
			B = B + Integer.toBinaryString(i);
		}

		for(int i=0;i<B.length()-bcn;i+=bcn) {
			T = T + Integer.toString(Integer.parseInt(B.substring(i, i+bcn), 2));
		}

		int value=0;
		System.out.println("bc" + bcn + "tc" + tcn);
		for(int i=0;i<26;i++) {
			alphabet_Value[i] = T.substring(value, value+tcn);
			System.out.print((char)(i+65) + ": ");
			System.out.println(alphabet_Value[i]);
			value+=tcn;
		}
	}
}
