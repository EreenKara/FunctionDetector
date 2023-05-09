# FunctionDetector
Principles of Programming Languages First Assignment : detect all comments, comment blocks and functions that within class.
1. Ödev [Tüm Şubeler için]
Teslim Tarihi
23 Nisan 2023 (Pazar günü saat 23:59’a kadar.)
Ödev İçeriği
Yazacağınız Eclipse projesi Java dilinde ve konsol uygulaması olmalıdır. 
Programa konsol üzerinden komut satırı parametresi olarak verilecek *.java dosyasını, program 
okuyacak ve ilk önce tüm fonksiyonları tespit edecektir. Daha sonra fonksiyonların içinde yazılmış 
olan yorumları ve Javadoc’ları ayrı ayrı fonksiyonlar ile ilişkili olarak tutacaktır. Ayrıca fonksiyonun 
üstünde yazılı fonksiyona ait olan javadoc varsa onları da alacaktır. Ekranda her fonksiyona ait kaç 
adet tek satır yorum (// yorumlar), çok satırlı yorum (/* yorumlar */) ve javadoc (/** yorumlar */)
olduğunu listeleyecektir. Yorumlar 3 farklı dosyaya kaydedilmelidir. Javadoc yorumlari javadoc.txt, 
tek satır yorumları teksatir.txt ve çok satırlı yorumlar coksatir.txt ye aşağıdaki formatta 
kaydedilmelidir. 3 dosyanın da formatı aynı olmalıdır.
Fonksiyon: fonksiyon adı
Yorumlar listelenecek
-----------------------------------------
Fonksiyon: fonksiyon adı
Yorumlar listelenecek
-----------------------------------------
Fonksiyon: fonksiyon adı
Yorumlar listelenecek
Ödevde kullanılacak yöntem serbest olmakla birlikte düzenli ifadeler (regex) konusuna
https://youtu.be/HggrScOQouc bakmanız faydalı olacaktır.
Örneğin okutulacak Java dosyasının aşağıdaki gibi olduğu varsayılırsa,
package odev;
import java.util.UUID;
public class Motor {
private String motorNo;
private boolean calisiyor;
/**
* Varsayılan kurucu fonksiyon
*/
public Motor() {
this.motorNo = UUID.randomUUID().toString(); 
/* Başlangıçta false */calisiyor = false;
}
/**
* 
* @param motorNo UUID olarak motor id
* @return motor instance
*/
public Motor(String motorNo) {
/*
* Varolan bir motor no ile oluşturuluyor.
*/
this.motorNo = motorNo;
calisiyor = false; // false yapılıyor
}
public void calistir() {
/**
* calisiyor true yapılıyor
*/
calisiyor = true;
}
/**
* Motorun durdurulması //
*/
public void durdur() {
calisiyor = false;
}
public String getMotorNo() {
// motor no getir
return motorNo;
}
@Override
public String toString() {
// durum belirlenmesi //
String durum = calisiyor ? "Motor Çalışıyor." : "Motor Çalışmıyor";
return durum;
}
}
Ekran çıktısı aşağıdaki gibi olmalıdır. Ekranda ve dosyada fonksiyon listelemesi Java dosyasındakine 
uygun olarak yukarıdan aşağıya doğru olmalıdır.
Programa okutulacak dosya, komut satırı parametresi olarak verileceği için proje mutlaka Runnable 
JAR olarak export yapılmalı ve oluşan jar dosyası proje ana klasöründe dist klasörü içerisine
yerleştirilmelidir. Boş gönderilen dist klasöründen puan kırılacaktır.
Farklı sayıda fonksiyon ve yorumları okuyabilecek şekilde program yazılmalıdır.
Yazılacak proje mutlaka Eclipse ortamında açılabilen ve derlenebilen proje olmalıdır.
Önemli Not: Raporunuz detaylı olmalı ve kendi cümleleriniz olmalıdır (Örnek rapor
SABİS’e yüklenmiştir). Kopya ödevler sıfır olarak değerlendirilecektir. SABİS şifreniz sizin 
sorumluluğunuz altındadır eğer arkadaşınız sizden habersiz ödevinizi alırsa bundan sizde 
sorumlu tutulur ve sıfır alırsınız.
ÖDEV BİREYSELDİR
Teslim Formatı
Ödevi Eclipse proje dosyaları ve bütün diğer dosyaların bulunduğu klasörü .zip’li bir şekilde SABİS
üzerinden gönderiniz. Klasörünüzün adı mutlaka öğrenci numaranız (b121210080 gibi) olmalıdır.
Yukarıda belirtilen teslim tarihinden sonra gönderilen ödev kesinlikle kabul edilmeyecektir.
Rapor pdf formatında olmalıdır. Raporu ayrıca çıktı olarak getirmenize gerek yoktur. Raporunuzda 
kısaca sizden istenilen, öğrendikleriniz, ödevde yaptıklarınız, eksik bıraktığınız yerler, zorlandığınız 
kısımlar anlatılabilir. Ödev raporu kapak hariç en az 1 sayfa en çok 3 sayfa olabilir.
Yazmış olduğunuz bütün kaynak kodların en başında aşağıdaki bilgiler bulunmalıdır. Bilgileri 
kendinize göre güncelleyiniz.
/**
*
* @author Yazar adı ve mail
* @since Programın yazıldığı tarih
* <p>
* Sınıf ile ilgili açıklama
* </p>
*/
KOPYA ÖDEV SIFIR OLARAK DEĞERLENDİRİLMEKTEDİR
