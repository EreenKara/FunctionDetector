/**
*
* @author Eren Kara errennkaaraa@hotmail.com
* @since 18.04.2023
* <p>
* TespitEtme sınıfından kalıtım alıyor.
* Sınıfın temel amacı dosya içerisindeki fonksiyon blokları içerisinde kalan yorumları bulmak.
* 
* Tespit ettiklerini ekrana veya dosyaya çıktı verebiliyor.
* 
* </p>
*/


package paket;

import java.io.FileWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FonksiyonIciTespitEtme extends TespitEtme{
	private String isim;
	private int start;
	private int end;
	
	/**
	 * 
	 * @param yazi Dosya içeriği
	 * @param isim Fonksiyonun ismi
	 * @param start Fonksiyonun { işaretinin indexi
	 * @param end Fonksiyonun } işaretinin indexi
	 */
	FonksiyonIciTespitEtme(String yazi,String isim,int start,int end)
	{
		super(yazi);
		this.isim=isim;
		this.start=start;
		this.end=end;
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
	 * Varsa fonksiyonun üstünde yorumları fonksiyona ekliyor
	 * 
	 */
	public void listeleriAktar(List<String> cokluYorumlar,List<String> docYorumlar,List<String> tekliYorumlar)
	{
		this.cokluYorumlar.addAll(0,cokluYorumlar);
		this.docYorumlar.addAll(0,docYorumlar);
		this.tekliYorumlar.addAll(0,tekliYorumlar);
	}
	
	/**
	 * Fonksiyon yorumlarını dosyaya yazdırıyor.
	 */
	public void dosyayaYazdir()
	{	
		try
		{
			FileWriter fwDoc=new FileWriter(docDosya,true); // true dosyanın sonuna yazmamızı sağlıyor.
			FileWriter fwCoklu=new FileWriter(cokluDosya,true);
			FileWriter fwTekli=new FileWriter(tekliDosya,true);
			
			if(docYorumlar.size()!=0)  // javadoc yorumu varsa dosyaya yazdırıyor.
			{
				fwDoc.write("Fonksiyon: "+isim+"\n\n");
				for (String yorumlar : docYorumlar) 
				{
					fwDoc.write(yorumlar+"\n\n");
				}	
				fwDoc.write("\n-------------------\n\n");
				fwDoc.close();
			}
			
			
			if(cokluYorumlar.size()!=0)  // coklu yorum varsa dosyaya yazdırıyor.
			{
				fwCoklu.write("Fonksiyon: "+isim+"\n\n");
				for (String yorumlar : cokluYorumlar) 
				{
					fwCoklu.write(yorumlar+"\n\n");
				}
				fwCoklu.write("\n-------------------\n\n");
				fwCoklu.close();
			}
			
			
			if(tekliYorumlar.size()!=0)  // tekli yorum varsa dosyaya yazdırıyor.
			{
				fwTekli.write("Fonksiyon: "+isim+"\n\n");
				for (String yorumlar : tekliYorumlar) 
				{
					fwTekli.write(yorumlar+"\n\n");
				}
				fwTekli.write("\n-------------------\n\n");
				fwTekli.close();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Fonksiyonları dosyaya yazma sırasında sıkıntı oluştu");
		}
		
	}
	
	/**
	 * Fonksiyon içerisindeki yorumları tespit ediyor.
	 * 
	 */
	
	@Override
	public boolean tespitEt()
	{
		this.cokluYorumlar.clear();
		this.docYorumlar.clear();
		this.tekliYorumlar.clear();
		if(yazi!=null)
		{
			Pattern oruntu=Pattern.compile(RegexIfadeleri.regexYorumlar);
			Matcher eslesme=oruntu.matcher(yazi);
			eslesme.region(start, end);

			while (eslesme.find()) 
			{
				if(eslesme.group().matches(RegexIfadeleri.regexDoc)) // bunların sırası değişmemeli ilk önce regexDoc'lar bulunacak
				{
					docYorumlar.add(eslesme.group());
				}
				else if (eslesme.group().matches(RegexIfadeleri.regexCoklu)) 
				{
					cokluYorumlar.add(eslesme.group());
				}
				else if(eslesme.group().matches(RegexIfadeleri.regexTekli))
				{
					tekliYorumlar.add(eslesme.group());
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
	 * Ekrana bizden istenilen şekilde çıktı vermeye yarıyor.
	 * 
	 */
	
	@Override
	public String toString()
	{
		String cikti="\t\tTek Satır Yorum Sayısı:   "+tekliYorumlar.size()+"\n";
		cikti+="\t\tÇok Satırlı Yorum Sayısı: "+cokluYorumlar.size()+"\n";
		cikti+="\t\tJavadoc Yorum Sayısı:     "+docYorumlar.size()+"\n";
		cikti+="-------------------------------------------\n";
		return cikti;
	}
	
	
}