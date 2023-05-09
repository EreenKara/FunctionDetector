/**
*
* @author Eren Kara errennkaaraa@hotmail.com
* @since 18.04.2023
* <p>
* Sınıfın temel amacı kendisinden kalıtım alan sınıflara bir şablon oluşturmak.
* 
* </p>
*/


package paket;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class TespitEtme {
	
	protected String yazi;
	protected List<String> docYorumlar;
	protected List<String> cokluYorumlar;
	protected List<String> tekliYorumlar;
	protected File docDosya=new File("javadoc.txt");
	protected File cokluDosya=new File("coksatir.txt");
	protected File tekliDosya=new File("teksatir.txt");
	
	TespitEtme(String yazi)
	{
		this.yazi=yazi;
		this.docYorumlar=new ArrayList<String>();
		this.cokluYorumlar=new ArrayList<String>();
		this.tekliYorumlar=new ArrayList<String>();
	}
	
	protected abstract boolean tespitEt();
	
	/**
	 * 
	 * @param start Başlangıctan başlayarak bu scope'un nerde bittiğini buluyor.
	 * @return Geriye } işareti nerde bitiyorsa onu döndürüyor.
	 */
	public int KumeParanteziEslestirme(int start)
	{
		Pattern oruntu=Pattern.compile(RegexIfadeleri.regexKumeParantezleri);
		Matcher eslesme=oruntu.matcher(yazi);
		eslesme.region(start, eslesme.regionEnd());
		int kumeparantezi=0;
		while(eslesme.find())
		{
			if (eslesme.group().equals("{")) 
			{
				kumeparantezi++;
			}
			else if (eslesme.group().equals("}")) 
			{
				kumeparantezi--;
			}
			if(kumeparantezi==0)
			{
				break;
			}
		}
		return eslesme.end();
	}
	
}