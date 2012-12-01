package pl.dolecinski.supdicium.client.ui;

import pl.dolecinski.supdicium.client.SDStyle;
import pl.dolecinski.supdicium.client.theme.base.MainCss;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.Widget;

public class ProblemListItemWidget extends Composite {

	public interface Binder extends UiBinder<Widget, ProblemListItemWidget> {
	}

	private static Binder binder=GWT.create(Binder.class);

	@UiField
	protected InlineHyperlink titleImage;
	@UiField
	protected InlineHyperlink title;
	
	public ProblemListItemWidget() {
		initWidget(binder.createAndBindUi(this));
	}

	@UiFactory
	public MainCss getResources() {
		MainCss mainCss = SDStyle.getTheme().getSDClientBundle().getMainCss();
		mainCss.ensureInjected();
		return mainCss;
	}
	
	public void setTitle(String aTitle){
		this.title.setText(aTitle);
		this.titleImage.setText(aTitle);
	}
}
