package com.dct.nextgen.constants;

/**
 * Messages for exceptions with internationalization (I18n) here<p>
 * The constant content corresponds to the message key in the resources bundle files in directories such as:
 * <ul>
 *   <li><a href="">resources/i18n/messages</a></li>
 * </ul>
 * These paths are defined in {@link BaseConstants#MESSAGE_SOURCE_BASENAME}
 *
 * @author thoaidc
 */
@SuppressWarnings("unused")
public interface ExceptionConstants {

    // I18n exception
    String TRANSLATE_NOT_FOUND = "exception.i18n.notFound";

    // Http exception
    String METHOD_NOT_ALLOW = "exception.http.methodNotAllow";

    // Runtime exception OR undetermined error
    String UNCERTAIN_ERROR = "exception.uncertain";
    String NULL_EXCEPTION = "exception.nullPointer";

    // Authentication exception
    String BAD_CREDENTIALS = "exception.auth.badCredentials";
    String CREDENTIALS_EXPIRED = "exception.auth.credentialsExpired";
    String ACCOUNT_EXPIRED = "exception.auth.accountExpired";
    String ACCOUNT_LOCKED = "exception.auth.accountLocked";
    String ACCOUNT_DISABLED = "exception.auth.accountDisabled";
    String ACCOUNT_NOT_FOUND = "exception.auth.accountNotFound";
    String UNAUTHORIZED = "exception.auth.unauthorized";
    String FORBIDDEN = "exception.auth.forbidden";
    String TOKEN_INVALID_OR_EXPIRED = "exception.auth.token.invalidOrExpired";

    // OAuth2 authorization exception
    String OAUTH2_AUTHORIZATION_CODE_NOT_FOUND = "exception.oauth2.authorizationCode.notFound";
    String OAUTH2_AUTHORIZATION_CODE_CLIENT_ERROR = "exception.oauth2.authorizationCode.clientError";
    String OAUTH2_AUTHORIZATION_CODE_SERVER_ERROR = "exception.oauth2.authorizationCode.serverError";
    String OAUTH2_AUTHORIZATION_CODE_EXCEPTION = "exception.oauth2.authorizationCode.exception";
    String OAUTH2_USER_INFO_NOT_FOUND = "exception.oauth2.userinfo.notFound";
    String OAUTH2_USER_INFO_CLIENT_ERROR = "exception.oauth2.userinfo.clientError";
    String OAUTH2_USER_INFO_SERVER_ERROR = "exception.oauth2.userinfo.serverError";
    String OAUTH2_USER_INFO_EXCEPTION = "exception.oauth2.userinfo.exception";

    // Validate account info exception
    String REGISTER_FAILED = "exception.account.register.failed";
    String ACCOUNT_EXISTED = "exception.account.existed";
    String ACCOUNT_NOT_EXISTED = "exception.account.notExisted";
    String OLD_PASSWORD_INVALID = "exception.account.oldPasswordInvalid";
    String NEW_PASSWORD_DUPLICATED = "exception.account.newPasswordDuplicated";

    // Upload file request
    String MAXIMUM_UPLOAD_SIZE_EXCEEDED = "exception.upload.maximumSizeExceed";

    // Request data error
    String INVALID_REQUEST_DATA = "exception.request.data.invalid";

    // Role
    String ROLE_EXISTED = "exception.role.existed";
    String ROLE_PERMISSIONS_NOT_EMPTY = "exception.role.permissions.notEmpty";
    String ROLE_PERMISSION_INVALID = "exception.role.permission.invalidList";

    // RabbitMQ
    String DIRECT_EXCHANGE_NULL = "exception.rabbitmq.exchange.directExchangeNull";

    // Company info
    String LOGO_NOT_NULL = "exception.company.logo.notNull";
    String IMAGE_NOT_NULL = "exception.company.image.notNull";
    String LICENSE_CODE_MAX_SIZE = "exception.company.licenseCode.maxSize";
    String WEBSITE_MAX_SIZE = "exception.company.website.maxSize";
    String COMPANY_MAP_NOT_NULL = "exception.company.map.notNull";
    String COMPANY_MAP_MAX_SIZE = "exception.company.map.maxSize";
    String COMPANY_MAP_IMAGE_NOT_NULL = "exception.company.mapImage.notNull";
    String COMPANY_MAP_SLIDE_TEXT_MAX_SIZE = "exception.company.mapSlideText.maxSize";

    // Banner
    String TEXT_STROKE_MAX_SIZE = "exception.banner.textStroke.maxSize";
    String TEXT_UPPERCASE_MAX_SIZE = "exception.banner.textUppercase.maxSize";
    String BANNER_POSITION_MAX_SIZE = "exception.banner.position.maxSize";
    String BANNER_POSITION_NOT_NULL = "exception.banner.position.notNull";
    String CANNOT_SAVE_VIDEO = "exception.home.video.canNotSaveFile";
    String CANNOT_SAVE_IMAGE = "exception.home.image.canNotSaveFile";

    // Form data request
    String DATA_INVALID = "exception.data.invalid";
    String DATA_NOT_FOUND = "exception.data.notFound";
    String DATA_EXISTED = "exception.data.existed";
    String DATA_NOT_EXISTED = "exception.data.notExisted";
    String ID_NOT_NULL = "exception.data.id.notNull";
    String ID_INVALID = "exception.data.id.invalid";
    String NAME_NOT_BLANK = "exception.data.name.notBlank";
    String NAME_MAX_LENGTH = "exception.data.name.maxLength";
    String CODE_NOT_BLANK = "exception.data.code.notBlank";
    String FULLNAME_NOT_BLANK = "exception.data.fullname.notBlank";
    String FULLNAME_MAX_LENGTH = "exception.data.fullname.maxLength";
    String USERNAME_NOT_BLANK = "exception.data.username.notBlank";
    String USERNAME_INVALID = "exception.data.username.invalid";
    String USERNAME_MIN_LENGTH = "exception.data.username.minLength";
    String USERNAME_MAX_LENGTH = "exception.data.username.maxLength";
    String PASSWORD_NOT_BLANK = "exception.data.password.notBlank";
    String PASSWORD_MIN_LENGTH = "exception.data.password.minLength";
    String PASSWORD_MAX_LENGTH = "exception.data.password.maxLength";
    String PASSWORD_INVALID = "exception.data.password.invalid";
    String EMAIL_NOT_BLANK = "exception.data.email.notBlank";
    String EMAIL_MIN_LENGTH = "exception.data.email.minLength";
    String EMAIL_MAX_LENGTH = "exception.data.email.maxLength";
    String EMAIL_INVALID = "exception.data.email.invalid";
    String PHONE_NOT_BLANK = "exception.data.phone.notBlank";
    String PHONE_MIN_LENGTH = "exception.data.phone.minLength";
    String PHONE_MAX_LENGTH = "exception.data.phone.maxLength";
    String PHONE_INVALID = "exception.data.phone.invalid";
    String DESCRIPTION_NOT_BLANK = "exception.data.description.notBlank";
    String DESCRIPTION_MAX_LENGTH = "exception.data.description.maxLength";
    String ADDRESS_NOT_BLANK = "exception.data.address.notBlank";
    String ADDRESS_MAX_LENGTH = "exception.data.address.maxLength";
    String STATUS_NOT_BLANK = "exception.data.status.notBlank";
    String STATUS_INVALID = "exception.data.status.invalid";
    String TITLE_NOT_BLANK = "exception.data.title.notBlank";
    String TITLE_MAX_LENGTH = "exception.data.title.maxLength";
    String CONTENT_NOT_BLANK = "exception.data.content.notBlank";
    String CONTENT_MAX_LENGTH = "exception.data.content.maxLength";
    String DEVICE_ID_NOT_BLANK = "exception.data.deviceId.notBlank";

    // Project
    String SUB_NAME_NOT_BLANK = "exception.project.subName.notBlank";
    String SUB_NAME_MAX_LENGTH = "exception.project.subName.maxLength";
    String CATEGORY_NAME_NOT_BLANK = "exception.project.categoryName.notBlank";
    String CATEGORY_NAME_MAX_LENGTH = "exception.project.categoryName.maxLength";
    String MORE_DESCRIPTION_NOT_BLANK = "exception.project.moreDescription.notBlank";
    String MORE_DESCRIPTION_MAX_LENGTH = "exception.project.moreDescription.maxLength";
    String CUSTOMER_NAME_NOT_BLANK = "exception.project.customerName.notBlank";
    String CUSTOMER_NAME_MAX_LENGTH = "exception.project.customerName.maxLength";
    String LINK_NAME_MAX_LENGTH = "exception.project.linkName.maxLength";
    String CATEGORY_IDS_INVALID = "exception.project.category.idInvalid";

    // Product
    String CAROUSEL_NOT_EMPTY = "exception.data.carousel.notEmpty";
    String PRODUCT_INTRO_NOT_EMPTY = "exception.data.intro.notEmpty";

    // Customer demand
    String CUSTOMER_DEMAND_NOT_EXISTED = "exception.customer.demand.notExist";
}
