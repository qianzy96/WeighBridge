package HTMLControls;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Li;
import com.hp.gagawa.java.elements.Ul;

public class MetroAppBar extends MetroComponent
{
    private Ul appBarMenu;
    public MetroAppBar()
    {
        parentElement = createDivElement("app-bar navy", "appbar");
        appBarMenu = createUlElement("app-bar-menu");
        parentElement.appendChild(appBarMenu);
    }
    public void addItem(String title, String icon, String onClickEvent)
    {
        Li aListItem = createLiElement("");
        appBarMenu.appendChild(aListItem);
        A aLink = createAElement("", title, "", onClickEvent);
        aListItem.appendChild(aLink);
        aLink.appendChild(createSpanElement("mif-" + icon));
    }
}
/*
//<span class="mif-enter"></span>
<div class="app-bar navy" data-role="appbar">
                    <a class="app-bar-element branding">Metro UI CSS</a>
                    <span class="app-bar-divider"></span>
                    <ul class="app-bar-menu">
                        <li><a href="">HomePage</a></li>
                        <li>
                            <a href="" class="dropdown-toggle">Products</a>
                            <ul class="d-menu" data-role="dropdown">
                                <li><a href="">Windows 10</a></li>
                                <li><a href="">Spartan</a></li>
                                <li><a href="">Outlook</a></li>
                                <li><a href="">Office 2015</a></li>
                                <li class="divider"></li>
                                <li><a href="" class="dropdown-toggle">Other Products</a>
                                    <ul class="d-menu" data-role="dropdown">
                                        <li><a href="">Internet Explorer 10</a></li>
                                        <li><a href="">Skype</a></li>
                                        <li><a href="">Surface</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="" class="dropdown-toggle">Support</a>
                            <ul class="d-menu" data-role="dropdown">
                                <li><a href="">About</a></li>
                                <li><a href="">Contacts</a></li>
                                <li><a href="">Community forum</a></li>
                                <li>
                                    <a href="" class="dropdown-toggle">Support</a>
                                    <ul class="d-menu" data-role="dropdown">
                                        <li><a href="" class="dropdown-toggle">About</a></li>
                                        <li><a href="">Contacts</a></li>
                                        <li><a href="">Community forum</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li><a href="">Help</a></li>
                    </ul>
                    <div class="app-bar-element place-right">
                        <a class="dropdown-toggle fg-white"><span class="mif-enter"></span> Enter</a>
                        <div class="app-bar-drop-container place-right" data-role="dropdown" data-no-close="true">
                            <div class="padding20">
                                <form>
                                    <h4 class="text-light">Login to service...</h4>
                                    <div class="input-control text">
                                        <span class="mif-user prepend-icon"></span>
                                        <input type="text">
                                    </div>
                                    <div class="input-control text">
                                        <span class="mif-lock prepend-icon"></span>
                                        <input type="password">
                                    </div>
                                    <label class="input-control checkbox small-check">
                                        <input type="checkbox">
                                        <span class="check"></span>
                                        <span class="caption">Remember me</span>
                                    </label>
                                    <div class="form-actions">
                                        <button class="button primary">Login</button>
                                        <button class="button link fg-grayLighter">Cancel</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
*/
