package com.imooc.dom4jtest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class DOM4JTest {

	/**
	 * 解析xml
	 * @param args
	 */
	private void parseXML() {
		// 解析book.xml文件
		// 创建SAXReader的对象reader
		SAXReader reader = new SAXReader();
		try {
			// 通过reader对象的read方法加载books.xml文件，获取document对象
			Document document = reader.read(new File("src/res/books.xml"));
			// 通过document对象获取根节点bookstore
			Element bookstore = document.getRootElement();
			// 通过element对象的elementIterator方法获取迭代器Iterator
			Iterator it = bookstore.elementIterator();
			// 遍历迭代器获取根节点中的信息（books）
			while (it.hasNext()) {
				System.out.println("=====开始遍历某本书=====");
				Element book = (Element) it.next();
				// 获取book的属性名及属性值
				List<Attribute> bookAttrs = book.attributes();
				for (Attribute attr : bookAttrs) {
					System.out.println("属性名：" + attr.getName() + "---属性值：" + attr.getValue());
				}
				// 获取book子节点的节点名及节点值
				// 通过节点element对象的elementIterator方法获取迭代器Iterator
				Iterator itt = book.elementIterator();
				// 遍历迭代器获取子节点中的信息
				while (itt.hasNext()) {
					Element bookChild = (Element) itt.next();
					// 获取bookChild的节点名及节点值
					System.out.println("节点名：" + bookChild.getName() + "---节点值：" + bookChild.getStringValue());
				}
				System.out.println("=====结束遍历某本书=====");
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 生成xml
	 * @param args
	 */
	private void createXML() {
		// 1.创建document对象，代表整个xml文档
		Document document = DocumentHelper.createDocument();
		// 2. 创建根节点rss
		Element rss = document.addElement("rss");
		// 3.向根节点中添加version属性
		rss.addAttribute("version", "2.0");
		// 4.生成子节点及子节点内容
		Element channel = rss.addElement("channel");
		Element title = channel.addElement("title");
		title.setText("国内最新新闻");
		Element content = channel.addElement("content");
		content.setText("<![马云到UON演讲。]>"); // 内容中有转义字符"<" 和  ">"，如果想要正常显示，则需要在下面设置是否转义writer.setEscapeText(false);
		// 5.设置生成漂亮的xml格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		// 设置encoding
		format.setEncoding("GBK");
		// 6.生成xml文件
		File file = new File("rssnews.xml");
		XMLWriter writer;
		try {
			writer = new XMLWriter(new FileOutputStream(file),format); // 将file转换成输出流
			// 设置是否转义，默认值为true，代表转义
			writer.setEscapeText(false);
			writer.write(document);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		new DOM4JTest().createXML();
	}

}
