/**
*
* @author Eren Kara errennkaaraa@hotmail.com
* @since 18.04.2023
* <p>
* Main sınıfı içerisinde main methodunu barındırıyor.
* Konsoldan aldığı parametreye ait dosya varsa bunun içerisindeki
* text'i okuyor yazi değişkenine atıyor.
* Ondan sonra dosyadan tespit etme sınıfından nesne oluşturuyor.
* Constructor'da sadece bir kaç atama var. 
* Sınıfın toString methodu içerisinde tespitEt fonksiyonunu da barındırıyor.
* Böylece sınıfı ekrana yazdırdığımda hem tespit edip hem yazdırıyor.
* Dosyaya yazdırma kısmında tespit etmeyi önceden yapmanız gerekiyor çünkü bu method 
* içerisinde tespit etmeyi barındırmıyor.
* </p>
*/



package paket;

public class Main {

	public static void main(String[] args) {
		String yazi=null;
		try
		{
			yazi=DosyadanOkuma.oku(args[0]);
			
		}
		catch(Exception e)
		{
			System.out.println("Main.main() : Konsol parametresi girilmedi" );
		}
		DosyaIciTespitEtme et=new DosyaIciTespitEtme(yazi);
		System.out.println(et);
		et.dosyayaYazdir();
		
	}
}
