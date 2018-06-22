import java.util.ArrayList;
import java.util.List;


class Trainer {
	private List<Person> trainData;
	private int countConservative = 0;
	private int countLiberal = 0;
	private int countConservativeAndFemale = 0;
	private int countConservativeAndMale = 0;
	private int countConservativeAndBarista = 0;
	private int countConservativeAndCook = 0;
	private int countConservativeAndAnalyst = 0;
	private int countConservativeAndDoctor = 0;
	private int countConservativeAndHigh = 0;
	private int countConservativeAndMedium = 0;
	private int countConservativeAndLow = 0;
	private int countLiberalAndLow = 0;
	private int countLiberalAndMedium = 0;
	private int countLiberalAndHigh = 0;
	private int countLiberalAndDoctor = 0;
	private int countLiberalAndAnalyst = 0;
	private int countLiberalAndCook = 0;
	private int countLiberalAndBarista = 0;
	private int countLiberalAndMale = 0;
	private int countLiberalAndFemale = 0;

	// probabilities of conservative

	static  double probConservative=0;

	static double probConservativeBarista=0;
	static double probConservativeAnalyst=0;
	static double probConservativeCook=0;
	static double probConservativeDoctor=0;

	static double probConservativeMale=0;
	static double probConservativeFemale=0;

	static double probConservativeHigh=0;
	static double probConservativeLow=0;
	static double probConservativeMedium=0;

	// probabilities of liberal
	static double probLiberal;

	static double probLiberalBarista;
	static double probLiberalAnalyst;
	static double probLiberalCook;
	static double probLiberalDoctor;

	static double probLiberalMale;
	static double probLiberalFemale;

	static double probLiberalHigh;
	static double probLiberalLow;
	static double probLiberalMedium;

	Trainer(List<Person> dataInput) {
		trainData = dataInput;
		computePartialProbabilities();
	}

	private void computePartialProbabilities() {
		for (Person o : trainData) {
			switch (o.getPoliticalAff()) {
				case "conservative":
					countConservative++;
					switch (o.getOccupation()) {
						case "barista":
							countConservativeAndBarista++;
							break;
						case "analyst":
							countConservativeAndAnalyst++;
							break;
						case "cook":
							countConservativeAndCook++;
							break;
						case "doctor":
							countConservativeAndDoctor++;
							break;
					}

					if (o.getGender().equals("female")) {
						countConservativeAndFemale++;
					} else if (o.getGender().equals("male")) {
						countConservativeAndMale++;
					}

					switch (o.getIncome()) {
						case "high":
							countConservativeAndHigh++;
							break;
						case "medium":
							countConservativeAndMedium++;
							break;
						case "low":
							countConservativeAndLow++;
							break;
					}

					break;
				case "liberal":
					countLiberal++;
					switch (o.getOccupation()) {
						case "barista":
							countLiberalAndBarista++;
							break;
						case "analyst":
							countLiberalAndAnalyst++;
							break;
						case "cook":
							countLiberalAndCook++;
							break;
						case "doctor":
							countLiberalAndDoctor++;
							break;
					}

					if (o.getGender().equals("female")) {
						countLiberalAndFemale++;
					} else if (o.getGender().equals("male")) {
						countLiberalAndMale++;
					}

					switch (o.getIncome()) {
						case "high":
							countLiberalAndHigh++;
							break;
						case "medium":
							countLiberalAndMedium++;
							break;
						case "low":
							countLiberalAndLow++;
							break;
					}

					break;
				default:

					break;
			}

		}
		probConservative = countConservative / (double) trainData.size();

		probConservativeBarista = countConservativeAndBarista / (double) trainData.size();
		probConservativeAnalyst = countConservativeAndAnalyst / (double) trainData.size();
		probConservativeCook = countConservativeAndCook / (double) trainData.size();
		probConservativeDoctor = countConservativeAndDoctor / (double) trainData.size();

		probConservativeFemale = countConservativeAndFemale / (double) trainData.size();
		probConservativeMale = countConservativeAndMale / (double) trainData.size();

		probConservativeHigh = countConservativeAndHigh / (double) trainData.size();
		probConservativeMedium = countConservativeAndMedium / (double) trainData.size();
		probConservativeLow = countConservativeAndLow / (double) trainData.size();

		probLiberal = countLiberal / (double) trainData.size();

		probLiberalBarista = countLiberalAndBarista / (double) trainData.size();
		probLiberalAnalyst = countLiberalAndAnalyst / (double) trainData.size();
		probLiberalCook = countLiberalAndCook / (double) trainData.size();
		probLiberalDoctor = countLiberalAndDoctor / (double) trainData.size();

		probLiberalFemale = countLiberalAndFemale / (double) trainData.size();
		probLiberalMale = countLiberalAndMale / (double) trainData.size();

		probLiberalHigh = countLiberalAndHigh / (double) trainData.size();
		probLiberalMedium = countLiberalAndMedium / (double) trainData.size();
		probLiberalLow = countLiberalAndLow / (double) trainData.size();

	}

}
