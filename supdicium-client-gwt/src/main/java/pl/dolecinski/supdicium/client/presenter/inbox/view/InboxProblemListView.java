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
package pl.dolecinski.supdicium.client.presenter.inbox.view;

import java.util.Date;

import pl.dolecinski.supdicium.client.model.problem.ProblemInfo;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.resources.client.CssResource.ImportedWithPrefix;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;

/**
 * @author Paweł Doleciński
 * 
 */
public class InboxProblemListView{

	public interface Binder extends UiBinder<Widget, InboxProblemListView> {
	}

	public interface SdCellTableResources extends CellTable.Resources {
		@Source("pl/dolecinski/supdicium/client/theme/base/css/CellTable.css")
		SdCellTableStyles cellTableStyle();
	}

	@ImportedWithPrefix("sd-CellTable")
	public interface SdCellTableStyles extends CellTable.Style {
	}

	@UiField(provided = true)
	CellTable<ProblemInfo> problemList;

	private ListDataProvider<ProblemInfo> problemsDataProvider;
	private final Widget widget;

	@Inject
	public InboxProblemListView(Binder binder, SdCellTableResources resources) {
		problemList = new CellTable<ProblemInfo>(25, resources,
				ProblemInfo.KEY_PROVIDER);
		problemList.setWidth("100%", true);
		problemList.setHeight("100%");
		// Attach a column sort handler to the ListDataProvider to sort the
		// list.
		problemsDataProvider = new ListDataProvider<ProblemInfo>();

		ListHandler<ProblemInfo> sortHandler = new ListHandler<ProblemInfo>(
				getProblemsDataProvider().getList());
		problemList.addColumnSortHandler(sortHandler);

		// Create a Pager to control the table.
		// SimplePager.Resources pagerResources =
		// GWT.create(SimplePager.Resources.class);
		// pager = new SimplePager(TextLocation.CENTER, pagerResources, false,
		// 0, true);
		// pager.setDisplay(problemList);

		// Add a selection model so we can select cells.
		final SelectionModel<ProblemInfo> selectionModel = new SingleSelectionModel<ProblemInfo>(
				ProblemInfo.KEY_PROVIDER);
		problemList.setSelectionModel(selectionModel);
		// Initialize the columns.
		initTableColumns(selectionModel, sortHandler);

		// Add the CellList to the adapter in the database.
		getProblemsDataProvider().addDataDisplay(problemList);

		widget = binder.createAndBindUi(this);
	}

	private void initTableColumns(
			final SelectionModel<ProblemInfo> selectionModel,
			ListHandler<ProblemInfo> sortHandler) {

		Column<ProblemInfo, Boolean> checkColumn = new Column<ProblemInfo, Boolean>(
				new CheckboxCell(false, false)) {
			@Override
			public Boolean getValue(ProblemInfo object) {
				return false;
			}
		};
		problemList.addColumn(checkColumn);
		problemList.setColumnWidth(checkColumn, 26, Unit.PX);

		// Experts.
		Column<ProblemInfo, String> expertsColumn = new Column<ProblemInfo, String>(
				new TextCell()) {
			@Override
			public String getValue(ProblemInfo object) {
				String[] experts = object.getExperts();
				String ret = "";
				for (String string : experts) {
					ret += string + ", ";
				}
				return ret.substring(0, ret.length() - 2);
			}
		};
		expertsColumn.setSortable(false);

		problemList.addColumn(expertsColumn);
		problemList.setColumnWidth(expertsColumn, 22, Unit.EX);

		// Description.
		Column<ProblemInfo, String> descColumn = new Column<ProblemInfo, String>(
				new TextCell()) {
			@Override
			public String getValue(ProblemInfo object) {
				return object.getDescription();
			}
		};
		descColumn.setSortable(false);

		problemList.addColumn(descColumn);
		problemList.setColumnWidth(descColumn, 100, Unit.PCT);

		// State.
		Column<ProblemInfo, String> stateColumn = new Column<ProblemInfo, String>(
				new TextCell()) {
			@Override
			public String getValue(ProblemInfo object) {
				return object.getState();
			}
		};
		stateColumn.setSortable(false);
		problemList.addColumn(stateColumn);
		problemList.setColumnWidth(stateColumn, 120, Unit.PX);

		// Date.
		Column<ProblemInfo, String> dateColumn = new Column<ProblemInfo, String>(
				new TextCell()) {
			@Override
			public String getValue(ProblemInfo object) {
				Date createDate = object.getCreateDate();
				return DateTimeFormat
						.getFormat(PredefinedFormat.MONTH_ABBR_DAY).format(
								createDate);
			}
		};
		dateColumn.setSortable(false);
		problemList.addColumn(dateColumn);
		problemList.setColumnWidth(dateColumn, 70, Unit.PX);
	}


	public ListDataProvider<ProblemInfo> getProblemsDataProvider() {
		return problemsDataProvider;
	}
}
