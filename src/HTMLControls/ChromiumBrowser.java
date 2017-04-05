package HTMLControls;
import HTMLPages.*;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.JSValue;
import com.teamdev.jxbrowser.chromium.events.*;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
public class ChromiumBrowser
{
    private BrowserView aBrowserView;
    private Browser aBrowser;
    private String address;
    private String text;
    public ChromiumBrowser(String address, String text)
    {
        this.address = address;
        this.text = text;
        this.aBrowser = new Browser();
        this.aBrowserView = new BrowserView(this.aBrowser);
        if(address.length() > 0)
            this.aBrowser.loadURL(this.address);
        if(text.length() > 0)
            this.aBrowser.loadHTML(this.text);
        aBrowser.addLoadListener(new LoadListener()
        {
            @Override
            public void onStartLoadingFrame(StartLoadingEvent startLoadingEvent)
            {

            }

            @Override
            public void onProvisionalLoadingFrame(ProvisionalLoadingEvent provisionalLoadingEvent)
            {

            }

            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent finishLoadingEvent)
            {
                if(finishLoadingEvent.isMainFrame())
                {
                    Browser aBrowser = finishLoadingEvent.getBrowser();
                    JSValue value = aBrowser.executeJavaScriptAndReturnValue("window");
                    value.asObject().setProperty("LogOn", new LogOnPage());
                    value.asObject().setProperty("Home", new HomePage());
                    value.asObject().setProperty("Administration", new AdministrationPage());
                    value.asObject().setProperty("Registration", new RegistrationPage());
                    value.asObject().setProperty("Portal", new PortalPage());
                    value.asObject().setProperty("WeighBridge", new WeighBridgePage());
                    value.asObject().setProperty("Calculator", new CalculatorPage());
                    value.asObject().setProperty("Dashboard", new DashboardPage());
                }
            }

            @Override
            public void onFailLoadingFrame(FailLoadingEvent failLoadingEvent) {

            }

            @Override
            public void onDocumentLoadedInFrame(FrameLoadEvent frameLoadEvent) {

            }

            @Override
            public void onDocumentLoadedInMainFrame(LoadEvent loadEvent) {

            }
        });
    }
    public BrowserView getBrowser()
    {
        return aBrowserView;
    }
}
