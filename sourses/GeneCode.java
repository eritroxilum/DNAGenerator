import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

import javax.lang.model.element.Element;

import org.omg.CORBA.CharSeqHolder;


public class GeneCode implements Serializable {
	
	public final static String FILEEXTENTION = ".genecode";
	
	String name;
	private char[] alphabet;
	private int wordLength;
	
	public Hashtable<String, String> codons;
	
	public GeneCode(){
		this("Default","ACGT",3);
	}
	
	public GeneCode(String name, String alph, int l){
		codons = new Hashtable<String, String>();
		this.alphabet = alph.toCharArray();
		alph="";
		for(char c: alphabet){
			if(!alph.contains(c + ""))
				alph+=c;
		}
		this.alphabet = alph.toCharArray();
		
		this.name = name;
		wordLength = l;
		init("",0);
	}
	
	private void init(String prefix, int position ){
		if(wordLength>position){
			for(int i=0;i<alphabet.length;i++){
				init(prefix + alphabet[i],position+1);
			}
		}
		else{
			codons.put(prefix, "*");
		}
	}
	
	public boolean setCodon(String name, String value){
		
		if(codons.containsKey(name)){
			codons.put(name, value);
			return true;
		}
		return false;
	}
	
	boolean SaveAs(String FileName) throws FileNotFoundException{
		
		 XMLEncoder e = new XMLEncoder(
                 new BufferedOutputStream(
                     new FileOutputStream(FileName)));
		 e.writeObject(this);
		 e.close();
		return false;
	}
	
	static GeneCode ReadCode(String fileName) throws FileNotFoundException{
		 XMLDecoder d = null;
	
			d = new XMLDecoder(
			         new BufferedInputStream(
			             new FileInputStream(fileName)));
	
		 GeneCode result = (GeneCode) d.readObject();
		 d.close();
		return result;
	}
	
	public String getValue(String codon){
		
		return codons.get(codon);
	}
	
	public int getSize(){
		return codons.size();
	}
	public Enumeration<String> getElements(){
		return codons.keys();
	}
	
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append( "Name:" + name + "\n");
		result.append("Alphabet: " + new String (alphabet) + "\n");
		result.append("Word length: " + wordLength + "\n");
		Enumeration<String> e = codons.keys();
		while(e.hasMoreElements()){
			String codon = e.nextElement();
			result.append( codon + " -> " + codons.get(codon) + "\n");
		}
		return result.toString();
	}
	
	public GeneCode clone(){
		GeneCode gc = new GeneCode("","A",0);
		gc.alphabet = alphabet.clone();
		gc.codons = (Hashtable<String, String>) codons.clone();
		gc.name = name;
		gc.wordLength = wordLength;
		return gc;
	}

	public String getName() {
		return name;
	}
	
	public char[] getAlphabet(){
		return alphabet;
	}

	public void setName(String text) {
		name = text;
		// TODO Auto-generated method stub
		
	}

	public int getCodonLength() {
		return wordLength;
		
	}
	
}
