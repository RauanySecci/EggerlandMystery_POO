package main;

import javax.swing.JFrame;

public class Main {
	 public static void main(String[] args) {
		 JFrame window = new JFrame();
	     window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //fechar a ela com o x
	     window.setResizable(false); //nao é possivel redimensionar
	     window.setTitle(" Eggerland mystery"); //titulo
	        
	     Painel painel = new Painel();
	     window.add(painel);
	     window.pack();
	        
	     window.setLocationRelativeTo(null); //centro da tela
	     window.setVisible(true);
	     
	     painel.setupGame();   
	     painel.startGameThread();
	    }
}
