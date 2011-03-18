/**
 * Copyright 2010 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.gwtplatform.dispatch.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to generate Action and Result classes.
 *
 * <p/>
 * If you type:
 *
 * <pre>
 * <code>
 * {@literal @}GenDispatch
 * public class RetrieveFoo {
 *   {@literal @}In(1) Key<Foo> fooKey;
 *   {@literal @}Out(1) Foo foo;
 *   {@literal @}Out(2) boolean bar;
 * }
 * </code>
 * </pre>
 * gwt-platform will generate two classes, RetrieveFooAction and
 * RetrieveFooResult.
 * <p/>
 * <code>@In</code> specifies that fooKey will only be used in the action. The
 * number passed to @In specifies the order of the arguments to the constructor.
 * <p/>
 * <code>@Out</code> specifies that foo and bar will only be used in the result.
 * The number passed to @Out specifies the order of the arguments to the
 * constructor.
 * <p/>
 * RetrieveFooAction will have fields, getters, and a constructor for fooKey,
 * plus equals, hashCode, toString etc, for it to function correctly as an
 * Action.
 * <p/>
 * RetrieveFooResult will have fields, getters, and a constructor that accepts
 * food and good, for bar, plus equals, hashCode, toString etc, for it to
 * function correctly as an Result.
 * <p/>
 * <b>Complex example:</b>
 *
 * <pre>
 * <code>public interface HasThing&lt;T&gt; {
 *   T getThing();
 * }
 *
 * {@literal @}GenDispatch(
 *     isSecure = false,
 *     serviceName = Action.DEFAULT_SERVICE_NAME + "MyBar",
 *     extraResultInterfaces = "com.example.shared.HasThing&lt;com.example.shared.Foo&gt;"
 * )
 * public class RetrieveBar {
 *   {@literal @}In(1) String id;
 *   {@literal @}Out(1) Foo thing;
 * }
 * </code>
 * </pre>
 * UpdateBarAction will have the following features;
 * <ul>
 * <li>The isSecure() method will return false.
 * <li>The getServiceName() method will return "dispatch/MyBar".
 * </ul>
 * And the UpdateBarResult object will be able to be passed to something that
 * knows how about the HasThing<Foo> interface, but does not know about
 * UpdateBarResult.
 *
 * <b>Notes:</b>
 * <p/>
 * There is no naming requirement for your class name. It will be appended with
 * Action and Result
 * <p/>
 * <p/>
 *
 * {@code isSecured} Specifies the value that the generated isSecured() method
 *          should return. Defaults to true if not specified. See
 *          {@link com.gwtplatform.dispatch.shared.Action} for more details.
 * <p/>
 * {@code serviceName} Specifies the value that the generated getServiceName()
 *          method should return. Defaults to Action.DEFAULT_SERVICE_NAME +
 *          "YourClassName" if not specified. See
 *          {@link com.gwtplatform.dispatch.shared.Action} for more details.
 * <p/>
 * {@code extraActionInterfaces} A comma separated list of fully qualified
 *          interfaces that the Action class will implement. Each of the
 *          functions defined in the interface must match an function
 *          autogenerated using the field definitions.
 * <p/>
 * {@code extraResultInterfaces} A comma separated list of fully qualified
 *          interfaces that the Result class will implement. Each of the
 *          functions defined in the interface must match an function
 *          autogenerated using the field definitions.
 *
 * @author Brendan Doherty
 * @author Stephen Haberman (concept)
 *
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface GenDispatch {
  boolean isSecure() default true;

  String serviceName() default "";

  String extraActionInterfaces() default "";

  String extraResultInterfaces() default "";
}
