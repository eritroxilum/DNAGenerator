import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;



public class GeneticQuiz extends JFrame{
	
	
	private DnaGenerator dnaGen = new DnaGenerator();
	JTextPane codeText;
	JLabel status;
	String rightTranslation="";
	JComboBox<Integer> l;
	JComboBox<String> direrction;
	JCheckBox reverse;
	JTextPane translText;
	Integer[] lengthList = new Integer []{5,10,15,20,25,30};
	String CBListe[] = {"DNA -> cDNA", "DNA -> mRNA",  "DNA -> Protein"};
	
	public GeneticQuiz() {
		
		setSize(500, 400);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,5));
		add(panel);
		
		
		JPanel inOutFields = new JPanel(new GridLayout(2,2,10,10));
		JLabel code = new JLabel("Code:");
		JLabel translation = new JLabel("Translation");
		status = new JLabel("            ");
		
		codeText = new JTextPane();
		translText = new JTextPane();
		
		inOutFields.add(code);
		inOutFields.add(codeText);
		inOutFields.add(translation);
		inOutFields.add(translText);
		
		
		
		
		JPanel options = new JPanel(new FlowLayout());
		
		reverse = new JCheckBox("Reverse");
		
		direrction = new JComboBox<String>(CBListe);
		JLabel LabelLength = new JLabel("Length:");
		l = new JComboBox<Integer>(lengthList);
		JLabel LabelTrDirection = new JLabel("Translation direction:");
		
		JButton createCode = new JButton("Create");
		createCode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getStringToTranslate();

			}
		});
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				evaluate();

			}
		});
		
		options.add(reverse);
		options.add(LabelTrDirection);
		options.add(direrction);
		options.add(LabelLength);
		options.add(l);
		options.add(createCode);
		options.add(submitButton);
		
		panel.add(inOutFields);
		panel.add(options);
		panel.add(status);
	}

	protected void getStringToTranslate() {
		// TODO Auto-generated method stub
		
		codeText.setText(dnaGen.generateDNA(lengthList[l.getSelectedIndex()], reverse.isSelected(), direrction.getSelectedIndex()+1));
	}
	
	protected void evaluate() {
		// TODO Auto-generated method stub
		if(dnaGen.checkSequence(translText.getText())){
			status.setText("Gratulation");
		}
		else{
			status.setText("FALSCH");
			translText.setText("PLATZHALTER");
		}
	}
}
