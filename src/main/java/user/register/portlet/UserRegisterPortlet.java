package user.register.portlet;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;
import java.util.Map;

import javax.portlet.*;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import user.register.constants.UserRegisterPortletKeys;

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
		"javax.portlet.name=" + UserRegisterPortletKeys.USERREGISTER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class UserRegisterPortlet extends MVCPortlet {

	/*
	Cria novo usuário adicionando valores dos seus campos customizados
	*/
	@ProcessAction(name = "registerUser")
	public void registerUser(ActionRequest request, ActionResponse response)
		throws IOException, PortalException, PortletException {

		String firstName = ParamUtil.getString(request, "firstName");
		String lastName = ParamUtil.getString(request, "lastName");
		String screenName = ParamUtil.getString(request, "screenName");
		String emailAddress = ParamUtil.getString(request, "emailAddress");

		// Campos customizados
		String cpf = ParamUtil.getString(request, "cpf");
		boolean isDeveloper = ParamUtil.getBoolean(request, "isDeveloper");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			request);

		try {
			User user = UserLocalServiceUtil.addUser(
				serviceContext.getUserId(), serviceContext.getCompanyId(), true,
				"", "", false, screenName, emailAddress,
				serviceContext.getLocale(), firstName, "", lastName, 0, 0, true,
				7, 12, 2001, "", new long[0], new long[0], new long[0],
				new long[0], false, serviceContext);

			// Instância campos customizáveis do usuário e altera
			ExpandoBridge expandoBridge = user.getExpandoBridge();
			expandoBridge.setAttribute("cpf", cpf);
			expandoBridge.setAttribute("isDeveloper", isDeveloper);

			// Persiste alteração de campos customizáveis
			user.setExpandoBridgeAttributes(serviceContext);
			UserLocalServiceUtil.updateUser(user);

			System.out.println("Usuário " + screenName + "adicinoado com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	Cria novos campos customizáveis dinamicamente para o usuário sempre que
	houver o deploy da portlet.
	*/
	@Activate
	@Modified
	protected void activate(Map<Object, Object> properties) {
		long companyId = 20096; // CompanyId setado
		ExpandoBridge userCustomFields =
			ExpandoBridgeFactoryUtil.getExpandoBridge(
				companyId, User.class.getName());
		String customField = "favoriteLanguage";

		if (!userCustomFields.hasAttribute(customField)) {
			try {
				userCustomFields.addAttribute(customField, false);
				System.out.println("Novo campo " + customField + " criado!");
			}
			catch (PortalException e) {
				e.printStackTrace();
			}
		}
	}
}