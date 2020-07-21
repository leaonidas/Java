package utils;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

import simulation.AGraph;
import simulation.Simulator;


/**
 * @author Group 20
 * Utils class opens and parses XML document
 */
public class Utils {

	/**
	 * @param name document name
	 * @return doc ready to be parsed
	 */
	public Document openDoc(String name) {
		
		File xmlFile=new File(name);
		
		try {

			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=factory.newDocumentBuilder();
			Document doc =builder.parse(xmlFile);
			return doc;
			
		} catch(Exception e) {
			System.out.println("File not found");
		}
		return null;
	}
	
	/**
	 * @param doc XML document for parsing
	 * @param sim current simulation
	 * Parses XML document and stores info in variables
	 */
	public void getInfo(Document doc, Simulator sim) {
		

		Element root = doc.getDocumentElement();
		
		sim.setFinalinst(Double.parseDouble(root.getAttribute("finalinst")));
		sim.setAntColSize(Integer.parseInt(root.getAttribute("antcolsize")));
		sim.setPlevel(Double.parseDouble(root.getAttribute("plevel")));
		
		
		NodeList nList=doc.getElementsByTagName("graph");
		Element a = (Element) nList.item(0);
		sim.setNbnodes(Integer.parseInt(a.getAttribute("nbnodes")));
		sim.setNest(Integer.parseInt(a.getAttribute("nestnode")));
		
		nList=doc.getElementsByTagName("node");
		sim.graph=new AGraph(sim.getNbnodes(), sim.getNest());
		sim.graph.setGraph();
		
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node n=nList.item(i);
			if(n.getNodeType()==Node.ELEMENT_NODE) {
				Element node = (Element) n;
				int nodeix=Integer.parseInt(node.getAttribute("nodeidx"));
				NodeList sonsList = node.getChildNodes();
				for(int j=0; j<sonsList.getLength();j++) {
					Node s =sonsList.item(j);
					if(s.getNodeType()==Node.ELEMENT_NODE) {
						Element weight = (Element) s;
						int targetnode=Integer.parseInt(weight.getAttribute("targetnode"));
						sim.graph.setElem(Integer.parseInt(weight.getTextContent()), nodeix-1, targetnode-1);
						sim.graph.setElem(Integer.parseInt(weight.getTextContent()), targetnode-1, nodeix-1);
					}
				}
			}
		}
		
		nList=doc.getElementsByTagName("move");
		a = (Element) nList.item(0);
		sim.setAlpha(Double.parseDouble(a.getAttribute("alpha")));
		sim.setBeta(Double.parseDouble(a.getAttribute("beta")));
		sim.setDelta(Double.parseDouble(a.getAttribute("delta")));
		
		nList=doc.getElementsByTagName("evaporation");
		a = (Element) nList.item(0);
		sim.setEta(Double.parseDouble(a.getAttribute("eta")));
		sim.setRho(Double.parseDouble(a.getAttribute("rho")));
			
	}
}
