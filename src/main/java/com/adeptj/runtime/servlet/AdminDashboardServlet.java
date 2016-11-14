/** 
###############################################################################
#                                                                             # 
#    Copyright 2016, AdeptJ (http://adeptj.com)                               #
#                                                                             #
#    Licensed under the Apache License, Version 2.0 (the "License");          #
#    you may not use this file except in compliance with the License.         #
#    You may obtain a copy of the License at                                  #
#                                                                             #
#        http://www.apache.org/licenses/LICENSE-2.0                           #
#                                                                             #
#    Unless required by applicable law or agreed to in writing, software      #
#    distributed under the License is distributed on an "AS IS" BASIS,        #
#    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. #
#    See the License for the specific language governing permissions and      #
#    limitations under the License.                                           #
#                                                                             #
###############################################################################
*/
package com.adeptj.runtime.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adeptj.runtime.admin.render.ContextObjects;
import com.adeptj.runtime.admin.render.RenderContext;
import com.adeptj.runtime.admin.render.RenderEngine;

/**
 * OSGi AdminDashboardServlet renders the admin dashboard page.
 *
 * @author Rakesh.Kumar, AdeptJ
 */
@WebServlet(name = "AdminDashboardServlet", urlPatterns = { "/admin/dashboard/*" })
public class AdminDashboardServlet extends HttpServlet {

	private static final long serialVersionUID = -3339904764769823449L;

	/**
	 * Render dashboard page.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RenderContext.Builder builder = new RenderContext.Builder();
		builder.view("auth/dashboard").contextObjects(new ContextObjects()).request(req).response(resp).locale(req.getLocale());
		RenderEngine.INSTANCE.render(builder.build());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("/admin/dashboard");
	}
}