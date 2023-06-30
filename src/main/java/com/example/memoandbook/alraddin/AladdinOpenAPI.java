package com.example.memoandbook.alraddin;

import com.example.memoandbook.domain.model.Book;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import lombok.Data;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.ParserAdapter;

public class AladdinOpenAPI {
  private static final String KEYWORD_BASE_URL = "http://www.aladdin.co.kr/ttb/api/ItemSearch.aspx?";
  private static final String RECOMMEND_BASE_URL = "http://www.aladin.co.kr/ttb/api/ItemList.aspx?";
  private static final String FIND_ONE_URL = "http://www.aladin.co.kr/ttb/api/ItemLookUp.aspx?";
  public static String getKeyWordUrl(String searchWord, String queryType) throws Exception {
    Map<String, String> map = new HashMap<>();
    map.put("ttbkey", "ttbparkjw5730527002");
    map.put("Query", URLEncoder.encode(searchWord, "UTF-8"));
    map.put("QueryType", queryType);
    map.put("MaxResults", "100");
    map.put("start", "1");
    map.put("SearchTarget", "Book");
    map.put("output", "xml");

    StringBuilder sb = new StringBuilder();
    for (String key : map.keySet()) {
      String val = map.get(key);
      sb.append(key).append("=").append(val).append("&");
    }
    return KEYWORD_BASE_URL + sb;
  }
  public static String getRecommendUrl(String queryType) throws Exception {
    Map<String, String> map = new HashMap<>();
    map.put("ttbkey", "ttbparkjw5730527002");
    map.put("QueryType", queryType);
    map.put("MaxResults", "10");
    map.put("start", "1");
    map.put("SearchTarget", "Book");
    map.put("output", "xml");
    map.put("Version", "20131101");

    StringBuilder sb = new StringBuilder();
    for (String key : map.keySet()) {
      String val = map.get(key);
      sb.append(key).append("=").append(val).append("&");
    }
    return RECOMMEND_BASE_URL + sb;
  }

  public static String getFindOneUrl(String isbn13) throws Exception {
    Map<String, String> map = new HashMap<>();
    map.put("ttbkey", "ttbparkjw5730527002");
    map.put("ItemId", isbn13);
    map.put("MaxResults", "10");
    map.put("start", "1");
    map.put("SearchTarget", "Book");
    map.put("output", "xml");
    map.put("Version", "20131101");

    StringBuilder sb = new StringBuilder();
    for (String key : map.keySet()) {
      String val = map.get(key);
      sb.append(key).append("=").append(val).append("&");
    }
    return FIND_ONE_URL + sb;
  }

  public static List<Book> getBooksFromKeyword(String keyword) throws Exception {
    String url = getKeyWordUrl(keyword, "keyword");
    AladdinOpenAPIHandler api = new AladdinOpenAPIHandler();
    api.parseXml(url);
    List<Book> bookList = new ArrayList<>();
    for(Item item : api.items) {
      item.extractDescription(item.getDescription());

      Book book = Book.builder()
          .title(item.getTitle())
          .author(item.getAuthor())
          .pubDate(item.getPubDate())
          .description(item.getDescription())
          .isbn13(item.getIdbn13()).build();
      bookList.add(book);
    }
    return bookList;
  }

  public static Book getOneBook(String isbn13) throws Exception {
    String url = getFindOneUrl(isbn13);
    AladdinOpenAPIHandler api = new AladdinOpenAPIHandler();
    api.parseXml(url);
    for(Item item : api.items) {
      item.extractDescription(item.getDescription());
      return Book.builder()
          .title(item.getTitle())
          .author(item.getAuthor())
          .pubDate(item.getPubDate())
          .description(item.getDescription())
          .isbn13(item.getIdbn13())
          .totalPage(Integer.parseInt(item.getItemPage()))
          .build();
    }
    return null;
  }
  @Data
  public static class Item {
    public String title = "";
    public String author = "";
    public String pubDate = "";
    public String description = "";
    public String idbn13 = "";
    public String itemPage = "";
    public String bestRank;
    public void extractDescription(String description) {
      this.description = description.substring(description.indexOf(">") + 1);
    }
  }

  public static class AladdinOpenAPIHandler extends DefaultHandler {
    public List<Item> items;
    private Item currentItem;
    private boolean inItemElement = false;
    private String tempValue;
    public AladdinOpenAPIHandler() {
      this.items = new ArrayList<>();
    }
    public void startElement(String namespace, String localName,  String qName, Attributes attributes) {
      if (localName.equals("item")) {
        currentItem = new Item();
        inItemElement = true;
      } else if (localName.equals("title")) {
        tempValue = "";
      } else if (localName.equals("author")) {
        tempValue = "";
      } else if (localName.equals("pubDate")) {
        tempValue = "";
      } else if (localName.equals("description")) {
        tempValue = "";
      } else if (localName.equals("isbn13")) {
        tempValue = "";
      } else if (localName.equals("itemPage")) {
        tempValue = "";
      } else if (localName.equals("bestRank")) {
        tempValue = "";
      }
    }
    public void characters(char[] ch, int start, int length) throws SAXException {
      tempValue = tempValue + new String(ch, start, length);
    }
    public void endElement(String namespaceURI, String localName, String qName) {
      if (inItemElement) {
        if (localName.equals("item")) {
          items.add(currentItem);
          currentItem = null;
          inItemElement = false;
        } else if (localName.equals("title")) {
          currentItem.title = tempValue;
        } else if (localName.equals("author")) {
          currentItem.author = tempValue;
        } else if (localName.equals("pubDate")) {
          currentItem.pubDate = tempValue;
        } else if (localName.equals("description")) {
          currentItem.description = tempValue;
        } else if (localName.equals("isbn13")) {
          currentItem.idbn13 = tempValue;
        } else if (localName.equals("itemPage")) {
          currentItem.itemPage = tempValue;
        } else if (localName.equals("bestRank")) {
          currentItem.bestRank = tempValue;
        }
      }
    }

    public void parseXml(String xmlUrl) throws Exception {
      SAXParserFactory spf = SAXParserFactory.newInstance();
      SAXParser sp = spf.newSAXParser();
      ParserAdapter pa = new ParserAdapter(sp.getParser());
      pa.setContentHandler(this);
      pa.parse(xmlUrl);
    }
  }
}


