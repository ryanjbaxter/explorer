/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.opensocial.explorer.server.login;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.EnumSet;
import java.util.Map;

import org.apache.shindig.auth.AbstractSecurityToken;
import org.apache.shindig.auth.AuthenticationMode;
import org.apache.shindig.auth.SecurityToken;
import org.apache.shindig.common.uri.Uri;
import org.openid4java.discovery.Identifier;

import com.google.caja.util.Maps;

/**
 * A container {@link SecurityToken} that contains information about the user and app.
 */
public final class LoginSecurityToken extends AbstractSecurityToken {

  private EnumSet<Keys> mapKeys;

  public LoginSecurityToken(Identifier identifier, String container) {
    String id = identifier.getIdentifier().split("=")[1];
    Map<String, String> values = Maps.newHashMap();
    values.put(Keys.VIEWER.getKey(), id);
    values.put(Keys.OWNER.getKey(), id);

    // CONSIDER: Should these be differentiated for any reason?
    values.put(Keys.APP_ID.getKey(), container);
    values.put(Keys.APP_URL.getKey(), container);
    values.put(Keys.CONTAINER.getKey(), container);
    values.put(Keys.DOMAIN.getKey(), container);

    this.mapKeys = EnumSet.allOf(Keys.class);
    this.mapKeys.add(Keys.VIEWER);
    this.mapKeys.add(Keys.OWNER);
    this.mapKeys.add(Keys.APP_ID);
    this.mapKeys.add(Keys.APP_URL);
    this.mapKeys.add(Keys.CONTAINER);
    this.mapKeys.add(Keys.DOMAIN);

    loadFromMap(values);

  }

  public LoginSecurityToken(String id, String container) {
    Map<String, String> values = Maps.newHashMap();
    values.put(Keys.VIEWER.getKey(), id);
    values.put(Keys.OWNER.getKey(), id);

    // CONSIDER: Should these be differentiated for any reason?
    values.put(Keys.APP_ID.getKey(), container);
    values.put(Keys.APP_URL.getKey(), container);
    values.put(Keys.CONTAINER.getKey(), container);
    values.put(Keys.DOMAIN.getKey(), container);

    this.mapKeys = EnumSet.allOf(Keys.class);
    this.mapKeys.add(Keys.VIEWER);
    this.mapKeys.add(Keys.OWNER);
    this.mapKeys.add(Keys.APP_ID);
    this.mapKeys.add(Keys.APP_URL);
    this.mapKeys.add(Keys.CONTAINER);
    this.mapKeys.add(Keys.DOMAIN);

    loadFromMap(values);
  }

  public String getAuthenticationMode() {
    return AuthenticationMode.SECURITY_TOKEN_URL_PARAMETER.name();
  }

  public String getUpdatedToken() {
    throw new UnsupportedOperationException("Not implemented");
  }

  public boolean isAnonymous() {
    return false;
  }

  @Override
  protected EnumSet<Keys> getMapKeys() {
    return this.mapKeys;
  }
}
