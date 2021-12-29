package tests;

public class ViewsTest {

	public static void main(String[] args) {
		System.out.println(divideElegantString("mirame fijamente hasta cegarme una dos o tres veces"));
	}
	
	public static String divideElegantString(String string) {
		String auxString = "";
		int index = 0;
		int length = (int)(string.length()*0.6);
		while(string.charAt(length) != 32) {
			length++;
		}
		for (int i = 0; i < length; i++) {
			auxString += string.charAt(i);
			index = i;
		}
		auxString+= "<br>";
		for (int i = index+2; i < string.length(); i++) {
			auxString+= string.charAt(i);
		}
		auxString += "</br>";
		return auxString;
	}
}
