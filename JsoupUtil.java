package Goodreader;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class JsoupUtil {
	private JsoupUtil() {
	}
	
	private static final JsoupUtil instance = new JsoupUtil();
	
	public static JsoupUtil getInstance() {
		return instance;
	}

//	public void writeFile(String name, String author) {
//		File file = new File("/Users/HYQ/Documents/Eclipse/586/src/goodreader.txt");
//		Writer writer = null;
//		try {
//			writer = new FileWriter(file, true);
//			writer.write(name + "    " + author + "\r\n");
//			writer.flush();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (null != writer) {
//				try {
//					writer.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
	
	public void getGDReview() {
		try {
			for (int i=6; i<=6; i++) {
				String url = Constants.URL + Constants.START + String.valueOf(i);
				Connection connection = Jsoup.connect(url);
				Document document = connection.get();
				Elements tr = document.select("tr");
				Iterator<Element> trIter = tr.iterator();
				
				while (trIter.hasNext()) {
					Element element = trIter.next();
					Elements eletd = element.select("[width='100%']");
					
					Elements elegrey = eletd.select("span.greyText");
					Elements eleminirating = elegrey.select("span.minirating");
					String stars = eleminirating.text();
					String[] starsarr = stars.split(" ");
					String star = starsarr[0];
					System.out.println(star);
					
//					Elements eletitlea = eletd.select("a.bookTitle");
					
//					Elements eletitle = eletitlea.select("span");
//					String title = eletitle.text();
					
//					String href = eletitlea.attr("href");
//					String detailsurl = "https://www.goodreads.com" + href;
//					getDetails(detailsurl);

//					Elements eleauthorspan = eletd.select("[itemprop='author']");
//					Elements eleauthora = eleauthorspan.select("a.authorName");
//					Elements eleauthor = eleauthora.select("span");
//					String author = eleauthor.text();
//					System.out.println(title+"	"+author);
					
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getDetails(String detailsurl) {
		try {
			Connection connection = Jsoup.connect(detailsurl);
			Document document = connection.get();
			
			
			Elements eledetailsdiv = document.select("#details");
			Elements elerow = eledetailsdiv.select("div.row");
			Element elepublisher = elerow.get(Math.min(1, elerow.size()-1));
			int indexofby = elepublisher.text().indexOf("by");
			int indexofparen = elepublisher.text().indexOf("(");
			String publisher = "";
			if (indexofby != -1) {
				if (indexofparen != -1) {
					publisher = elepublisher.text().substring(indexofby+3, indexofparen-1);
				} else {
					publisher = elepublisher.text().substring(indexofby+3);
				}
			}
//			System.out.println(publisher);
			
			Elements elestacked = document.select("div.stacked");
			Elements eleclear = elestacked.select("div.clearFloats");
			Elements elebigboxbody = eleclear.select("div.bigBoxBody");
			Elements eleboxcontent = elebigboxbody.select("div.bigBoxContent");
			String genre = "";
			if (eleboxcontent.select("div.elementList").size() != 0) {
				Element elementlist = eleboxcontent.select("div.elementList").get(0);
				Elements leftdiv = elementlist.select("div.left");
				Elements genrea = leftdiv.select("a");
				genre = genrea.text();
			}
//			System.out.println(genre);
			
			
			Elements bookdatabox = document.select("#bookDataBox");
			Elements eleclearFloats = bookdatabox.select("div.clearFloats");
			Elements eleclearlang = eleclearFloats.select("div:contains(Edition)");
			Elements infoBoxlang = eleclearlang.select("div.infoBoxRowItem");
			String language = "";
			language = infoBoxlang.text();
//			System.out.println(language);
			
			
			System.out.println(publisher+"	"+genre+"	"+language+"	"+detailsurl);
		
			
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}

