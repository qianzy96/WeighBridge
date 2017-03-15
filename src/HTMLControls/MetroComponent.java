package HTMLControls;
import com.hp.gagawa.java.elements.*;
public class MetroComponent
{
    protected Div parentElement;
    public MetroComponent()
    {

    }
    public Div toHTML()
    {
        return parentElement;
    }
    public String toString()
    {
        return parentElement.write();
    }
    protected Table createTableElement(String className, String dataRole, String id, Boolean allowDataSearching)
    {
        Table aTableTag = new Table();
        if(className.length() > 0)
            aTableTag.setAttribute("class", className);
        if(dataRole.length() > 0)
            aTableTag.setAttribute("data-role", dataRole);
        if(id.length() > 0)
            aTableTag.setId(id);
        if(allowDataSearching)
            aTableTag.setAttribute("data-searching", "true");
        return aTableTag;
    }
    protected Thead createTableHeadElement()
    {
        Thead aTableHeadingTag = new Thead();
        return aTableHeadingTag;
    }
    protected Tbody createTableBodyElement()
    {
        Tbody aTableBodyTag = new Tbody();
        return aTableBodyTag;
    }
    protected Tr createTableRowElement()
    {
        Tr aTableRowTag = new Tr();
        return aTableRowTag;
    }
    protected Tr createTableRowElement(String onClickEvent)
    {
        Tr aTableRowTag = new Tr();
        if(onClickEvent.length() > 0)
            aTableRowTag.setAttribute("onclick", onClickEvent);
        return aTableRowTag;
    }
    protected Th createTableHeadCellElement(String textualContent)
    {
        Th aTableHeadingCellTag = new Th();
        aTableHeadingCellTag.appendText(textualContent);
        return aTableHeadingCellTag;
    }
    protected Td createTableRowCellElement(String textualContent)
    {
        Td aTableRowCellTag = new Td();
        aTableRowCellTag.appendText(textualContent);
        return aTableRowCellTag;
    }
    protected A createAElement(String hrefAttribute, String textualContent, String className, String onClickEvent)
    {
        A anATag = new A();
        if(hrefAttribute.length() > 0)
            anATag.setHref(hrefAttribute);
        if(textualContent.length() > 0)
            anATag.appendText(textualContent);
        if(className.length() > 0)
            anATag.setAttribute("class", className);
        if(onClickEvent.length() > 0)
            anATag.setAttribute("onclick", onClickEvent);
        return anATag;
    }
    protected A createAElement(String hrefAttribute, String textualContent, String className)
    {
        A anATag = new A();
        if(hrefAttribute.length() > 0)
            anATag.setHref(hrefAttribute);
        if(textualContent.length() > 0)
            anATag.appendText(textualContent);
        if(className.length() > 0)
            anATag.setAttribute("class", className);
        return anATag;
    }
    protected A createAElement(String hrefAttribute, String textualContent)
    {
        A anATag = new A();
        if(hrefAttribute.length() > 0)
            anATag.setHref(hrefAttribute);
        if(textualContent.length() > 0)
            anATag.appendText(textualContent);
        return anATag;
    }
    protected A createAElement(String hrefAttribute)
    {
        A anATag = new A();
        if(hrefAttribute.length() > 0)
            anATag.setHref(hrefAttribute);
        return anATag;
    }
    protected H1 createHeadingElement(String headingText, String alignPosition)
    {
        H1 aH1Tag = new H1();
        if(alignPosition.length() > 0)
            aH1Tag.setAlign(alignPosition);
        if(headingText.length() > 0)
            aH1Tag.appendText(headingText);
        return aH1Tag;
    }
    protected H1 createHeadingElement(String headingText, String className, String dataPosition)
    {
        H1 aH1Tag = new H1();
        if(className.length() > 0)
            aH1Tag.setAttribute("class", className);
        if(dataPosition.length() > 0)
            aH1Tag.setAttribute("data-position", dataPosition);
        if(headingText.length() > 0)
            aH1Tag.appendText(headingText);
        return aH1Tag;
    }
    protected P createParagraphElement(String paragraphText, String className, String dataPosition)
    {
        P aPTag = new P();
        if(className.length() > 0)
            aPTag.setAttribute("class", className);
        if(dataPosition.length() > 0)
            aPTag.setAttribute("data-position", dataPosition);
        if(paragraphText.length() > 0)
            aPTag.appendText(paragraphText);
        return aPTag;
    }
    protected Li createLiElement(String className, String onClickEvent)
    {
        Li aLiTag = new Li();
        if(className.length() > 0)
            aLiTag.setAttribute("class", className);
        if(onClickEvent.length() > 0)
            aLiTag.setAttribute("onclick", onClickEvent);
        return aLiTag;
    }
    protected Li createLiElement(String className)
    {
        Li aLiTag = new Li();
        if(className.length() > 0)
            aLiTag.setAttribute("class", className);
        return aLiTag;
    }
    protected Ul createUlElement(String className)
    {
        Ul anUlTag = new Ul();
        if(className.length() > 0)
            anUlTag.setAttribute("class", className);
        return anUlTag;
    }
    protected Ul createUlElement(String className, String styleAttribute)
    {
        Ul anUlTag = new Ul();
        if(className.length() > 0)
            anUlTag.setAttribute("class", className);
        if(styleAttribute.length() > 0)
            anUlTag.setStyle(styleAttribute);
        return anUlTag;
    }
    protected Input createInputElement(String inputType, String inputID)
    {
        Input anInputTag = new Input();
        if(inputType.length() > 0)
            anInputTag.setType(inputType);
        if(inputID.length() > 0)
            anInputTag.setId(inputID);
        return anInputTag;
    }
    protected Input createInputElement(String inputType, String inputID, String inputText)
    {
        Input anInputTag = new Input();
        if(inputType.length() > 0)
            anInputTag.setType(inputType);
        if(inputID.length() > 0)
            anInputTag.setId(inputID);
        if(inputText.length() > 0)
            anInputTag.setValue(inputText);
        return anInputTag;
    }
    protected Button createButtonElement(String className, String onClickEvent, String buttonTitle, String styleContent)
    {
        Button aButtonTag = new Button();
        if(className.length() > 0)
            aButtonTag.setAttribute("class", className);
        if(onClickEvent.length() > 0)
            aButtonTag.setAttribute("onclick", onClickEvent);
        if(buttonTitle.length() > 0)
            aButtonTag.appendText(buttonTitle);
        if(styleContent.length() > 0)
            aButtonTag.setAttribute("style", styleContent);
        return aButtonTag;
    }
    protected Button createButtonElement(String className)
    {
        Button aButtonTag = new Button();
        if(className.length() > 0)
            aButtonTag.setAttribute("class", className);
        return aButtonTag;
    }
    protected Small createSmallElement(String textualContent)
    {
        Small aSmallTag = new Small();
        aSmallTag.appendText(textualContent);
        return aSmallTag;
    }
    protected Label createLabelElement(String className)
    {
        Label aLabelTag = new Label();
        if(className.length() > 0)
            aLabelTag.setAttribute("class", className);
        return aLabelTag;
    }
    protected Div createDivElement(String className)
    {
        Div aDivTag = new Div();
        if(className.length() > 0)
            aDivTag.setAttribute("class", className);
        return aDivTag;
    }
    protected Div createDivElement(String className, String dataRole)
    {
        Div aDivTag = new Div();
        if(className.length() > 0)
            aDivTag.setAttribute("class", className);
        if(dataRole.length() > 0)
            aDivTag.setAttribute("data-role", dataRole);
        return aDivTag;
    }
    protected Div createDivElement(String className, String dataRole, String textualContent)
    {
        Div aDivTag = new Div();
        if(className.length() > 0)
            aDivTag.setAttribute("class", className);
        if(dataRole.length() > 0)
            aDivTag.setAttribute("data-role", dataRole);
        if(textualContent.length() > 0)
            aDivTag.appendText(textualContent);
        return aDivTag;
    }
    protected Div createDivElement(String className, String dataRole, String textualContent, String id)
    {
        Div aDivTag = new Div();
        if(className.length() > 0)
            aDivTag.setAttribute("class", className);
        if(dataRole.length() > 0)
            aDivTag.setAttribute("data-role", dataRole);
        if(textualContent.length() > 0)
            aDivTag.appendText(textualContent);
        if(id.length() > 0)
            aDivTag.setId(id);
        return aDivTag;
    }
    protected Div createDivElement(String className, String dataRole, String textualContent, String id, String onClickEvent)
    {
        Div aDivTag = new Div();
        if(className.length() > 0)
            aDivTag.setAttribute("class", className);
        if(dataRole.length() > 0)
            aDivTag.setAttribute("data-role", dataRole);
        if(textualContent.length() > 0)
            aDivTag.appendText(textualContent);
        if(id.length() > 0)
            aDivTag.setId(id);
        if(onClickEvent.length() > 0)
            aDivTag.setAttribute("onclick", onClickEvent);
        return aDivTag;
    }
    protected Div createDivElement(String className, String dataRole, String textualContent, String id, String onClickEvent, String styleAttribute)
    {
        Div aDivTag = new Div();
        if(className.length() > 0)
            aDivTag.setAttribute("class", className);
        if(dataRole.length() > 0)
            aDivTag.setAttribute("data-role", dataRole);
        if(textualContent.length() > 0)
            aDivTag.appendText(textualContent);
        if(id.length() > 0)
            aDivTag.setId(id);
        if(onClickEvent.length() > 0)
            aDivTag.setAttribute("onclick", onClickEvent);
        if(styleAttribute.length() > 0)
            aDivTag.setStyle(styleAttribute);
        return aDivTag;
    }
    protected Div createDivElement(String className, String dataRole, String textualContent, String id, String onClickEvent, String dataValue, String dataColour)
    {
        Div aDivTag = new Div();
        if(className.length() > 0)
            aDivTag.setAttribute("class", className);
        if(dataRole.length() > 0)
            aDivTag.setAttribute("data-role", dataRole);
        if(textualContent.length() > 0)
            aDivTag.appendText(textualContent);
        if(id.length() > 0)
            aDivTag.setId(id);
        if(onClickEvent.length() > 0)
            aDivTag.setAttribute("onclick", onClickEvent);
        if(dataValue.length() > 0)
            aDivTag.setAttribute("data-value", dataValue);
        if(dataColour.length() > 0)
            aDivTag.setAttribute("data-color", dataColour);
        return aDivTag;
    }
    protected Span createSpanElement(String className)
    {
        Span aSpanTag = new Span();
        if(className.length() > 0)
            aSpanTag.setAttribute("class", className);
        return aSpanTag;
    }
    protected Span createSpanElement(String className, String textualContent)
    {
        Span aSpanTag = new Span();
        if(className.length() > 0)
            aSpanTag.setAttribute("class", className);
        if(textualContent.length() > 0)
            aSpanTag.appendText(textualContent);
        return aSpanTag;
    }
    protected Script createLinkToScript(String scriptLocation)
    {
        Script aScript = new Script("");
        aScript.setAttribute("src", scriptLocation);
        return aScript;
    }
    protected Link createLinkToStyleSheet(String styleSheetLocation)
    {
        Link aLink = new Link();
        aLink.setAttribute("href", styleSheetLocation);
        aLink.setAttribute("rel", "stylesheet");
        return aLink;
    }
    protected Title createTitleTag(String title)
    {
        Title aTitleTag = new Title();
        aTitleTag.appendText(title);
        return aTitleTag;
    }
    protected Meta createMetaTagForCharset()
    {
        Meta aMetaTag = new Meta("");
        aMetaTag.setAttribute("charset", "UTF-8");
        return aMetaTag;
    }
    protected Meta createMetaTagForHTTPEquivalent()
    {
        Meta aMetaTag = new Meta("");
        aMetaTag.setAttribute("http-equiv", "X-UA-Compatible");
        aMetaTag.setAttribute("content", "IE=edge");
        return aMetaTag;
    }
    protected Meta createMetaTagForViewport()
    {
        Meta aMetaTag = new Meta("");
        aMetaTag.setAttribute("name", "viewport");
        aMetaTag.setAttribute("content", "width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no");
        return aMetaTag;
    }
}
