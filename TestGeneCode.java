import java.io.FileNotFoundException;

import javax.swing.JDialog;


public class TestGeneCode {

	public static void main(String[] str){
		
		
		GeneCode gc = null;  ;// = new GeneCode();
		
		gc = new GeneCode("Test","AB",4);
		System.out.println(gc.toString());
		
		gc.setCodon("BBBB", "Met");
		System.out.println(gc.toString());
		
		
		//Für DNACreator wichtige funktionen
		gc.getName(); // Sring Bezeichnung (zb Mitochondrium)
		gc.getCodonLength(); // int wortlänge, wie lang ein codon ist
		gc.getAlphabet(); //char[] die verfügbaren buchstaben. wichtig für die generierung von zufallstrings

		//Gencode aus einer Datei lesen
		try {
			gc = GeneCode.ReadCode("default.genecode");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//Dialog zum Konfigurieren einer Datei
		ConfigGeneCode dialog = new ConfigGeneCode();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		
		dialog.setGeneCode(gc);
		
		dialog.setModal(true);
		//dialog.setVisible(true);
		
		GeneticQuiz gq = new GeneticQuiz();
		gq.setVisible(true);

		try {
			gc = GeneCode.ReadCode("Default");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
	
		//System.out.println(gc.toString());
		
		
	}
}
