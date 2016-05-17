Feature: Show patient details

In order to check the physical condition of a patient
As a caretaker
I want to consult his/her personal details

	Scenario: the personal details of a registered patient are given
		Given a patient with the social security number 93051822361, gender male and birthdate 1993-05-18
		And on 2000-04-10 the patient had a length of 180 cm and a weight of 75000 gr
		And the patient is registered
		When I ask for the details of the patient using his social security number
		Then the personal details social security number, gender and birthdate are given
		And the examination details length, weight and last examination date are given
		And the calculated bmi 23.15 is given

		
