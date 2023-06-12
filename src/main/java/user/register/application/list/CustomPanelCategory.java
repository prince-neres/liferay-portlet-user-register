package user.register.application.list;

import com.liferay.application.list.BasePanelCategory;
import com.liferay.application.list.PanelCategory;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import org.osgi.service.component.annotations.Component;
import user.register.constants.CustomPanelCategoryKeys;

import java.util.Locale;
import java.util.ResourceBundle;

@Component(

        property = {
                "panel.category.key=" + PanelCategoryKeys.CONTROL_PANEL,
                "panel.category.order:Integer=100"
        },
        service = PanelCategory.class
)

public class CustomPanelCategory extends BasePanelCategory {

    @Override
    public String getKey() {
        return CustomPanelCategoryKeys.CUSTOM_PANEL_CATEGORY;
    }

    @Override
    public String getLabel(Locale locale) {
        ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
                "content.Language", locale, getClass());

        return LanguageUtil.get(resourceBundle, "custom.panel.category.label");
    }
}
