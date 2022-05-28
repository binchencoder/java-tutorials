package com.binchencoder.study.spring;

import com.google.common.base.Preconditions;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SpringEnvProfilesHelper {

    private static final Logger logger = LoggerFactory.getLogger(SpringEnvProfilesHelper.class);

    @Autowired
    private Environment environment;

    private Profile profile;

    @PostConstruct
    public void initEnvProfile() {
        String[] profiles = environment.getActiveProfiles();
        profile = new Profile();
        if (ArrayUtils.isEmpty(profiles)) {
            logger.warn("Spring active profiles not configured");
            profile.setDev(true);
            return;
        }

        logger.info("Spring active profiles: {}", Arrays.toString(profiles));
        if (ArrayUtils.contains(profiles, Profile.DEV)
            || ArrayUtils.contains(profiles, Profile.TEST)) {
            profile.setDev(true);
        }

        if (ArrayUtils.contains(profiles, Profile.PROD)) {
            profile.setProduction(true);
        }

        Preconditions.checkArgument(!(profile.isDev() && profile.isProduction()),
            "Environment variables do not allow both dev and production!");
    }

    public Profile getProfile() {
        return this.profile;
    }
}
