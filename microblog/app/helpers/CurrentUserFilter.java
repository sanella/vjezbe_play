package helpers;

import models.User;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

/**
 * We use this class as a controller action filter
 * to ensure that only a logedin user can perform certain actions
 * @author benjamin
 *
 */
public class CurrentUserFilter extends Security.Authenticator {

	@Override
	public String getUsername(Context ctx) {
		if(!ctx.session().containsKey("username"))
			return null;
		String username = ctx.session().get("username");
		User u = User.find(username);
		if (u != null)
			return u.username;
		return null;
	}

	@Override
	public Result onUnauthorized(Context ctx) {
		return redirect("/loginToComplete");
	}


}
