package com.tengzhi.base.security.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

/**
 * @author lqy 在源码基础上增加字段
 */
public class SecurityUser implements UserDetails, CredentialsContainer {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private static final Log logger = LogFactory.getLog(SecurityUser.class);

    // ~ Instance fields
    // ================================================================================================
    private String password;
    private final String username;
    private final Set<GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    // 扩展字段
    private String userId;
    private Boolean gender;
    private String nickName;
    private String realName;
    private String fact;
    private String mobile;
    private String email;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date genTime;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifyTime;
    private String corpId;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lastloginTime;
    private Boolean isForbidden;
    private String identityType;
    private String indetifiter;
    private String identifierToken;
    private String jobNumber;
    private String sign;
    private String deptId;
    private String originalCorpId;
    private String businessPersonnelIds;
    private String orgName;

    /**
     * Calls the more complex constructor with all boolean arguments set to
     * {@code true}.
     */
    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this(username, password, true, true, true, true, authorities);
    }


    /**
     * Calls the more complex constructor with all boolean arguments set to
     * {@code true}.
     */
    public SecurityUser(String username, String userid, String password, Collection<? extends GrantedAuthority> authorities) {
        this(username, userid, password, true, true, true, true, authorities);
    }

    /**
     * Construct the <code>User</code> with the details required by
     * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider}.
     *
     * @param username              the username presented to the
     *                              <code>DaoAuthenticationProvider</code>
     * @param password              the password that should be presented to the
     *                              <code>DaoAuthenticationProvider</code>
     * @param enabled               set to <code>true</code> if the user is enabled
     * @param accountNonExpired     set to <code>true</code> if the account has not
     *                              expired
     * @param credentialsNonExpired set to <code>true</code> if the credentials have
     *                              not expired
     * @param accountNonLocked      set to <code>true</code> if the account is not
     *                              locked
     * @param authorities           the authorities that should be granted to the
     *                              caller if they presented the correct username
     *                              and password and the user is enabled. Not null.
     * @throws IllegalArgumentException if a <code>null</code> value was passed
     *                                  either as a parameter or as an element in
     *                                  the <code>GrantedAuthority</code> collection
     */
    public SecurityUser(String username, String password, boolean enabled, boolean accountNonExpired,
                        boolean credentialsNonExpired, boolean accountNonLocked,
                        Collection<? extends GrantedAuthority> authorities) {

        if (((username == null) || "".equals(username)) || (password == null)) {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }

        this.username = username;
        this.nickName = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
    }

    public SecurityUser(String username, String userid, String password, boolean enabled, boolean accountNonExpired,
                        boolean credentialsNonExpired, boolean accountNonLocked,
                        Collection<? extends GrantedAuthority> authorities) {

        if (((username == null) || "".equals(username)) || (password == null)) {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }

        this.username = username;
        this.nickName = username;
        this.jobNumber = userid;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
    }

    // ~ Methods
    // ========================================================================================================

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public void eraseCredentials() {
        password = null;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        // Ensure array iteration order is predictable (as per
        // UserDetails.getAuthorities() contract and SEC-717)
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(new AuthorityComparator());

        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        @Override
        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            // Neither should ever be null as each entry is checked before adding it to
            // the set.
            // If the authority is null, it is a custom authority and should precede
            // others.
            if (g2.getAuthority() == null) {
                return -1;
            }

            if (g1.getAuthority() == null) {
                return 1;
            }

            return g1.getAuthority().compareTo(g2.getAuthority());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SecurityUser other = (SecurityUser) obj;
        if (accountNonExpired != other.accountNonExpired) {
            return false;
        }
        if (accountNonLocked != other.accountNonLocked) {
            return false;
        }
        if (authorities == null) {
            if (other.authorities != null) {
                return false;
            }
        } else if (!authorities.equals(other.authorities)) {
            return false;
        }
        if (corpId == null) {
            if (other.corpId != null) {
                return false;
            }
        } else if (!corpId.equals(other.corpId)) {
            return false;
        }
        if (credentialsNonExpired != other.credentialsNonExpired) {
            return false;
        }
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        if (enabled != other.enabled) {
            return false;
        }
        if (fact == null) {
            if (other.fact != null) {
                return false;
            }
        } else if (!fact.equals(other.fact)) {
            return false;
        }
        if (genTime == null) {
            if (other.genTime != null) {
                return false;
            }
        } else if (!genTime.equals(other.genTime)) {
            return false;
        }
        if (gender == null) {
            if (other.gender != null) {
                return false;
            }
        } else if (!gender.equals(other.gender)) {
            return false;
        }
        if (identifierToken == null) {
            if (other.identifierToken != null) {
                return false;
            }
        } else if (!identifierToken.equals(other.identifierToken)) {
            return false;
        }
        if (identityType == null) {
            if (other.identityType != null) {
                return false;
            }
        } else if (!identityType.equals(other.identityType)) {
            return false;
        }
        if (indetifiter == null) {
            if (other.indetifiter != null) {
                return false;
            }
        } else if (!indetifiter.equals(other.indetifiter)) {
            return false;
        }
        if (isForbidden == null) {
            if (other.isForbidden != null) {
                return false;
            }
        } else if (!isForbidden.equals(other.isForbidden)) {
            return false;
        }
        if (lastloginTime == null) {
            if (other.lastloginTime != null) {
                return false;
            }
        } else if (!lastloginTime.equals(other.lastloginTime)) {
            return false;
        }
        if (mobile == null) {
            if (other.mobile != null) {
                return false;
            }
        } else if (!mobile.equals(other.mobile)) {
            return false;
        }
        if (modifyTime == null) {
            if (other.modifyTime != null) {
                return false;
            }
        } else if (!modifyTime.equals(other.modifyTime)) {
            return false;
        }
        if (nickName == null) {
            if (other.nickName != null) {
                return false;
            }
        } else if (!nickName.equals(other.nickName)) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }
        if (userId == null) {
            if (other.userId != null) {
                return false;
            }
        } else if (!userId.equals(other.userId)) {
            return false;
        }
        if (originalCorpId == null) {
            if (other.originalCorpId != null) {
                return false;
            }
        } else if (!originalCorpId.equals(other.originalCorpId)) {
            return false;
        }
        if (username == null) {
            return other.username == null;
        } else {
            return username.equals(other.username);
        }


    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (accountNonExpired ? 1231 : 1237);
        result = prime * result + (accountNonLocked ? 1231 : 1237);
        result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
        result = prime * result + ((corpId == null) ? 0 : corpId.hashCode());
        result = prime * result + (credentialsNonExpired ? 1231 : 1237);
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + (enabled ? 1231 : 1237);
        result = prime * result + ((fact == null) ? 0 : fact.hashCode());
        result = prime * result + ((genTime == null) ? 0 : genTime.hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + ((identifierToken == null) ? 0 : identifierToken.hashCode());
        result = prime * result + ((identityType == null) ? 0 : identityType.hashCode());
        result = prime * result + ((indetifiter == null) ? 0 : indetifiter.hashCode());
        result = prime * result + ((isForbidden == null) ? 0 : isForbidden.hashCode());
        result = prime * result + ((lastloginTime == null) ? 0 : lastloginTime.hashCode());
        result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
        result = prime * result + ((modifyTime == null) ? 0 : modifyTime.hashCode());
        result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((originalCorpId == null) ? 0 : originalCorpId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");
        sb.append("Username: ").append(this.username).append("; ");
        sb.append("Password: [PROTECTED]; ");
        sb.append("Enabled: ").append(this.enabled).append("; ");
        sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
        sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
        sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");

        if (!authorities.isEmpty()) {
            sb.append("Granted Authorities: ");

            boolean first = true;
            for (GrantedAuthority auth : authorities) {
                if (!first) {
                    sb.append(",");
                }
                first = false;

                sb.append(auth);
            }
        } else {
            sb.append("Not granted any authorities");
        }

        return sb.toString();
    }

    /**
     * Creates a UserBuilder with a specified user name
     *
     * @param username the username to use
     * @return the UserBuilder
     */
    public static UserBuilder withUsername(String username) {
        return builder().username(username);
    }

    /**
     * Creates a UserBuilder
     *
     * @return the UserBuilder
     */
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    /**
     * <p>
     * <b>WARNING:</b> This method is considered unsafe for production and is only
     * intended for sample applications.
     * </p>
     * <p>
     * Creates a user and automatically encodes the provided password using
     * {@code PasswordEncoderFactories.createDelegatingPasswordEncoder()}. For
     * example:
     * </p>
     *
     * <pre>
     * <code>
     * UserDetails user = User.withDefaultPasswordEncoder()
     *     .username("user")
     *     .password("password")
     *     .roles("USER")
     *     .build();
     * // outputs {bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG
     * System.out.println(user.getPassword());
     * </code>
     * </pre>
     * <p>
     * This is not safe for production (it is intended for getting started
     * experience) because the password "password" is compiled into the source code
     * and then is included in memory at the time of creation. This means there are
     * still ways to recover the plain text password making it unsafe. It does
     * provide a slight improvement to using plain text passwords since the
     * UserDetails password is securely hashed. This means if the UserDetails
     * password is accidentally exposed, the password is securely stored.
     * <p>
     * In a production setting, it is recommended to hash the password ahead of
     * time. For example:
     *
     * <pre>
     * <code>
     * PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
     * // outputs {bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG
     * // remember the password that is printed out and use in the next step
     * System.out.println(encoder.encode("password"));
     * </code>
     * </pre>
     *
     * <pre>
     * <code>
     * UserDetails user = User.witUsername("user")
     *     .password("{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG")
     *     .roles("USER")
     *     .build();
     * </code>
     * </pre>
     *
     * @return a UserBuilder that automatically encodes the password with the
     * default PasswordEncoder
     * @deprecated Using this method is not considered safe for production, but is
     * acceptable for demos and getting started. For production
     * purposes, ensure the password is encoded externally. See the
     * method Javadoc for additional details. There are no plans to
     * remove this support. It is deprecated to indicate that this is
     * considered insecure for production purposes.
     */
    @Deprecated
    public static UserBuilder withDefaultPasswordEncoder() {
        logger.warn(
                "User.withDefaultPasswordEncoder() is considered unsafe for production and is only intended for sample applications.");
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return builder().passwordEncoder(encoder::encode);
    }

    public static UserBuilder withUserDetails(UserDetails userDetails) {
        return withUsername(userDetails.getUsername()).password(userDetails.getPassword())
                .accountExpired(!userDetails.isAccountNonExpired()).accountLocked(!userDetails.isAccountNonLocked())
                .authorities(userDetails.getAuthorities()).credentialsExpired(!userDetails.isCredentialsNonExpired())
                .disabled(!userDetails.isEnabled());
    }

    /**
     * Builds the user to be added. At minimum the username, password, and
     * authorities should provided. The remaining attributes have reasonable
     * defaults.
     */
    public static class UserBuilder {
        private String username;
        private String password;
        private List<GrantedAuthority> authorities;
        private boolean accountExpired;
        private boolean accountLocked;
        private boolean credentialsExpired;
        private boolean disabled;
        private Function<String, String> passwordEncoder = password -> password;

        /**
         * Creates a new instance
         */
        private UserBuilder() {
        }

        /**
         * Populates the username. This attribute is required.
         *
         * @param username the username. Cannot be null.
         * @return the {@link UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         */
        public UserBuilder username(String username) {
            Assert.notNull(username, "username cannot be null");
            this.username = username;
            return this;
        }

        /**
         * Populates the password. This attribute is required.
         *
         * @param password the password. Cannot be null.
         * @return the {@link UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         */
        public UserBuilder password(String password) {
            Assert.notNull(password, "password cannot be null");
            this.password = password;
            return this;
        }

        /**
         * Encodes the current password (if non-null) and any future passwords supplied
         * to {@link #password(String)}.
         *
         * @param encoder the encoder to use
         * @return the {@link UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         */
        public UserBuilder passwordEncoder(Function<String, String> encoder) {
            Assert.notNull(encoder, "encoder cannot be null");
            this.passwordEncoder = encoder;
            return this;
        }

        /**
         * Populates the roles. This method is a shortcut for calling
         * {@link #authorities(String...)}, but automatically prefixes each entry with
         * "ROLE_". This means the following:
         *
         * <code>
         * builder.roles("USER","ADMIN");
         * </code>
         * <p>
         * is equivalent to
         *
         * <code>
         * builder.authorities("ROLE_USER","ROLE_ADMIN");
         * </code>
         *
         * <p>
         * This attribute is required, but can also be populated with
         * {@link #authorities(String...)}.
         * </p>
         *
         * @param roles the roles for this user (i.e. USER, ADMIN, etc). Cannot be null,
         *              contain null values or start with "ROLE_"
         * @return the {@link UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         */
        public UserBuilder roles(String... roles) {
            List<GrantedAuthority> authorities = new ArrayList<>(roles.length);
            for (String role : roles) {
                Assert.isTrue(!role.startsWith("ROLE_"), role + " cannot start with ROLE_ (it is automatically added)");
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }
            return authorities(authorities);
        }

        /**
         * Populates the authorities. This attribute is required.
         *
         * @param authorities the authorities for this user. Cannot be null, or contain
         *                    null values
         * @return the {@link UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         * @see #roles(String...)
         */
        public UserBuilder authorities(GrantedAuthority... authorities) {
            return authorities(Arrays.asList(authorities));
        }

        /**
         * Populates the authorities. This attribute is required.
         *
         * @param authorities the authorities for this user. Cannot be null, or contain
         *                    null values
         * @return the {@link UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         * @see #roles(String...)
         */
        public UserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = new ArrayList<>(authorities);
            return this;
        }

        /**
         * Populates the authorities. This attribute is required.
         *
         * @param authorities the authorities for this user (i.e. ROLE_USER, ROLE_ADMIN,
         *                    etc). Cannot be null, or contain null values
         * @return the {@link UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         * @see #roles(String...)
         */
        public UserBuilder authorities(String... authorities) {
            return authorities(AuthorityUtils.createAuthorityList(authorities));
        }

        /**
         * Defines if the account is expired or not. Default is false.
         *
         * @param accountExpired true if the account is expired, false otherwise
         * @return the {@link UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         */
        public UserBuilder accountExpired(boolean accountExpired) {
            this.accountExpired = accountExpired;
            return this;
        }

        /**
         * Defines if the account is locked or not. Default is false.
         *
         * @param accountLocked true if the account is locked, false otherwise
         * @return the {@link UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         */
        public UserBuilder accountLocked(boolean accountLocked) {
            this.accountLocked = accountLocked;
            return this;
        }

        /**
         * Defines if the credentials are expired or not. Default is false.
         *
         * @param credentialsExpired true if the credentials are expired, false
         *                           otherwise
         * @return the {@link UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         */
        public UserBuilder credentialsExpired(boolean credentialsExpired) {
            this.credentialsExpired = credentialsExpired;
            return this;
        }

        /**
         * Defines if the account is disabled or not. Default is false.
         *
         * @param disabled true if the account is disabled, false otherwise
         * @return the {@link UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         */
        public UserBuilder disabled(boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public UserDetails build() {
            String encodedPassword = this.passwordEncoder.apply(password);
            return new SecurityUser(username, encodedPassword, !disabled, !accountExpired, !credentialsExpired,
                    !accountLocked, authorities);
        }
    }

    /**
     * 获取用户昵称
     *
     * @return
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置用户昵称
     *
     * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取用户头像
     *
     * @return
     */
    public String getFact() {
        return fact;
    }

    /**
     * 设置用户头像
     *
     * @param fact
     */
    public void setFact(String fact) {
        this.fact = fact;
    }

    /**
     * 获取用户手机号码
     *
     * @return
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置用户手机号码
     *
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取用户的email
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置用户的email
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取用户的生成(创建)时间
     *
     * @return
     */
    public Date getGenTime() {
        return genTime;
    }

    /**
     * 设置用户的生成(创建)时间
     *
     * @param genTime
     */
    public void setGenTime(Date genTime) {
        this.genTime = genTime;
    }

    /**
     * 获取用户的最后修改(调整)时间
     *
     * @return
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置用户的最后修改(调整)时间
     *
     * @param modifyTime
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取用户的当前激活账套
     *
     * @return
     */
    public String getCorpId() {
        return corpId;
    }

    /**
     * 设置用户的当前激活账套
     *
     * @param corpId
     */
    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    /**
     * 获取用户的最后登录时间
     *
     * @return
     */
    public Date getLastloginTime() {
        return lastloginTime;
    }

    /**
     * 设置用户的最后登录时间
     *
     * @param lastloginTime
     */
    public void setLastloginTime(Date lastloginTime) {
        this.lastloginTime = lastloginTime;
    }

    /**
     * 获取用户的禁用状态
     *
     * @return
     */
    public Boolean getIsForbidden() {
        return isForbidden;
    }

    /**
     * 设置用户的禁用状态
     *
     * @param isForbidden
     */
    public void setIsForbidden(Boolean isForbidden) {
        this.isForbidden = isForbidden;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIndetifiter() {
        return indetifiter;
    }

    public void setIndetifiter(String indetifiter) {
        this.indetifiter = indetifiter;
    }

    public String getIdentifierToken() {
        return identifierToken;
    }

    public void setIdentifierToken(String identifierToken) {
        this.identifierToken = identifierToken;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public static Log getLogger() {
        return logger;
    }

    /**
     * 设置用户密码
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取用户ID
     *
     * @return
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取用户的性别
     *
     * @return
     */
    public Boolean getGender() {
        return gender;
    }

    /**
     * 设置用户的性别
     *
     * @param gender
     */
    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    /**
     * 获取用户的工号
     *
     * @return
     */
    public String getJobNumber() {
        return jobNumber;
    }

    /**
     * 设置用户的工号
     *
     * @param jobNumber
     */
    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }
    

    public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	/**
     * 获取用户的个性签名
     *
     * @return
     */
    public String getSign() {
        return sign;
    }

    /**
     * 设置用户的个性签名
     *
     * @param sign
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * 获取用户所在的部门ID
     *
     * @return
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * 设置用户所在的部门ID
     *
     * @param deptId
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取用户所拥有的业务人员权限
     *
     * @return
     */
    public String getBusinessPersonnelIds() {
        return businessPersonnelIds;
    }

    /**
     * 设置用户所拥有的业务人员权限
     *
     * @param businessPersonnelIds
     */
    public void setBusinessPersonnelIds(String businessPersonnelIds) {
        this.businessPersonnelIds = businessPersonnelIds;
    }

    /**
     * 获取用户档案所在的公司账套
     *
     * @return
     */
    public String getOriginalCorpId() {
        return originalCorpId;
    }

    /**
     * 设置用户档案所在的公司账套
     *
     * @param originalCorpId
     */
    public void setOriginalCorpId(String originalCorpId) {
        this.originalCorpId = originalCorpId;
    }
}
