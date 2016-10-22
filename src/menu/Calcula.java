package menu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Calcula {
	
	private final String LINHA = System.lineSeparator();
	private List<Double> notas_disc;
	private List<Integer> creditos;
	private String caminho;
	
	public Calcula(String caminho){
		this.caminho = caminho;	
	}
	
	public double calculo(List<Double> notas_disc, List<Integer> creditos){
		double soma_total = 0.0;
		int soma_creditos = 0;
		for(int i = 0; i < notas_disc.size(); i++){
			soma_total += (notas_disc.get(i)) * (creditos.get(i));
			soma_creditos += (creditos.get(i));
		}
					
		return soma_total / soma_creditos;
	}
		
	public String leArquivo() throws IOException {
		notas_disc = new ArrayList<Double>();
		creditos = new ArrayList<Integer>();
		BufferedReader in = new BufferedReader ( new FileReader (this.caminho));
		String linha;

		while ( (linha = in.readLine()) != null){
			String nota[] = linha.split(",");
			int ultimoIndice = nota.length - 1;
			int penultimoIndice = nota.length - 2;
			double nota_disc = Double.parseDouble(nota[penultimoIndice]);
			double creditos = Double.parseDouble(nota[ultimoIndice]);
			this.notas_disc.add(nota_disc);
			this.creditos.add((int) creditos);
			
		}
		in.close();
		return toString();
	}
	
	public int totalCreditos(){
		int soma = 0;
		for(int i = 0; i < creditos.size(); i++){
			soma += creditos.get(i);
		}
		return soma;
	}
	
	public int horas(){
		int soma = 0;
		for(int i = 0; i < creditos.size(); i++){
			soma += creditos.get(i) * 15;
		}
		return soma;
	}
	
	public String toString(){
		String cra = String.format("CRA: %.2f", calculo(this.getNotas_disc(), this.getCreditos()));
		return "Disciplinas: " + getCreditos().size() + LINHA
			+ "Total de creditos: " + totalCreditos() + LINHA
			+ "Total de horas: " + horas() + LINHA
			+ cra + LINHA;
	}

	public List<Double> getNotas_disc() {
		return notas_disc;
	}

	public void setNotas_disc(List<Double> notas_disc) {
		this.notas_disc = notas_disc;
	}

	public List<Integer> getCreditos() {
		return creditos;
	}

	public void setCreditos(List<Integer> creditos) {
		this.creditos = creditos;
	}
	
	
}