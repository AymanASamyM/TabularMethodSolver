package Try;

import java.util.LinkedList;

import firstStep.GenerateFistStep;
import firstStep.Implicant;
import firstStep.Inputs;
import secondStep.PrimeImplicants;

public class Try {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GenerateFistStep tabular = new GenerateFistStep();
		tabular.generateTabular("0 1 2 3 6 7 14 15", "2 4");
		System.out.println(tabular.printCols() + "\n");
		System.out.println(tabular.printTable(tabular.getImplicants()));

		LinkedList<Implicant> impls = tabular.getImplicants();

		for (int i = 0; i < impls.size(); i++) {
			System.out.print(impls.get(i).num + " ");
			for (int j = 0; j < impls.get(i).difs.size(); j++) {
				try {
					System.out.print(impls.get(i).difs.get(j) + " ");

				} catch (Exception e) {

				}
			}
			System.out.print("==> it covers : ");
			for (int k = 0; k < impls.get(i).coveredMinterms.size(); k++) {
				System.out.print(impls.get(i).coveredMinterms.get(k) + " ");
			}
			System.out.println();
		}
		for (int i = 0; i < tabular.getMinterms().size(); i++) {
			System.out.print(tabular.getMinterms().get(i) + " ");

		}
		System.out.println();

		System.out.println("\n");
		PrimeImplicants solver = new PrimeImplicants(tabular.getMinterms(), impls);
		for (int i = 0; i < solver.getRigthCombinations().size(); i++) {
			try {
				for (int j = 0; j < solver.getRigthCombinations().get(i).myCombination.size(); j++) {
					System.out.print(solver.getRigthCombinations().get(i).myCombination.get(j).num + " ");
					for (int k = 0; k < impls.get(j).difs.size(); k++) {
						try {
							System.out
									.print(solver.getRigthCombinations().get(i).myCombination.get(j).difs.get(k) + " ");

						} catch (Exception e) {

						}
					}
					System.out.println();
				}
			} catch (Exception e) {

			}
			for (int x = 0; x < solver.getEssentials().size(); x++) {
				System.out.print(solver.getEssentials().get(x).num + " ");
				for (int k = 0; k < solver.getEssentials().get(x).difs.size(); k++) {
					try {
						System.out.print(solver.getEssentials().get(x).difs.get(k) + " ");

					} catch (Exception e) {

					}
				}
				System.out.println();
			}
			System.out.println();
		}

		System.out.println("\nminmum solutions are\n");
		for (int x = 0; x < solver.getMinimumSolutions().size(); x++) {
			for (int k = 0; k < solver.getMinimumSolutions().get(x).myCombination.size(); k++) {
				System.out.print(solver.getMinimumSolutions().get(x).myCombination.get(k).num + " ");
				for (int j = 0; j < solver.getMinimumSolutions().get(x).myCombination.get(k).difs.size(); j++) {
					try {
						System.out.print(solver.getMinimumSolutions().get(x).myCombination.get(k).difs.get(j) + " ");

					} catch (Exception e) {

					}
				}

				System.out.println();
			}
			System.out.println();
		}
		System.out.println("minmum solutions are :\n");
		solver.printMinmumSolution(solver.getMinimumSolutions());
		for (int i = 0; i < solver.solutions.size(); i++) {
			System.out.println(solver.solutions.get(i));
		}
		 
	}

}
