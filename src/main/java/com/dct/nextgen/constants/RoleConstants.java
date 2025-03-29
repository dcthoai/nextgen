package com.dct.nextgen.constants;

/**
 * Please do not delete fields that are considered unused <p>
 * As it is used to reference the database permission list config for comparison <p>
 * Used in this application with @{@link com.dct.nextgen.aop.annotation.CheckAuthorize}
 *
 * @author thoaidc
 */
@SuppressWarnings("unused")
public interface RoleConstants {

    interface System {

        String SYSTEM = "01";
        String VIEW = "0101";
        String UPDATE = "0102";
    }

    interface Account {

        String ACCOUNT = "02";
        String VIEW = "0201";
        String CREATE = "0202";
        String UPDATE = "0203";
        String DELETE = "0204";
    }

    interface Role {

        String ROLE = "03";
        String VIEW = "0301";
        String CREATE = "0302";
        String UPDATE = "0303";
        String DELETE = "0304";
    }

    interface Customer {

        String CUSTOMER = "04";
        String VIEW = "0401";
        String UPDATE = "0402";
        String DELETE = "0403";
    }

    interface Home {

        String HOME = "05";
        String VIEW = "0501";
        String UPDATE = "0502";
    }

    interface About {

        String ABOUT = "06";

        interface Story {

            String STORY = "0601";
            String VIEW = "060101";
            String UPDATE = "060102";
        }

        interface Partner {

            String PARTNER = "0602";
            String VIEW = "060201";
            String CREATE = "060202";
            String UPDATE = "060203";
            String DELETE = "060204";
        }

        interface Motto {

            String MOTTO = "0603";
            String VIEW = "060301";
            String CREATE = "060302";
            String UPDATE = "060303";
            String DELETE = "060304";
        }

        interface Staff {

            String STAFF = "0604";
            String VIEW = "060401";
            String CREATE = "060402";
            String UPDATE = "060403";
            String DELETE = "060404";
        }

        interface ContactForm {

            String CONTACT_FORM = "0605";
            String VIEW = "060501";
            String UPDATE = "060502";
        }
    }

    interface Product {

        String PRODUCT = "07";

        interface Category {

            String CATEGORY = "0701";
            String VIEW = "070101";
            String CREATE = "070102";
            String UPDATE = "070103";
            String DELETE = "070104";
        }

        interface Item {

            String ITEM = "0702";
            String VIEW = "070201";
            String CREATE = "070202";
            String UPDATE = "070203";
            String DELETE = "070204";
        }
    }

    interface Works {

        String WORK = "08";

        interface Category {

            String CATEGORY = "0801";
            String VIEW = "080101";
            String CREATE = "080102";
            String UPDATE = "080103";
            String DELETE = "080104";
        }

        interface Project {

            String PROJECT = "0802";
            String VIEW = "080201";
            String CREATE = "080202";
            String UPDATE = "080203";
            String DELETE = "080204";
        }
    }

    interface Jobs {

        String JOB = "09";
        String VIEW = "0901";
        String CREATE = "0902";
        String UPDATE = "0903";
        String DELETE = "0904";
    }

    interface Company {

        String COMPANY = "10";
        String VIEW = "1001";
        String UPDATE = "1002";
    }
}
