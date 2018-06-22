import java.util.List;



class Tester {

	Tester(List<Person> data) {
		int accuracy = 0;
		for (Person obs : data) {
			NBClassfier nb = new NBClassfier();
			if (nb.classify(obs).equals(obs.getPoliticalAff())) {
				accuracy++;
			}
		}
		Program.results.setText("\nAccuracy of test set is "+ accuracy +" out of a test dataset size of "+data.size());

	}

}
