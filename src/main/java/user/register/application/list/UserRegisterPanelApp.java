package user.register.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import user.register.constants.CustomPanelCategoryKeys;
import user.register.constants.UserRegisterPortletKeys;

import java.util.Locale;
import java.util.ResourceBundle;

@Component(
        immediate = true,
        service = PanelApp.class,
        property = {
                "panel.app.order:Integer=100",
                "panel.category.key=" + CustomPanelCategoryKeys.CUSTOM_PANEL_CATEGORY
        }
)

public class UserRegisterPanelApp extends BasePanelApp {
    @Override
    public String getPortletId() {
        return UserRegisterPortletKeys.USERREGISTER;
    }

    @Override
    public String getLabel(Locale locale) {
        ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
                "content.Language", locale, getClass());

        return LanguageUtil.get(resourceBundle, "category.custom.label");
    }

    @Reference(
            target = "(javax.portlet.name=" + UserRegisterPortletKeys.USERREGISTER + ")",
            unbind = "-"
    )

    public void setPortlet(Portlet portlet) {
        super.setPortlet(portlet);
    }
}
