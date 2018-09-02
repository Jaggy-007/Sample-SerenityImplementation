package support.libraries;

import java.util.List;

import org.openqa.selenium.WebElement;

import net.thucydides.core.pages.components.HtmlTable;

public class HtmlTableExtension extends HtmlTable {
	
	private final WebElement tableElement;
	private List<String> headings;
	
	public HtmlTableExtension(WebElement tableElement){
		super(tableElement);
		this.tableElement=tableElement;
		this.headings=null;
		
	}
	

}
