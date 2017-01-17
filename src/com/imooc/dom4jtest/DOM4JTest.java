package com.imooc.dom4jtest;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class DOM4JTest {

	public static void main(String[] args) {
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

}
