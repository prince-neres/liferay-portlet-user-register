package custom.login.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import custom.login.constants.CustomLoginPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;

/**
 * @author prince
 */

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.custom",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=UserRegister",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CustomLoginPortletKeys.CUSTOMLOGIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)

public class CustomLoginPortlet extends MVCPortlet {

}