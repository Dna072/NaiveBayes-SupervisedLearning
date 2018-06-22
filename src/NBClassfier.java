
class NBClassfier {
	
	NBClassfier(){
		
	}
	
	
	NBClassfier(Person person) {
		classify(person);
	}

	String classify(Person person) {
		double partialProbConservative = Trainer.probConservative;
		double partialProbLiberal = Trainer.probLiberal;
		double totalProbConservative=0;
		double totalProbLiberal=0;

		switch (person.getOccupation()) {
			case "barista":
				partialProbConservative *= Trainer.probConservativeBarista;
				partialProbLiberal *= Trainer.probLiberalBarista;
				break;
			case "analyst":
				partialProbConservative *= Trainer.probConservativeAnalyst;
				partialProbLiberal *= Trainer.probLiberalAnalyst;
				break;
			case "cook":
				partialProbConservative *= Trainer.probConservativeCook;
				partialProbLiberal *= Trainer.probLiberalCook;
				break;
			case "doctor":
				partialProbConservative *= Trainer.probConservativeDoctor;
				partialProbLiberal *= Trainer.probLiberalDoctor;
				break;
		}

		if (person.getGender().equals("female")) {
			partialProbConservative *= Trainer.probConservativeFemale;
			partialProbLiberal *= Trainer.probLiberalFemale;
		} else if (person.getGender().equals("male")) {
			partialProbConservative *= Trainer.probConservativeMale;
			partialProbLiberal *= Trainer.probLiberalMale;
		}

		switch (person.getIncome()) {
			case "high":
				partialProbConservative *= Trainer.probConservativeHigh;
				partialProbLiberal *= Trainer.probLiberalHigh;
				break;
			case "medium":
				partialProbConservative *= Trainer.probConservativeMedium;
				partialProbLiberal *= Trainer.probLiberalMedium;
				break;
			case "low":
				partialProbConservative *= Trainer.probConservativeLow;
				partialProbLiberal *= Trainer.probLiberalLow;
				break;
		}

		totalProbConservative = partialProbConservative / (partialProbConservative + partialProbLiberal);
		
		totalProbLiberal = partialProbLiberal / (partialProbLiberal + partialProbConservative);
		
		if (totalProbConservative > totalProbLiberal)
			return "conservative";
		else
			return "liberal";

	}

}
