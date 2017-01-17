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

}
