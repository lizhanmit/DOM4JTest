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
	 * ����xml
	 * @param args
	 */
	private void parseXML() {
		// ����book.xml�ļ�
		// ����SAXReader�Ķ���reader
		SAXReader reader = new SAXReader();
		try {
			// ͨ��reader�����read��������books.xml�ļ�����ȡdocument����
			Document document = reader.read(new File("src/res/books.xml"));
			// ͨ��document�����ȡ���ڵ�bookstore
			Element bookstore = document.getRootElement();
			// ͨ��element�����elementIterator������ȡ������Iterator
			Iterator it = bookstore.elementIterator();
			// ������������ȡ���ڵ��е���Ϣ��books��
			while (it.hasNext()) {
				System.out.println("=====��ʼ����ĳ����=====");
				Element book = (Element) it.next();
				// ��ȡbook��������������ֵ
				List<Attribute> bookAttrs = book.attributes();
				for (Attribute attr : bookAttrs) {
					System.out.println("��������" + attr.getName() + "---����ֵ��" + attr.getValue());
				}
				// ��ȡbook�ӽڵ�Ľڵ������ڵ�ֵ
				// ͨ���ڵ�element�����elementIterator������ȡ������Iterator
				Iterator itt = book.elementIterator();
				// ������������ȡ�ӽڵ��е���Ϣ
				while (itt.hasNext()) {
					Element bookChild = (Element) itt.next();
					// ��ȡbookChild�Ľڵ������ڵ�ֵ
					System.out.println("�ڵ�����" + bookChild.getName() + "---�ڵ�ֵ��" + bookChild.getStringValue());
				}
				System.out.println("=====��������ĳ����=====");
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * ����xml
	 * @param args
	 */
	private void createXML() {
		// 1.����document���󣬴�������xml�ĵ�
		Document document = DocumentHelper.createDocument();
		// 2. �������ڵ�rss
		Element rss = document.addElement("rss");
		// 3.����ڵ������version����
		rss.addAttribute("version", "2.0");
		// 4.�����ӽڵ㼰�ӽڵ�����
		Element channel = rss.addElement("channel");
		Element title = channel.addElement("title");
		title.setText("������������");
		Element content = channel.addElement("content");
		content.setText("<![���Ƶ�UON�ݽ���]>"); // ��������ת���ַ�"<" ��  ">"�������Ҫ������ʾ������Ҫ�����������Ƿ�ת��writer.setEscapeText(false);
		// 5.��������Ư����xml��ʽ
		OutputFormat format = OutputFormat.createPrettyPrint();
		// ����encoding
		format.setEncoding("GBK");
		// 6.����xml�ļ�
		File file = new File("rssnews.xml");
		XMLWriter writer;
		try {
			writer = new XMLWriter(new FileOutputStream(file),format); // ��fileת���������
			// �����Ƿ�ת�壬Ĭ��ֵΪtrue������ת��
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
