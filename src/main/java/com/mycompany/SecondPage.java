/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mycompany;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.atmosphere.EventBus;
import org.apache.wicket.atmosphere.Subscribe;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class SecondPage extends WebPage {
	private static final long serialVersionUID = 1L;

	protected final Form<Void> form;
	protected final Label label;

	public SecondPage(final PageParameters parameters) {
		super(parameters);
		form = new Form<Void>("form");
		add(form);
		form.add(new AjaxSubmitLink("send", form) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				EventBus.get().post("foo");
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
			}
		});
		label = new Label("label");

		form.setOutputMarkupId(true);
		label.setOutputMarkupId(false);
		
		add(label);
		setVersioned(false);
	}

	@Subscribe
	public void receiveMessage(AjaxRequestTarget target, String message) {
		//
//		target.add(label);
		target.add(form);
	}

}
