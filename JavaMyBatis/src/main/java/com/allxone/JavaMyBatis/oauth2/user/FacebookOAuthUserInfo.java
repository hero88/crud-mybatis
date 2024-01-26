package com.allxone.JavaMyBatis.oauth2.user;

import java.util.Map;

public class FacebookOAuthUserInfo extends OAuth2UserInfo {

    public FacebookOAuthUserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("id");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        return null;
    }


}
