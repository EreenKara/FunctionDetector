/**
*
* @author Eren Kara errennkaaraa@hotmail.com
* @since 18.04.2023
* <p>
* Bu sınıf DosyadanOkuma methoduna girdi olarak verilen text adında file 
* varsa dosyanın içerisindeki text'i String'e aktarıp bunu geri döndürüyor.
* </p>
*/


package paket;

import java.io.File;
import java.io.FileReader;

public class DosyadanOkuma {  
	
	private DosyadanOkuma() {}
	
	public static String oku(String dosya)
	{
		String yazi="";
		
		int kontrol;
		char c;
		try
		{
			File f= new File(dosya);
			FileReader reader=new FileReader(f);
			kontrol=reader.read();	
			while (kontrol!=-1) 
			{
				c=(char)kontrol;
				yazi+=c;
				kontrol=reader.read();	
			}
			reader.close();
			return yazi;
		}
		catch(Exception e) 
		{
			System.out.println("DosyadanOkuma.oku() : Dosya bulunamadı");
			return null;
		}
		
		
	}

}
