/** Autoren: Abel Hodelin Hernandez
	Timur Horn*/
	/* */

import java.util.*;

public class DnaGenerator
{
	private Random random = new Random();
	public String dnaGeneral;
	public String cdnaGeneral;
	public String rnaGeneral;
	public String proteinGeneral;
	private boolean senseGeneral;
	private int seqTypGeneral;

	/*Generiert die DNAsequenz sowohl ruckwear als auch forwear*/
	public String generateDNA(int seqLeng ,boolean invert, int typSeqToCheck)
	{
		String dnaSequenz = "";
		seqTypGeneral = typSeqToCheck;
		senseGeneral = invert;
		
		if (typSeqToCheck == 3)
			seqLeng = seqLeng * 3;
		
		for(int i = 0; i < seqLeng; i ++)
		{
			int nucleotideRandomizer = 1 + random.nextInt(4);
			String nucleotide = "";
			switch(nucleotideRandomizer)
			{
				case 1: nucleotide = "A";
						break;
				case 2: nucleotide = "C";
						break;
				case 3: nucleotide = "G";
						break;
				case 4: nucleotide = "T";
						break;
			}
			dnaSequenz += nucleotide;
		}
		
		dnaGeneral = dnaSequenz;
		return dnaGeneral;
	}
	
	private String invertSequence(String dna)
	{
		dna = new StringBuffer(dna).reverse().toString();
		return dna;
	}
	
	// DNA to cDNA
	private String dnaToCDNA()
	{
		String cDNA = "";
		
		if(!senseGeneral)
			dnaGeneral = invertSequence(dnaGeneral);

			for(char c : dnaGeneral.toCharArray())
			{
				if(c == 'A')
					cDNA += "T";
				if(c == 'C')
					cDNA += "G";
				if(c == 'G')
					cDNA += "C";
				if(c == 'T')
					cDNA += "A";
			}
			cdnaGeneral = cDNA;
		return cdnaGeneral;
	}
	
	//DNA to mRNA
	private String dnaToMRNA()
	{
		String mRNA = "";
		
		if(!senseGeneral)
			dnaGeneral = invertSequence(dnaGeneral);

		for(char c : dnaGeneral.toCharArray())
		{
			if(c == 'A')
				mRNA += "U";
			if(c == 'C')
				mRNA += "G";
			if(c == 'G')
				mRNA += "C";
			if(c == 'T')
				mRNA += "A";
		}
		rnaGeneral = mRNA;
		return rnaGeneral;
	}
	
	//RNA to protein
	private String dnaToProtein(int code)
	{
		String protein = "";
		String codon = "";
		String mRNA = dnaToMRNA();
		GeneticCode geneticCode = new GeneticCode();
		for(char c : mRNA.toCharArray())
		{
			codon += c;
			
			if(codon.length() == 3)
			{
				protein += geneticCode.RNAToProtein(codon, code);
				
				codon = "";
			}	
		}
		proteinGeneral = protein;
		return proteinGeneral;
	}
	
	/*Analiziert das Ergebnis*/
	public boolean checkSequence(String querySequence)
	{
		String generatedSequence = "";
		switch(seqTypGeneral)
		{
			case 1: generatedSequence = dnaToCDNA();
					break;
			case 2: generatedSequence = dnaToMRNA();
					break;
			case 3: generatedSequence = dnaToProtein(seqTypGeneral);
					break;
		}
		if(!generatedSequence.equalsIgnoreCase(querySequence))
		{
			return false;
		}
		return true;
	}
}