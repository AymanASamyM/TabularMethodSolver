package firstStep;

import java.util.LinkedList;

import interFaces.FirstStepInterface;

public class GenerateFistStep implements FirstStepInterface {
	public LinkedList<Column> columns = new LinkedList<Column>();
	private LinkedList<Implicant> primeImplicants = new LinkedList<Implicant>();

	/**
	 * merge possible groups in each column
	 * 
	 * @param minterms
	 * @param dontcares
	 */
	@Override
	public void generateTabular(String minterms, String dontcares) {
		Inputs.setInputs(minterms, dontcares);
		columns.add(Column.generateFirstColumn(Inputs.implicants));
		for (int i = 0; i < Inputs.variblesNum; i++) {
			Column nextCol = columns.get(i).generateNextCol();
			columns.add(nextCol);
		}
	}

	/**
	 * 
	 * @return prime Implicants
	 */
	@Override
	public LinkedList<Implicant> getImplicants() {
		for (int i = 0; i < columns.size(); i++) {
			LinkedList<Implicant> tmp = null;
			for (int j = 0; j < columns.get(i).myColumn.size(); j++) {
				tmp = columns.get(i).myColumn.get(j).getPrimeImplicants();
				if (tmp != null) {
					for (int k = 0; k < tmp.size(); k++) {
						if (!Inputs.minTerms.contains(tmp.get(k).num) && tmp.get(k).difs.size() == 0) {
							continue;
						}
						primeImplicants.add(tmp.get(k));
					}
				}
			}
		}
		return primeImplicants;
	}

	/**
	 * 
	 * @return Minterms
	 */
	@Override
	public LinkedList<Integer> getMinterms() {
		return Inputs.minTerms;
	}

	public String printCols() {
		StringBuilder res = new StringBuilder();
		
		for (int j = 0; j < columns.get(0).colConcat().size(); j++) {
			for (int i = 0; i < columns.size(); i++) {
				try {
					res.append(columns.get(i).colConcat().get(j).printImpl() + "| \t");
				} catch (Exception e) {
					
				}
			}
			res.append("\n");
		}
		return res.toString();
	}

	public String printTable(LinkedList<Implicant> primes) {
		char[][] tmp = new char[primes.size()][Inputs.minTerms.size()];
		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp[i].length; j++) {
				if (primes.get(i).coveredMinterms.contains(Inputs.minTerms.get(j))) {
					tmp[i][j] = '\u2716';
				} else {
					tmp[i][j] = ' ';
				}
			}
		}
		StringBuilder res = new StringBuilder();
		char[] variables = new char[Inputs.variblesNum];
 		for (int i = 0; i < variables.length; i++) {
			variables[i] = (char) ('A' + i);
		}
		res.append("Prime Implicants are : \n");
		for (int i = 0; i< primes.size(); i++) {
			res.append("P" + i + " = " + primes.get(i).printImplicant(variables) + "\n");
		}
		for (int k = 0; k < Inputs.minTerms.size() * 2; k++) {
			res.append("-----");
		}
		res.append("\n     ");
		for (int i = 0; i < Inputs.minTerms.size(); i++) {
			res.append("| " + Inputs.minTerms.get(i) + " |     ");
		}
		res.append("\n");
		for (int k = 0; k < Inputs.minTerms.size() * 2; k++) {
			res.append("-----");
		}
		res.append("\n");

		for (int i = 0; i < primes.size(); i++) {
			res.append("P" + i + "   ");
			for (int j = 0; j < Inputs.minTerms.size(); j++) {
				res.append("| " + tmp[i][j] + " |     ");
			}
			res.append("\n");
			for (int k = 0; k < Inputs.minTerms.size() * 2; k++) {
				res.append("-----");
			}
			res.append("\n");
		}
		return res.toString();
	}
}
