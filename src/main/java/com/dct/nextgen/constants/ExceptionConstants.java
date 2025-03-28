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
    String ACCOUNT_EXPIRED = "exception.auth.accountExpired";
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

    // Form data request
    String DATA_INVALID = "";
    String DATA_NOT_FOUND = "";
    String DATA_EXISTED = "";
    String DATA_NOT_EXISTED = "";
    String ID_NOT_NULL = "";
    String ID_INVALID = "";
    String NAME_NOT_BLANK = "";
    String NAME_MAX_LENGTH = "";
    String CODE_NOT_BLANK = "";
    String USERNAME_NOT_BLANK = "";
    String USERNAME_INVALID = "";
    String USERNAME_MIN_LENGTH = "";
    String USERNAME_MAX_LENGTH = "";
    String PASSWORD_NOT_BLANK = "";
    String PASSWORD_MIN_LENGTH = "";
    String PASSWORD_MAX_LENGTH = "";
    String PASSWORD_INVALID = "";
    String EMAIL_NOT_BLANK = "";
    String EMAIL_MIN_LENGTH = "";
    String EMAIL_MAX_LENGTH = "";
    String EMAIL_INVALID = "";
    String PHONE_NOT_BLANK = "";
    String PHONE_MIN_LENGTH = "";
    String PHONE_MAX_LENGTH = "";
    String PHONE_INVALID = "";
    String DESCRIPTION_NOT_BLANK = "";
    String DESCRIPTION_MAX_LENGTH = "";
    String ADDRESS_NOT_BLANK = "";
    String ADDRESS_MAX_LENGTH = "";
    String STATUS_NOT_BLANK = "";
    String STATUS_INVALID = "";
}
