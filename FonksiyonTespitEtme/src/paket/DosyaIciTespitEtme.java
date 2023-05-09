/**
*
* @author Eren Kara errennkaaraa@hotmail.com
* @since 18.04.2023
* <p>
* TespitEtme sınıfından kalıtım alıyor.
* Sınıfın temel amacı dosya içerisindeki sınıf blokları içerisinde kalmayan yorumları ve sınıfı bulmak.
* 
* Sınıfa ait yorumları bir başka sınıfın bulması için görevi tespitEt() içerisinde SinifIciTespitEtme sınıfına devrediyor.
* 
* 
* Tespit ettiklerini ekrana veya dosyaya çıktı verebiliyor.
* </p>
*/


package paket;

import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DosyaIciTespitEtme extends TespitEtme{
	
	private SinifIciTespitEtme siniflar;
	
	/**
	 * 
	 * Constructor'ı parametre olarak dosyanın içeriğini alıyor ve kendi field'ına aktarıyor.
	 * 
	 */
	
	DosyaIciTespitEtme(String yazi)
	{
		super(yazi);
		this.siniflar=null;
	}
	
	/**
	 * degistir() methodu başka bir dosyanın içeriğinin tekrar alınmasını sağlıyor yani farklı bir 
	 * dosyanın içeriği üzerinde işlem yapmak için kullanılabilir.
	 */
	
	public void degistir(String yazi)
	{
		
		this.yazi=yazi;
		this.siniflar=null;
		this.cokluYorumlar.clear();
		this.docYorumlar.clear();
		this.tekliYorumlar.clear();
	}
	
	/**
	 * dosyayaYazdir() methodu sınıf bulunduysa dosyaya yazdırması için bir başka sınıf olan SinifIciTEspitEtme
	 * sınıfının bir methodunu çağırıyor. Sınıf bulunuamadıysa uyarı olarak çıktı veriyor.
	 */
	
	public void dosyayaYazdir()
	{
		try 
		{
			FileWriter fwDoc=new FileWriter(docDosya);
			FileWriter fwCoklu=new FileWriter(cokluDosya);
			FileWriter fwTekli=new FileWriter(tekliDosya);
			fwDoc.close();
			fwCoklu.close();
			fwTekli.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(siniflar!=null)
		{
			siniflar.dosyayaYazdir();
		}
		else
		{
			System.out.println("DosyaIciTespitEtme.dosyayaYazdir() : Sınıf bulunamadığından dosyaya yazılamadı.");
		}
	}
	
	/**
	 * tespitEt() mehodu sınıf sınıfı veya varsa sınıf üstündeki yorum satırlarını tespit ediyor.
	 * sınıf tespit ederse bir diğer sınıf olan SinifIciTespitEtme sınıfının tespit et methodunu cagiriyor.
	 * 
	 * Eğerki dosyanın içeriği null'sa yani dosya yoksa ortada uyarı veriyor.
	 * 
	 */
	
	
	@Override
	public boolean tespitEt() 
	{
		this.siniflar=null;
		this.cokluYorumlar.clear();
		this.docYorumlar.clear();
		this.tekliYorumlar.clear();
	
		if(yazi!=null)
		{
			Pattern oruntu=Pattern.compile(RegexIfadeleri.regexGenelFonksiyonsuz);
			Matcher eslesme=oruntu.matcher(yazi);
			while (eslesme.find()) 
			{
				if(eslesme.group().matches(RegexIfadeleri.regexClass))
				{
					int start=eslesme.end(); // sınıfın sonu sınıfın kume parantezine denk geliyor yani baslangıc
					int end=KumeParanteziEslestirme(eslesme.end());  // kume parantezinin sonunu buldum eslestirdim yani her birini
					siniflar=new SinifIciTespitEtme(yazi,eslesme.group(6),start,end);
					siniflar.tespitEt();
					
					
					eslesme.region(end, eslesme.regionEnd()); // yeni sınıfı bulmak için diğer sınıfın sonunu baslangiç olarak verdim. 
					// ama vazgeçtim sonradan bunu yapmaktan. Javada her dosyada 1 sınıf var
					// ilerde belki programı geliştirebilirm diye bıraktım bunu burda.
					break;
				}
				else if(eslesme.group().matches(RegexIfadeleri.regexDoc)) // bunların sırası değişmemeli ilk önce regexDoc'lar bulunacak
				{
					docYorumlar.add(eslesme.group());
				}
				else if(eslesme.group().matches(RegexIfadeleri.regexCoklu))
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
			System.out.println("DosyaIciTespitEtme.tespitEt() : Dosya bulunamadığından tespit etme islemi gerçekleşmedi");
			return false;
		}
	}
	
	/**
	 * Ekrana bizden istenilen şekilde çıktı vermeye yarıyor.
	 * 
	 * Bir hata ile karşılaşırsa uyarı veriyor.
	 * İçerisinde tespitEt() methodunu da çağırıyor.
	 */
	@Override
	public String toString()
	{
		if(tespitEt()==true)
		{
			if(siniflar!=null)
			{
				String cikti=     "Sınıfın Dışındaki Yorumlar:\n"+
						"  Tek Satır Yorum Sayısı:   "+tekliYorumlar.size()+"\n"+
						"  Çok Satırlı Yorum Sayısı: "+cokluYorumlar.size()+"\n"+
						"  Javadoc Yorum Sayısı:     "+docYorumlar.size()+"\n"+
						"-------------------------------------------\n"+
						"-------------------------------------------\n"+
						"Sınıf: "+siniflar.getIsim()+"\n"+
						"-------------------------------------------\n"+
								siniflar;
				return cikti;
			}
			return "Sınıf tespit edilemedi";
			
		}
		else
		{
			return "DosyaIciTespitEtme.toString() : Sınıf ici tespit gerceklesemedi cunku dosya bulunamadı";
		}
	}
}