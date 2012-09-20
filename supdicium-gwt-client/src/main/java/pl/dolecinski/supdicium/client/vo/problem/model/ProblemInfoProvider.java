/**
 * Copyright 2011 Paweł Doleciński.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package pl.dolecinski.supdicium.client.vo.problem.model;

import java.util.List;

import pl.dolecinski.subdicium.common.vo.problem.ProblemInfo;

import com.google.gwt.user.client.Random;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;

/**
 * @author Paweł Doleciński
 * 
 */
public class ProblemInfoProvider {


	private static final String[] EXPERTS = { "Kasia,me,Mehmet",
			"me,Bolek,Lolek", "Wojtek,Maciek, me" };

	private static final String[] DESCS = {
			"Very huge problem and its short description.",
			"Another huge problem and its very short description." };

	private static final String[] STATES = { "New", "In progress", "Resolved" };

	/**
	 * The provider that holds the list of contacts in the database.
	 */
	private ListDataProvider<ProblemInfo> dataProvider = new ListDataProvider<ProblemInfo>();

	/**
	 * Construct a new contact database.
	 */
	public ProblemInfoProvider() {

		// Generate initial data.
		generateContacts(250);
	}

	/**
	 * Add a new contact.
	 * 
	 * @param contact
	 *            the contact to add.
	 */
	public void addContact(ProblemInfo contact) {
		List<ProblemInfo> contacts = dataProvider.getList();
		// Remove the contact first so we don't add a duplicate.
		contacts.remove(contact);
		contacts.add(contact);
	}

	/**
	 * Add a display to the database. The current range of interest of the
	 * display will be populated with data.
	 * 
	 * @param display
	 *            a {@Link HasData}.
	 */
	public void addDataDisplay(HasData<ProblemInfo> display) {
		dataProvider.addDataDisplay(display);
	}

	/**
	 * Generate the specified number of contacts and add them to the data
	 * provider.
	 * 
	 * @param count
	 *            the number of contacts to generate.
	 */
	public void generateContacts(int count) {
		List<ProblemInfo> contacts = dataProvider.getList();
		for (int i = 0; i < count; i++) {
			contacts.add(createProblemInfo());
		}
	}

	public ListDataProvider<ProblemInfo> getDataProvider() {
		return dataProvider;
	}

	/**
	 * Refresh all displays.
	 */
	public void refreshDisplays() {
		dataProvider.refresh();
	}

	/**
	 * Create a new random {@link ProblemInfo}.
	 * 
	 * @return the new {@link ProblemInfo}.
	 */
	private ProblemInfo createProblemInfo() {
		ProblemInfo contact = new ProblemInfo(0, nextValue(EXPERTS).split(","),
				nextValue(DESCS), nextValue(STATES));
		return contact;
	}

	/**
	 * Get the next random value from an array.
	 * 
	 * @param array
	 *            the array
	 * @return a random value in the array
	 */
	private <T> T nextValue(T[] array) {
		return array[Random.nextInt(array.length)];
	}

}
