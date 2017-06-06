<%--

    Licensed to Jasig under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Jasig licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License.  You may obtain a
    copy of the License at the following location:

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.

--%>
<%@ page pageEncoding="UTF-8" %>
<jsp:directive.include file="includes/top.jsp" />
<!-- Content area -->
<div class="content">
  <form:form method="post" id="fm1" commandName="${commandName}" htmlEscape="true">
    <div class="panel panel-body login-form">
      <div class="text-center">
        <div class="icon-object border-slate-300 text-slate-300"><i class="icon-cloud"></i></div>
      </div>

      <div class="form-group has-feedback has-feedback-left">
        <form:input cssClass="form-control" placeholder="手机号" cssErrorClass="error" id="username" size="25" tabindex="1" accesskey="${userNameAccessKey}" path="username" autocomplete="off" htmlEscape="true" />
        <div class="form-control-feedback">
          <i class="icon-user text-muted"></i>
        </div>
      </div>

      <div class="form-group has-feedback has-feedback-left">
        <form:password cssClass="form-control" cssErrorClass="error" placeholder="密码"   size="25" tabindex="2" path="password"  accesskey="${passwordAccessKey}" htmlEscape="true" autocomplete="off" />
        <div class="form-control-feedback">
          <i class="icon-lock text-muted"></i>
        </div>
      </div>

      <%--<div class="form-group login-options">--%>
        <%--<div class="row">--%>
          <%--<div class="col-sm-6">--%>
            <%--<label class="checkbox-inline">--%>
              <%--<div class="checker"><span class="checked">--%>
                <%--<input type="checkbox"  class="styled" name="rememberMe" id="rememberMe" checked="checked" value="true" tabindex="5"  /></span></div>--%>
              <%--记住我--%>
            <%--</label>--%>
          <%--</div>--%>
        <%--</div>--%>
      <%--</div>--%>

      <div class="form-group">
        <input type="hidden" name="lt" value="${loginTicket}" />
        <input type="hidden" name="execution" value="${flowExecutionKey}" />
        <input type="hidden" name="_eventId" value="submit" />
        <input class="btn bg-blue btn-block" name="submit" accesskey="l" value="登录" tabindex="6" type="submit" />
      </div>

      <div class="content-divider text-muted form-group"><span>其他登录方式</span></div>
      <ul class="list-inline form-group list-inline-condensed text-center">
        <li><a href="${QQClientUrl}" class="btn border-indigo text-indigo btn-flat btn-icon btn-rounded"><i class="icon-qq"></i></a></li>
        <%--<li><a href="#" class="btn border-pink-300 text-pink-300 btn-flat btn-icon btn-rounded"><i class="icon-dribbble3"></i></a></li>--%>
        <%--<li><a href="#" class="btn border-slate-600 text-slate-600 btn-flat btn-icon btn-rounded"><i class="icon-github"></i></a></li>--%>
        <%--<li><a href="#" class="btn border-info text-info btn-flat btn-icon btn-rounded"><i class="icon-twitter"></i></a></li>--%>
      </ul>

      <%--<div class="content-divider text-muted form-group"><span>Don't have an account?</span></div>--%>
      <%--<a href="login_registration.html" class="btn btn-default btn-block content-group">Sign up</a>--%>
      <%--<span class="help-block text-center no-margin">By continuing, you're confirming that you've read our <a href="#">Terms &amp; Conditions</a> and <a href="#">Cookie Policy</a></span>--%>
    </div>
  </form:form>



  <!-- Footer -->
  <%--<div class="footer text-muted">--%>
    <%--© 2015. <a href="#">Limitless Web App Kit</a> by <a href="http://themeforest.net/user/Kopyov" target="_blank">Eugene Kopyov</a>--%>
  <%--</div>--%>
  <!-- /footer -->

</div>
<!-- /content area -->

</div>
<!-- /main content -->

</div>
<!-- /page content -->

</div>
<!-- /page container -->
</body>


<%--<div class="box" id="login">--%>
  <%--<form:form method="post" id="fm1" commandName="${commandName}" htmlEscape="true">--%>

    <%--<form:errors path="*" id="msg" cssClass="errors" element="div" htmlEscape="false" />--%>

    <%--<h2><spring:message code="screen.welcome.instructions" /></h2>--%>

    <%--<section class="row">--%>
      <%--<label for="username"><spring:message code="screen.welcome.label.netid" /></label>--%>
      <%--<c:choose>--%>
        <%--<c:when test="${not empty sessionScope.openIdLocalId}">--%>
          <%--<strong>${sessionScope.openIdLocalId}</strong>--%>
          <%--<input type="hidden" id="username" name="username" value="${sessionScope.openIdLocalId}" />--%>
        <%--</c:when>--%>
        <%--<c:otherwise>--%>
          <%--<spring:message code="screen.welcome.label.netid.accesskey" var="userNameAccessKey" />--%>
          <%--<form:input cssClass="required" cssErrorClass="error" id="username" size="25" tabindex="1" accesskey="${userNameAccessKey}" path="username" autocomplete="off" htmlEscape="true" />--%>
        <%--</c:otherwise>--%>
      <%--</c:choose>--%>
    <%--</section>--%>

    <%--<section class="row">--%>
      <%--<label for="password"><spring:message code="screen.welcome.label.password" /></label>--%>
      <%--<spring:message code="screen.welcome.label.password.accesskey" var="passwordAccessKey" />--%>
      <%--<form:password cssClass="required" cssErrorClass="error" id="password" size="25" tabindex="2" path="password"  accesskey="${passwordAccessKey}" htmlEscape="true" autocomplete="off" />--%>
    <%--</section>--%>
    <%--<input type="checkbox" name="rememberMe" id="rememberMe" value="true" />--%>
    <%--<label for="rememberMe">Remember Me</label>--%>

    <%--<section class="row check">--%>
      <%--<input id="warn" name="warn" value="true" tabindex="3" accesskey="<spring:message code="screen.welcome.label.warn.accesskey" />" type="checkbox" />--%>
      <%--<label for="warn"><spring:message code="screen.welcome.label.warn" /></label>--%>
    <%--</section>--%>

    <%--<section class="row btn-row">--%>
      <%--<input type="hidden" name="lt" value="${loginTicket}" />--%>
      <%--<input type="hidden" name="execution" value="${flowExecutionKey}" />--%>
      <%--<input type="hidden" name="_eventId" value="submit" />--%>

      <%--<input class="btn-submit" name="submit" accesskey="l" value="<spring:message code="screen.welcome.button.login" />" tabindex="4" type="submit" />--%>
      <%--<input class="btn-reset" name="reset" accesskey="c" value="<spring:message code="screen.welcome.button.clear" />" tabindex="5" type="reset" />--%>
    <%--</section>--%>
      <%--<section class="row">--%>
          <%--<a href="${WechatClientUrl}">微信登录</a>--%>
      <%--</section>--%>

    <%--<section class="row">--%>
      <%--<a href="${QQClientUrl}">qq登录</a>--%>
    <%--</section>--%>

  <%--</form:form>--%>
<%--</div>--%>

<jsp:directive.include file="includes/bottom.jsp" />
