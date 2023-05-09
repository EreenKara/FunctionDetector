/**
*
* @author Eren Kara errennkaaraa@hotmail.com
* @since 18.04.2023
* <p>
* Icinde static olarak regex ifadelerini barındırıyor. 
* 
* Nesne oluşturulamasın diye private constructor'ı var.
* 
* </p>
*/


package paket;

public class RegexIfadeleri {
	
	
	public static final String regexClass="class(?:\\s|\\R)+?(\\w+)(?:\\s|\\R)*(?:extends\\s(?:\\w+))?(?:\\s|\\R)*(?:implements\\s(?:\\w|,\\s*)+)?";
	public static final String regexString="[\\\"](?:[\\s\\S])*?(?<!\\\\)[\\\"]";
	private static final String regexFonksiyon1="(?:(?:public|private|protected|static)(?:\\s+|\\R+))*(?:(";
	private static final String regexFonksiyon2=")\\s*|\\w+(?:(?<!\\n)\\s)+(\\w+))\\((?:.|\\R)*?\\)(?:\\s*|\\R*)?\\{";
	public static final String regexTekli="\\/\\/.*";
	public static final String regexCoklu="\\/\\*(?:.|\\R)*?\\*\\/";
	public static final String regexDoc="\\/\\*\\*(?!\\/)(?:.|\\R)*?\\*\\/";
	public static final String regexKumeParantezleri="(\\{)|(\\})";
	public static final String regexYorumlar=String.format("(?:(%s)|(%s)|(%s)|(%s))",regexString,regexDoc,regexCoklu,regexTekli);
	public static final  String regexGenelFonksiyonsuz=String.format("(?:(%s)|(%s)|(%s)|(%s)|(%s))",regexString,regexDoc,regexCoklu,regexTekli,regexClass);

	
	private RegexIfadeleri() {}
	
	/**
	 * 
	 * @param sinifIsmi Sınıfın constructor'ını bulmak için sınıf ismine ihtiyacım var
	 * 
	 */
	public static String getRegexGenel(String sinifIsmi)
	{
		return String.format("(?:(%s)|(%s)|(%s)|(%s)|(%s)|(%s%s%s))",regexString,regexDoc,regexCoklu,regexTekli,regexClass,regexFonksiyon1,sinifIsmi,regexFonksiyon2);
	}
	/**
	 * 
	 * @param sinifIsmi Sınıfın constructor'ını bulmak için sınıf ismine ihtiyacım var
	 * @return
	 */
	public static String getRegexFonksiyon(String sinifIsmi)
	{
		return regexFonksiyon1+sinifIsmi+regexFonksiyon2;
	}
	
}
