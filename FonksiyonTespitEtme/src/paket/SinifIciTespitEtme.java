/**
*
* @author Eren Kara errennkaaraa@hotmail.com
* @since 18.04.2023
* <p>
* TespitEtme sınıfından kalıtım alıyor.
* Sınıfın temel amacı dosya içerisindeki fonksiyon blokları içerisinde kalmayan yorumları ve fonksiyonları bulmak.
* Fonksiyona ait yorumları bir başka sınıfın bulması için görevi tespitEt() içerisinde FonksiyonIciTespitEtme sınıfına devrediyor.
* 
* Tespit ettiklerini ekrana veya dosyaya çıktı verebiliyor.
* 
* </p>
*/


package paket;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SinifIciTespitEtme extends TespitEtme{ // Sinif yine kendi içerisinde kendini tutabilecek bir yapı ile iç içe sınıflarıda bulabilirim
	
	private String isim;
	private int start;
	private int end;
	private List<FonksiyonIciTespitEtme> fonksiyonlar;
	
	
	/**
	 * 
	 * @param yazi Tespit edilecek dosya içeriği
	 * @param isim Sınıfın ismi
	 * @param start Sınıfın { bloğunun indexi
	 * @param end  Sınıfın } bloğunun indexinden bir fazlası
	 */
	SinifIciTespitEtme(String yazi,String isim,int start,int end) 
	{
		super(yazi);
		this.isim=isim;
		this.start=start;
		this.end=end;
		this.fonksiyonlar=new ArrayList<FonksiyonIciTespitEtme>();
	}
	
	/**
	 * 
	 * @return Sınıfın ismini döndürüyor.
	 */
	public String getIsim()
	{
		return isim;
	}
	
	/**
	 * Fonksiyonları dosyaya yazdırmak için bir bulduğu fonksiyonların içeriğindeki yorumları yazdıracak
	 * FonksiyonIciTespitEtme sınıfına ait dosyayaYazdir methodunu çağırıyor.
	 * 
	 */
	public void dosyayaYazdir()
	{	
		for (FonksiyonIciTespitEtme fonksiyon : fonksiyonlar) 
		{
			fonksiyon.dosyayaYazdir();
		}
	}
	
	/**
	 * Sınıf parantezleri içerisinde kalan fonksiyonları tespit ediyor veya varsa bu fonksiyona ait javadoc'u.
	 * 
	 */
	
	@Override
	public boolean tespitEt() 
	{
		this.fonksiyonlar.clear();
		this.cokluYorumlar.clear();
		this.docYorumlar.clear();
		this.tekliYorumlar.clear();
		
		if (yazi!=null) 
		{
			Pattern oruntu=Pattern.compile(RegexIfadeleri.getRegexGenel(isim));
			Matcher eslesme=oruntu.matcher(yazi);
			eslesme.region(start, end);
			
			while (eslesme.find()) 
			{
				if(eslesme.group().matches(RegexIfadeleri.getRegexFonksiyon(isim)))
				{
					if (eslesme.group(8)!=null) {
						// fonksiyonun sınırlarını ve ismini gönderiyorum burda. eslesme group 8 constructor'ın ismi oluyor.
						fonksiyonlar.add(new FonksiyonIciTespitEtme(yazi,eslesme.group(8),eslesme.end()-1,KumeParanteziEslestirme(eslesme.end()-1)));
					}
					else if (eslesme.group(9)!=null) {
						// fonksiyonun sınırlarını ve ismini gönderiyorum burda. eslesme group 9 constructor olmayan fonksiyonun ismi oluyor.
						fonksiyonlar.add(new FonksiyonIciTespitEtme(yazi,eslesme.group(9),eslesme.end()-1,KumeParanteziEslestirme(eslesme.end()-1)));
					}

					
					fonksiyonlar.get(fonksiyonlar.size()-1).tespitEt();
					fonksiyonlar.get(fonksiyonlar.size()-1).listeleriAktar(cokluYorumlar, docYorumlar, tekliYorumlar);
					
					// bir sonraki fonksiyonu bulabilmek için sınırları son bulunan fonksiyonun } işaretininden başlatıcak ve dosyanın sonuna gidecek şekilde ayarladım
					eslesme.region(KumeParanteziEslestirme(eslesme.end()-1), eslesme.regionEnd()); 
					cokluYorumlar.clear();
					docYorumlar.clear();
					tekliYorumlar.clear();
				}
				else if(eslesme.group().matches(RegexIfadeleri.regexDoc)) // bunların sırası değişmemeli ilk önce regexDoc'lar bulunacak
				{
					docYorumlar.add(eslesme.group());
				}
			}
			return true;
		}
		else
		{
			System.out.println("Dosya bulunamadı");
			return false;
		}
	}
	
	/**
	 * Ekrana bizden istenilen şekilde çıktı vermeme yarıyor.
	 * 
	 */
	@Override
	public String toString()
	{
		String cikti="";
		for(FonksiyonIciTespitEtme fonksiyon : fonksiyonlar) {
			cikti+="\tFonsiyon: "+fonksiyon.getIsim()+"\n"+fonksiyon; // burda fonksiyon yazdım bu bir sınıf ve toString() methodu çağrılıyor.
		}
		return cikti;
	}
}