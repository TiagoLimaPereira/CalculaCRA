package menu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Calcula {

	private final String LINHA = System.lineSeparator();
	private List<Double> notas_disc;
	private List<Integer> creditos;
	private String caminho;

	public Calcula(String caminho) {
		this.caminho = caminho;
	}

	public double calculo(List<Double> notas_disc, List<Integer> creditos) {
		double soma_total = 0.0;
		int soma_creditos = 0;
		for (int i = 0; i < notas_disc.size(); i++) {
			soma_total += (notas_disc.get(i)) * (creditos.get(i));
			soma_creditos += (creditos.get(i));
		}
		if (soma_creditos > 0) {
			return soma_total / soma_creditos;

		} else {
			return 0;
		}
	}

	// os arrayLists sao inicializados aqui pois se forem inicializados no construtor,
	// a cada solicitacao de calcular cra vai acumular as disciplinas e as notas
	// gerando um calculo errado
	public String leArquivo() throws Exception, IOException {
		notas_disc = new ArrayList<Double>();
		creditos = new ArrayList<Integer>();
		BufferedReader in = new BufferedReader(new FileReader(this.caminho));
		String linha;

		while ((linha = in.readLine()) != null) {
			String nota[] = linha.split(",");
			if ((nota.length > 2) && !nota[nota.length - 1].isEmpty() && !nota[nota.length - 2].isEmpty()) {
				int ultimoIndice = nota.length - 1;
				int penultimoIndice = nota.length - 2;
				try {
					double nota_disc = Double.parseDouble(nota[penultimoIndice]);
					double creditos = Double.parseDouble(nota[ultimoIndice]);
					this.notas_disc.add(nota_disc);
					this.creditos.add((int) creditos);
				} catch (Exception e) {
					throw new Exception("Formato da(s) linha(s) invalido." + LINHA + "Inserir:'nome, nota, creditos' em cada linha.");
				}

			}

		}
		in.close();
		if (this.notas_disc.size() == 0 || this.notas_disc.size() == 0) {
			throw new Exception("Arquivo vazio ou formato da(s) linha(s) invalido."
					+ LINHA + "Inserir: 'nome, nota, creditos' em cada linha.");
		}
		return toString();
	}

	public int totalCreditos() {
		int soma = 0;
		for (int i = 0; i < creditos.size(); i++) {
			soma += creditos.get(i);
		}
		return soma;
	}

	public int horas() {
		int soma = 0;
		for (int i = 0; i < creditos.size(); i++) {
			soma += creditos.get(i) * 15;
		}
		return soma;
	}

	public String toString() {
		
		String cra = String.format("CRA: %.2f", calculo(this.getNotas_disc(), this.getCreditos()));
		String disciplinas = "Disciplinas: " + getCreditos().size();
		String totalCreditos = "Total de creditos: " + totalCreditos();
		String totalHoras = "Total de horas: " + horas();
		
		return disciplinas + LINHA + totalCreditos + LINHA
				+ totalHoras + LINHA + cra + LINHA;
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
